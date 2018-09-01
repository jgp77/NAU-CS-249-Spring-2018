package p11_Package;

public class GraphClassMain
{



    public static void main(String args[])
    {
        GraphClass gc = new GraphClass();

        gc.setVertex( 'B', 'D', 2 );
        gc.setVertex( 'D', 'E', 5 );
        gc.setVertex( 'D', 'C', 3 );
        gc.setVertex( 'B', 'F', 4 );
        gc.setVertex( 'E', 'A', 3 );
        gc.setVertex( 'A', 'M', 4 );
        gc.setVertex( 'A', 'R', 6 );
        gc.setVertex( 'A', 'T', 5 );

        System.out.println(gc.DFS( 'B',true ));
    }
}
