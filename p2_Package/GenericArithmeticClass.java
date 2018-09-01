package p2_Package;

/**
 * Description: Class wrapper for a Java array of generic data (classes), with
 * additional management operations
 * <p>
 * Note: Only works with class that extends Comparable, as shown in class
 * declaration
 * <p>
 * Note: Maintains a capacity value for maximum number of items that can be
 * stored
 * 
 * @author Joshua Pollock
 *
 * 
 */
public class GenericArithmeticClass<GenericData extends Comparable<GenericData>>
{
    /**
     * Holds default capacity of array if it is not specified
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Holds local array of generic items
     */
    private Object[] localArray;
    /**
     * Holds the capacity of the array
     */
    private int arrayCapacity;

    /**
     * Default constructor, initializes array to default capacity
     */
    public GenericArithmeticClass()
    {
        arrayCapacity = DEFAULT_CAPACITY;
        localArray = new Object[arrayCapacity];
    }

    /**
     * Copy constructor, initializes array to size and capacity of copied array,
     * then copies only the elements up to the given size
     * 
     * @param copied
     *            ArrayClass object to be copied
     */
    public GenericArithmeticClass(GenericArithmeticClass<GenericData> copied)
    {
        arrayCapacity = copied.arrayCapacity;
        localArray = copied.localArray;
    }

    /**
     * Initializing constructor, initializes array to specified capacity
     * 
     * @param capacity
     *            Integer maximum capacity specification for the array
     */
    public GenericArithmeticClass(int capacity)
    {
        arrayCapacity = capacity;
        localArray = new Object[arrayCapacity];
    }

    /**
     * Sets element into array at index
     * 
     * @param index
     *            Location in index to place data
     * @param newValue
     *            Value to be appended to array
     * @return Boolean success if element set at index, false if index outside
     *         boundaries
     */
    public boolean setValueAt(int index, GenericData newValue)
    {
        if (index > arrayCapacity - 1)
        {
            return false;
        }

        localArray[index] = newValue;
        return true;

    }

    /**
     * Description: Acquires data at specified element
     * 
     * @param index
     *            Index of element into which value is to be placed
     * @return GenericData item retrieved from array; null if index outside
     *         boundaries
     */
    @SuppressWarnings("unchecked")
    public GenericData getValueAt(int index)
    {
        if (index > arrayCapacity - 1)
        {
            return null;
        }
        return (GenericData) localArray[index];

    }

    /**
     * Description: Resets array capacity, copies current size and current size
     * number of elements
     * <p>
     * Exception: Method will not resize capacity below current array size, returns
     * false if this is attempted, true otherwise
     * 
     * @param newCapacity
     *            New capacity to be set; must be larger than current capacity
     * @return Boolean condition of resize success or failure
     */
    public boolean resize(int newCapacity)
    {
        int iterator = 0;
        Object[] tmpArr = new Object[newCapacity];
        if (newCapacity > arrayCapacity)
        {
            for (iterator = 0; iterator < arrayCapacity; iterator++)
            {
                tmpArr[iterator] = localArray[iterator];
            }
            localArray = tmpArr;
            arrayCapacity = newCapacity;
            return true;
        }

        return false;

    }

    /**
     * Description: Sorts elements using the bubble sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given data set;
     * the compareTo method decides the key and the order resulting
     * 
     * @param size
     *            Indicates how many items to sort; method will sort all items
     *            between and including index 0 and index size - 1
     */
    @SuppressWarnings("unchecked")
    public void runBubbleSort(int size)
    {
        int outerIndex, innerIndex, comparedDataResult;
        GenericData innerIndexObject, outerIndexObject;

        for (innerIndex = 0; innerIndex < size; innerIndex++)
        {
            outerIndex = innerIndex++;
            innerIndexObject = (GenericData) localArray[innerIndex];
            outerIndexObject = (GenericData) localArray[outerIndex];
            comparedDataResult = outerIndexObject.compareTo(innerIndexObject);

            if (comparedDataResult < 0)
            {
                swapElements(outerIndex, innerIndex);
            }

        }
    }

    /**
     * Description: Sorts elements using the selection sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given data set;
     * the compareTo method decides the key and the order resulting
     * 
     * @param size
     *            Indicates how many items to sort; method will sort all items
     *            between and including index 0 and index size - 1
     */
    @SuppressWarnings("unchecked")
    public void runSelectionSort(int size)
    {
        int outerIndex, innerIndex, lowestIndex, comparedDataResult;
        GenericData innerIndexObject, lowestIndexObject;

        for (outerIndex = 0; outerIndex < size - 1; outerIndex++)
        {
            lowestIndex = outerIndex;
            for (innerIndex = lowestIndex + 1; innerIndex < size; innerIndex++)
            {
                lowestIndexObject = (GenericData) localArray[lowestIndex];
                innerIndexObject = (GenericData) localArray[innerIndex];
                comparedDataResult = innerIndexObject.compareTo(lowestIndexObject);
                if (comparedDataResult < 0)
                {
                    lowestIndex = innerIndex;
                }
            }
            swapElements(outerIndex, lowestIndex);
        }
    }

    /**
     * Description: Sorts elements using the insertion sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given data set;
     * the compareTo method decides the key and the order resulting
     * 
     * @param size
     *            Indicates how many items to sort; method will sort all items
     *            between and including index 0 and index size - 1
     */
    @SuppressWarnings("unchecked")
    public void runInsertionSort(int size)
    {
        int outerIndex, innerIndex;
        GenericData outerIndexObject, innerIndexObject;

        for (outerIndex = 1; outerIndex < size - 1; outerIndex++)
        {
            outerIndexObject = (GenericData) localArray[outerIndex];
            innerIndex = outerIndex;
            innerIndexObject = (GenericData) localArray[innerIndex - 1];

            while (innerIndex > 0 && outerIndexObject.compareTo(innerIndexObject) < 0)
            {
                localArray[innerIndex] = localArray[innerIndex - 1];
                innerIndex--;

            }
            localArray[innerIndex] = outerIndexObject;

        }
    }

    /**
     * Description: Gets current capacity of array
     * <p>
     * Note: Capacity of array indicates number of values the array can hold
     * 
     * 
     * @return Capacity of array
     */
    public int getCurrentCapacity()
    {
        return arrayCapacity;
    }

    /**
     * Swaps one element in the local array at a given index with another element in
     * the array at the other given element
     * 
     * @param oneIndex
     *            Index of one of two elements to be swapped
     * @param otherIndex
     *            Index of second of two elements to be swapped
     */
    private void swapElements(int oneIndex, int otherIndex)
    {
        Object tmp = localArray[oneIndex];

        localArray[oneIndex] = localArray[otherIndex];

        localArray[otherIndex] = tmp;
    }

}
