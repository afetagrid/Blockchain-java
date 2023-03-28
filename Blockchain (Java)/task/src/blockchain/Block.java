package blockchain;

public final class Block {

    private final Integer minerId;
    private final Long id;
    private final Long timeStamp;
    private final String previousHash;
    private final Long magicNumber;
    private final String hash;

    private Long generatedTime;
    private String numberOfZerosStatus;

    public Block(Integer minerId, Long id, Long timeStamp, String previousHash, Long magicNumber, String hash) {
        this.minerId = minerId;
        this.id = id;
        this.timeStamp = timeStamp;
        this.previousHash = previousHash;
        this.magicNumber = magicNumber;
        this.hash = hash;
    }

    public Block(Block b) {
        this.minerId = b.minerId;
        this.id = b.id;
        this.timeStamp = b.timeStamp;
        this.previousHash = b.previousHash;
        this.magicNumber = b.magicNumber;
        this.hash = b.hash;
    }

    @Override
    public String toString() {
        return "Block:" + '\n' +
                "Created by miner # " + minerId + '\n' +
                "Id: " + id + '\n' +
                "Timestamp: " + timeStamp + '\n' +
                "Magic number: " + magicNumber + '\n' +
                "Hash of the previous block:\n" + previousHash + '\n' +
                "Hash of the block:\n" + hash + '\n' +
                "Block was generating for " + generatedTime + " seconds\n" +
                numberOfZerosStatus + '\n';
    }

    public Integer getMinerId() {
        return minerId;
    }

    public Long getId() {
        return id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public Long getMagicNumber() {
        return magicNumber;
    }

    public String getHash() {
        return hash;
    }

    public void setGeneratedTime(Long generatedTime) {
        this.generatedTime = generatedTime;
    }

    public void setNumberOfZerosStatus(String numberOfZerosStatus) {
        this.numberOfZerosStatus = numberOfZerosStatus;
    }
}
