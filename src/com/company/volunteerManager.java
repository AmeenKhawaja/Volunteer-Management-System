package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class contains all the binary search tree functions such as inserting, finding height, depth, and node deletion.
 * The constructor that runs all the functions is located in the Main class.
 *             50            Depth = 0  Height = 4
 *        42      71         Depth = 1  Height = 3
 *    13        60   77      Depth = 2  Height = 2
 * 008    24  55       98    Depth = 3  Height = 1
 *                       92  Depth = 4  Height = 0
 */

public class volunteerManager<T extends Comparable<T>> {

    FileWriter file = new FileWriter("src/com/company/results.txt");
    BufferedWriter writer = new BufferedWriter(file);

    private Node<T> root; // The root node that contains all the data

    public volunteerManager() throws IOException {
    }

    /**
     * Based off the assignment instructions, from my understanding we have to store all the information of each
     * volunteer into a single node.
     * This method first checks to see if the root is equal to null, if it is null, a new node will be
     * inserted into the tree containing data from the text file
     *
     * @param data the volunteer_data text file gets passed in as a parameter to insert
     * @throws IOException
     */
    public void insert(T data) throws IOException {
        // inserts the first node into the BST while the parent is null
        if (root == null) {
            root = new Node<>(data, null);
        } else {
            insertT(data, root);
        }

    }

    /**
     * This method checks the values of the right and left side of the search tree and inserts accordingly
     *
     * @param data data being passed in from the volunteer data file
     * @param node root node that stores the data
     */
    private void insertT(T data, Node<T> node) {

        if (node.getData().compareTo(data) > 0) {
            if (node.getLeftChild() != null)
                insertT(data, node.getLeftChild());
            else
                node.setLeftChild(new Node<>(data, node));
            //going to right subtree
        } else {
            if (node.getRightChild() != null)
                insertT(data, node.getRightChild());
            else
                node.setRightChild(new Node<>(data, node));
        }
    }

    /**
     * @param data information being passed in to be written to the file
     * @throws IOException
     */
    public void writeFile(String data) throws IOException {
        writer.write(data);
    }

    /**
     * This method writes the preorder traversal results to the file and prints it out to the console
     *
     * @throws IOException
     */
    public void preOrder() throws IOException {
        writer.newLine();
        preOrder(root);
        writer.write("preOrder Traversal");
        System.out.println("preOrder Traversal");
        System.out.println("\n");
        writer.newLine();
        writer.newLine();
    }

    /**
     * This method visits the root, then the left subtree, and finally the right subtree last
     *
     * @param root contains data from the file
     * @throws IOException
     */
    private void preOrder(Node<T> root) throws IOException {
        if (root == null)
            return;
        else {
            System.out.println(root.getData() + " ");
            writer.write(root.getData() + " ");
            writer.newLine();
            preOrder(root.getLeftChild());
            preOrder(root.getRightChild());
        }
    }

    /**
     * This method writes Inorder traversal results to the file and console
     *
     * @throws IOException
     */
    public void inOrder() throws IOException {
        writer.newLine();
        inOrder(root);
        writer.write("inOrder Traversal");
        System.out.println("inOrder Traversal");
        System.out.println("\n");
        writer.newLine();
        writer.newLine();
    }

    /**
     * This method traverses the left subtree, visits the root, than visits the right subtree
     *
     * @param root contains data from the file
     * @throws IOException
     */
    private void inOrder(Node<T> root) throws IOException {
        if (root == null)
            return;
        else {
            inOrder(root.getLeftChild());
            System.out.println(root.getData() + " ");
            writer.write(root.getData() + " ");
            writer.newLine();
            inOrder(root.getRightChild());
        }
    }

    /**
     * Prints out post order traversal results to the file and console
     *
     * @throws IOException
     */
    public void postOrder() throws IOException {
        writer.newLine();
        postOrder(root);
        writer.write("postOrder Traversal");
        System.out.println("postOrder Traversal");
        writer.newLine();
        writer.newLine();
        System.out.println("\n");
    }

    /**
     * This method visits the left subtree, then the right subtree, and then the root
     *
     * @param root contains data from the file
     * @throws IOException
     */
    private void postOrder(Node<T> root) throws IOException {
        if (root == null) {
            return;
        } else {
            postOrder(root.getLeftChild());
            postOrder(root.getRightChild());
            System.out.println(root.getData() + " ");
            writer.write(root.getData() + " ");
            writer.newLine();
        }

    }

