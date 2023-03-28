package blockchain;

import blockchain.utils.StringUtil;

import java.util.Date;
import java.util.Random;

public class Miner implements Runnable {
    private final Integer id;
    private final Blockchain blockchain;

    public Miner(Integer id, Blockchain blockchain) {
        this.id = id;
        this.blockchain = blockchain;
    }

    private Long getProofOfWork(String input, int numberOfZeros) {
        StringBuilder zeros = new StringBuilder();
        for (int i = 0; i < numberOfZeros; i++) {
            zeros.append("0");
        }

        Random random = new Random();
        long magicNumber = random.nextLong();
        String generatedHash = StringUtil.applySha256(input + magicNumber);
        while (!(generatedHash.startsWith(zeros.toString()) && !generatedHash.startsWith(zeros.toString() + '0'))) {
            magicNumber = random.nextLong();
            generatedHash = StringUtil.applySha256(input + magicNumber);
        }
        return magicNumber;
    }

    @Override
    public void run() {
        boolean success = false;

        while (!success) {
            Block lastBlock = blockchain.getLastBlock();

            Long myBlockId = lastBlock.getId() + 1;
            Long myBlockTimestamp = new Date().getTime();
            String myBlockPreviousHash = lastBlock.getHash();

            String myBlockFields = id.toString() + myBlockId.toString() + myBlockTimestamp.toString() + myBlockPreviousHash;
            long start = System.currentTimeMillis();
            Long myMagicNumber = getProofOfWork(myBlockFields, blockchain.getNumberOfZeros());
            long finish = System.currentTimeMillis();
            long time = (finish - start) / 1000;

            String myBlockHash = StringUtil.applySha256(myBlockFields + myMagicNumber);

            Block myBlock = new Block(id, myBlockId, myBlockTimestamp, myBlockPreviousHash, myMagicNumber, myBlockHash);
            success = blockchain.addNewBlock(myBlock, time);
        }
    }
}