package p5_Package;

public class TestClass
{
    public static void main(String[] args)
    {
        System.out.println("\nIterator:");
        IteratorClass util = new IteratorClass(4);
        util.insertAfterIterator(7);
        System.out.println(util.toString());
        util.insertPriorToIterator( 1);
        System.out.println(util.toString());
        util.insertAfterIterator(2);
        util.insertAfterIterator(3);
        util.insertAfterIterator(4);
        util.insertAfterIterator(5);
        util.insertAfterIterator(6);
        System.out.println(util.toString());
        util.setToEnd();
        System.out.println(util.toString());



    }

}
