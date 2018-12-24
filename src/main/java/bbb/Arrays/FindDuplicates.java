package bbb.Arrays;

import java.util.*;

public class FindDuplicates {

    /*
        Time: O(N^2), Space: O(1)
        Quadratic Time, Constant Space
    */
    private static Set<Integer> findDupsApproach1(List<Integer> nums) {

        Set<Integer> dups = new HashSet<>();
        for(int i = 0; i < nums.size(); i++) {
            for(int j = 0; j < nums.size(); j++) {
                if(i != j && nums.get(i).intValue() == nums.get(j).intValue()) {
                    dups.add(nums.get(i));
                }
            }
        }

        return dups;
    }

    /*
        Time: O(N Log N), Space: O(1)
        Sort Time, Constant Space
    */
    private static Set<Integer> findDupsApproach2(List<Integer> nums) {
        Collections.sort(nums);
        Set<Integer> dups = new HashSet<>();
        for(int i = 0; i < nums.size() - 1; i++) {
            if(nums.get(i).intValue() == nums.get(i+1).intValue()) {
                dups.add(nums.get(i));
            }
        }
        return dups;
    }

    /*
        Time: O(N), Space: O(2*N)
        Linear Time, Linear Space
    */
    private static Set<Integer> findDupsApproach3(List<Integer> nums) {
        Set<Integer> singles = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        for(int num : nums) {
            if(singles.contains(num)) {
                dups.add(num);
            } else {
                singles.add(num);
            }
        }
        return dups;
    }

    /*
        Time: O(N), Space: O(1)
        Linear Time, Constant Space

        1 <= X <= len(array)
        0 < X < len(array) - 1
    */
    private static Set<Integer> findDupsApproach4(List<Integer> nums) {

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.size(); i++) {
            int index = Math.abs(nums.get(i)) - 1;
            if(nums.get(index) < 0) {
                // already processed index, candidate for result
                set.add(Math.abs(nums.get(i)));
            } else {
                // -Ve the value at the index
                nums.set(index, nums.get(index).intValue() * -1);
            }
        }
        // reverting back origin list
        for(Integer num : nums) {
            if(num.intValue() < 0) {
                num = num.intValue() * -1;
            }
        }
        return set;
    }

    private static void evaluate(List<Integer> dups) {
        System.out.println(dups.toString());
        Set<Integer> result1 = findDupsApproach1(dups);
        System.out.println("Approach1   : " + result1.toString());
        Set<Integer> result2 = findDupsApproach2(dups);
        System.out.println("Approach2   : " + result2.toString());
        Set<Integer> result3 = findDupsApproach3(dups);
        System.out.println("Approach3   : " + result3.toString());
        Set<Integer> result4 = findDupsApproach4(dups);
        System.out.println("Approach4   : " + result4.toString());
    }

    public static void main(String[] args) {
        List<Integer> dups = Arrays.asList(new Integer[]{1, 2, 2, 5, 5});
        evaluate(dups);
    }
}
