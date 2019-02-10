package leetcode;

public class SingleNumberII {

    public static int singleNumberAmongThreeSets1(int[] nums, int times) {
        int result = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    sum++;
                }
            }
            sum = sum % times;
            if(sum == 1) {
                result = result | (sum << i);
            }
        }
        return result;
    }

    private static void evaluate(int[] nums, int times) {
        int result1 = singleNumberAmongThreeSets1(nums, times);
        System.out.println("Approach1 : " + result1);
    }

    public static void main(String[] args) {
        evaluate(new int[]{2, 2, 3, 2, 2}, 4);
        evaluate(new int[]{5, 25, 5, 25, 5, 25, 5, 25, 99}, 4);
    }
}