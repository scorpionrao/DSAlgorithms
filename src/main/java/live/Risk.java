package live;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by manushaonly on 9/14/18.
 */
public class Risk {

    // add 12
    // get 1
    // add 34
    // get 2
    // get 3
    // get 4
    /**
     * Interface to represent a data data container.
     */
    public interface Jarvis {
        public void addData(final String input);

        public char getData();
    }

    // initial requirements - no restrictions
/*
    Solving the example given:
    data = 12
    get - 1 ==> data = 2
    add 34 ==> data = 234
    get - 2 ==> data = 34
    get - 3 ==> data = 4
    get - 4 ==> data = empty
*/
    public class Solution implements Jarvis {

        Queue<Character> queue = new LinkedList();

        public synchronized void addData(final String input) {
            for(int i = 0; i < input.length(); i++) {
                queue.offer(input.charAt(i));
            }
        }

        public synchronized char getData() {
            return this.queue.poll();
        }
    }

    /**
     * Write a function to return true if input is a valid palindrome.
     * A palindrome is defined as a string that reads the same from either directions.
     * Example : [Radar, Top spot]
     * @param input
     * @return
     */
 /*
     Important example - Top spot
     Brute Force - Traverse through the entire string and eliminate empty spaces - O(N) Time and O(N) space
                 - apply the regular palindrome check - O(N)

     Optimization - Try to complete both the steps in single O(N)

     Even length - d e e d
     Odd length - ra da r

     Time complexity - O(N)

 */
    public boolean isValidPalindrome(final String input) {

        int leftPointer = 0;
        int rightPointer = input.length() - 1;

        while(leftPointer < rightPointer) {
            while(input.charAt(leftPointer) == ' ') {
                leftPointer++;
            }

            while(input.charAt(rightPointer) == ' ') {
                rightPointer--;
            }

            if(input.charAt(leftPointer) != input.charAt(rightPointer)) {
                return false;
            }
            leftPointer++;
            rightPointer--;
        }
        return true;
    }

    public static void main(String[] args) {
        Risk risk = new Risk();
        System.out.println("d e e d : " + risk.isValidPalindrome("d e e d"));
    }



}
