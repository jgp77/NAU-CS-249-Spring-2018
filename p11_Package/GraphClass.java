package p11_Package;

/**
 * Simple class for managing vertices and edges in a graph
 *
 * @author MichaelL
 */
public class GraphClass
{

    /**
     * default vertex capacity
     * <p>
     * Note: Limited to number of upper case letters in alphabet
     */
    private final int VERTEX_CAPACITY = 26;

    /**
     * indication of vertex not in list
     */
    private final int NOT_IN_LIST = -1;

    /**
     * constant space character
     */
    private final char SPACE = ' ';

    /**
     * constant dash character
     */
    private final char DASH = '-';

    /**
     * size of vertex array
     */
    private int vertexListSize;

    /**
     * array of vertices
     */
    VertexNode[] vertexList;

    /**
     * Default constructor
     */
    public GraphClass()
    {
        vertexList = new VertexNode[ VERTEX_CAPACITY ];

        vertexListSize = 0;
    }

    /**
     * Sets vertex with adjacency
     * <p>
     * Note: Adds new vertex as needed; otherwise adds adjacent vertex and
     * weight to existing vertex
     * <p>
     * Note: Adds vertices in both directions (e.g., A with B as adjacency, and
     * B with A as adjacency)
     * <p>
     * Uses insertVertex to minimize excessive coding
     *
     * @param vertex    character vertex letter
     * @param adjVertex character adjacent vertex letter
     * @param weight    integer weight between vertices
     * @return boolean result of action, false if vertex array is full, true
     * otherwise
     */
    public boolean setVertex( char vertex, char adjVertex, int weight )
    {
        int vertexIndex = vertexInList( vertex );
        VertexNode workingNode;

        if(VERTEX_CAPACITY != vertexListSize)
        {
            if(vertexIndex == NOT_IN_LIST)
            {
                insertVertex( vertex, adjVertex, weight );
                return true;
            }
            else
            {
                vertexList[ vertexIndex ]
                        .addAdjacentVertex( adjVertex, weight );
                return true;
            }
        }
        return false;
    }

    /**
     * inserts vertex, adjacent vertex, and weight into array alphabetically
     *
     * @param vertex    character vertex letter
     * @param adjVertex character adjacent vertex letter
     * @param weight    integer weight between vertices
     * @return boolean result of insertion, false if vertex array is full, true
     * otherwise
     */
    private boolean insertVertex( char vertex, char adjVertex, int weight )
    {
        VertexNode newNode = new VertexNode( vertex, adjVertex, weight );
        if(vertexListSize - 1 == VERTEX_CAPACITY)
        {
            return false;
        }

        vertexList[ vertexListSize ] = newNode;
        vertexListSize++;

        return true;
    }

    /**
     * tests for vertex in list
     *
     * @param testVertex character vertex to search for
     * @return integer index if vertex found, constant NOT_IN_LIST otherwise
     */
    private int vertexInList( char testVertex )
    {
        int index;

        for(index = 0; index < vertexListSize; index++)
        {
            if(vertexList[ index ].getVertex() == testVertex)
            {
                return index;
            }
        }

        return NOT_IN_LIST;
    }

    /**
     * gets complete vertex node and data using the adjacent node data
     * <p>
     * Note: Cleans up access to this data in the BFS and DFS methods
     *
     * @param adjNode AdjacentNode data provided
     * @return VertexNode data found in array
     */
    private VertexNode adjToVertex( AdjacentNode adjNode )
    {
        int index = vertexInList( adjNode.getVertex() );
        if(index != NOT_IN_LIST)
        {
            return vertexList[ index ];
        }
        return null;
    }

