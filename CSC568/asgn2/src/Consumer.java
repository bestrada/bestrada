/*
This set of files illustrate the Producer-Consumer pattern,
with the producer and the consumers running as threads.
The source code comes from the Java online tutorial:
http://java.sun.com/docs/books/tutorial/essential/threads/synchronization.html
*/

public class Consumer extends Thread {
    private CubbyHole cubbyhole;
    private int number;

    public Consumer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }

    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = cubbyhole.get();
            System.out.println("Consumer #" + 
              this.number + " got: " + value);                        
        }
    }
}



