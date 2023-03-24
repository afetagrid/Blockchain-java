package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blockchain {
    private final List<Block> blocks;

    public Blockchain() {
        this.blocks = new ArrayList<>();
    }

    public void newBlock() {
        if (blocks.size() == 0) {
            blocks.add(new Block("0"));
            return;
        }
        String previousHash = blocks.get(blocks.size() - 1).getHash();
        blocks.add(new Block(previousHash));
    }

    public boolean validateBlockchain() {
        for (int i = 1; i < blocks.size(); i++) {
            if (!Objects.equals(blocks.get(i).getPreviousHash(), blocks.get(i - 1).getHash())) {
                return false;
            }
        }
        return true;
    }

    public void printBlockchain() {
        blocks.forEach(System.out::println);
    }
}
