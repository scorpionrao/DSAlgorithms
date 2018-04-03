package mit;

import java.util.Arrays;

public class ValidTree {
	public static boolean validTree(int n, int[][] edges) {
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        
        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);
            
            if (x == y) { return false; }
            
            // union
            nums[x] = y;
        }
        return edges.length == n - 1;
    }
    
    static int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }
    
    public static void main(String[] args) {
    	int n = 5;
    	int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
    	System.out.println(validTree(n, edges));
    }
}
