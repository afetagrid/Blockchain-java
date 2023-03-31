package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Blockchain blockchain = new Blockchain();

        int numberOfMiners = 5;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfMiners);
        for (int i = 1; i <= numberOfMiners; i++) {
            executor.execute(new Miner(i,"\nMessage no. " + i, blockchain));
        }
        executor.shutdown();
        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
        blockchain.printBlockchain();
    }
}
