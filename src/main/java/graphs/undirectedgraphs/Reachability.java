package graphs.undirectedgraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Reachability {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adjacentList = (ArrayList<Integer>[]) new ArrayList[n];
        for(int i = 0; i < n; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            adjacentList[x - 1].add(y - 1);
            adjacentList[y - 1].add(x - 1);
        }
        int entryCell = scanner.nextInt() - 1;
        int exitCell = scanner.nextInt() - 1;
        System.out.println(dfsVertexes(adjacentList, entryCell, exitCell));
        scanner.close();
    }

    private static int dfsVertexes(ArrayList<Integer>[] adjacentList, int x, int y) {
        // DFS to dfsEdges connectivity between x and y */
        boolean[] visited = new boolean[adjacentList.length];
        Arrays.fill(visited, false);

        for(int rootVertex = 0; rootVertex < adjacentList.length; rootVertex++) {
            if(!visited[rootVertex]) {
                dfsEdges(rootVertex, adjacentList, visited);
                // if one of this found, check for the other before component finishes.
                if (visited[x] || visited[y]) {
                    return visited[x] == visited[y] ? 1 : 0;
                }
            }
        }
        return 0;
    }

    private static void dfsEdges(int rootVertex, ArrayList<Integer>[] adjacentList, boolean[] visited) {

        visited[rootVertex] = true;
        for(int oppositeVertex : adjacentList[rootVertex]) {
            if(!visited[oppositeVertex]) {
                dfsEdges(oppositeVertex, adjacentList, visited);
            }
        }
    }
}
