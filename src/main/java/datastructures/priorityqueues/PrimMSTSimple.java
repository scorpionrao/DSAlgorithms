package datastructures.priorityqueues;

import java.util.*;

public class PrimMSTSimple {

    public static class Edge {
        Integer oppositeVertex;
        Integer weight;

        Edge(Integer oppositeVertex, Integer weight) {
            this.oppositeVertex = oppositeVertex;
            this.weight = weight;
        }
    }

    public static class MST {

        /* Always vertices of the graph can be traversed in - O(V^2) */

        /*

            Edge    Weight
            0 - 1    2
            1 - 2    3
            0 - 3    6
            1 - 4    5
            Start: 295512824189400, End: 295512825120967, TimeTaken: 931567ms, Adjacency Matrix
            Start: 295512839128539, End: 295512839934306, TimeTaken: 805767ms, Adjacency List Array
            Start: 295512840561456, End: 295512852377651, TimeTaken: 11816195ms, Adjacency List Map

         */
        void primMSTAdjacencyMatrix(int[][] graph) {
            /* Actions - Add --> O(1), Update --> O(1) */
            int[] parentOrResult = new int[graph.length];
            /* Actions - Add --> O(1), Update --> O(1) */
            int[] adjustableWeights = new int[graph.length];
            /* Actions - Add --> O(1), Update --> O(1) */
            boolean[] mstSet = new boolean[graph.length];

            // Initialize all keys as INFINITE
            Arrays.fill(adjustableWeights, Integer.MAX_VALUE);
            Arrays.fill(mstSet, false);

            adjustableWeights[0] = 0;
            parentOrResult[0] = -1;

            // Always - O(V)
            for(int count = 0; count < graph.length; count++) {
                int rootVertex = minWeightArray(adjustableWeights, mstSet);
                mstSet[rootVertex] = true;

                // Worst case O(V) - All nodes connected to all nodes.
                for(int oppositeVertex = 0; oppositeVertex < graph.length; oppositeVertex++) {
                    if(graph[rootVertex][oppositeVertex] != 0
                            && !mstSet[oppositeVertex]
                            && graph[rootVertex][oppositeVertex] < adjustableWeights[oppositeVertex]) {

                        parentOrResult[oppositeVertex] = rootVertex;
                        adjustableWeights[oppositeVertex] = graph[rootVertex][oppositeVertex];
                    }
                }
            }
            printMSTAdjacencyMatrix(parentOrResult, graph);
        }

        /*

            Edge    Weight
            0 - 1    2
            1 - 2    3
            0 - 3    6
            1 - 4    5
            Start: 295512824189400, End: 295512825120967, TimeTaken: 931567ms, Adjacency Matrix
            Start: 295512839128539, End: 295512839934306, TimeTaken: 805767ms, Adjacency List Array
            Start: 295512840561456, End: 295512852377651, TimeTaken: 11816195ms, Adjacency List Map

         */
        void primMSTAdjListArray(ArrayList<Edge>[] adjList) {
            /* Actions - Add --> O(1), Update --> O(1) */
            int[] parentOrResult = new int[adjList.length];
            /* Actions - Add --> O(1), Update --> O(1) */
            int[] adjustable = new int[adjList.length];
            /* Actions - Add --> O(1), Update --> O(1) */
            boolean[] mstSet = new boolean[adjList.length];

            Arrays.fill(adjustable, Integer.MAX_VALUE);
            Arrays.fill(mstSet, false);

            adjustable[0] = 0;
            parentOrResult[0] = -1;

            // Like BFS - O(V + E)
            for(int count = 0; count < adjList.length; count++) {
                /* O(V), if using minHeap - O(log V) */
                int rootVertex = minWeightArray(adjustable, mstSet);
                mstSet[rootVertex] = true;
                /* O(E), if using minHeap - O(log E) */
                for(Edge edge : adjList[rootVertex]) {
                    if(!mstSet[edge.oppositeVertex]
                            && edge.weight < adjustable[edge.oppositeVertex]) {
                        parentOrResult[edge.oppositeVertex] = rootVertex;
                        adjustable[edge.oppositeVertex] = edge.weight;
                    }
                }
            }

            printMSTAdjListArray(parentOrResult, adjList);
        }

