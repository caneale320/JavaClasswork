import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Homework 5 PrintQueue Implement the class below as specified in the homework 5 document.
 * 
 * @author can4ku
 */

// Don't forget to include the appropriate import statements

public class PrintQueue {

    private LinkedList<String> toPrint;     // the printer's list of documents
    private Lock documentChangeLock;  // a ReentrantLock lock
    private Condition hasPrintTask;   // a condition object
    private boolean isOn;             // boolean variable describing if the
                                      // queue is on (accepting jobs)

    // TODO: Handle locking and conditions around the
    // enqueueing and dequeuing of documents
    // in this PrintQueue's document list (toPrint)
    // Note: See example in the zip folder 'Thread Example 6 - Bank Deadlock'

    /**
     * Constructor
     */
    public PrintQueue() {
        toPrint = new LinkedList<String>(); // create the list of documents
        isOn = true; // turn on the print queue
        documentChangeLock = new ReentrantLock();
        hasPrintTask = documentChangeLock.newCondition();
    }

    /**
     * enqueue TODO: Write more comments
     * 
     * @throws InterruptedException
     */
    public void enqueue(String s) {
        // Lock while in use
        documentChangeLock.lock();

        try {
            // make sure printer is on
            while (!isOn) {
                hasPrintTask.await();
            }
            // add string if it is
            toPrint.add(s); // add to the list of documents
        } catch (InterruptedException e) {

        } finally {
            // unlock when done
            documentChangeLock.unlock();
        }

    }

    /**
     * dequeue TODO: Write more comments
     * 
     * @throws InterruptedException
     */
    public String dequeue() throws InterruptedException {
        // lock while in use
        documentChangeLock.lock();

        // check printer is on and there are availabe documents
        while (!isOn || (toPrint.size() == 0)) {
            hasPrintTask.await();
        }

        try {
            // remove document
            return toPrint.remove(); // return the first document
        } finally {
            // unlock when done
            documentChangeLock.lock();
        }
    }

    public void turnOff() {
        // change turn off variable 
        this.isOn = false;
        // lock to signal
        documentChangeLock.lock();
        // signal all threads to eventually stop 
        hasPrintTask.signalAll();
        //unlock
        documentChangeLock.unlock();
    }

    public boolean isOn() {
        return this.isOn;
    }

}
