package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jack Croghan
 */
public class main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //Gets the file name
        Scanner inputScnr = new Scanner(System.in);
        System.out.print("Please enter filename to decode: ");
        String fileName = inputScnr.nextLine();
        inputScnr.close();

        //Reads the encodedString and the message from the given file
        File file = new File(fileName);
        Scanner fileScnr = new Scanner(file);
        String encodedString = fileScnr.nextLine();
        String message = fileScnr.nextLine();

        if (fileScnr.hasNext())
        {
            encodedString = encodedString + "\n" + message;
            message = fileScnr.nextLine();
        }

        fileScnr.close();


        //Constructs a new MsgTree
        String code = "";
        MsgTree root = new MsgTree(encodedString);

        //Prints the code table
        System.out.println("character  code\n-------------------------");
        MsgTree.printCodes(root, code);

        //Prints the decoded message
        System.out.println("\nMessage:");
        MsgTree.decode(root, message);

        double avgBits = (double) message.length() / MsgTree.totalChar;
        double spaceSavings = (1.0 - (avgBits / 16.0)) * 100.0;

        //Prints the extra credit statistics
        System.out.println("\n\nStatistics: ");
        System.out.print("Avg bits/char:       ");
        System.out.printf("%.2f", avgBits);
        System.out.println("\nTotal characters:    " + MsgTree.totalChar);
        System.out.print("Space savings:       ");
        System.out.printf("%.2f", spaceSavings);
        System.out.print("%");
    }
}
