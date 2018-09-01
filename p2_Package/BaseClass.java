package p2_Package;

/**
 * Class used for managing a single base -defined (non-decimal) value
 * <p>
 * Contains array with digits and the number of valid digits in the array, and a
 * Boolean flag in case any of the operations cause an overflow
 * 
 * 
 * @author Joshua Pollock
 */
public class BaseClass implements Comparable<BaseClass>
{
    /**
     * Holds base of given value
     */
    int base;
    /**
     * Array for holding digits in the given number
     */
    char[] digitArray;
    /**
     * Maximum number of digits that can be held for this value
     */
    int maxDigits;
    /**
     * Current number of digits held for this value
     */
    int numDigits;
    /**
     * Flag set if register is overflowed by any operation
     */
    boolean overFlow;

    /**
     * Base 10 constant used in code
     */
    private static int BASE_10 = 10;
    /**
     * Highest base value that can be used
     */
    private static int MAX_BASE_VALUE = 16;
    /**
     * Lowest base value that can be used
     */
    private static int MIN_BASE_VALUE = 2;

    /**
     * Copy constructor for BaseClass
     * 
     * @param BaseClass
     *            object to be copied to this object
     */
    public BaseClass(BaseClass copied)
    {
        base = copied.base;
        maxDigits = copied.maxDigits;
        digitArray = copied.digitArray;
        numDigits = copied.numDigits;
        overFlow = copied.overFlow;
    }

    /**
     * Default constructor for BaseClass
     * 
     * @param baseSet
     *            Sets base of BaseClass
     * @param maxDigitSet
     *            Sets maximum number of digits of value
     * 
     * @param decValeSet
     *            Accepts a decimal value to be stored as the BaseClass value
     */
    public BaseClass(int baseSet, int maxDigitSet, String decValSet)
    {
        base = baseSet;
        maxDigits = maxDigitSet;
        digitArray = decToBase(decValSet);
    }

    /**
     * Implements the compareTo required of the Comparable class
     *
     * @param value
     *            BaseClass data to be compared with this
     * @return Value specifying result: for this greater than value: +1, for this
     *         equal value: 0, for this less than value: -1
     */
    @Override
    public int compareTo(BaseClass value)
    {
        if (this.numDigits > value.numDigits)
        {
            return 1;
        }
        else if (this.numDigits < value.numDigits)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Converts given positive decimal integer as string input to the specified base
     * as a character array within the object
     * 
     * @param decValue
     *            Decimal value as string to be converted
     * @return Character array holding the converted value, or null if base is
     *         outside range (1 less than range less than 17 ), if this occurs, the
     *         overflow flag is also set
     */
    private char[] decToBase(String decValue)
    {
        int result = 0, index, output = 0, remainder;
        char[] tmpArray = initializeDigits();
        char tmpChar;
        if (base > MAX_BASE_VALUE || base < MIN_BASE_VALUE)
        {
            overFlow = true;
            return null;
        }

        for (index = decValue.length() - 1; index >= 0; index--)
        {
            tmpChar = decValue.charAt(index);
            result += digitToInt(tmpChar);
            output += result * intToPower(base, index);
        }
        index = 0;
        while (output > 0 && numDigits >= 0)
        {
            remainder = output % base;
            tmpArray[index] = (char) remainder;
            output /= base;
            index++;
        }

        return tmpArray;

    }

    /**
     * Translates character digit to integer value
     * 
     * @param digit
     *            Character digit to be processed
     * @return Integer value of character digit
     */
    private int digitToInt(char digit)
    {
        if (digit > 47 && digit < 57)
        {
            return ((int) digit) - '0';
        }
        else
        {
            return ((int) digit) - 54;
        }
    }

    /**
     * Accesses the digit value directly
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

        for (iterator = 0; iterator < numDigits; iterator++)
        {
            output += digitArray[iterator];
        }
        return output;

    }

    /**
     * Accesses the value as decimal
     * <p>
     * Exception: If value is overflowed, returns 0
     * 
     * @return Integer value in decimal form
     */
    public int getValueAsDecimal()
    {
        int output = 0, index;
        if (overFlow)
        {
            return 0;
        }

        for (index = 0; index < digitArray.length - 1; index++)
        {

            output += intToDigit(digitArray[index]);
        }

        return output;
    }

    /**
     * Creates new digit array and zeroes out the array
     * 
     * @return New integer array created, and initialized with all zeroes
     */
    public char[] initializeDigits()
    {
        char[] tmpArray = new char[maxDigits];
        int iterator;
        for (iterator = 0; iterator < maxDigits; iterator++)
        {
            tmpArray[iterator] = '0';
        }
        digitArray = tmpArray;
        return digitArray;
    }

    /**
     * Translates integer value to character digit
     * 
     * @param digitValue
     *            Integer value to be processed
     * @return Character digit
     */
    private char intToDigit(int digitValue)
    {
        if (digitValue < 10)
        {
            return (char) ((char) digitValue + '0');
        }
        else
        {
            return (char) (digitValue + 55);
        }
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
    int intToPower(int base, int exponent)
    {
        int exp = exponent;
        if (exp > 0)
        {
            return base * intToPower(base, exp - 1);
        }
        return 1;
    }

    /**
     * Checks for base value zero
     * 
     * @return True if the number of digits is one or less, and the LSD is zero
     */
    public boolean isZero()
    {
        int counter = 0, index;

        return numDigits < 2;

    }

    /**
     * Overrides toString method and provides output as both the base and a decimal
     * value
     * <p>
     * Note: Output must be in the form (base number) decimal number
     * <p>
     * Example: "(4E56) 20054"
     *
     * @returns String result as specified
     */
    @Override
    public String toString()
    {
        return "(" + getValueAsBase() + ") " + getValueAsDecimal();
    }
}
