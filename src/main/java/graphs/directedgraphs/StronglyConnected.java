package graphs.directedgraphs;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adjList) {

        ArrayList<Integer>[] adjReverse = reverseAdj(adjList);
        // DFS on adjReverse for all CCs in post order
        Stack<Integer> stack = dfs(adjReverse);

        int numOfSCC = 0;
        boolean[] visited = new boolean[adjList.length];
        while(!stack.isEmpty()) {
            int vertex = stack.pop();
            if(!visited[vertex]) {
                explore(vertex, adjList, visited, null);
                numOfSCC++;
            }
        }
        return numOfSCC;
    }

    private static Stack<Integer> dfs(ArrayList<Integer>[] adjList) {
        Stack<Integer> postOrder = new Stack<>();
        boolean[] visited = new boolean[adjList.length];
        for(int rootVertex = 0; rootVertex < adjList.length; rootVertex++) {
            if(!visited[rootVertex]) {
                explore(rootVertex, adjList, visited, postOrder);
            }
        }
        return postOrder;
    }

    private static void explore(int rootVertex,
                                ArrayList<Integer>[] adjList,
                                boolean[] visited,
                                Stack<Integer> postOrder) {

        visited[rootVertex] = true;
        for(int oppositeVertex : adjList[rootVertex]) {
            if(!visited[oppositeVertex]) {
                explore(oppositeVertex, adjList, visited, postOrder);
            }
        }

        // in reverse graph, first-in will be beginning node in original
        // in reverse graph, last-in will be sink in original
        if(postOrder != null) {
            postOrder.push(rootVertex);
        }
    }

    private static ArrayList<Integer>[] reverseAdj(ArrayList<Integer>[] adj) {
        /**Reverse G to GR: O(V+E)*/
        ArrayList<Integer>[] reverseAdjList = new ArrayList[adj.length];
        // Initialize empty adjacency list for each vertex. O(V)
        for(int i = 0; i < adj.length; i++) {
            reverseAdjList[i] = new ArrayList<>();
        }
        // for each vertex v, add v into reverse list of its neighbors.
        // BZ: O(V*E)? O(V+E)? traverse each vertex and each edge once.
        for(int i = 0; i < adj.length; i++) {
            for(Integer integer : adj[i]) {
                reverseAdjList[integer].add(i);
            }
        }
        return reverseAdjList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

