package edu.iastate.cs228.hw4;

/**
 * A binary tree that contains all of the characters used in an encoded language. The message tree will print the
 * character and the characters code as well as use the binary tree to decode an encoded message.
 *
 * @author Jack Croghan
 */
public class MsgTree
{
    public char payLoadChar;
    public MsgTree left = null;
    public MsgTree right = null;

    //Need static char idx to the tree string for recursive solution
    private static int staticCharIdx = 0;

    //Keeps track of all of the characters in the final message
    public static int totalChar = 0;

    /**
     * A recursive constructor building the tree from a string
     *
     * @param encodingString
     *  the string to be used to construct the binary tree
     */

    public MsgTree(String encodingString)
    {
        payLoadChar = encodingString.charAt(staticCharIdx);
        ++staticCharIdx;
        if (payLoadChar != '^')
        {
            return;
        }
        left = new MsgTree(encodingString);
        right = new MsgTree(encodingString);
    }

    /**
     * Method to print characters and their binary codes
     *
     * @param root
     *  the current node in the tree
     * @param code
     *  the code at the current position within the tree
     */
    public static void printCodes(MsgTree root, String code)
    {
        if (root.payLoadChar == '\n')
        {
            System.out.println("  \\n" + "       " + code);
        }
        else if (root.payLoadChar != '^')
        {
            System.out.println("   " + root.payLoadChar + "       " + code);
        }
        if (root.left != null)
        {
            printCodes(root.left, code + "0");
        }
        if (root.right != null)
        {
            printCodes(root.right, code + "1");
        }
    }

    /**
     * Decodes the encrypted message and prints the final result
     *
     * @param root
     *  the root of the MsgTree to be used for decoding
     * @param msg
     *  the encoded message
     */
    public static void decode(MsgTree root, String msg)
    {
        int index = 0;
        MsgTree currentNode = root;
        String decodedMessage = "";
        while (index < msg.length())
        {
            if (currentNode.payLoadChar == '^')
            {
                if (msg.charAt(index) == '0') { currentNode = currentNode.left; }
                else { currentNode = currentNode.right; }
                if (currentNode.payLoadChar != '^')
                {
                    decodedMessage = decodedMessage + currentNode.payLoadChar;
                    currentNode = root;
                    ++totalChar;
                }
                ++index;
            }
            else
            {
                decodedMessage = decodedMessage + currentNode.payLoadChar;
                currentNode = root;
                ++totalChar;
            }
        }
        System.out.print(decodedMessage);
    }
}
