package blockchain;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.printBlockchain();
    }
}
