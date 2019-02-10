package codecamp;

import java.util.*;

public class PowerSet {

    private static List<List<Integer>> generatePowerSet(List<Integer> input) {
        List<List<Integer>> result = new ArrayList<>();
        int powerSetSize = (int) Math.pow(2, input.size());
        for(int i = 0; i < powerSetSize; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < input.size(); j++) {
                if((i & (1 << j)) > 0) {
                    list.add(input.get(j));
                }
            }
            result.add(list);
        }
        return result;
    }

    private static void evaluate(List<Integer> input) {
        System.out.println("Input : " + input.toString());
        List<List<Integer>> result = generatePowerSet(input);
        for(List<Integer> list : result) {
            System.out.println(list.toString());
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        evaluate(list);
    }
}
