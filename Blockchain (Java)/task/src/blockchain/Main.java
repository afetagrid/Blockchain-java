package blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many zeros the hash must start with: ");
        int numberOfZeros = scanner.nextInt();
        System.out.println();

        Blockchain blockchain = new Blockchain(numberOfZeros);
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.newBlock();
        blockchain.printBlockchain();
    }
}
