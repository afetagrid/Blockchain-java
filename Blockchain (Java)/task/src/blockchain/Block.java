package blockchain;

import blockchain.utils.MagicNumberUtil;
import blockchain.utils.StringUtil;

import java.util.Date;

public final class Block {
    private static Long numberOfBlocks = 0L;

    private final Long id;
    private final Long timeStamp;
    private final Long magicNumber;
    private final String previousHash;
    private final String hash;

    private final Long time;

    public Block(String previousHash, int numberOfZeros) {
        long start = System.currentTimeMillis();

        this.id = ++numberOfBlocks;
        this.timeStamp = new Date().getTime();
        this.previousHash = previousHash;

        String concatFields = concatFields();
        this.magicNumber = MagicNumberUtil.proofOfWork(concatFields, numberOfZeros);
        this.hash = StringUtil.applySha256(concatFields + magicNumber);

        long finish = System.currentTimeMillis();
        time = (finish - start) / 1000;
    }

    @Override
    public String toString() {
        return "Block:" + '\n' +
                "Id: " + id + '\n' +
                "Timestamp: " + timeStamp + '\n' +
                "Magic number: " + magicNumber + '\n' +
                "Hash of the previous block:\n" + previousHash + '\n' +
                "Hash of the block:\n" + hash + '\n' +
                "Block was generating for " + time + " seconds\n";
    }

    private String concatFields() {
        return id.toString() +
                timeStamp.toString() +
                previousHash;
    }

    public static Long getNumberOfBlocks() {
        return numberOfBlocks;
    }

    public Long getId() {
        return id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public Long getMagicNumber() {
        return magicNumber;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

}
