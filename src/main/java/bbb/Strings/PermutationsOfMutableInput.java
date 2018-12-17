package bbb.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsOfMutableInput {

    public static class CharArrayPermutation {

        private static List<char[]> permutations(char[] charArray) {
            List<char[]> result = new ArrayList<>();
            permutations(charArray, 0, result);
            return result;
        }

        private static void permutations(char[] charArray, int start, List<char[]> result) {
            System.out.println("New Start Index : " + start);
            if(start == charArray.length) {
                System.out.println(Arrays.toString(charArray));
                result.add(charArray.clone());
                System.out.println("End Start Index : " + start);
                return;
            }

            for(int i = start; i < charArray.length; i++) {
                swap(charArray, start, i);
                permutations(charArray, start+1, result);
                swap(charArray, start, i);
            }
            System.out.println("End Start Index : " + start);
        }

        private static void swap(char[] charArray, int i, int j) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }
    }

    public static class IntArrayPermutation {

        private static List<int[]> permutations(int[] intArray) {
            List<int[]> result = new ArrayList<>();
            permutations(intArray, 0, result);
            return result;
        }

        private static void permutations(int[] intArray, int start, List<int[]> result) {
            System.out.println("New Start Index : " + start);
            if(start == intArray.length) {
                System.out.println(Arrays.toString(intArray));
                result.add(intArray.clone());
                System.out.println("End Start Index : " + start);
                return;
            }

            for(int i = start; i < intArray.length; i++) {
                swap(intArray, start, i);
                permutations(intArray, start+1, result);
                swap(intArray, start, i);
            }
            System.out.println("End Start Index : " + start);
        }

        private static void swap(int[] intArray, int i, int j) {
            int temp = intArray[i];
            intArray[i] = intArray[j];
            intArray[j] = temp;
        }
    }

    public static void main(String[] args) {
        char[] charArray = {'a', 'b', 'c'};
        new CharArrayPermutation().permutations(charArray);
        System.out.println("**********************");
        int[] intArray = {1, 2, 3};
        new IntArrayPermutation().permutations(intArray);
    }


}
