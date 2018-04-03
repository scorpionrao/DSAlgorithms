package graphs.directedgraphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {

        int[] distance = new int[adj.length];
        int[] prev = new int[adj.length];
        for(int i = 0; i < adj.length; i++) {
            distance[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        distance[s] = 0;
        queue.offer(s);
        while (!queue.isEmpty()) {
            Integer rootVertex = queue.poll();
            for(Integer oppositeVertex : adj[rootVertex]) {
                if(distance[oppositeVertex] == -1) {
                    queue.offer(oppositeVertex);
                    distance[oppositeVertex] = distance[rootVertex] + 1;
                    prev[oppositeVertex] = rootVertex;
                }
            }
        }

        // Reconstruction
        List<Integer> result = new ArrayList<>();
        while (t != s) {
            result.add(t);
            t = prev[t];
        }
        Collections.reverse(result);
        System.out.println(result);
        return distance[t];
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

