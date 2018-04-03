package graphs.weighteddirectedgraphs;

public class weighteddirectedgraph {

    /*
        Relaxed(u, v, dist, prev) {
            if(dist[v] > dist[u] + weight(u,v)) {
                dist[v] = dist[u] + weight(u,v);
                prev[v] = u;
            }
        }

        NaiveFastestRoute(G, S) {
            for all u in V {
                dist[u] = INFINITY
                PREV[U] = NILL
            }

            dist[S] = 0
            do {
                relax all the edges
            } while (at least one dist changes in the graph)
        }

        // Data Structure - H - support 1) create from set of dist values,
        // 2) extract minimum value from it outside of known origin,
        // 3) update dist value of node
        // Array or Priority Queue

        // Running Time = T(queue creation) + |V|*T(extractMin) + |E|*T(ChangePriority)
        *** Shortest Path from origin node to all nodes in the graph ***
        Dijkstra1(G, S) {
            for all u in V {
                dist[u] = INFINITY
                PREV[U] = NILL
            }
            dist[S] = 0
            H = Make Queue(V) {dist-values as keys}
            while (!H.isEmpty() {
                u = ExtractMin(H)
                for all (u,v) edges {
                    RELAX(u,v, dist, prev)
                    ChangePriority(H, v, dist[v])
                }
            }
        }

        // Runtime - O(|V||E|)
        *** Bellman-Ford algorithm ***
        BellmanFord(G, S) {
            for all u in V {
                dist[u] = INFINITY
                PREV[U] = NILL
            }
            dist[S] = 0
            repeat |V| - 1 times:
                for all (u,v) edges {
                    RELAX(u,v)
                }
            }
        }
     */
}
