package leetcode;

import java.util.*;

public class ZerosAndOnesWildCard {

    /*
        Given a string of 0s, 1s, and ?s (wildcards), generate all 0-1 strings that match this pattern.
        Eg: 1?00?101 -> [10000101, 10001101, 11000101, 11001101].
     */

    public List<String> print(String regex) {

        if(regex == null) {
            throw new NullPointerException("Input is null");
        }

        char[] inputArray = regex.toCharArray();
        List<String> output = new ArrayList<>();
        print(inputArray, 0, output);
        System.out.println(output.toString());
        return output;
    }

    public void printVoidReturn(String regex) {
        List<String> output = new ArrayList<>();
        char[] charArray = regex.toCharArray();
        print(charArray, 0, output);
        System.out.println(output.toString());
    }

    /*
        Time Complexity: O(2 ^ N)

        Each index can multiply into 2.
     */
    private void print(char[] charArray, int currentIndex, List<String> output) {

        if(currentIndex == charArray.length) {
            output.add(String.valueOf(charArray));
            return;
        }

        if(charArray[currentIndex] == '?') {
            charArray[currentIndex] = '0';
            print(charArray, currentIndex+1, output);
            charArray[currentIndex] = '1';
            print(charArray, currentIndex+1, output);
        } else {
            print(charArray, currentIndex+1, output);
        }
    }

    public static void main(String[] args) {
        ZerosAndOnesWildCard zerosAndOnesWildCard = new ZerosAndOnesWildCard();
        zerosAndOnesWildCard.print("1?1");
    }
}