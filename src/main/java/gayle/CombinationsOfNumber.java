package gayle;


import java.util.ArrayList;
import java.util.List;

public class CombinationsOfNumber {

    static void printCombinations(int targetLeft) {
        printCombinations(1, targetLeft, new ArrayList<>());
    }

    static void printCombinations(int startNumber, int targetLeft, List<Integer> listSoFar) {

        if(targetLeft == 0) {
            System.out.println(listSoFar.toString());
            return;
        }

        if(targetLeft < 0) {
            return;
        }

        for(int i = startNumber; i <= targetLeft; i++) {
            List<Integer> list = new ArrayList<>(listSoFar);
            list.add(i);
            printCombinations(i, targetLeft - i, list);
        }
    }

    public static void main(String[] args) {
        printCombinations(1, 4, new ArrayList<>());
    }
}
