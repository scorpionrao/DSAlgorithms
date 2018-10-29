package graphs.undirectedgraphs;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println(dfsVertexes(adjacentList));
        scanner.close();
    }

    private static int dfsVertexes(ArrayList<Integer>[] adjacentList) {

        boolean[] visited = new boolean[adjacentList.length];
        Arrays.fill(visited, false);

        int numOfComponents = 0;
        for(int rootVertex = 0; rootVertex < adjacentList.length; rootVertex++) {
            if(!visited[rootVertex]) {
                dfsEdges(rootVertex, adjacentList, visited);
                numOfComponents++;
            }
        }
        return numOfComponents;
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
