package p5_Package;


/**
 * Class provides data storage with iterator management
 *
 * @author Joshua Pollock
 */
public class IteratorClass
{

    private static final int DEFAULT_CAPACITY = 10;
    public static final int FAILED_ACCESS = -999999;
    private int iterCapacity;
    private int iterSize;
    private int iterIndex;
    private int[] iterStorage;

    /**
     * Default constructor
     */
    public IteratorClass()

    {
        iterCapacity = DEFAULT_CAPACITY;
        iterSize = 0;
        iterIndex = 0;
        iterStorage = new int[ iterCapacity ];
    }

    /**
     * Initialization constructor
     * <p>
     * Initializes array capacity, iterator index, prepares class for use
     *
     * @param capacitySetting initial capacity of storage class
     */
    public IteratorClass( int capacitySetting )
    {
        iterCapacity = capacitySetting;
        iterSize = 0;
        iterIndex = 0;
        iterStorage = new int[ iterCapacity ];
    }

    /**
     * Copy constructor
     * <p>
     * Copies IteratorClass object int this object
     *
     * @param copied IteratorClass object to be copied
     */
    public IteratorClass( IteratorClass copied )
    {
        int index;
        iterIndex = copied.iterIndex;
        iterSize = copied.iterSize;
        iterCapacity = copied.iterCapacity;
        iterStorage = new int[ iterCapacity ];

        for(index = 0; index < iterSize; index++)
        {
            iterStorage[ index ] = copied.iterStorage[ index ];
        }

    }

    /**
     * Inserts item prior to iterator index in list
     * <p>
     * Iterator points at inserted item after completion
     * <p>
     * Resizes array if needed
     *
     * @param newValue Value to be inserted in list
     */
    public void insertPriorToIterator( int newValue )
    {
        int index;
        checkForResize();

        if(iterSize>0)
        {
            for(index = iterSize; index > iterIndex - 1; index--)
            {
                iterStorage[ index + 1 ] = iterStorage[ index ];
            }
        }

        iterStorage[ iterIndex ] = newValue;
        iterSize++;
    }

    /**
     * Inserts item after iterator index in list
     * <p>
     * Iterator points at inserted item after completion
     * <p>
     * Resizes array if needed
     *
     * @param newValue Value to be inserted in list
     */
    public void insertAfterIterator( int newValue )
    {
        int index;

        checkForResize();

        if(iterSize>0)
        {
            for(index = iterSize-1; index > iterIndex + 1; index--)
            {
                iterStorage[ index + 1 ] = iterStorage[ index ];
            }
        }

        iterStorage[ iterIndex ] = newValue;
        if(iterSize>0)
        {
            iterIndex++;
        }

        iterSize++;

    }

    /**
     * Move iterator cursor to next item if not currently at end
     */
    public void moveNext()
    {
        if(iterIndex < iterSize)
        {
            iterIndex++;
        }
    }

    /**
     * Move iterator cursor to previous item if not currently at beginning
     */
    public void movePrevious()
    {
        if(iterIndex > 0)
        {
            iterIndex--;
        }
    }

    /**
     * Removes and returns value from list at current iterator position
     * <p>
     * Note: If index is greater than zero, sets iterator index to previous item
     * after removal action
     *
     * @return Value returned if successful, FAILED_ACCESS if not
     */
    public int removeAtCurrent()
    {
        int returnedItem, index;

        if(!isEmpty())
        {
            returnedItem = iterStorage[ iterIndex ];
            for(index = iterIndex; index < iterSize; index++)
            {
                iterStorage[ index ] = iterStorage[ index + 1 ];
            }
            if(iterIndex > 0)
            {
                iterIndex--;
            }
            return returnedItem;
        }
        return FAILED_ACCESS;

    }

    /**
     * Gets value at current location of iterator
     *
     * @return Value returned if successful, FAILED_ACCESS if not
     */
    public int retrieveAtCurrent()
    {
        if(!isEmpty())
        {
            return iterStorage[ iterIndex ];
        }

        return FAILED_ACCESS;
    }

    /**
     * Sets iterator to beginning of list
     */
    public void setToBeginning()
    {
        iterIndex = 0;
    }

    /**
     * Sets iterator to end of list
     */
    public void setToEnd()
    {
        iterIndex = iterSize--;
    }

    /**
     * checks for iterator at end of list
     *
     * @return Boolean result of test
     */
    public boolean isAtEnd()
    {
        return iterIndex == iterSize - 1;
    }

    /**
     * checks for iterator at beginning of list
     *
     * @return Boolean result of test
     */
    public boolean isAtBeginning()
    {
        return iterIndex == 0;
    }

    /**
     * checks for empty list
     *
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return iterSize == 0;
    }

    /**
     * Checks for resize and resizes to twice the current capacity if needed
     */
    private void checkForResize()
    {
        int[] tempArray = new int[ iterCapacity * 2 ];
        int index;
        if(iterCapacity == iterSize)
        {
            iterCapacity *= 2;
            for(index = 0; index < iterSize; index++)
            {
                tempArray[ index ] = iterStorage[ index ];
            }
            iterStorage = tempArray;
        }
    }

    /**
     * Clears iterator array by setting size and iterator index to zero
     */
    public void clear()
    {
        iterSize = 0;
        iterIndex = 0;
    }

    /**
     * provides array data as a string, including indication of current
     * element,
     * <p>
     * Note: no spaces at beginning or end of string
     *
     * @return String result of reported array
     *
     * @overrides toString in class java.lang.Object
     */
    public String toString()
    {
        int index = 0;
        String stringOutput = "";
        while(index < iterSize)
        {
            if(index == iterIndex)
            {
                stringOutput += "|" + iterStorage[ index ] + "|";
            }
            else
            {
                stringOutput += iterStorage[ index ];
            }
            if(index != iterSize - 1)
            {
                stringOutput += ' ';
            }
            index++;
        }
        return stringOutput;
    }
}

