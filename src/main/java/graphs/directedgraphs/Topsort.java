package graphs.directedgraphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Topsort {

    private static List<Integer> topsort(List<Integer>[] adj) {
        List<Integer> order = dfs(adj);
        Collections.reverse(order);
        return order;
    }

    private static List<Integer> dfs(List<Integer>[] adj) {

        List<Integer> order = new ArrayList<>();

        boolean[] visited = new boolean[adj.length];
        for(int rootVertex = 0; rootVertex < visited.length; rootVertex = rootVertex + 1) {
            if(!visited[rootVertex]) {
                explore(order, adj, visited, rootVertex);
            }
        }
        return order;
    }

    private static void explore(List<Integer> order, List<Integer>[] adj, boolean[] visited, int rootVertex) {

        visited[rootVertex] = true;
        for(Integer oppositeVertex : adj[rootVertex]) {
            if(!visited[oppositeVertex]) {
                explore(order, adj, visited, oppositeVertex);
            }
        }
        // post order policy
        order.add(rootVertex);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        List<Integer> order = topsort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

