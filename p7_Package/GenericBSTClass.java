package p7_Package;

public class GenericBSTClass< GenericData extends Comparable< GenericData > >
{


    /**
     * Root of BST
     */
    private BST_Node BST_Root;
    /**
     * Traverse code - inorder
     */
    static int INORDER_TRAVERSE = 102;
    /**
     * Traverse code - postorder
     */
    static int POSTORDER_TRAVERSE = 103;
    /**
     * Traverse code - preorder
     */
    static int PREORDER_TRAVERSE = 101;

    private class BST_Node
    {

        BST_Node leftChildRef;
        private GenericData nodeData;
        BST_Node rightChildRef;

        private BST_Node( GenericData inData )
        {
            leftChildRef = null;
            rightChildRef = null;
            nodeData = inData;
        }
    }

    /**
     * Default class constructor, initializes BST
     */
    public GenericBSTClass()
    {
        BST_Root = null;
    }

    /**
     * Insert method for BST
     * <p>
     * Note: uses insert helper method
     *
     * @param inData GenericData data to be added to BST
     */
    public void insert( GenericData inData )
    {
        if(isEmpty())
        {
            BST_Root = new BST_Node( inData );
        }
        else
        {
            insertHelper( BST_Root, inData );
        }
    }

    /**
     * Insert helper method for BST insert action
     *
     * @param localRoot BST_Node tree root reference at the current recursion
     *                  level
     * @param inData    GenericData item to be added to BST
     */
    public void insertHelper( BST_Node localRoot, GenericData inData )
    {
        if(localRoot == null)
        {
            localRoot = new BST_Node( inData );
        }
        else if(localRoot.nodeData.compareTo( inData ) < 0)
        {
            if(localRoot.leftChildRef == null)
            {
                localRoot.leftChildRef = new BST_Node( inData );
            }
            else
            {
                insertHelper( localRoot.leftChildRef, inData );
            }
        }
        else if(localRoot.nodeData.compareTo( inData ) > 0)
        {
            if(localRoot.rightChildRef == null)
            {
                localRoot.rightChildRef = new BST_Node( inData );
            }
            else
            {
                insertHelper( localRoot.rightChildRef, inData );
            }
        }

    }

    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     *
     * @param inData GenericData that includes the necessary key
     *
     * @return GenericData result of remove action
     */
    public GenericData removeNode( GenericData inData )
    {
        if(!isEmpty())
        {
            return removeNodeHelper( BST_Root, inData ).nodeData;
        }
        else
        {
            return null;
        }
    }

    /**
     * Remove helper for BST remove action
     * <p>
     * Note: uses removeFromMax method
     *
     * @param localRoot BST_Node tree root reference at the current recursion
     *                  level
     * @param outData   GenericData item that includes the necessary key
     *
     * @return BST_Node reference result of remove helper action
     */
    private BST_Node removeNodeHelper( BST_Node localRoot, GenericData outData )
    {
        BST_Node workingNode,returnNode;
        if(localRoot.nodeData==outData)
        {
           workingNode = removeFromMax( localRoot,localRoot.rightChildRef );
           returnNode=localRoot;

           localRoot=workingNode;

           return returnNode;
        }
        else if(localRoot.nodeData.compareTo( outData )< 0)
        {
            return removeNodeHelper( localRoot.leftChildRef,outData );
        }
        else if(localRoot.nodeData.compareTo( outData ) > 0)
        {
            return removeNodeHelper( localRoot.rightChildRef,outData );
        }
        else
        {
            return null;
        }

    }

    /**
     * Searches tree from given node to maximum value node below it, stores data
     * value found, and then unlinks the node
     *
     * @param maxParent BST_Node reference to current node
     * @param maxLoc    BST_Node reference to child node to be tested
     *
     * @return BST_Node reference containing removed node
     */
    private BST_Node removeFromMax( BST_Node maxParent, BST_Node maxLoc )
    {
        if(maxLoc.rightChildRef != null && maxParent.nodeData.compareTo( maxLoc.nodeData ) > 0)
        {
            removeFromMax( maxParent,maxParent.rightChildRef );
        }
        else
        {

            maxParent.rightChildRef=maxLoc.rightChildRef;

            return maxLoc;
        }

    return null;
    }

