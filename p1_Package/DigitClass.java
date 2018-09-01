package p1_Package;

/**
 * Class used for managing a single based-defined (non-decimal) value Contains
 * array with digits and the number of valid digits in the array, and a Boolean
 * flag in case any of the operations cause an overflow
 *
 * @author Joshua Pollock
 */
public class DigitClass
{
    /**
     * Holds base of given value
     */
    protected int base;
    /**
     * Array for holding digits in the given number
     */
    protected int[] digitArray;
    /**
     * Maximum number of digits that can be held for this value
     */
    protected int maxDigits;
    /**
     * Current number of digits held for this value
     */
    protected int numDigits;
    /**
     * Flag set if register is overflowed by any operation
     */
    protected boolean overFlow;

    /**
     * Copy constructor for DigitClass
     *
     * @param copied
     *            object to be copied to this object
     */
    public DigitClass(DigitClass copied)
    {
        base = copied.base;
        maxDigits = copied.maxDigits;
        digitArray = copied.digitArray;
        numDigits = copied.numDigits;
        overFlow = copied.overFlow;
    }

    /**
     * Default constructor for DigitClass
     *
     * @param baseSet
     *            Sets base of DigitClass
     * @param maxDigitSet
     *            Sets maximum number of digits of value
     * @param decValSet
     *            Accepts a decimal value to be stored as the DigitClass value
     */
    public DigitClass(int baseSet, int maxDigitSet, int decValSet)
    {
        base = baseSet;
        maxDigits = maxDigitSet;
        digitArray = decToBase(decValSet);
    }

    /**
     * Converts given positive integer input to the specified base within the object
     *
     * @param decValue
     *            Decimal value to be converted
     * @return Integer array holding the converted value, or null if base is outside
     *         range (1 less than range less than 10 ), if this occurs, the overflow
     *         flag is also set
     */
    private int[] decToBase(int decValue)
    {
        int index = 0;
        int[] arr = initializeDigits();
        if (base > 10 || base < 2)
        {
            overFlow = true;
            return null;
        }
        else
        {
            while (decValue > 0)
            {
                if (index >= maxDigits)
                {
                    overFlow = true;
                    numDigits = 0;
                    return null;
                }
                arr[index] = decValue % base;
                decValue /= base;
                index++;
            }
            numDigits = index;
            return arr;
        }

    }

    /**
     * Accesses the value as decimal
     * <p>
     * Exception: If value is overflowed, returns 0
     *
     * @return Integer value in decimal form
     */
    int getValueAsDecimal()
    {
        int output = 0, iterator;
        if (overFlow)
        {
            return 0;
        }
        else
        {
            for (iterator = 0; iterator < numDigits; iterator++)
            {
                output += digitArray[iterator] * intToPower(base, iterator);
            }
            return output;
        }
    }

    /**
     * Accesses the value directly
     * <p>
     * Exception: If value is overflowed, returns dummy string
     *
     * @return String value in base form
     */
    public String getValueAsBase()
    {
        int iterator;
        String output = "";

        if (overFlow)
        {
            return "";
        }
        else
        {
            for (iterator = 0; iterator < numDigits; iterator++)
            {
                output += digitArray[iterator];
            }
            return output;
        }

    }

    /**
     * Creates new digit array and zeroes out the array
     *
     * @return New integer array created, and initialized with all zeroes
     */
    public int[] initializeDigits()
    {
        int[] tmpArray = new int[maxDigits];
        int iterator;
        for (iterator = 0; iterator < maxDigits; iterator++)
        {
            tmpArray[iterator] = 0;
        }
        digitArray = tmpArray;
        return digitArray;
    }

    /**
     * Checks for base value zero
     *
     * @return True if the number of digits is one or less, and the LSD is zero
     */
    public boolean isZero()
    {
        return (numDigits <= 1 && digitArray[0] == 0);
    }

    /**
     * Calculates integer base value to exponent power
     * <p>
     * Note: Required function since Java toPower function requires floating point
     * values, and conducts floating point math
     *
     * @param base
     *            Value to multiplied by itself
     * @param exponent
     *            Number of times base value is to be multiplied by itself
     * @return Integer result of calculations
     */
    protected int intToPower(int base, int exponent)
    {
        int exp = exponent;
        if (exp > 0)
        {
            return base * intToPower(base, exp - 1);
        }
        return 1;
    }

}
