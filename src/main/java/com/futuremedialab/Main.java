package com.futuremedialab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Block genesisBlock = createGenesisBlock();

        System.out.println(genesisBlock.getTimestamp());

        List<Block> blockchain = new ArrayList<>();
        blockchain.add(genesisBlock);

        Block previousBlock = blockchain.get(0);

        for(int i = 0; i < 20; i++) {
            Block blockToAdd = newBlock(previousBlock);
            blockchain.add(blockToAdd);
            previousBlock = blockToAdd;

            System.out.format("Block #%s has been added to the blockchain \n", blockToAdd.getIndex());
            System.out.format("Hash: %s \n\n", blockToAdd.getHash());
        }
    }

    public static Block createGenesisBlock() {
        return new Block(0, new Date(), new String[]{"Genesis block"}, 0);
    }

    public static Block newBlock(Block lastBlock) {
        int newIndex = lastBlock.getIndex()+1;
        return new Block(newIndex, new Date(), new String[]{"This is block number " + newIndex}, lastBlock.getHash());
    }
}
