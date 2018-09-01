package p6_Package;

/**
 * Class provides data storage with iterator management
 *
 * @author Joshua Pollock
 */
public class IteratorClassLL
{

    /**
     * Provides constant -999999 for access failure messaging
     */
    static int FAILED_ACCESS = -999999;
    /**
     * iterator cursor reference
     */
    NodeClass cursorRef;
    /**
     * iterator head reference
     */
    NodeClass headRef;


    /**
     * Default constructor
     */
    public IteratorClassLL()
    {
        headRef = null;
        cursorRef = null;
    }


    /**
     * Copy constructor
     * <p>
     * Copies IteratorClass object int this object
     *
     * @param copied IteratorClass object to be copied
     */
    public IteratorClassLL( IteratorClassLL copied )
    {
        NodeClass baseNode = new NodeClass( copied.headRef.value ), tmpNode;
        NodeClass workingNode = copied.headRef;
        headRef=baseNode;

        while(workingNode.nextNode != null)
        {

            tmpNode = new NodeClass( workingNode.nextNode.value );
            baseNode.nextNode = tmpNode;
            if(workingNode == copied.cursorRef)
            {
                cursorRef = baseNode;
            }
            baseNode = baseNode.nextNode;
            workingNode = workingNode.nextNode;
        }


//        cursorRef=copied.cursorRef;
//        headRef=copied.headRef;


    }

    /**
     * Inserts item prior to iterator index in list
     * <p>
     * Iterator points at inserted item after completion
     * <p>
     * Resizes array if needed
     *
     * @param newValue Value to be inserted in list
     */
    public void insertPriorToIterator( int newValue )
    {
        NodeClass iterNode = headRef;
        NodeClass tmpNode = new NodeClass( newValue );
        if(cursorRef == null && headRef == null)
        {
            cursorRef = tmpNode;
            headRef = cursorRef;
        }
        else if(cursorRef != headRef)
        {
            while(iterNode.nextNode != cursorRef)
            {
                iterNode = iterNode.nextNode;
            }
            iterNode.nextNode = tmpNode;
            tmpNode.nextNode = cursorRef;
            cursorRef = tmpNode;
        }
        else
        {
            tmpNode.nextNode = headRef;
            headRef = tmpNode;
            cursorRef = headRef;
        }

    }

    /**
     * Inserts item after iterator index in list
     * <p>
     * Iterator points at inserted item after completion
     *
     * @param newValue Value to be inserted in list
     */
    public void insertAfterIterator( int newValue )
    {
        NodeClass tmpNode = new NodeClass( newValue );
        if(cursorRef == null && headRef == null)
        {
            cursorRef = tmpNode;
            headRef = cursorRef;
        }
        else if(cursorRef.nextNode == null)
        {
            cursorRef.nextNode = tmpNode;
            cursorRef = cursorRef.nextNode;
        }
        else
        {
            tmpNode.nextNode = cursorRef.nextNode;
            cursorRef.nextNode = tmpNode;
            cursorRef = cursorRef.nextNode;
        }
    }

    /**
     * Move iterator cursor to next item if not currently at end
     */
    public void moveNext()
    {
        if(cursorRef.nextNode != null)
        {
            cursorRef = cursorRef.nextNode;
        }
    }

    /**
     * Move iterator cursor to previous item if not currently at beginning
     */
    public void movePrevious()
    {
        movePreviousHelper( headRef );
    }

    /**
     * Helper method uses recursion to move iterator cursor to previous item
     * <p>
     * Precondition: cursor reference is not at beginning
     *
     * @param workingRef reference used to support recursion
     */
    private void movePreviousHelper( NodeClass workingRef )
    {
        if(workingRef.nextNode != null && workingRef.nextNode == cursorRef)
        {
            cursorRef = workingRef;
        }
        else if(workingRef.nextNode != null)
        {
            movePreviousHelper( workingRef.nextNode );
        }
    }

    /**
     * Removes and returns value from list at current iterator position
     * <p>
     * Note: If index is greater than zero, sets iterator index to previous item
     * after removal action
     *
     * @return Value returned if successful, FAILED_ACCESS if not
     */

    public int removeAtCurrent()
    {
        int returnVal;
        NodeClass tmpNode;

        if(cursorRef == null)
        {
            return FAILED_ACCESS;
        }
        tmpNode = cursorRef.nextNode;
        returnVal = cursorRef.value;
        if(cursorRef != headRef)
        {
            movePrevious();
            cursorRef.nextNode = tmpNode;
        }
        else
        {
            headRef = headRef.nextNode;
            cursorRef = headRef;
        }

        return returnVal;
    }

    /**
     * Gets value at current location of iterator
     *
     * @return Value returned if successful, FAILED_ACCESS if not
     */
    public int retrieveAtCurrent()
    {
        if(cursorRef == null)
        {
            return FAILED_ACCESS;
        }
        return cursorRef.value;
    }

    /**
     * Sets iterator to beginning of list
     */
    public void setToBeginning()
    {
        cursorRef = headRef;
    }

    /**
     * Sets iterator to end of list
     */
    public void setToEnd()
    {
        if(headRef != null)
        {
            setToEndHelper( headRef );
        }
    }

    /**
     * sets cursor to end of list using recursion
     * <p>
     * Precondition: cursor reference is not at head
     *
     * @param workingRef NodeClass object necessary for recursion
     */
    private void setToEndHelper( NodeClass workingRef )
    {

        if(workingRef != null && workingRef.nextNode == null)
        {
            cursorRef = workingRef;
        }
        else if(workingRef != null)
        {
            setToEndHelper( workingRef.nextNode );
        }
    }

    /**
     * checks for iterator at end of list
     *
     * @return Boolean result of test
     */
    public boolean isAtEnd()
    {
        return cursorRef.nextNode == null;
    }

    /**
     * checks for iterator at beginning of list
     *
     * @return Boolean result of test
     */
    public boolean isAtBeginning()
    {
        return headRef == cursorRef;
    }

    /**
     * checks for empty list
     *
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return headRef == null;
    }

    /**
     * Clears iterator array by setting size and iterator index to zero
     */
    public void clear()
    {
        headRef = null;
        cursorRef = null;
    }

    /**
     * provides array data as a string, including indication of current element,
     * using recursive toStringHelper method
     * <p>
     * Note: no spaces at beginning or end of string
     *
     * @return String result of reported array
     *
     * @overrides toString in class java.lang.Object
     */
    public String toString()
    {
        return toStringHelper( headRef );
    }

    /**
     * recursive helper method that creates string from data with cursor
     * indicator at appropriate location and no space at end of string
     *
     * @param workingRef NodeClass reference used for recursion
     *
     * @return String containing list of numbers as specified
     */
    public String toStringHelper( NodeClass workingRef )
    {
        String outputString = "";
        if(workingRef == null)
        {
            return outputString;
        }
        else
        {
            if(workingRef != headRef)
            {
                outputString += " ";
            }
            if(workingRef == cursorRef)
            {
                outputString += "|" + workingRef.value + "|";
            }
            else
            {
                outputString += workingRef.value;
            }
            return outputString + toStringHelper( workingRef.nextNode );
        }
    }

    /**
     * node data structure for linked list
     */
    private class NodeClass
    {

        NodeClass nextNode;
        int value;

        /**
         * Initialization constructor for NodeClass
         *
         * @param inVal data value for Node Class object
         */
        private NodeClass( int inVal )
        {
            value = inVal;
            nextNode = null;
        }

    }

}

