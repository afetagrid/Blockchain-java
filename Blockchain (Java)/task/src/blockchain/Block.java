package blockchain;

import blockchain.utils.StringUtil;

import java.util.Date;

public final class Block {
    private static Long numberOfBlocks = 0L;

    private final Long id;
    private final Long timeStamp;
    private final String hash;
    private final String previousHash;

    public Block(String previousHash) {
        this.id = ++numberOfBlocks;
        this.timeStamp = new Date().getTime();
        this.hash = StringUtil.applySha256(concatFields());
        this.previousHash = previousHash;
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

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + id + '\n' +
                "Timestamp: " + timeStamp + '\n' +
                "Hash of the previous block:\n" + previousHash + '\n' +
                "Hash of the block:\n" + hash + '\n';
    }
}
