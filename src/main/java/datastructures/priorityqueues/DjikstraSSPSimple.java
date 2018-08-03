package datastructures.priorityqueues;

import java.util.*;

public class DjikstraSSPSimple {

    public static class Edge {
        Integer oppositeVertex;
        Integer weight;

        Edge(Integer oppositeVertex, Integer weight) {
            this.oppositeVertex = oppositeVertex;
            this.weight = weight;
        }
    }

    public static class SPT {

        void dijkstraSPTAdjacencyMatrix(int graph[][]) {
            /* Weak - Assumes index is weight, range restricted */
            int[] parentOrResult = new int[graph.length];
            /* Weak - Assumes index is weight, range restricted */
            int[] adjustableWeights = new int[graph.length];
            /* Weak - Assumes index is weight, range restricted */
            boolean[] sptSet = new boolean[graph.length];

            // Initialize all keys as INFINITE
            Arrays.fill(adjustableWeights, Integer.MAX_VALUE);
            Arrays.fill(sptSet, false);

            adjustableWeights[0] = 0;
            parentOrResult[0] = -1;

            // Always O(V)
            for (int count = 0; count < graph.length; count++) {
                int rootVertex = minWeightArray(adjustableWeights, sptSet);
                sptSet[rootVertex] = true;

                // Worst case O(V)
                for (int oppositeVertex = 0; oppositeVertex < graph.length; oppositeVertex++) {
                    if (graph[rootVertex][oppositeVertex] != 0
                            && !sptSet[oppositeVertex]
                            && adjustableWeights[rootVertex] != Integer.MAX_VALUE
                            && adjustableWeights[rootVertex] +
                                graph[rootVertex][oppositeVertex] < adjustableWeights[oppositeVertex]) {

                        parentOrResult[oppositeVertex] = rootVertex;
                        adjustableWeights[oppositeVertex] =
                                adjustableWeights[rootVertex] + graph[rootVertex][oppositeVertex];
                    }
                }
            }
            printSPTArrayOutput(parentOrResult, adjustableWeights, null);
        }

        void dijkstraSPTAdjListArray(ArrayList<Edge>[] adjList) {

            int[] parentOrResult = new int[adjList.length];
            int[] adjustableWeights = new int[adjList.length];
            boolean[] sptSet = new boolean[adjList.length];

            // Initialize all keys as INFINITE
            Arrays.fill(adjustableWeights, Integer.MAX_VALUE);
            Arrays.fill(sptSet, false);

            adjustableWeights[0] = 0;
            parentOrResult[0] = -1;

            // Like BFS - O(V + E)
            for (int count = 0; count < adjList.length; count++) {
                /* O(V), if using minHeap - O(log V) */
                int rootVertex = minWeightArray(adjustableWeights, sptSet);
                sptSet[rootVertex] = true;
                /* O(E), if using minHeap - O(log E) */
                for (Edge edge : adjList[rootVertex]) {
                    if (!sptSet[edge.oppositeVertex]
                            && adjustableWeights[rootVertex] != Integer.MAX_VALUE
                            && adjustableWeights[rootVertex] +
                                edge.weight < adjustableWeights[edge.oppositeVertex]) {

                        parentOrResult[edge.oppositeVertex] = rootVertex;
                        adjustableWeights[edge.oppositeVertex] =
                                adjustableWeights[rootVertex] + edge.weight;
                    }
                }
            }
            printSPTArrayOutput(parentOrResult, adjustableWeights, adjList);
        }

        /*
            No range constraint from 0 to length. Can be any vertexID.
         */
        void dijkstraSPTAdjListMap(ArrayList<Edge>[] adjList) {

            Map<Integer, Integer> parentOrResult = new HashMap<>(adjList.length);
            Map<Integer, Integer> adjustableWeights = new HashMap<>(adjList.length);
            Map<Integer, Boolean> sptSet = new HashMap<>(adjList.length);

            for(int vertex = 0; vertex < adjList.length; vertex++) {
                adjustableWeights.put(vertex, Integer.MAX_VALUE);
                sptSet.put(vertex, Boolean.FALSE);
            }

            adjustableWeights.put(0, 0);
            parentOrResult.put(0, -1);

            // Solve one vertex per iteration
            for (int count = 0; count < adjList.length; count++) {
                int rootVertex = minWeightMap(adjustableWeights, sptSet);
                sptSet.put(rootVertex, Boolean.TRUE);
                // Iterate over each edge
                for(Edge edge : adjList[rootVertex]) {
                    if(!sptSet.get(edge.oppositeVertex)
                            && adjustableWeights.get(rootVertex) != Integer.MAX_VALUE
                            && adjustableWeights.get(rootVertex) +
                                edge.weight < adjustableWeights.get(edge.oppositeVertex)) {
                        parentOrResult.put(edge.oppositeVertex, rootVertex);
                        adjustableWeights.put(edge.oppositeVertex,
                                adjustableWeights.get(rootVertex) + edge.weight);
                    }
                }
            }
            printSPTMapOutput(parentOrResult, adjustableWeights, adjList);
        }

