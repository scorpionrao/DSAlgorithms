package datastructures.leetcode;

import java.util.*;

public class MergedInterval {

    public static class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "{" + this.start + ", " + this.end + "}";
        }
    }

    /*
        Time complexity: O(N Log N) + O(N)
        Space complexity: O(N)

        ALGORITHM:
        1. Sort the intervals based on increasing order of starting time.
        2. Push the first interval on to a stack.
        3. For each interval do the following
           a. If the current interval does not overlap with the stack
               top, push it.
           b. If the current interval overlaps with stack top and ending
               time of current interval is more than that of stack top,
               update stack top with the ending  time of current interval.
        4. At the end stack contains the merged intervals.
    */
    public static List<Interval> mergeIntervals(List<Interval> intervals) {

        if(intervals == null || intervals.isEmpty()) {
            return new ArrayList<>();
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval interval1, Interval interval2) {
                return interval1.start - interval2.start;
            }
        });

        Stack<Interval> stack = new Stack<>();
        stack.push(intervals.get(0));

        for(int i = 1; i < intervals.size(); i++) {
            Interval top = stack.peek();
            Interval current = intervals.get(i);
            if(top.end < current.start) {
                stack.push(current);
            } else if (top.end < current.end) {
                top.end = current.end;
            }
        }

        List<Interval> result = new ArrayList<>();
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        Collections.reverse(result);
        return result;
    }

    /*
        Time complexity: O(N Log N) + O(N)
        Space complexity: O(1)

        ALGORITHM:
        1) Sort all intervals in decreasing order of start time.
        2) Traverse sorted intervals starting from first interval,
           do following for every interval.
              a) If current interval is not first interval and it
                 overlaps with previous interval, then merge it with
                 previous interval. Keep doing it while the interval
                 overlaps with the previous one.
              b) Else add current interval to output list of intervals.
    */
    public static List<Interval> mergeIntervalsInSpace(List<Interval> intervalList) {

        Collections.sort(intervalList, new Comparator<Interval>() {
            public int compare(Interval interval1, Interval interval2) {
                return interval2.start - interval1.start;
            }
        });

        List<Interval> result = new ArrayList<>();

        for(int i = 0; i < intervalList.size(); i++) {
            if(i > 0 && intervalList.get(i).end >= intervalList.get(i-1).start) {
                while (i > 0 && intervalList.get(i).end >= intervalList.get(i-1).start) {
                    intervalList.get(i-1).end = Math.max(intervalList.get(i-1).end, intervalList.get(i).end);
                    intervalList.get(i-1).start = Math.max(intervalList.get(i-1).start, intervalList.get(i).start);
                    i--;
                }
            } else {
                result.add(intervalList.get(i));
            }
        }
        System.out.println(result.toString());
        return result;
    }


    public static void main(String[] args) {
        Interval interval1 = new Interval(1, 3);
        Interval interval2 = new Interval(2, 6);
        Interval interval3 = new Interval(8, 10);
        Interval interval4 = new Interval(15, 18);

        Interval interval5 = new Interval(6, 8);
        Interval interval6 = new Interval(1, 9);
        Interval interval7 = new Interval(2, 4);
        Interval interval8 = new Interval(4, 7);

        List<Interval> input1 = new ArrayList<>();
        input1.add(interval1);
        input1.add(interval2);
        input1.add(interval3);
        input1.add(interval4);

        List<Interval> input2 = new ArrayList<>();
        input2.add(interval5);
        input2.add(interval6);
        input2.add(interval7);
        input2.add(interval8);

        mergeIntervals(input2);
        mergeIntervalsInSpace(input2);
    }
}
