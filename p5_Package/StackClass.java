package p5_Package;

public class StackClass
{

    /**
     * stack data managed by IteratorClass object
     */
    IteratorClass stackData;

    /**
     * default constructor
     */
    public StackClass()
    {
        stackData = new IteratorClass();
    }

    /**
     * initialization constructor, constructs StackClass with initial capacity
     *
     * @param setCapacity integer value to set capacity of stack array
     */
    public StackClass( int setCapacity )
    {
        stackData = new IteratorClass( setCapacity );
    }

    /**
     * copies a StackClass object
     *
     * @param copied StackClass object
     */
    public StackClass( StackClass copied )
    {
        stackData = new IteratorClass( copied.stackData );
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
