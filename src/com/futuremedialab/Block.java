package com.futuremedialab;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by stasiuz on 11/10/2017.
 */
public class Block {

    private int index;
    private Date timestamp;
    private int hash;
    private int previousHash;
    private String[] transactions;

    public Block(int previousHash, String[] transactions) {
        this.previousHash = previousHash;
        this.transactions = transactions;

        hashBlock();
    }

    private void hashBlock() {
        Object[] content = {Arrays.hashCode(transactions), previousHash};
        this.hash = Arrays.hashCode(content);
    }

    public void editTransactions() {
        transactions = new String[]{"Edited transaction"};
        hashBlock();
    }


    public int getHash() {
        return hash;
    }

    public String[] getTransactions() {
        return transactions;
    }

    public int getPreviousHash() {
        return previousHash;
    }
}
