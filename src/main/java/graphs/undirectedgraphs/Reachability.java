package graphs.undirectedgraphs;

import java.util.ArrayList;
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
        System.out.println(dfs(adjacentList, entryCell, exitCell));
        scanner.close();
    }

    private static int dfs(ArrayList<Integer>[] adjacentList, int x, int y) {
        // DFS to explore connectivity between x and y */
        boolean[] visited = new boolean[adjacentList.length];
        for(int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        for(int rootVertex = 0; rootVertex < adjacentList.length; rootVertex++) {
            if(!visited[rootVertex]) {
                explore(rootVertex, adjacentList, visited);
                // specific to this problem at the end of each connected component
                if (visited[x] || visited[y]) {
                    return visited[x] == visited[y] ? 1 : 0;
                }
            }
        }
        return 0;
    }

    private static int dfs(int x, int y, ArrayList<Integer>[] adjacentList) {
        boolean[] visited = new boolean[adjacentList.length];
        for(int i = 0; i < adjacentList.length; i++) {
            visited[i] = false;
        }

        for(int rootVertex = 0; rootVertex < adjacentList.length; rootVertex++) {
            if(!visited[rootVertex]) {
                explore(adjacentList, visited, rootVertex);
            }
            // specific to problem
            if(visited[x] || visited[y]) {
                return visited[x] == visited[y] ? 1 : 0;
            }
        }
        return 0;
    }

    private static void explore(ArrayList<Integer>[] adjacentList, boolean[] visited, int rootVertex) {
        visited[rootVertex] = true;
        for(int oppositeVertex : adjacentList[rootVertex]) {
            if(!visited[oppositeVertex]) {
                explore(adjacentList, visited, oppositeVertex);
            }
        }
    }

    private static void explore(int rootVertex,
                                ArrayList<Integer>[] adjacentList,
                                boolean[] visited) {

        // All connected vertices have same count of CC.
        visited[rootVertex] = true;
        // recursively explore unvisited adjacent vertices
        for(int oppositeVertex : adjacentList[rootVertex]) {
            if(!visited[oppositeVertex]) {
                explore(oppositeVertex, adjacentList, visited);
            }
        }
    }
}
