package p8_Package;

/**
 * Binary Search Tree (BST) class with self-balancing attributes specifically
 * using the Adelson-Velsky and Landis (AVL) strategy
 *
 * @author Michael Leverington extended by Joshua Pollock
 */
public class AVL_TreeClass
{

    /**
     * Traverse code - preorder
     */
    public static final int PREORDER_TRAVERSE = 101;
    /**
     * Traverse code - inorder
     */
    public static final int INORDER_TRAVERSE = 102;
    /**
     * Traverse code - postorder
     */
    public static final int POSTORDER_TRAVERSE = 103;
    /**
     * Constant used to represent space
     */
    private static final char SPACE = ' ';
    /**
     * Constant used to represent dash
     */
    private static final char DASH = '-';
    /**
     * Null character returned if data not available
     */
    private final char NULL_CHAR = '\n';
    /**
     * Root of AVL Tree
     */
    private AVL_Node AVL_TreeRoot;
    /**
     * Class global variable used to display tree structure
     */
    private boolean rowStartFlag;

    /**
     * Default class constructor, root initialized
     */
    public AVL_TreeClass()
    {
        AVL_TreeRoot = null;
    }

    /**
     * Insert method for AVL Tree
     * <p>
     * Note: uses insert helper method which returns the root node reference to
     * this method
     *
     * @param inData char data to be added to AVL Tree
     */
    public void insert( char inData )
    {
        System.out.println( "Inserting " + inData + " and balancing" );

        AVL_TreeRoot = insertHelper( AVL_TreeRoot, inData );
    }

    /**
     * Insert helper method for AVL Tree insert action
     *
     * @param localRef AVL_Node tree root reference at the current recursion
     *                 level
     * @param inData   char item to be added to AVL Tree
     *
     * @return AVL_Node reference to current AVL Tree root
     */
    private AVL_Node insertHelper( AVL_Node localRef, char inData )
    {
        int treeBalance;

        if(localRef == null)
        {
            return new AVL_Node( inData );
        }

        if(inData < localRef.nodeData)
        {
            localRef.leftChildRef = insertHelper( localRef.leftChildRef,
                                                  inData );
        }
        else if(inData > localRef.nodeData)
        {
            localRef.rightChildRef = insertHelper( localRef.rightChildRef,
                                                   inData );
        }
        else
        {
            return localRef;
        }

        treeBalance = getBalanceFactor( localRef );

        if(treeBalance > 1 && inData < localRef.leftChildRef.nodeData)
        {
            System.out.println( "    Identified: Left Left Case" );
            System.out.println( "      - Rotating Left" );
            return rotateRight( localRef );
        }

        else if(treeBalance < -1 && inData > localRef.rightChildRef.nodeData)
        {
            System.out.println( "    Identified: Right Right Case" );
            System.out.println( "      - Rotating Left" );
            return rotateLeft( localRef );
        }

        else if(treeBalance > 1 && inData > localRef.leftChildRef.nodeData)
        {
            System.out.println( "    Identified: Left Right Case" );
            System.out.println( "      - Rotating Left" );
            localRef.leftChildRef = rotateLeft( localRef.leftChildRef );
            System.out.println( "      - Rotate Right" );
            return rotateRight( localRef );
        }

        else if(treeBalance < -1 && inData < localRef.rightChildRef.nodeData)
        {
            System.out.println( "    Identified: Right Left Case" );
            System.out.println( "      - Rotating Right" );
            localRef.rightChildRef = rotateRight( localRef.rightChildRef );
            System.out.println( "      - Rotating Left" );
            return rotateLeft( localRef );
        }

        else
        {
            return localRef;
        }
    }

    /**
     * Rotates local tree left or CCW
     *
     * @param localRef reference of current item
     *
     * @return AVL_Node resulting current root
     */
    private AVL_Node rotateLeft( AVL_Node localRef )
    {
        AVL_Node localNode = localRef.rightChildRef;
        AVL_Node leftNode = localNode.leftChildRef;

        localNode.leftChildRef = localRef;
        localRef.rightChildRef = leftNode;

        return localNode;
    }

    /**
     * Rotates local tree right or CW
     *
     * @param localRef reference of current item
     *
     * @return AVL_Node resulting current root
     */
    private AVL_Node rotateRight( AVL_Node localRef )
    {
        AVL_Node localNode = localRef.leftChildRef;
        AVL_Node rightNode = localNode.rightChildRef;

        localNode.rightChildRef = localRef;
        localRef.leftChildRef = rightNode;

        return localNode;
    }

