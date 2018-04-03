package live;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class refd {

    /*
        Given a set of integers, e.g. {1,3,2} and an array of random integers e.g.
        [1, 2, 2, 5, 4, 0, 1, 1, 2, 2, 0, 3, 3]

        Find the shortest continuous subarray that contains "all" of the values from
        the set.
        Result: [1, 2, 2, 0, 3]


     */

    public static class IndexRange {
        int start;
        int end;
        public IndexRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int difference() {
            return end - start + 1;
        }
    }

    public static List<Integer> bruteForce(Set<Integer> set, int[] randomIntegers) {
        IndexRange bestCandidate = new IndexRange(0, randomIntegers.length - 1);
        for(int windowBegins = 0; windowBegins < randomIntegers.length; windowBegins++) {
            // Data Structure to identify existence
            Set<Integer> cloneSet = new HashSet<>(set);
            int windowEnds = 0;
            for(windowEnds = windowBegins; windowEnds < randomIntegers.length; windowEnds++) {
                // O(M*N)
                if(cloneSet.contains(randomIntegers[windowEnds])) {
                    cloneSet.remove(randomIntegers[windowEnds]);
                }
            }
            windowEnds = windowEnds - 1;

            if(cloneSet.isEmpty()) {
                // for loop adjustment
                bestCandidate = getIndexRange(bestCandidate, windowBegins, windowEnds);
            }
        }
        return getIntegers(randomIntegers, bestCandidate);
    }

    public static List<Integer> optimized(Set<Integer> set, int[] randomIntegers) {
        IndexRange bestCandidate = new IndexRange(0, randomIntegers.length - 1);
        for(int windowBegins = 0; windowBegins < randomIntegers.length; windowBegins++) {
            // Data Structure to identify frequency
            Map<Integer, Integer> map = new HashMap<>();
            for(Integer integer : set) {
                map.put(integer, 0);
            }
            int windowEnds = 0;
            for(windowEnds = windowBegins; windowEnds < randomIntegers.length; windowEnds++) {
                // O(1*N)
                Integer frequency = map.get(randomIntegers[windowEnds]);
                // O(1*N)
                if(frequency != null) {
                    map.put(randomIntegers[windowEnds], frequency + 1);
                }
            }
            windowEnds = windowEnds - 1;
            boolean potentialCandidate = true;
            for(Map.Entry entry : map.entrySet()) {
                int value = (int) entry.getValue();
                if(value == 0) {
                    potentialCandidate = false;
                    break;
                }
            }
            if(potentialCandidate) {
                bestCandidate = getIndexRange(bestCandidate, windowBegins, windowEnds);
            }
        }
        return getIntegers(randomIntegers, bestCandidate);
    }

    private static IndexRange getIndexRange(IndexRange bestCandidate, int windowBegins, int windowEnds) {
        int span = windowEnds - windowBegins + 1;
        if(span < bestCandidate.difference()) {
            bestCandidate = new IndexRange(windowBegins, windowEnds);
        }
        return bestCandidate;
    }

    private static List<Integer> getIntegers(int[] randomIntegers, IndexRange bestCandidate) {
        List<Integer> result = new ArrayList<>();
        for(int i = bestCandidate.start; i < bestCandidate.end; i++) {
            result.add(randomIntegers[i]);
        }
        return result;
    }


    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(2);

        int[] randomIntegers = new int[]{1, 2, 2, 5, 4, 0, 1, 1, 2, 2, 0, 3, 3};
        long start = System.currentTimeMillis();
        List<Integer> result = bruteForce(set, randomIntegers);
        System.out.println(result);
        long end = System.currentTimeMillis();
        System.out.println("Bruteforce: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        result = optimized(set, randomIntegers);
        System.out.println(result);
        end = System.currentTimeMillis();
        System.out.println("Optimized: " + (end - start) + "ms");
    }
}
