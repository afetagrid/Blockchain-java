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
            executor.execute(new Miner(i, blockchain));
        }
        executor.shutdown();
        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
        blockchain.printBlockchain();
    }
}
// Creez un blockchain care sa aiba N = 0 (numarul de 0 uri din hash)
// Creez mai multe thread uri care sa "mineze"

// Un miner va incerca sa creeze un block nou
// Minerul primeste de la blockchain id ul si hash ul ultimului block din blockchain
// Minerul defineste un timestamp propriu
// Minerul incearca gasirea unei valori ("magic number") care prin hash uirea cu id, timestamp si previousHash
//sa dea un hash nou care sa aiba exact N zero uri
// Daca block ul este validat atunci el este adaugat in blockchain si N ul este updatat (prin urmare
//primul thread care calculeazaa un magic number pentru ultimul block din blockchain va fi validat,
//iar toate celelalte thread uri, cand vor termina de calculat, nu vor fi validate si nu vor fi adadugate.
