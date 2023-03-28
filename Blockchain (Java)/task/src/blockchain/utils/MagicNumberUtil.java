package blockchain.utils;

import java.util.Random;

public class MagicNumberUtil {

    public static long proofOfWork(String input, int numberOfZeros) {
        Random random = new Random();
        long magicNumber = random.nextLong();
        StringBuilder zeros = new StringBuilder();
        for (int i = 0; i < numberOfZeros; i++) {
            zeros.append("0");
        }
        while (!StringUtil.applySha256(input + magicNumber).startsWith(zeros.toString())) {
            magicNumber = random.nextLong();
        }
        return magicNumber;
    }
}
