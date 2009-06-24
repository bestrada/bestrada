/*
This set of files illustrate the Producer-Consumer pattern,
with the producer and the consumers running as threads.
The source code comes from the Java online tutorial:
http://java.sun.com/docs/books/tutorial/essential/threads/synchronization.html

This is the Producer class.
*/

public class Producer extends Thread {
    private CubbyHole cubbyhole;
    private int number;

    public Producer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }

    public void run() {
        for (int i = (number-1)*10; i < (number-1)*10 +10; i++) {
            cubbyhole.put(i);
            System.out.println("Producer #" + this.number
                               + " put: " + i);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}




