package p10_Package;

/**
 * Simple generic hash class
 */
public class GenericHashClass <GenericData extends Comparable<GenericData>>
{

    /**
     * Constant for setting linear probing
     */
    public static final int LINEAR_PROBING = 101;
    /**
     * Constant for setting quadratic probing
     */
    public static final int QUADRATIC_PROBING = 102;
    /**
     * Constant for returning item not found with search
     */
    public final int ITEM_NOT_FOUND = -1;
    /**
     * Default number of characters of data string to use in hash calculation
     */
    private final int DEFAULT_NUM_HASH_DIGITS = 6;
    /**
     * Table size default
     */
    private final int DEFAULT_TABLE_SIZE = 10;
    /**
     * Array for hash table
     */
    private Object[] tableArray;
    /**
     * Working number of characters from data string to use in hash calculation
     */
    private int numHashDigits;
    /**
     * Flag for setting linear or quadratic probing
     */
    private int probeFlag;
    /**
     * Size of the array table
     */
    private int tableSize;

    /**
     * Default constructor
     * <p>
     * Initializes to default table size, number of hash digits, with probe flag
     * set to linear probing
     */
    public GenericHashClass()
    {
        tableSize = DEFAULT_TABLE_SIZE;
        numHashDigits = DEFAULT_NUM_HASH_DIGITS;
        probeFlag = LINEAR_PROBING;
        tableArray = new Object[ tableSize ];
    }

    /**
     * Initialization constructor
     *
     * @param inProbeFlag sets linear or quadratic probing
     */
    public GenericHashClass( int inProbeFlag )
    {
        tableSize = DEFAULT_TABLE_SIZE;
        numHashDigits = DEFAULT_NUM_HASH_DIGITS;
        probeFlag = inProbeFlag;
        tableArray = new Object[ tableSize ];
    }

    /**
     * Initialization constructor
     *
     * @param inTableSize  sets table size
     * @param inHashDigits sets number of characters from data string for use in
     *                     hash calculation
     * @param inProbeFlag  sets linear or quadratic probing
     */
    public GenericHashClass( int inTableSize, int inHashDigits,
                             int inProbeFlag )
    {
        tableSize = inTableSize;
        numHashDigits = inHashDigits;
        probeFlag = inProbeFlag;
        tableArray = new Object[ tableSize ];
    }

    /**
     * Copy constructor
     *
     * @param copied GenericHashClass copied object
     */
    public GenericHashClass( GenericHashClass<GenericData> copied )
    {
        int index;

        tableSize = copied.tableSize;
        tableArray = new Object[ tableSize ];
        probeFlag = copied.probeFlag;
        numHashDigits = copied.numHashDigits;

        for(index = 0; index < tableSize; index++)
        {
            tableArray[ index ] = copied.tableArray[ index ];
        }
    }

    /**
     * Adds GenericData item to hash table
     *
     * @param newItem GenericData item
     * @return Boolean success of operation
     */
    @SuppressWarnings( "unchecked" )
    public boolean addItem( GenericData newItem )
    {
        int hash = generateHash( newItem ) %
                   tableSize, workingIndex = hash, probe = 0, QUADRATIC_VALUE = 2;
        GenericData accessedItem;

        System.out.print( "State: " + newItem.toString() + " - hash sum: " +
                          generateHash( newItem ) );

        while(workingIndex < tableSize)
        {
            accessedItem = (GenericData) tableArray[ workingIndex ];
            if(accessedItem == null)
            {
                tableArray[ workingIndex ] = newItem;
                System.out
                        .print( ", index: " + workingIndex + ",probed index: " +
                                hash + "\n" );
                return true;
            }
            else if(accessedItem.compareTo( newItem ) == 0)
            {
                tableArray[ workingIndex ] = newItem;
                return true;
            }

            probe++;

            if(probeFlag == QUADRATIC_PROBING)
            {
                workingIndex = generateHash( newItem ) %
                               tableSize + toPower( probe, QUADRATIC_VALUE );
            }
            else
            {
                workingIndex += 1;
            }

            workingIndex %= tableSize;
        }

        return false;
    }

    /**
     * Removes item from hash table
     *
     * @param toBeRemoved GenericData value used for requesting data uses
     *                    findItemIndex
     * @return GenericData item removed, or null if not found
     */
    @SuppressWarnings( "unchecked" )
    public GenericData removeItem( GenericData toBeRemoved )
    {
        int index = findItemIndex( toBeRemoved );
        GenericData returnItem;

        if(index == ITEM_NOT_FOUND)
        {
            return null;
        }
        else
        {
            returnItem = (GenericData) tableArray[ index ];
            tableArray[ index ] = null;
            return returnItem;
        }

    }