    /**
     * Searches for data in AVL Tree given char with necessary key
     *
     * @param searchData char item containing key
     *
     * @return char reference to found data
     */
    public char search( char searchData )
    {
        return searchHelper( AVL_TreeRoot, searchData );
    }

    /**
     * Helper method for AVL Tree search action
     *
     * @param localRef   AVL_Node tree root reference at the current recursion
     *                   level
     * @param searchData char item containing key
     *
     * @return char result of search
     */
    private char searchHelper( AVL_Node localRef, char searchData )
    {
        if(localRef != null)
        {
            if(searchData < localRef.nodeData)
            {
                return searchHelper( localRef.leftChildRef, searchData );
            }

            else if(searchData > localRef.nodeData)
            {
                return searchHelper( localRef.rightChildRef, searchData );
            }

            return localRef.nodeData;
        }

        return NULL_CHAR;
    }

    /**
     * provides tree height to user
     *
     * @return integer height of tree
     */
    public int findTreeHeight()
    {
        return getTreeHeight( AVL_TreeRoot );
    }

    /**
     * Finds height of tree using helper method
     *
     * @param localRef AVL_Node node from which height is found
     *
     * @return integer height of tree
     */
    private int getTreeHeight( AVL_Node localRef )
    {
        if(localRef != null)
        {
            return 1 + getMax( getTreeHeight( localRef.leftChildRef ),
                               getTreeHeight( localRef.rightChildRef ) );
        }
        return -1;
    }

    /**
     * gets balance factor indicating if tree is unbalanced from given root
     * down
     *
     * @param localRef AVL_Node from which balance factor is found
     *
     * @return integer balance factor
     */
    private int getBalanceFactor( AVL_Node localRef )
    {

        if(localRef != null)
        {
            return getTreeHeight( localRef.leftChildRef ) -
                   getTreeHeight( localRef.rightChildRef );
        }

        return 0;

    }


    /**
     * Provides user with three ways to display AVL Tree data
     *
     * @param traverseCode integer code for selecting AVL Tree traversal method,
     *                     accepts PREORDER_TRAVERSE, INORDER_TRAVERSE,
     *                     POSTORDER_TRAVERSE
     */
    public void displayTree( int traverseCode )
    {
        switch(traverseCode)
        {
            case PREORDER_TRAVERSE:
                displayPreOrder( AVL_TreeRoot );
                break;

            case POSTORDER_TRAVERSE:
                displayPostOrder( AVL_TreeRoot );
                break;

            default:
                displayInOrder( AVL_TreeRoot );
                break;
        }

        System.out.println();
    }

    /**
     * Provides preOrder traversal action
     *
     * @param localRef AVL_Node tree root reference at the current recursion
     *                 level
     */
    private void displayPreOrder( AVL_Node localRef )
    {
        if(localRef != null)
        {
            System.out.print( localRef.nodeData + SPACE );
            displayPreOrder( localRef.leftChildRef );
            displayPreOrder( localRef.rightChildRef );
            System.out.print( SPACE );
        }
    }

    /**
     * Provides postOrder traversal action
     *
     * @param localRef AVL_Node tree root reference at the current recursion
     *                 level
     */
    private void displayPostOrder( AVL_Node localRef )
    {
        if(localRef != null)
        {
            displayPostOrder( localRef.leftChildRef );
            displayPostOrder( localRef.rightChildRef );
            System.out.print( localRef.nodeData + SPACE );
            System.out.print( SPACE );
        }
    }

    /**
     * Provides inOrder traversal action
     *
     * @param localRef AVL_Node tree root reference at the current recursion
     *                 level
     */
    private void displayInOrder( AVL_Node localRef )
    {
        if(localRef != null)
        {
            displayInOrder( localRef.leftChildRef );

            System.out.print( localRef.nodeData );
            System.out.print( SPACE );

            displayInOrder( localRef.rightChildRef );
        }
    }

    /**
     * Displays text-graphical representation of AVL tree
     */
    public void displayTreeStructure()
    {
        int displayLevel, nodeHeight = getTreeHeight( AVL_TreeRoot ) + 2;
        int workingLevel = 1;

        if(AVL_TreeRoot != null)
        {
            for(displayLevel = 1; displayLevel <= nodeHeight; displayLevel++)
            {
                rowStartFlag = true;

                displayAtTreeLevel( AVL_TreeRoot, nodeHeight,
                                    displayLevel, workingLevel );

                System.out.println();
            }
        }

        else
        {
            System.out.println( "\nEmpty Tree - No Display" );
        }
    }

