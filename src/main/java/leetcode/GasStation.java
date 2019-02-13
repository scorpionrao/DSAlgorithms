package leetcode;

import java.util.Arrays;

public class GasStation {

    public static int canCompleteCircuit(int[] gallons, int[] cost) {

        for(int start = 0; start < gallons.length; start++) {
            int tank = gallons[start];
            int reached = start;
            for(; reached < gallons.length + start; reached++) {
                tank = tank - cost[reached % gallons.length];
                if(tank < 0) {
                    break;
                }
                tank = tank + gallons[(reached + 1) % gallons.length];
            }
            if(reached == gallons.length + start) {
                return start;
            }
        }
        return -1;
    }

    public static void evaluate(int[] gallons, int[] cost) {
        System.out.println("Gas in Stations : " + Arrays.toString(gallons));
        System.out.println("Cost to next Stations : " + Arrays.toString(cost));
        int result = canCompleteCircuit(gallons, cost);
        System.out.println("Result = " + result);
    }

    public static void main(String[] args) {
        int[] gallons1  = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        evaluate(gallons1, cost1);

        int[] gallons2  = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        evaluate(gallons2, cost2);

    }
}
