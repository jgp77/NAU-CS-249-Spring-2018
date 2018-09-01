package p6_Package;

public class QueueClassLL
{

    /**
     * queue data managed by IteratorClassLL object
     */
    IteratorClassLL queueData;

    /**
     * Default Constructor
     */
    public QueueClassLL()
    {
        queueData = new IteratorClassLL();
    }

    /**
     * copies a QueueClassLL object
     *
     * @param copied QueueClassLL object to be copied
     */
    public QueueClassLL( QueueClassLL copied )
    {
        queueData = new IteratorClassLL( copied.queueData );
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