    /**
     * Displays text-graphical representation of one level/line of the AVL tree
     * <p>
     * Note: choice of color or letter data is made with
     * setTreeDisplayCharacter
     *
     * @param workingNode  node reference at current recursive level
     * @param nodeHeight   height of tree plus two for current height of nodes,
     *                     including lowermost null children
     * @param displayLevel level of tree at which the current line of display is
     *                     to be presented
     * @param workingLevel current level during recursive actions
     */
    private void displayAtTreeLevel( AVL_Node workingNode, int nodeHeight,
                                     int displayLevel, int workingLevel )
    {
        char charOut = workingNode.nodeData;

        if(workingLevel == displayLevel)
        {
            displayValue( charOut, nodeHeight, workingLevel );

            return;
        }

        if(workingNode.leftChildRef != null)
        {
            displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
                                displayLevel, workingLevel + 1 );
        }

        else
        {
            displayEmptyNodeSpaces( nodeHeight, displayLevel,
                                    workingLevel + 1 );
        }

        if(workingNode.rightChildRef != null)
        {
            displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
                                displayLevel, workingLevel + 1 );
        }

        else
        {
            displayEmptyNodeSpaces( nodeHeight, displayLevel,
                                    workingLevel + 1 );
        }
    }

    /**
     * Method used to display a character or color letter along with calculated
     * leading spaces
     * <p>
     * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
     *
     * @param data         data value to display, either letter or color data
     * @param nodeHeight   height of tree plus two for current height of nodes,
     *                     including lowermost null children
     * @param workingLevel current level during recursive actions
     */
    private void displayValue( char data, int nodeHeight, int workingLevel )
    {
        int leadingSpaces;

        if(rowStartFlag)
        {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel );

            rowStartFlag = false;
        }

        else
        {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
        }

        displayChars( leadingSpaces, SPACE );

        System.out.print( data );
    }

    /**
     * Method that displays null or blank nodes for a tree at null locations
     * <p>
     * Note: used by displayAtTreeLevel
     *
     * @param nodeHeight   height of tree plus two for current height of nodes,
     *                     including lowermost null children
     * @param displayLevel level of the tree at which the display will be
     *                     applied
     * @param workingLevel level of tree just below non-null node at which
     *                     method is currently working
     */
    private void displayEmptyNodeSpaces( int nodeHeight,
                                         int displayLevel, int workingLevel )
    {
        int nodesToDisplay = toPower( 2, displayLevel - workingLevel );
        char charOut = SPACE;

        if(displayLevel == workingLevel)
        {
            charOut = DASH;
        }

        while(nodesToDisplay > 0)
        {
            displayValue( charOut, nodeHeight, displayLevel );

            nodesToDisplay--;
        }
    }

    /**
     * Test for empty tree
     *
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return AVL_TreeRoot == null;
    }

    /**
     * Finds maximum of two given numbers
     *
     * @param one   one of two values to be tested
     * @param other other of two values to be tested
     *
     * @return greater of the two values
     */
    private int getMax( int one, int other )
    {
        int max = one;

        if(other > max)
        {
            max = other;
        }

        return max;
    }

    /**
     * Local recursive method to display a specified number of a specified
     * character
     *
     * @param numChars number of characters to display
     * @param outChar  character to display
     */
    private void displayChars( int numChars, char outChar )
    {
        if(numChars > 0)
        {
            System.out.print( outChar );

            displayChars( numChars - 1, outChar );
        }
    }

    /**
     * Local recursive method to calculate exponentiation with integers
     *
     * @param base     base of exponentiation
     * @param exponent exponent of exponentiation
     *
     * @return result of exponentiation calculation
     */
    private int toPower( int base, int exponent )
    {
        if(exponent > 0)
        {
            return toPower( base, exponent - 1 ) * base;
        }

        return 1;
    }

    /**
     * clears tree
     */
    public void clearTree()
    {
        AVL_TreeRoot = null;
    }

    /**
     * Binary Search Tree node class for managing character data within an AVL
     * Tree
     *
     * @author MichaelL
     */
    private class AVL_Node
    {

        private char nodeData;

        private AVL_Node leftChildRef;

        private AVL_Node rightChildRef;

        /**
         * Initialization constructor for data
         *
         * @param inData char quantity
         */
        public AVL_Node( char inData )
        {
            nodeData = inData;

            leftChildRef = null;

            rightChildRef = null;
        }
    }
}

