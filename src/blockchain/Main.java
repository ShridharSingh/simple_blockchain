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

        System.out.println("\nPress any key to continue...");
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
        System.out.println("-----------------------------------------------------------");


        /**
         * Compromised blockchain example
         * **/

        ArrayList properBlockchain = new ArrayList();
        ArrayList compromisedBlockchain = new ArrayList();
        properBlockchain.add(genesisBlock);
        properBlockchain.add(block2);
        compromisedBlockchain.add(genesisBlock);
        compromisedBlockchain.add(block2);


        System.out.println("\n3. Now we look at how the Blockchain reacts to interference");
        Scanner next = new Scanner(System.in);
        System.out.println("Press any key to continue...");
        next.next();

        System.out.println("\nExample: 5 bitcoin was sent to Ray by Bill, but Ray got a hold of the code and gave himself 50 bitcoin");
        System.out.println("\nScenario one: Proper transaction");

        //Proper transaction
        String[] proper_transaction = {"5 bitcoin was sent to Ray by Bill"};
        Block properBlock = new Block(block2.getBlockHash(), proper_transaction);
        properBlockchain.add(properBlock);
        System.out.println("\nProper Transaction " + properBlock.toString());

        //Compromised transaction
        System.out.println("-----------------------------------------------------------");
        System.out.println("\nScenario two: Compromised transaction");
        String[] compromised_transaction = {"50 bitcoin was sent to Ray by Bill"};
        Block compromisedBlock = new Block(block2.getBlockHash(), compromised_transaction);
        compromisedBlockchain.add(compromisedBlock);
        System.out.println("\nProper Transaction " + compromisedBlock.toString());

        System.out.println("-----------------------------------------------------------");


        System.out.println("\nNotice how the Block Hash's are different imagine this occurred earlier on in the blockchain..." +
                "\nPress any key to amend the blockchain");


        /**Adding new identical transactions to blockchain**/
        //Transaction 3
        String[] transaction3 = {"Nakamoto paid 50 bitcoin to Amazon, Lara received 7 bitcoin for her birthday"};
        Block propertransactionblock3 = new Block(properBlock.getBlockHash(), transaction3);
        properBlockchain.add(propertransactionblock3);
        Block compromisedtransactionblock3 = new Block(compromisedBlock.getBlockHash(), transaction3);
        compromisedBlockchain.add(compromisedtransactionblock3);

        //Transaction 4
        String[] transaction4 = {"Shivani sold 10 bitcoin to Tony, Max bight a tv for 7 bitcoin"};
        Block propertransactionblock4 = new Block(propertransactionblock3.getBlockHash(), transaction4);
        properBlockchain.add(propertransactionblock4);
        Block compromisedtransactionblock4 = new Block(compromisedtransactionblock3.getBlockHash(), transaction4);
        compromisedBlockchain.add(compromisedtransactionblock4);


        System.out.println("New transactions: \n3. Nakamoto paid 50 bitcoin to Amazon, Lara received 7 bitcoin for her birthday" +
                "\nShivani sold 10 bitcoin to Tony, Max bight a tv for 7 bitcoin");


        System.out.println("-----------------------------------------------------------");


        System.out.println("\nOriginal blockchain\t: " + properBlockchain.toString());
        System.out.println("\nCompromised blockchain\t: " + compromisedBlockchain.toString());

        System.out.println("\n-----------------------------------------------------------");
        System.out.println("\nEven though the rest of the transactions following the change are exactly the same, the hash codes differ" +
                "\ntherefore it is not possible to fool a blockchain!!!");

    }
}
