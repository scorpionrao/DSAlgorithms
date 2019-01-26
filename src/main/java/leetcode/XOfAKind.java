package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class XOfAKind {

    public static boolean hasGroupsSizeX1(int[] deck) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int d : deck) {
            freq.put(d, freq.getOrDefault(d, 0) + 1);
        }
        for(int i = 2; i < 100; i++) {
            if(deck.length % i == 0) {
                int count = 0;
                for(int value : freq.values()) {
                    if(value % i == 0) {
                        count++;
                    } else {
                        break;
                    }
                }
                if(count == freq.size()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasGroupsSizeX2(int[] deck) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int d : deck) {
            freq.put(d, freq.getOrDefault(d, 0) + 1);
        }

        int gcd = -1;
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if(gcd == -1) {
                gcd = entry.getValue();
            } else {
                gcd = gcd(gcd, entry.getValue());
            }
        }
        return gcd >= 2;
    }

    private static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }

    private static void evaluate(int[] decks, boolean expected) {
        System.out.println(Arrays.toString(decks));
        System.out.println("Expected : " + expected);
        boolean actual1 = hasGroupsSizeX1(decks);
        System.out.println("App1 - " + actual1);
        boolean actual2 = hasGroupsSizeX2(decks);
        System.out.println("App2 - " + actual2);
    }

    public static void main(String[] args) {
        evaluate(new int[]{1, 2, 3, 4, 4, 3, 2, 1}, true);
        evaluate(new int[]{1, 1, 1, 2, 2, 2, 3, 3}, false);
        evaluate(new int[]{1}, false);
        evaluate(new int[]{1, 1}, true);
        evaluate(new int[]{1, 1, 2, 2, 2, 2}, true);
    }
}