    /**
     * Returns item found
     *
     * @param searchItem GenericData value to be found; uses findItemIndex
     * @return GenericData item found
     */
    @SuppressWarnings( "unchecked" )
    public GenericData findItem( GenericData searchItem )
    {
        int index = findItemIndex( searchItem );

        if(index == ITEM_NOT_FOUND)
        {
            return null;
        }
        else
        {
            return (GenericData) tableArray[ index ];
        }
    }

    /**
     * Searches for item index in hash table
     * <p>
     * Uses linear or quadratic probing as configured
     *
     * @param searchItem GenericData value to be found
     * @return integer index location of search item
     */
    @SuppressWarnings( "unchecked" )
    private int findItemIndex( GenericData searchItem )
    {
        int searchHash = generateHash(
                searchItem ) %
                         tableSize, workingIndex = searchHash, probe = 0, QUADRATIC_VALUE = 2;
        GenericData accessedItem;

        while(workingIndex < tableSize)
        {
            accessedItem = (GenericData) tableArray[ workingIndex ];

            if(searchItem.compareTo( accessedItem ) == 0)
            {
                return workingIndex;
            }

            probe++;

            if(probeFlag == QUADRATIC_PROBING)
            {
                workingIndex = searchHash + toPower( probe, QUADRATIC_VALUE );
            }
            else
            {
                workingIndex += 1;
            }

            workingIndex %= tableSize;
        }

        return ITEM_NOT_FOUND;
    }

    /**
     * Method converts GenericData item to hash value for use in hash table
     *
     * @param item GenericData value to be converted to hash value
     *             <p>
     *             Note: gets data from object via toString, then adds integer
     *             values of each character to sum and converts to array index
     *             <p>
     *             Note: Uses number of characters from string specified by
     *             numHashDigits values
     * @return integer hash value
     */
    public int generateHash( GenericData item )
    {
        int generatedHash = 0, index, hashLength;
        String itemString = item.toString();

        for(index = 0; index < numHashDigits; index++)
        {
            generatedHash += (int) itemString.charAt( index );
        }
        return generatedHash;
    }

    /**
     * traverses through all array bins, finds min and max number of contiguous
     * elements, and number of empty nodes; also shows table loading
     *
     * @return String result of hash table analysis
     */
    @SuppressWarnings( "unchecked" )
    public String showHashTableStatus()
    {
        int totalEmptyCount = 0, totalMaxCount = 0, totalMinCount = tableSize, index,
                minCounter = 0, maxCounter = 0;
        String workingStr = "", outStr;

        for(index = 0; index < tableSize; index++)
        {
            if(tableArray[ index ] == null)
            {
                totalEmptyCount++;
                maxCounter = 0;

                minCounter = 0;
                workingStr += "N";
            }
            if(tableArray[ index ] != null)
            {
                workingStr += 'D';
                maxCounter++;
                minCounter++;
                if(maxCounter >= totalMaxCount)
                {
                    totalMaxCount = maxCounter;
                }
                if(minCounter < totalMinCount)
                {

                    totalMinCount = minCounter;
                }

            }

        }

        outStr = "Hash Table Status: " + workingStr + "\n\n" +
                 "     Minimum contiguous bins:" + totalMinCount + "\n" +
                 "     Maximum contiguous bins:" + totalMaxCount + "\n" +
                 "        Number of empty bins:" + totalEmptyCount +
                 "\n\nArray Dump:\n";

        for(index = 0; index < tableSize; index++)
        {
            if(tableArray[ index ] == null)
            {
                outStr += "\nnull";
            }
            else
            {
                outStr +=
                        "\n" + tableArray[ index ].toString();
            }
        }
        return outStr;
    }

    /**
     * Clears hash table by setting all bins to null
     */
    public void clearHashTable()
    {
        int index;

        for(index = 0; index < tableSize; index++)
        {
            tableArray[ index ] = null;
        }
    }

    /**
     * Local recursive method to calculate exponentiation with integers
     *
     * @param base     base of exponentiation
     * @param exponent exponent of exponentiation
     * @return result of exponentiation calculation
     */
    private int toPower( int base, int exponent )
    {
        if(exponent > 0)
        {
            return base * toPower( base, exponent - 1 );
        }

        return 1;
    }
}
