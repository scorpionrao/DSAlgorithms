package live;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cube {

    public static class IndexPair {
        int first;
        int last;
        IndexPair(int first, int last) {
            this.first = first;
            this.last = last;
        }
    }

    public static class Solution {
        /*
            Time - O(N^4)
            Space - O(1)
        */
        public void cubeNaive(int max) {
            int count = 0;
            for(int a = 0; a < max; a++) {
                for(int b = 0; b < max; b++) {
                    for(int c = 0; c < max; c++) {
                        for(int d = 0; d < max; d++) {
                            if(Math.pow(a,3) + Math.pow(b,3) == Math.pow(c,3) + Math.pow(d,3)) {
                                ++count;
                                //System.out.println(String.format("%d %d %d %d", a, b, c, d));
                            }
                        }
                    }
                }
            }
            System.out.println("Total Count:" + count);
        }

        /*
            Time - O(N^3)
            Space - O(1)
        */
        public void cubeOptimizedLevel1(int max) {
            int count = 0;
            for(int a = 0; a < max; a++) {
                for(int b = 0; b < max; b++) {
                    for(int c = 0; c < max; c++) {
                        double remainder = Math.cbrt(Math.pow(a,3) + Math.pow(b,3) - Math.pow(c,3));
                        if(remainder == Math.floor(remainder)) {
                            ++count;
                            //System.out.println(String.format("%d %d %d %d", a, b, c, d));
                        }
                    }
                }
            }
            System.out.println("Total Count:" + count);
        }

        /*
            Time - O(N^2)
            Space - O(N^2)
        */
        public void cubeOptimizedLevel2(int max) {
            int count = 0;
            Map<Double, List<IndexPair>> map = new HashMap<>();
            for(int a = 0; a < max; a++) {
                for (int b = 0; b < max; b++) {
                    double key = Math.pow(a, 3) + Math.pow(b, 3);
                    IndexPair indexPair = new IndexPair(a, b);
                    if (map.get(key) != null) {
                        map.get(key).add(indexPair);
                    } else {
                        List<IndexPair> list = new ArrayList<>();
                        list.add(indexPair);
                        map.put(key, list);
                    }
                }
            }
            for(int c = 0; c < max; c++) {
                for(int d = 0; d < max; d++) {
                    double key = Math.pow(c,3) + Math.pow(d,3);
                    if(map.get(key) != null) {
                        count = count + map.get(key).size();
                    }
                }
            }
            System.out.println("Total Count:" + count);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int max = 50;
        solution.cubeNaive(max);
        solution.cubeOptimizedLevel1(max);
        solution.cubeOptimizedLevel2(max);
    }
}