    /**
     * Searches for data in BST given GenericData with necessary key
     *
     * @param searchData GenericData item containing key
     *
     * @return GenericData reference to found data
     */
    public GenericData search( GenericData searchData )
    {
        if(!isEmpty())
        {
            return searchHelper( BST_Root, searchData );
        }
        return null;
    }

    /**
     * Helper method for BST search action
     *
     * @param localRoot  BST_Node tree root reference at the current recursion
     *                   level
     * @param searchData GenericData item containing key
     *
     * @return GenericData item found
     */
    private GenericData searchHelper( BST_Node localRoot,
                                      GenericData searchData )
    {
        if(localRoot.nodeData.compareTo(searchData) == 0)
        {
            return localRoot.nodeData;
        }
        else if(localRoot.nodeData.compareTo( searchData ) > 0)
        {
            return searchHelper( localRoot.rightChildRef,searchData );
        }
        else if(localRoot.nodeData.compareTo( searchData ) < 0)
        {
            return searchHelper( localRoot.leftChildRef,searchData );
        }
        else
        {
            return null;
        }
    }

    /**
     * Provides user with three ways to display BST data
     *
     * @param traverseCode int code for selecting BST traversal method, accepts
     *                     PREORDER_TRAVERSE, INORDER_TRAVERSE,
     *                     POSTORDER_TRAVERSE
     */
    public void displayTree( int traverseCode )
    {
        if(traverseCode == PREORDER_TRAVERSE)
        {
            displayPreOrder( BST_Root );
        }

        else if(traverseCode == INORDER_TRAVERSE)
        {
            displayInOrder( BST_Root );
        }
        else if(traverseCode == POSTORDER_TRAVERSE)
        {
            displayPostOrder( BST_Root );
        }
    }

    /**
     * Provides preOrder traversal action
     *
     * @param localRoot BST_Node tree root reference at the current recursion
     *                  level
     */
    private void displayPreOrder( BST_Node localRoot )
    {
        System.out.println("PreOrder Display: ");
        if(localRoot.leftChildRef!=null)
        {
            displayInOrder( localRoot.leftChildRef );
        }
        if(localRoot.rightChildRef!=null)
        {
            displayInOrder( localRoot.rightChildRef );
        }
        System.out.println(localRoot.nodeData.toString());


    }

    /**
     * Provides postOrder traversal action
     *
     * @param localRoot BST_Node tree root reference at the current recursion
     *                  level
     */
    private void displayPostOrder( BST_Node localRoot )
    {
        System.out.println("PostOrder Display: ");

        System.out.println(localRoot.nodeData.toString());

        if(localRoot.rightChildRef!=null)
        {
            displayInOrder( localRoot.rightChildRef );
        }
        if(localRoot.leftChildRef!=null)
        {
            displayInOrder( localRoot.leftChildRef );
        }
    }

    /**
     * Provides inOrder traversal action
     *
     * @param localRoot BST_Node tree root reference at the current recursion
     *                  level
     */
    private void displayInOrder( BST_Node localRoot )
    {
        System.out.println("InOrder Display: ");

        if(localRoot.leftChildRef!=null)
        {
            displayInOrder( localRoot.leftChildRef );
        }
        if(localRoot.rightChildRef!=null)
        {
            displayInOrder( localRoot.rightChildRef );
        }
        System.out.println(localRoot.nodeData.toString());



    }

    /**
     * Clears tree
     */
    public void clearTree()
    {
        BST_Root = null;
    }

    /**
     * Test for empty tree
     *
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return BST_Root == null;
    }

}