        /*

            Edge    Weight
            0 - 1    2
            1 - 2    3
            0 - 3    6
            1 - 4    5
            Start: 295512824189400, End: 295512825120967, TimeTaken: 931567ms, Adjacency Matrix
            Start: 295512839128539, End: 295512839934306, TimeTaken: 805767ms, Adjacency List Array
            Start: 295512840561456, End: 295512852377651, TimeTaken: 11816195ms, Adjacency List Map

         */
        void primMSTAdjListMap(ArrayList<Edge>[] adjList) {
            /* Actions - Add --> O(1), Update --> O(1) */
            Map<Integer, Integer> parentOrResult = new HashMap<>();
            /* Actions - Add --> O(1), Update --> O(1) */
            Map<Integer, Integer> adjustableWeights = new HashMap<>();
            /* Actions - Add --> O(1), Contains --> O(1) */
            Set<Integer> mstSet = new HashSet<>();

            for(int vertex = 0; vertex < adjList.length; vertex++) {
                adjustableWeights.put(vertex, Integer.MAX_VALUE);
            }

            adjustableWeights.put(0, 0);
            parentOrResult.put(0, -1);

            // Solve one vertex per iteration
            while (!adjustableWeights.isEmpty()){
                int rootVertex = minWeightMap(adjustableWeights, mstSet);
                mstSet.add(rootVertex);
                adjustableWeights.remove(rootVertex);
                // Iterate over each edge
                for(Edge edge : adjList[rootVertex]) {
                    if(!mstSet.contains(edge.oppositeVertex)
                            && edge.weight < adjustableWeights.get(edge.oppositeVertex)) {
                        parentOrResult.put(edge.oppositeVertex, rootVertex);
                        adjustableWeights.put(edge.oppositeVertex, edge.weight);
                    }
                }
            }

            printMSTForMapForAdjList(parentOrResult, adjList);
        }


        void printMSTAdjListArray(int[] parentOrResult, ArrayList<Edge>[] adjList) {
            System.out.println("Edge    Weight");
            for(int vertex = 1; vertex < adjList.length; vertex++) {
                int weight = 0;
                for(Edge edge : adjList[parentOrResult[vertex]]) {
                    if(edge.oppositeVertex == vertex) {
                        weight = edge.weight;
                    }
                }
                System.out.println(parentOrResult[vertex] + " - " + vertex + "    " + weight);
            }
        }

        void printMSTForMapForAdjList(Map<Integer, Integer> parentOrResult, ArrayList<Edge>[] adjList) {
            System.out.println("Edge    Weight");
            for(int vertex = 1; vertex < adjList.length; vertex++) {
                ArrayList<Edge> edgeList = adjList[parentOrResult.get(vertex)];
                int weight = 0;
                for(Edge edge : edgeList) {
                    if(edge.oppositeVertex == vertex) {
                        weight = edge.weight;
                    }
                }
                System.out.println(parentOrResult.get(vertex) + " - " + vertex + "    " + weight);
            }
        }

        void printMSTAdjacencyMatrix(int[] parentOrResult, int[][] graph) {
            System.out.println("Edge    Weight");
            for(int vertex = 1; vertex < graph.length; vertex++) {
                System.out.println(parentOrResult[vertex] + " - " + vertex + "    " + graph[vertex][parentOrResult[vertex]]);
            }
        }

        int minWeightMap(Map<Integer, Integer> adjustableWeights, Set<Integer> includedSet) {

            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            // O(N)
            for (Integer vertex : adjustableWeights.keySet()) {
                if (!includedSet.contains(vertex) && adjustableWeights.get(vertex) < minValue) {
                    minValue = adjustableWeights.get(vertex);
                    minIndex = vertex.intValue();
                }
            }
            return minIndex;
        }

        int minWeightArray(int adjustableWeights[], boolean[] includedSet) {

            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            // O(N)
            for (int vertex = 0; vertex < adjustableWeights.length; vertex++) {
                if (!includedSet[vertex] && adjustableWeights[vertex] < minValue) {
                    minValue = adjustableWeights[vertex];
                    minIndex = vertex;
                }
            }
            return minIndex;
        }
    }

    static void print(String type, long start, long end) {
        System.out.println(String.format("Start: %d, End: %d, TimeTaken: %dms, %s", start, end, (end-start), type));
    }

    public static void main(String[] args) {

        /*
                   2    3
                (0)--(1)--(2)
                |   / \   |
              6 | 8/   \5 |7
                | /     \ |
                (3)-------(4)
                     9
        */
        PrimMSTSimple.MST mst = new PrimMSTSimple.MST();
        /*
        int[][] adjMatrix = new int[][] {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };
        */
        int[][] adjMatrix = new int[][] {
                {0, 10, 30},
                {10, 0, 20},
                {30, 20, 0}
        };

        long start = System.nanoTime();
        mst.primMSTAdjacencyMatrix(adjMatrix);
        long end = System.nanoTime();
        print("Adjacency Matrix", start, end);

        ArrayList<Edge>[] adjList = new ArrayList[adjMatrix.length];
        for(int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int i = 0; i < adjMatrix.length; i++) {
            for(int j = 0; j < adjMatrix[i].length; j++) {
                if(adjMatrix[i][j] != 0) {
                    adjList[i].add(new Edge(j, adjMatrix[i][j]));
                }
            }
        }

        start = System.nanoTime();
        mst.primMSTAdjListArray(adjList);
        end = System.nanoTime();
        print("Adjacency List Array", start, end);

        start = System.nanoTime();
        mst.primMSTAdjListMap(adjList);
        end = System.nanoTime();
        print("Adjacency List Map", start, end);
    }
}
