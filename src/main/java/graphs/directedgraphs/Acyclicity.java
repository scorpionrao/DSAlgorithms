package graphs.directedgraphs;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {

    // actually DFS
    /*
        1 - Has Cycle
        0 - No Cycle
     */
    private static int isCyclic(ArrayList<Integer>[] adjacentList) {

        boolean[] visited = new boolean[adjacentList.length];
        boolean[] onstack = new boolean[adjacentList.length];
        for(int i = 0; i < adjacentList.length; i++) {
            visited[i] = false;
            onstack[i] = false;
        }

        for(int rootVertex = 0; rootVertex < adjacentList.length; rootVertex++) {
            if(!visited[rootVertex]
                && explore(rootVertex, adjacentList, visited, onstack) == 1) {
                    return 1;
                }
        }
        // no cycle
        return 0;
    }

    private static int explore(int rootVertex, ArrayList<Integer>[] adjacentList,
                                boolean[] visited,
                                boolean[] onstack) {

        visited[rootVertex] = true;
        onstack[rootVertex] = true;
        // recursively explore unvisited adjacent vertices
        for(int oppositeVertex : adjacentList[rootVertex]) {
            if(!visited[oppositeVertex]) {
                if(explore(oppositeVertex, adjacentList, visited, onstack) == 1) {
                    // CYCLE
                    return 1;
                }
            }
            // cycle - base condition post visit (post order)
            // guaranteed to be visited
            // onstack is true - if this is the current path being tracked for cycle
            if(onstack[oppositeVertex]) {
                return 1;
            }
        }
        // unravel all traversed rootVertex and mark them back as untouched
        onstack[rootVertex] = false;
        return 0;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // ADJACENT LIST DATA STRUCTURE
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int rootVertex = 0; rootVertex < m; rootVertex++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            // for directed graph - no reverse edge added to adjacent list
        }
        System.out.println(isCyclic(adj));
    }
}

