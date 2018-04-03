import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    public static class Node implements Comparable<Node> {
        int index;
        long distance;

        public Node(int index, long distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if(this.distance > o.distance) {
                return 1;
            } else if (this.distance < o.distance) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private static int distance(ArrayList<Integer>[] adj,
                                ArrayList<Integer>[] cost, int s, int t) {

        int[] dist = new int[adj.length];
        for(int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(s, dist[s]));
        while(!queue.isEmpty()) {
            Node rootVertex = queue.poll();
            int root_index = rootVertex.index;
            for(int opposite_index : adj[root_index]) {
                int v_index = adj[root_index].indexOf(opposite_index);
                if (dist[opposite_index] > dist[root_index] + cost[root_index].get(v_index)) {
                    dist[opposite_index] = dist[root_index] + cost[root_index].get(v_index);
                    queue.add(new Node(opposite_index, dist[opposite_index]));
                }
            }
        }
        return (dist[t] == Integer.MAX_VALUE) ? -1 : dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