    /**
     * Breadth-First Search (BFS) is actually just a traversal
     *
     * @param startVertex character vertex to start with
     * @param showQueue   boolean flag to control display of queue during
     *                    operations
     * @return String result of traversal process showing each visited vertex in
     * the order it was visited
     */
    public String BFS( char startVertex, boolean showQueue )
    {
        int vertexIndex = vertexInList( startVertex );
        VertexNode workingNode, adjacentVertex;
        AdjacentNode adjacentNode;
        String outStr = "";
        VertexQueue vertexQueue = new VertexQueue();
        Boolean foundVertex = true;

        if(vertexIndex != NOT_IN_LIST)
        {
            workingNode = vertexList[ vertexIndex ];
            workingNode.setVisited();
            vertexQueue.enqueue( workingNode );
            if(showQueue)
            {
                System.out.println( "Breadth-First Traversal:\n" );
                System.out.println( "Vertex Queue: " + vertexQueue.toString() );
            }

            outStr += workingNode.getVertex() + " ";

            while(!vertexQueue.isEmpty())
            {
                workingNode = vertexQueue.dequeue();
                adjacentNode = workingNode.getFirstAdjacency();

                while(adjacentNode != null)
                {
                    adjacentVertex = adjToVertex( adjacentNode );

                    if(adjacentVertex != null &&
                       !adjacentVertex.hasBeenVisited())
                    {
                        adjacentVertex.setVisited();
                        vertexQueue.enqueue( adjacentVertex );

                        if(showQueue)
                        {
                            System.out.println( "Vertex Queue: " +
                                                adjacentVertex.getVertex() +
                                                SPACE );
                        }
                        adjacentNode = workingNode.getNextAdjacency();
                    }
                }
            }

        }

        return "Breadth-First Result: " + outStr;
    }

    /**
     * Depth-First Search (DFS) is actually just a traversal
     *
     * @param startVertex character vertex to start with
     * @param showStack   boolean flag to control display of stack during
     *                    operations
     * @return String result of traversal process showing each visited vertex in
     * the order it was visited
     */
    public String DFS( char startVertex, boolean showStack )
    {
        int vertexIndex = vertexInList( startVertex );
        VertexNode workingNode, adjacentVertex;
        AdjacentNode adjacentNode;
        String outStr = "";
        VertexStack vertexStack = new VertexStack();
        Boolean foundVertex = true;

        if(vertexIndex != NOT_IN_LIST)
        {
            workingNode = vertexList[ vertexIndex ];
            workingNode.setVisited();

            vertexStack.push( workingNode );

            if(showStack)
            {
                System.out.println( "Depth First Traversal: \n" );
                System.out.println( "Vertex Stack: " + vertexStack.toString() );
            }

            outStr += workingNode.getVertex() + " ";

            while(!vertexStack.isEmpty())
            {
                workingNode = vertexStack.peekTop();

                adjacentNode = workingNode.getFirstAdjacency();

                while(adjacentNode != null)
                {
                    adjacentVertex = adjToVertex( adjacentNode );

                    if(adjacentVertex != null &&
                       !adjacentVertex.hasBeenVisited())
                    {
                        adjacentVertex.setVisited();
                        vertexStack.push( adjacentVertex );

                        if(showStack)
                        {
                            System.out.println( "Vertex Stack: " +
                                                adjacentVertex.getVertex() +
                                                SPACE );
                        }

                        foundVertex = true;
                        adjacentNode = null;
                    }
                    else
                    {
                        adjacentNode = workingNode.getNextAdjacency();
                        foundVertex = false;
                    }
                }

                if(!foundVertex)
                {
                    vertexStack.pop();
                    if(showStack)
                    {
                        System.out.println(
                                "Vertex Stack: " + vertexStack.toString() );
                    }
                }
            }
        }

        clearVisitedFlags();
        return "Depth-First Result: " + outStr;
    }

    /**
     * generates an adjacency matrix table that displays weights between
     * vertices
     */
    public void generateAdjacencyMatrix()
    {
        int index;
        System.out.println( "\n" );
        printChars( 6, SPACE );
        for(index = 0; index < vertexListSize; index++)
        {
            System.out.print( vertexList[ index ].getVertex() + SPACE );
        }
    }

    /**
     * clears all vertex visited flags for use after completion of BFS, DFS
     */
    public void clearVisitedFlags()
    {
        int index;

        for(index = 0; index < vertexListSize; index++)
        {
            vertexList[ index ].unSetVisited();
        }
    }

    /**
     * Recursive method that prints a specified number of specified characters
     *
     * @param numChars integer number of characters to print
     * @param outChar  character value to be printed
     */
    void printChars( int numChars, char outChar )
    {
        if(numChars > 0)
        {
            System.out.print( outChar );

            printChars( numChars - 1, outChar );
        }
    }
}
