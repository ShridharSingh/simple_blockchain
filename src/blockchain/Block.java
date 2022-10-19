package blockchain;

import java.util.Arrays;

public class Block {
    private int previousHash;
    private String[] transactions;

    private int blockHash;

    public Block(int previousHash, String[] transactions){
        this.previousHash = previousHash;
        this.transactions = transactions;

        Object[] contents = {Arrays.hashCode(transactions), previousHash};
        this.blockHash = Arrays.hashCode(contents);
    }

    public int getPreviousHash() {
        return previousHash;
    }

    public String[] getTransaction(){
        return transactions;
    }

    public int getBlockHash() {
        return blockHash;
    }


    @Override
    public String toString(){
        return "Block [Transactions = " + Arrays.toString(transactions) + ", Previous Block hash = " + previousHash
                + ", Block hash = " + blockHash + "]\n";
    }
}
