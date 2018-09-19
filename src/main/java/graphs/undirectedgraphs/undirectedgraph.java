package graphs.undirectedgraphs;

public class undirectedgraph {

    /*
        Types of data structures for Matrix:

                            Is Edge ?       List Edge       List Numbers

        Adjacent Matrix - V x V matrix (1 for edge, 0 for no edge)

        Edge List - List of all edges - (A,B) (A,C) (C,D)

        Adjacency List - Each vertex with a list of neighbors -> A to B, D

        Runtime for Graphs:
        Linear Time = O(|V| * |E|)
        Dense Graphs: |E| = |V| ^ 2
        Sparse Graphs: |E| = |V|
     */
    /*
        Component(s) {
            DiscoveredNodes = {s}
            while there is 'e(i)' leaving discoverednodes and 'e(i)' unexplored {
                notSynchronizedMethod v at the other end of e(i) to discoverednodes
            }
            return DiscoveredNodes
        }
    */

    /*

        // Book keeping
        1. Keep track of vertices found
        2. Keep track of vertices with edges left to check
        3. Which order to follow new edges

        // Preferred data structure - Adjacency List
        Explore(v) {
            visited(v) = true;
            // this gives list of all neighbors
            for(v, w) that are edges {
                if(!visited(w)) {
                    *** Explore(w)
                }
            }
        }

        // Runtime for checks = O(num of neighbor checks = num of edges) = O(|E|)
        // Total Runtime = O(1) work per vertex, O(1) per edge, O(|V|+|E|)
        // Runtime is same for - just explore, connected components, function
        DFS(Graph) {
            for all v of V {
                mark as unvisited
            }
            connectedComponents = 1
            // blindly explores all vertices irrespective of islands
            for all v of Graph {
                if not visited(v) {
                    // ***EXPLORE START - Explore(v)***
                    visited(v) = true
                    PREVISITFUNCTION(v)
                    connectedComponents(v) = connectedComponents
                    for(all w -> other side of each edge from v) {
                        if(!visited(w)) {
                            explore(w)
                        }
                    }
                    // At this point all edges in this island are explored
                    // time to explore
                    POSTVISITFUNCTION(V)
                    connectedComponents = connectedComponents + 1
                    // ***EXPLORE END***
                }
            }
        }

        PREVISITFUNCTION(V) { pre(V) = clock; clock++; }
        POSTVISITFUNCTION(V) { post(V) = clock; clock++; }

        Component(Graph) {
            DiscoveredNodes = {S}
            while (there is an edge 'e' leaving and unexplored) {
                notSynchronizedMethod end of 'e' vertex to Discovered nodes
            }
            return DiscoveredNodes;
        }


    */
}
