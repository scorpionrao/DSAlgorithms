package kickstart;

public class Graph {

    public static class GraphDataStructure {
        /*
            DS1 - EDGE LIST:
                Space Complexity:
                    1. Vertex List - String based vertexes - variable lengths, no random access, huge scanning costs.
                                   - Integer based vertexes - fixed lengths, random access.
                                   - O(|V|)
                    2. Edges List - [Source vertex, End vertex, weight].
                                  - O(|E|)
                    3. Total Space - O(|V| + |E|)

                Time Complexity:
                    1. Find edges for a vertex - scan all edges.
                    2. Edges list unsorted - O(|E|)
                    3. Edges list sorted - O(log |E|)



            DS2 - ADJACENCY MATRIX:
                Space Complexity:
                    1. Vertex and Edges Matrix
                                     - V[][] - storing weights / existence of edges.
                                     - Uses more space than required.
                                     - Dense - great data structure.
                                     - Sparse - bad data structure.
                                     - O(|V| ^ 2)
                                     - Useful values - O(|E|)
                                     - Not useful values - O(|V|^2 - |E|)

                Time Complexity:C
                    1. Find edges for a vertex - scan the matrix row
                    2. Does edge exist - O(1) - access edge existence or weight through direct index based access.


            DS3 - ADJACENCY LIST:
                Space Complexity:
                    1. Vertex and Edges - [heads] ( [LinkedLists] / [BST] / [array] )
                                        - O(|V|+|E|)

                Time Complexity:
                    1. Find edges for a vertex - scan the nodes attached to the head.
                    2. [array of heads] gives O(1) random access to heads.
                    3. [array of edges] - O(e) - e is the number of edges (cells) attached but "size" cannot change.
                    4. [linked list of edges] - O(e) - e is the number of edges.
                    5. [BST of edges] - "O(log e)" - e is the number of edges.
         */
    }
}
