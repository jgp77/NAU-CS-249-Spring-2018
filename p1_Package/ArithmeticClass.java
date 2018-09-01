package p1_Package;

/**
 * Class for managing number systems between base 2 and base 9 Description:
 * Converts decimal numbers to a specified base and conducts addition,
 * subtraction, multiplication, division, and modulo operations on the data
 * 
 * @author Joshua Pollock
 *
 */
public class ArithmeticClass
{
    /**
     * Initialization constructor for ArithmeticClass
     */
    public ArithmeticClass()
    {

    }

    /**
     * Adds two numbers in their base, returns sum
     * <p>
     * Note: Registers are not modified within this method
     * 
     * @param register_1
     *            First of two values to be processed, as specified
     * @param register_2
     *            Second of two values to be processed, as specified
     * @return DigitClass sum of registers; null if the bases are not the same
     */
    public DigitClass addRegisters(DigitClass register_1, DigitClass register_2)
    {
        int result = 0, greatestDigit = 0, carry = 0, iterator = 0;
        DigitClass sumOfAddition;

        if (register_1.base != register_2.base || register_1.overFlow
                || register_2.overFlow)
        {
            return null;
        }

        if (getMax(register_1.numDigits, register_2.numDigits) == register_1.numDigits)
        {
            sumOfAddition = new DigitClass(register_1);
        }

        else
        {
            sumOfAddition = new DigitClass(register_2);
        }

        sumOfAddition.maxDigits++;
        sumOfAddition.digitArray = sumOfAddition.initializeDigits();
        greatestDigit = sumOfAddition.numDigits;

        for (iterator = 0; iterator < greatestDigit; iterator++)

        {
            if (iterator < register_1.numDigits)
            {
                result += register_1.digitArray[iterator];
            }
            if (iterator < register_2.numDigits)
            {
                result += register_2.digitArray[iterator];
            }

            sumOfAddition.digitArray[iterator] = result % sumOfAddition.base;

            carry = result / sumOfAddition.base;
            result = carry;

        }

        if (carry > 0)
        {
            sumOfAddition.digitArray[iterator] = carry;
            iterator++;
        }

        sumOfAddition.numDigits = iterator;
        // Flip the data around before passing back the array?
        return sumOfAddition;

    }

    /**
     * Subtracts register two from register one, returns difference
     * <p>
     * Note: Registers are not modified within this method
     * 
     * @param register_1
     *            First of two values to be processed, as specified
     * @param register_2
     *            Second of two values to be processed, as specified
     * @return DigitClass positive difference between values, null if any failure
     *         occurred, including: 1) the bases are not the same; or 2) register_2
     *         is numerically greater than register_1
     */
    public DigitClass subtractRegisters(DigitClass register_1, DigitClass register_2)
    {
        int iterator, result = 0, numDigits = register_1.numDigits;
        boolean carry = false;
        DigitClass subtractionResult = new DigitClass(register_1);

        if (register_1.base != register_2.base || register_1.overFlow
                || register_2.overFlow)
        {
            return null;
        }

        if (register_1.numDigits < register_2.numDigits)
        {
            return null;
        }

        for (iterator = 0; iterator < numDigits; iterator++)
        {

            if (carry)
            {
                if (0 > subtractionResult.digitArray[iterator])
                {
                    subtractionResult.digitArray[iterator] += subtractionResult.base - 1;

                }
                else
                {
                    subtractionResult.digitArray[iterator]--;
                    carry = false;
                }
            }

            if (register_2.numDigits > iterator)
            {
                result = register_2.digitArray[iterator];
            }

            if (subtractionResult.digitArray[iterator] >= result)
            {
                subtractionResult.digitArray[iterator] -= result;
            }
            else
            {
                carry = true;
                subtractionResult.digitArray[iterator] += register_1.base - result;
            }

            result = 0;
        }

        return subtractionResult;
    }

    /**
     * Multiplies register one by register two, returns product
     * <p>
     * Note: Registers are not modified within this method
     * 
     * @param register_1
     *            First of two values to be processed, as specified
     * @param register_2
     *            Second of two values to be processed, as specified
     * @return DigitClass product between values, null if the bases are not the same
     */
    public DigitClass multiplyRegisters(DigitClass register_1, DigitClass register_2)
    {
        // Extra Credit
        return null;
    }

    /**
     * Divides register one by register two, returns quotient
     * <p>
     * Note: Registers are not modified within this method
     * 
     * @param register_1
     *            First of two values to be processed, as specified
     * @param register_2
     *            Second of two values to be processed, as specified
     * @return DigitClass quotient between values, null if the bases are not the
     *         same
     */
    public DigitClass divideRegistersToQuotient(DigitClass register_1,
            DigitClass register_2)
    {
        // Extra Credit
        return null;
    }

    /**
     * Divides register one by register two, returns remainder
     * <p>
     * Note: Registers are not modified within this method
     * 
     * @param register_1
     *            First of two values to be processed, as specified
     * @param register_2
     *            Second of two values to be processed, as specified
     * @return DigitClass remainder between values, null if the bases are not the
     *         same
     */
    public DigitClass divideRegistersToRemainder(DigitClass register_1,
            DigitClass register_2)
    {
        // Extra Credit
        if (register_1.base != register_2.base)
        {
            return null;
        }
        else
        {
            return null;
        }
    }

    /**
     * Utility method that finds the maximum of two integer values
     * 
     * @param valOne
     *            One of the two values to be compared
     * @param valTwo
     *            Other of the two values to be compared
     * @return Larger of the two values as specified
     */
    private int getMax(int valOne, int valTwo)
    {
        if (valOne > valTwo)
        {
            return valOne;
        }
        return valTwo;

    }
}
