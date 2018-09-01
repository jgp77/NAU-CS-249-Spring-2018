package p6_Package;

public class StackClassLL
{

    /**
     * stack data managed by IteratorClass object
     */
    IteratorClassLL stackData;

    /**
     * default constructor
     */
    public StackClassLL()
    {
        stackData = new IteratorClassLL();
    }

    /**
     * copies a StackClass object
     *
     * @param copied StackClass object
     */
    public StackClassLL( StackClassLL copied )
    {
        stackData = new IteratorClassLL( copied.stackData );
    }

    /**
     * places a value on the stack
     *
     * @param value integer value to be placed on stack
     */
    public void push( int value )
    {
        stackData.insertAfterIterator( value );
    }

    /**
     * views the value on the top of the stack
     *
     * @return integer value found on top of the stack
     */
    public int peekTop()
    {
        return stackData.retrieveAtCurrent();
    }

    /**
     * removes a value from the top of the stack
     *
     * @return integer value removed from the top of the stack
     */
    public int pop()
    {
        return stackData.removeAtCurrent();
    }

    /**
     * clears stack data
     */
    public void clear()
    {
        stackData.clear();
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
        return stackData.toString();
    }

}
