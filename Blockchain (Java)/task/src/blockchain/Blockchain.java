package blockchain;

import blockchain.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Blockchain {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private final List<Block> blocks;
    private int numberOfZeros;

    public Blockchain() {
        this.blocks = new ArrayList<>();
        this.numberOfZeros = 0;
    }

    private boolean validateBlockchain(Block newBlock) {
        if (blocks.size() == 0) {
            if (!StringUtil.hasNumberOfZeros(newBlock.getHash(), numberOfZeros)) {
                return false;
            }
        } else {
            if (!Objects.equals(blocks.get(blocks.size() - 1).getHash(), newBlock.getPreviousHash()) ||
                    !StringUtil.hasNumberOfZeros(newBlock.getHash(), numberOfZeros)) {
                return false;
            }
        }
        return true;
    }

    public Block getLastBlock() {
        readLock.lock();
        if (blocks.size() == 0) {
            readLock.unlock();
            return new Block(-1, 0L, -1L, "0", -1L, "0");
        }
        Block lastBlock = blocks.get(blocks.size() - 1);
        readLock.unlock();
        return new Block(lastBlock);
    }

    public int getNumberOfZeros() {
        return numberOfZeros;
    }

    public boolean addNewBlock(Block newBlock, long timeSpent) {
        writeLock.lock();
        if (validateBlockchain(newBlock)) {
            blocks.add(new Block(newBlock));
            blocks.get(blocks.size() - 1).setGeneratedTime(timeSpent);
            if (timeSpent < 10) {
                numberOfZeros++;
                blocks.get(blocks.size() - 1).setNumberOfZerosStatus("N was increased to " + numberOfZeros);
            } else if (timeSpent < 60) {
                blocks.get(blocks.size() - 1).setNumberOfZerosStatus("N stays the same");
            } else {
                numberOfZeros--;
                blocks.get(blocks.size() - 1).setNumberOfZerosStatus("N was decreased to " + numberOfZeros);
            }
            writeLock.unlock();
            return true;
        }
        writeLock.unlock();
        return false;
    }

    public void printBlockchain() {
        blocks.forEach(System.out::println);
    }
}
