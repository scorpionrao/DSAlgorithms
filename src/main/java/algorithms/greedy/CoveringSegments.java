package algorithms.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CoveringSegments {

    /*
        points = [1....segments.length]
        Sort segments by end date

        current position = end date of first segment
        points[0] = current position
        for each segment {
            if (start > current position || end < current position) {
                add end of this segment to points
             }
        }

        return points;
     */

    private static int[] optimalPoints(Segment[] segments) {

        int[] points = new int[segments.length];
        Arrays.sort(segments, new Comparator<Segment>() {
            @Override
            public int compare(Segment s1, Segment s2) {
                return Integer.compare(s1.end, s2.end);
            }
        });
        int currentPosition = segments[0].end;
        int j = 0;
        points[j] = currentPosition;
        for(int i = 1; i < segments.length; i++) {
            if(segments[i].start > currentPosition || segments[i].end < currentPosition) {
                currentPosition = segments[i].end;
                points[++j] = currentPosition;
            }
        }

        int[] result = new int[j + 1];
        for(int i = 0; i < result.length; i++) {
            result[i] = points[i];
        }
        return result;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
