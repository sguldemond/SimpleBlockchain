package com.futuremedialab;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {

        String[] genesisTransactions = {"null"};
        Block genesisBlock = new Block(0, genesisTransactions);
        blockchain.add(genesisBlock);

        String[] block1Transactions = {"Block 1 transactions"};
        Block block1 = new Block(genesisBlock.getHash(), block1Transactions);
        blockchain.add(block1);

        String[] block2Transactions = {"Block 2 transactions"};
        Block block2 = new Block(block1.getHash(), block2Transactions);
        blockchain.add(block2);

        String[] block3Transactions = {"Block 3 transactions"};
        Block block3 = new Block(block2.getHash(), block3Transactions);
        blockchain.add(block3);

        String[] block4Transactions = {"Block 4 transactions"};
        Block block4 = new Block(block3.getHash(), block4Transactions);
        blockchain.add(block4);


        int position = 0;
        for(Block block : blockchain) {
            System.out.println("Hash of block " + position + ":");
            System.out.println(block.getHash());
            position++;
        }

        block3.editTransactions();

        Block block = validate(blockchain);
        if(block == null) {
            System.out.println("BLOCKCHAIN IS VALID");
        } else {
            System.out.println("BLOCK: " + block.getTransactions() + " IS INVALID");
        }
    }

    /*
    validates blockchain
     */

    private static Block validate(List<Block> blockchain) {
        int chainSize = blockchain.size();

        Block unvalidBlock = null;
        for(int i = chainSize-1; i > 0; i--) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i-1);
            if(currentBlock.getPreviousHash() != previousBlock.getHash()) {
                unvalidBlock = currentBlock;
                break;
            }
        }

        return unvalidBlock;
    }
}
