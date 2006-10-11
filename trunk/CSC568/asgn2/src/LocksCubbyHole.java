import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 public class LocksCubbyHole 
 {
    private Lock _lock;
    private Condition _notFull;
    private Condition _notEmpty;
    
    private Queue<Integer> _queue;
    private int _curlen;
    private int _size;
    
    public LocksCubbyHole()
    {
       this(3);
    }
    
    public LocksCubbyHole(int size)
    {
       this._lock = new ReentrantLock();
       this._notFull = _lock.newCondition();
       this._notEmpty = _lock.newCondition();
       
       this._queue = new LinkedList<Integer>();
       this._curlen = 0;
       this._size = size;
    }
    
    public int size() { return this._size; }
    public boolean isEmpty() { return this._curlen <= 0; }
    
    public void put(int value)
    {
       _lock.lock();
       try
       {
	  while (this._curlen >= this._size)
	  {
	     _notFull.await();
	  }
	  _queue.add(new Integer(value));
	  _notEmpty.signal();
       }
       catch (InterruptedException e) { }
       finally
       {
	  _lock.unlock();
       }
    }

    public int get()
    {
       _lock.lock();
       try
       {
	  while (this.isEmpty())
	  {
	     _notEmpty.await();
	  }
	  Integer result = this._queue.remove();
	  this._curlen--;
	  _notFull.signal();
	  return result.intValue();
       }
       catch (InterruptedException e) { }
       finally
       {
	  _lock.unlock();
       }
       
       /* make the compiler happy */
       return -1;
    }
 }
 