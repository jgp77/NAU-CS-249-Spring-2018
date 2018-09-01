package p6_Package;

public class mainTest
{

    public static void main( String[] args )
    {
        IteratorClassLL iter = new IteratorClassLL();
        System.out.println(iter.isEmpty());

        iter.insertAfterIterator( 1 );
        iter.insertAfterIterator( 2 );
        iter.insertAfterIterator( 3 );
        iter.insertAfterIterator( 4 );
        iter.insertAfterIterator( 5 );
        iter.insertAfterIterator( 6 );
        iter.insertAfterIterator( 7 );
        iter.insertAfterIterator( 8 );
        iter.insertAfterIterator( 9 );
        iter.insertAfterIterator( 10 );
        iter.insertAfterIterator( 11 );
        iter.insertAfterIterator( 12 );
        iter.insertAfterIterator( 13 );
        iter.insertAfterIterator( 15 );
        iter.insertAfterIterator( 16 );
        iter.insertAfterIterator( 17 );
        System.out.println( iter.toString() );
        System.out.println(iter.isAtBeginning());
        System.out.println(iter.isAtEnd());
        System.out.println(iter.isEmpty());
        iter.setToBeginning();
        System.out.println(iter.isAtBeginning());
        System.out.println(iter.isAtEnd());


    }

}
