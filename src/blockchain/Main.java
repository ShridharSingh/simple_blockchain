package blockchain;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        /**
         * Interface design
         * **/

        new Interface();

        System.out.println("\n\nWELCOME to the Blockchain");
        System.out.println("In this demo we will go over what the blockchain looks like, how you can add to the blockchain, and what were to happen if the blockchain was compromised");

        System.out.println("\nPress y to continue...");
        Scanner breaks = new Scanner(System.in);
        breaks.next();

        System.out.println("\n1. What does the blockchain look like:");


        /**
         * Building the Blockchain
         * **/

        ArrayList<Block> blockchain = new ArrayList<>();

        // Existing Block transactions
        String[] genesisTransactions = {"Satoshi sent Nakamoto 100 bitcoin", "Nakamoto sent 5 bitcoin to Percy"};
        String[] block2_transaction = {"Shridhar received 200 bitcoin from paypal", "Jeff paid Shridhar 30 more bitcoin"};
        String[] block3_transaction = {"Dan paid 2 bitcoin for a car", "Sarah sold her house for 40 bitcoin"};

        // Existing Blocks
        Block genesisBlock = new Block(0, genesisTransactions);
        Block block2 = new Block(genesisBlock.getBlockHash(), block2_transaction);
        Block block3 = new Block(block2.getBlockHash(), block3_transaction);
        blockchain.add(genesisBlock);
        blockchain.add(block2);
        blockchain.add(block3);


        // String[] for hashes
        ArrayList hashes = new ArrayList();
        hashes.add(genesisBlock.getBlockHash());
        hashes.add(block2.getBlockHash());
        hashes.add(block3.getBlockHash());


        System.out.println("SIMPLE BLOCKCHAIN OUTPUT");

        System.out.println("-----------------------------------------------------------");
        System.out.println("First block " + genesisBlock.toString());
        System.out.println("Second block " + block2.toString());
        System.out.println("Third block " + block3.toString());
        System.out.println("\nEntire blockchain " + blockchain.toString());


        System.out.println("-----------------------------------------------------------");
        System.out.println("\n2. Let's try creating a new block in this blockchain");

        /**
         * User creates new transaction for the blockchain
         * **/

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a new transaction for the blockchain: ");
        String[] block4_transaction = {"" + scan.nextLine() + ""};

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add this transaction to the blockchain y/n?");
        Block block4;

        if (scanner.nextLine().equalsIgnoreCase("y")) {
            block4 = new Block(block3.getBlockHash(), block4_transaction);
            blockchain.add(block4);
            System.out.println("\nNew values");
            System.out.println("-----------------------------------------------------------");
            System.out.println("Fourth block " + block4.toString());
            System.out.println("\nAnd the updated blockchain is now: \n" + blockchain.toString());

        }else if (scanner.nextLine().equalsIgnoreCase("n")){
            return;
        }



        /**
         * Compromised blockchain example
         * **/

        System.out.println("\n3. Now we look at how the Blockchain reacts to interference");
        System.out.println("\nExample: 5 bitcoin was sent to Ray by Bill, but Ray got a hold of the code and gave himself 50 bitcoin");
        System.out.println("\nScenario one: Proper transaction");

        //Proper transaction
        String[] proper_transaction = {"5 bitcoin was sent to Ray by Bill"};
        Block properBlock = new Block(0, proper_transaction);
        blockchain.add(properBlock);
        System.out.println("\nProper Transaction " + properBlock.toString());

        //Compromised transaction
        System.out.println("\nScenario two: Proper transaction");
        String[] compromised_transaction = {"50 bitcoin was sent to Ray by Bill"};
        Block compromisedBlock = new Block(0, compromised_transaction);
        blockchain.add(properBlock);
        System.out.println("\nProper Transaction " + compromisedBlock.toString());

        System.out.println("\nNotice how the Block Hash's are different imagine this occurred earlier on in the blockchain, the hash codes will not match up as shown below:");


        System.out.println("Original blockchain \t[" + genesisBlock.getBlockHash() +", "+ block2.getBlockHash() +", "+ block3.getBlockHash() + "]");
        int badhash = block2.getBlockHash() - 15650;
        System.out.println("Compromised blockchain\t[" + genesisBlock.getBlockHash() +", "+ badhash +", "+ block3.getBlockHash() + "]");


    }
}
