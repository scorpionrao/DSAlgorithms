package codecamp;

import java.util.Random;

public class Rand5ToRand7 {

    private static int rand5() {
        return new Random().nextInt(5);
    }

    private static int rand7() {
        int randomNum = 5 * rand5() + rand5();
        if(randomNum <= 20) {
            return (randomNum % 7) + 1;
        }
        return rand7();
    }

    public static void main(String[] args) {
        int iterations = 50000000;
        int expectedSum = 0;
        int actualSum = 0;
        for(int i = 0; i < iterations; i++) {
            expectedSum = expectedSum + new Random().nextInt(7) + 1;
            actualSum = actualSum + rand7();
        }
        System.out.println("Expected Sum : " + expectedSum);
        System.out.println("Actual Sum : " + actualSum);
        System.out.println("Epsilon : " + Math.abs(expectedSum - actualSum) / (double) iterations);
    }
}
