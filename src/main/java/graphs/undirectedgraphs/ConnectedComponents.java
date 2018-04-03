package graphs.undirectedgraphs;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {

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
        System.out.println(dfs(adjacentList));
        scanner.close();
    }

    private static int dfs(ArrayList<Integer>[] adjacentList) {
        // DFS to explore connectivity between x and y */
        boolean[] visited = new boolean[adjacentList.length];
        for(int i = 0; i < adjacentList.length; i++) {
            visited[i] = false;
        }
        int numOfComponents = 0;
        for(int rootVertex = 0; rootVertex < adjacentList.length; rootVertex++) {
            if(!visited[rootVertex]) {
                explore(rootVertex, adjacentList, visited);
                // specific to this problem
                numOfComponents = numOfComponents + 1;
            }
        }
        return numOfComponents;
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