    /**
     * Used to close the writer when there is no more results to write to file.
     *
     * @throws IOException
     */
    public void closeWriter() throws IOException {
        writer.close();
    }

    /**
     * Prints height of the binary search tree to the file and console
     *
     * @throws IOException
     */
    public void height() throws IOException {
        System.out.println("The height of the binary search tree is:  " + height(root));
        writer.write("The height of the binary search tree is " + height(root));
        writer.newLine();
    }

    /**
     * This method recursively calls itself to obtain the height of the binary search tree. It checks if the root
     * is equal to null and will return -1, else it will traverse the left and right side of the tree and return the
     * larger value +1
     *
     * @param root root node contains the volunteer information
     * @return maximum height of the tree
     */
    private int height(Node<T> root) {
        if (root == null) {
            return -1;
        }
        return Math.max(height(root.getLeftChild()), height(root.getRightChild())) + 1;
    }

    /**
     * This method prints depth of the tree to file and console
     *
     * @throws IOException
     */
    public void depth() throws IOException {
        System.out.println("The depth of the binary search tree is: " + depth(root));
        writer.write("The depth of the binary search tree is: " + depth(root));
        System.out.println("\n");
        writer.newLine();
        writer.newLine();
    }

    /**
     * This method obtains the depth of the deepest node in the binary search tree recursively. The depth of the
     * deepest node is 092 which is 4.
     *
     * @param node root node contains the volunteer information
     * @return depth of the tree
     */
    private int depth(Node<T> node) {
        if (node == null) {
            return -1;
        } else {
            if (depth(node.getLeftChild()) > depth(node.getRightChild()))
                return (depth(node.getLeftChild()) + 1);
            else
                return (depth(node.getRightChild()) + 1);
        }
    }

    /**
     * This method finds the smallest item in a subtree
     *
     * @param node the node that roots the subtree
     * @return node containing the smallest item in the tree
     */
    private Node<T> findMin(Node<T> node) {
        if (node != null) {
            while (node.getLeftChild() != null)
                node = node.getLeftChild();
        }
        return node;
    }

    /**
     * This method prints out the depth of a specific node iteratively to the file and console
     *
     * @param data specific node that wants its depth found
     */
    public void depth2(T data) throws IOException {
        System.out.println("depth2 (iterative) is: " + depth2(root, data));
        writer.write("depth2 (iterative) is: " + depth2(root, data));
        writer.newLine();
    }

    /**
     * My thought process behind finding the depth of the tree iteratively is to use a while loop. First, I check
     * if the data stored within the node is not equal to the data that is being passed in. If it is not equal, I use
     * compareTo to check the strings lexicographically. If it returns a negative value, we traverse the left side of
     * the tree, else, go to the right side. Within each iteration, the depth counter increments by one until the data
     * has been found.
     *
     * @param node the node that roots the subtree
     * @param data the data being passed in that wants its depth found
     * @return depth of the tree
     */
    public int depth2(Node<T> node, T data) {

        int depth = 0;

        while (!node.data.equals(data)) {
            if (data.compareTo(node.data) < 0) {
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
            if (node == null) {
                return 0;
            }
            depth++;
        }
        return depth;
    }

    /**
     * This method deletes the specific node passed in from the volunteer list
     *
     * @param data that wants to be deleted
     * @throws IOException
     */
    public void delete(T data) throws IOException {
        delete(data, root);
    }

    /**
     * This method removes an entire node from a subtree, which contains volunteerID, name, contact, and address
     *
     * @param data the item to remove
     * @param node the node that roots the subtree
     * @return the new root of the subtree
     */
    private Node<T> delete(T data, Node<T> node) {
        if (node == null)
            return node;
        int resultToInt = data.compareTo(node.getData());
        if (resultToInt < 0)
            node.leftChild = delete(data, node.getLeftChild());
        else if (resultToInt > 0)
            node.rightChild = delete(data, node.getRightChild());
        else if (node.leftChild != null && node.rightChild != null) {
            node.data = findMin(node.rightChild).data;
            node.rightChild = delete(node.data, node.rightChild);
        } else
            node = (node.leftChild != null) ? node.leftChild : node.rightChild;
        return node;
    }
}
