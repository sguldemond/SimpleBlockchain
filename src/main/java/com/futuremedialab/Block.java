package com.futuremedialab;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by stasiuz on 11/10/2017.
 */
public class Block {

    private int index;
    private Date timestamp;
    private int previousHash;
    private String[] transactions;

    private int hash;

    public Block(int index, Date timestamp, String[] transactions, int previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.previousHash = previousHash;

        this.hash = hashBlock();
    }

    /**
     * Hash content of block
     * @return hash of content
     */
    private int hashBlock() {
        Object[] content = {index, timestamp, Arrays.hashCode(transactions), previousHash};
        return Arrays.hashCode(content);
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

    public Date getTimestamp() {
        return timestamp;
    }

    public int getIndex() {
        return index;
    }
}
