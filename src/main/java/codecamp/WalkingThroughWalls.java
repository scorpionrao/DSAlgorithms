package codecamp;

import java.util.LinkedList;
import java.util.Queue;

public class WalkingThroughWalls {

    private static class Point {
        int x;
        int y;
        int distance;
        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private static Integer traverse(char[][] matrix, int startRow, int startCol,
                                int endRow, int endCol) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startRow, startCol, 0));
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            if(point.x == -1 || point.y == -1 || point.x == matrix.length || point.y == matrix[0].length
                    || matrix[point.x][point.y] == 't') {
                continue;
            }
            if(point.x == endRow && point.y == endCol) {
                return new Integer(point.distance);
            }
            matrix[point.x][point.y] = 't';

            queue.offer(new Point(point.x - 1, point.y, point.distance + 1));
            queue.offer(new Point(point.x + 1, point.y, point.distance + 1));
            queue.offer(new Point(point.x, point.y - 1, point.distance + 1));
            queue.offer(new Point(point.x, point.y + 1, point.distance + 1));
        }
        return null;
    }

    public static void main(String[] args) {
        char[][] input = {
                {'f', 'f', 'f', 'f'},
                {'t', 't', 'f', 't'},
                {'f', 'f', 'f', 'f'},
                {'f', 'f', 'f', 'f'}
        };
        Integer result = traverse(input, 3, 0, 0, 0);
        System.out.println("Min Steps = " + result);
    }
}
