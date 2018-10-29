package live;

import java.util.List;
import java.util.ArrayList;

public class CellState {

    public static List<Integer> cellCompete(int[] states, int days){
        List<Integer> previousDay = new ArrayList<>(states.length);
        for(int state : states) {
            previousDay.add(state);
        }
        List<Integer> today = null;
        for(int day = 0; day < days; day++) {
            today = new ArrayList<>(states.length);
            for(int cell = 0; cell < previousDay.size(); cell++) {
                int leftValue;
                int rightValue;
                if(cell == 0) {
                    leftValue = 0;
                    rightValue = previousDay.get(cell+1);
                } else if(cell == previousDay.size() - 1) {
                    leftValue = previousDay.get(cell-1);
                    rightValue = 0;
                } else {
                    leftValue = previousDay.get(cell-1);
                    rightValue = previousDay.get(cell+1);
                }
                today.add(calculateState(leftValue, rightValue));
            }
            previousDay = new ArrayList<>(today);
        }
        return today;
    }

    private static int calculateState(int left, int right) {
        return (left == right) ? 0 : 1;
    }

    public static void main(String[] args) {
        int[] input1 = {1, 0, 0, 0, 0, 1, 0, 0};
        List<Integer> result1 = cellCompete(input1, 1);
        System.out.println("Expected: \t[0, 1, 0, 0, 1, 0, 1, 0]");
        System.out.println("Actual: \t" + result1.toString());

        int[] input2 = {1, 1, 1, 0, 1, 1, 1, 1};
        List<Integer> result2 = cellCompete(input2, 2);
        System.out.println("Expected: \t[0, 0, 0, 0, 0, 1, 1, 0]");
        System.out.println("Actual: \t" + result2.toString());
    }
}
