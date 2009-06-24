import java.util.LinkedList;
import java.util.Queue;

public class CubbyHole 
{
   /* I use a queue because I can just call queue.add() and queue.remove() to 
    * put and retrieve items in a first-in-first-out manner. */
   private Queue<Integer> _queue;
   private int _curlen;
   private int _size;
   
   public int size() { return this._size; }
   public boolean isEmpty() { return this._curlen <= 0; }
   
   public CubbyHole()
   {
      this(3);
   }
   
   public CubbyHole(int size)
   {
      this._queue = new LinkedList<Integer>();
      this._curlen = 0;
      this._size = size;
   }
   
   public synchronized void put(int value)
   {
      while (this._curlen >= this._size)
      {
	 try
	 {
	    this.wait();
	 }
	 catch (InterruptedException e) { }
      }
      this._queue.add(new Integer(value));
      this._curlen++;
      this.notifyAll();
   }
   
   public synchronized int get()
   {
      while (this.isEmpty())
      {
	 try
	 {
	    this.wait();
	 }
	 catch (InterruptedException e) { }
      }
      Integer result = this._queue.remove();
      this._curlen--;
      this.notifyAll();
      return result.intValue();
   }
}
