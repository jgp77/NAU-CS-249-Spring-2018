package p1_Package;

public class ArithmeticClassMain
{
    public ArithmeticClassMain()
    {

    }

    /**
     * Main method for conducting individual tests
     *
     * @param args
     *            String command-line arguments, not used in this method
     */
    public static void main(String[] args)
    {
        DigitClass test = new DigitClass(2, 10, 2);
        for (int i = 0; i < test.numDigits; i++)
        {
            System.out.print(test.digitArray[i]);
        }
        System.out.println("\n");
        DigitClass test2 = new DigitClass(2, 50, 2);
        for (int i = 0; i < test2.numDigits; i++)
        {
            System.out.print(test2.digitArray[i]);
        }
        ArithmeticClass testy = new ArithmeticClass();
        System.out.println("\n");

        DigitClass output = testy.addRegisters(test, test2);
        for (int i = 0; i < output.numDigits; i++)
        {
            System.out.print(output.digitArray[i]);
        }
        System.out.println(output.getValueAsDecimal());

    }

}
