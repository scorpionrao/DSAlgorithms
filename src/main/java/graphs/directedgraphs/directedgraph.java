package graphs.directedgraphs;

public class directedgraph {

    /*
            LinearOrder(Graph) {
                while(!G.isEmpty()) {
                    Find a vertex without extension (sink)
                    Put v at end of order
                    Remove v from Graph
                }
            }

            Runtime:
            - O(|V|) paths
            - Each path can take in worst case O(|V|)
            - Total - O(|V|^2)



            TopologicalSort(G) {
                DFS(G)
                sort vertices by reverse post-order
            }

            Strongly connected component:

            A component in directed acyclic graph in which you can travel
            from each node to any other node.
            Once you leave the component, you cannot get back.

            // Runtime - DFS on Greverse and DFS on G
            // O(|V|+|E|)
            SCCs(G) {
                run DFS on Greverse
                for v in V in reverse post order {
                    if not visited(v)
                        explore V
                        mark all visited as a new SCC
                }
            }

            Efficient SCC with least distance --> Shortest Path between nodes (if possible)
            Efficient SCC with least weightage --> Cheapest cost between nodes (is possible)

            SHORTEST PATH PROBLEM - A type of SCC problem

            LENGTH OF PATH = number of edges, number of flights from src to dest
            SHORTEST PATH = least number of edges, least number of flights from src to dest
            DISTANCE = length of shortest path

            If nodes are numbers - then choose data structure arrays - index is keys
            If nodes are string or objects - then choose maps - object id is keys

            BFS1(G, Start)

            for all v of V {
                dist[v] = INFINITY, prev[v] = null
            }
            Queue = {Start}
            distanceOrLayer[Start] = 0
            while (!Q.isEmpty) {
                u = Deque(Queue)
                for all Edges (u, v) {
                    if distanceOrLayer[v] = INFINITY {
                        Enqueue(Q, v)
                        distanceOrLayer[v] = distanceOrLayer[u] + 1, prev[v] = u
                    }
                }
            }

            ReconstructionPath(G, End, Prev) {
                result = []
                while (End != S) {
                    result.append(End)
                    End = prev[End]
                }
                return reverse(result);
            }


     */
}