        int minWeightMap(Map<Integer, Integer> adjustableWeights, Map<Integer, Boolean> includedSet) {

            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            // O(N)
            for (Map.Entry entry : adjustableWeights.entrySet()) {
                if (!includedSet.get(entry.getKey()) && adjustableWeights.get(entry.getKey()) <= minValue) {
                    minValue = adjustableWeights.get(entry.getKey());
                    minIndex = ((Integer) entry.getKey()).intValue();
                }
            }
            return minIndex;
        }

        int minWeightArray(int adjustableWeights[], boolean[] includedSet) {

            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int vertex = 0; vertex < adjustableWeights.length; vertex++) {
                if (!includedSet[vertex] && adjustableWeights[vertex] <= minValue) {
                    minValue = adjustableWeights[vertex];
                    minIndex = vertex;
                }
            }
            return minIndex;
        }

        void printSPTArrayOutput(int[] parentOrResult, int adjustableWeights[], ArrayList<Edge>[] adjList) {
            System.out.println("Vertex   Distance from Source");
            for (int vertex = 0; vertex < adjustableWeights.length; vertex++) {
                System.out.println(vertex + "\t\t\t" + adjustableWeights[vertex]);
            }

            if(adjList != null) {
                System.out.println("Edge    Weight");
                for (int vertex = 1; vertex < adjList.length; vertex++) {
                    ArrayList<Edge> edgeList = adjList[parentOrResult[vertex]];
                    int weight = 0;
                    for (Edge edge : edgeList) {
                        if (edge.oppositeVertex == vertex) {
                            weight = edge.weight;
                        }
                    }
                    System.out.println(parentOrResult[vertex] + " - " + vertex + "    " + weight);
                }
            }
        }

        void printSPTMapOutput(Map<Integer, Integer> parentOrResult,
                               Map<Integer, Integer> adjustableWeights, ArrayList<Edge>[] adjList) {
            System.out.println("Vertex   Distance from Source");
            for (int vertex = 0; vertex < adjustableWeights.size(); vertex++) {
                System.out.println(vertex + "\t\t\t" + adjustableWeights.get(vertex));
            }

            if(adjList != null) {
                System.out.println("Edge    Weight");
                for (int vertex = 1; vertex < adjList.length; vertex++) {
                    ArrayList<Edge> edgeList = adjList[parentOrResult.get(vertex)];
                    int weight = 0;
                    for (Edge edge : edgeList) {
                        if (edge.oppositeVertex == vertex) {
                            weight = edge.weight;
                        }
                    }
                    System.out.println(parentOrResult.get(vertex) + " - " + vertex + "    " + weight);
                }
            }
        }
    }

        static void print(String type, long start, long end) {
        System.out.println(String.format("Start: %d, End: %d, TimeTaken: %dms, %s", start, end, (end-start), type));
    }


        // Driver method
    public static void main (String[] args) {

        DjikstraSSPSimple.SPT spt = new DjikstraSSPSimple.SPT();
        int adjMatrix[][] = new int[][] {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11,0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14,0, 0, 0},
                {0, 0, 0, 9, 0, 10,0, 0, 0},
                {0, 0, 4, 14,10,0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11,0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        long start = System.nanoTime();
        spt.dijkstraSPTAdjacencyMatrix(adjMatrix);
        long end = System.nanoTime();
        print("Adjacency Matrix", start, end);


        ArrayList<Edge>[] adjList = new ArrayList[9];
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
        spt.dijkstraSPTAdjListArray(adjList);
        end = System.nanoTime();
        print("Adjacency List Array", start, end);

        start = System.nanoTime();
        spt.dijkstraSPTAdjListMap(adjList);
        end = System.nanoTime();
        print("Adjacency List Map", start, end);
    }
}
