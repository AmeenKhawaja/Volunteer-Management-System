package com.company;

import java.io.*;
import java.util.Scanner;

/*
 * COSC 2P03 Advanced Data Structures Assignment 2
 *
 * @author Ameen Khawaja - Student ID#: 6935688 - email: ak19nu@brocku.ca
 *
 * @version 2.0 2021/10/18 @ 10:00 PM
 */

/**
 * I chose to create this class and run all the main functions here because the volunteerManager class was getting too
 * messy, so to keep things a bit more readable I made this new class.
 */

public class Main {

    // initializing the volunteerManager class to access it

    volunteerManager<String> bst = new volunteerManager<>();

    public Main() throws IOException {
        createBST();
    }

    /**
     * This method createBST uses scanner to read the volunteer data file and store it as nodes into the binary
     * search tree. I stored the first line of the file (Volunteer ID, name Address, Contact) as a string variable
     * that can be accessed at anytime because otherwise it would be stored as a node, which would affect the output
     * of the search tree. After it stores all the data into the tree, it traverses them with preorder, inorder, and
     * postorder and then calculates the height, depth, and deletes specific nodes from the tree.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */

    private void createBST() throws FileNotFoundException, IOException {

        File file = new File("src/com/company/volunteer_data.txt");
        Scanner scan = new Scanner(file);

        String firstLineOfFile = scan.nextLine(); // reads the first line of the file which is the title and stores it

        if (firstLineOfFile.contains("VolunteerID")) {
            System.out.println(firstLineOfFile);
            bst.writeFile(firstLineOfFile);
        }

        while (scan.hasNextLine()) {
            String data = scan.nextLine();
            bst.insert(data);
        }
        bst.preOrder();
        bst.writeFile(firstLineOfFile);
        System.out.println(firstLineOfFile);

        bst.inOrder();
        System.out.println(firstLineOfFile);
        bst.writeFile(firstLineOfFile);

        bst.postOrder();

        bst.height();

        bst.depth();

        bst.depth2("092\tLaura\t45 Mill Street\t905-244-0086");    // the depth of 092
        bst.depth2("098\tPatrick\t126 Oxford Street\t905-099-9535"); // the depth of 098
        System.out.println("\n");
        bst.writeFile("\n");

        bst.writeFile(firstLineOfFile);
        System.out.println(firstLineOfFile);
        bst.delete("024\tSean\t909 Green Avenue\t905-232-5445");
        bst.delete("060\tDaniel\t125 Ottawa Street\t905-666-3290");
        bst.delete("071\tArthur\t36 York Road\t905-242-5643");
        bst.inOrder();
        bst.writeFile("Deleted 024, 060, and 071");
        System.out.println("Deleted 024, 060, and 071");
        bst.closeWriter();
    }

    public static void main(String[] args) throws IOException {
        Main assignTwo = new Main();
    }

}
