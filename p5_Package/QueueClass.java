package p5_Package;

/**
 * Class manages data in queue form, using IteratorClass
 *
 * @author Joshua Pollock
 */
public class QueueClass
{

    /**
     * queue data managed by IteratorClass object
     */
    IteratorClass queueData;

    /**
     * Default Constructor
     */
    public QueueClass()
    {
        queueData = new IteratorClass();
    }

    /**
     * initialization constructor, constructs QueueClass with initial capacity
     *
     * @param setCapacity value for setting data capacity
     */
    public QueueClass( int setCapacity )
    {
        queueData = new IteratorClass( setCapacity );
    }

    /**
     * copies a QueueClass object
     *
     * @param copied QueueClass object to be copied
     */
    public QueueClass( QueueClass copied )
    {
        queueData = new IteratorClass( copied.queueData );
    }

    /**
     * Enqueues data to queue
     *
     * @param value integer data to be enqueued
     */
    public void enqueue( int value )
    {
        queueData.setToEnd();
        queueData.insertAfterIterator( value );
    }

    /**
     * views data at front of queue
     *
     * @return integer value found at front of queue
     */
    public int peekFront()
    {
        queueData.setToBeginning();
        return queueData.retrieveAtCurrent();
    }

    /**
     * dequeue data from queue
     *
     * @return integer value removed from queue
     */
    public int dequeue()
    {
        queueData.setToBeginning();
        return queueData.removeAtCurrent();
    }

    /**
     * clears queue data
     */
    public void clear()
    {
        queueData.clear();
    }

    /**
     * provides array data as a string
     *
     * @return String result of reported array
     *
     * @overrides toString in class java.lang.Object
     */
    public String toString()
    {
       return queueData.toString();
    }
}
