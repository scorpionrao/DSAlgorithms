package graphs.undirectedgraphs;

import java.util.ArrayList;
import java.util.List;

public class GraphIsValidTree {

    /* Methods checks for cycle in multiple components */
    private static boolean hasCycleDfsVertexes(List<List<Integer>> graph) {

        boolean[] visited = new boolean[graph.size()];

        for(int i = 0; i < graph.size(); i++) {
            if(!visited[i] && hasCycleDfsEdges(graph, visited, i, -1)) {
                return true;
            }
        }
        return false;
    }

    /*
        Method searches over edges in particular component.

        Has Cycle - check an edge - two cases

        1 - Opposite vertex is already visited + it is not a PARENT of Source (alternate way is found).
        2 - Opposite vertex is not visited + Has Cycle (opposite vertex is new source, source is new parent)
     */
    private static boolean hasCycleDfsEdges(List<List<Integer>> graph, boolean[] visited, int sourceVertex, int parentVertex) {

        visited[sourceVertex] = true;
        for(Integer oppositeVertex : graph.get(sourceVertex)) {
            boolean oppositeVisitedAndNotParent = visited[oppositeVertex] && oppositeVertex != parentVertex;
            if(oppositeVisitedAndNotParent) {
                return true;
            }

            boolean oppositeNotVisitedAndHasCycle = !visited[oppositeVertex] && hasCycleDfsEdges(graph, visited, oppositeVertex, sourceVertex);
            if(oppositeNotVisitedAndHasCycle) {
                return true;
            }
        }
        return false;
    }


    private static boolean isSingleComponentDfsVertexes(List<List<Integer>> graph) {

        boolean[] visited = new boolean[graph.size()];

        int numOfComponents = 0;
        for(int rootVertex = 0; rootVertex < graph.size(); rootVertex++) {
            if(!visited[rootVertex]) {
                dfsEdges(rootVertex, graph, visited);
                numOfComponents++;
            }
        }
        return numOfComponents == 1;
    }

    private static void dfsEdges(int rootVertex, List<List<Integer>> graph, boolean[] visited) {

        visited[rootVertex] = true;
        for(int oppositeVertex : graph.get(rootVertex)) {
            if(!visited[oppositeVertex]) {
                dfsEdges(oppositeVertex, graph, visited);
            }
        }
    }

    /*
        isValidTree = No cycles + Strongly connected graph
     */
    private static void isValidTree(int numOfNodes, int[][] edgeArray) {

        List<List<Integer>> graph = new ArrayList<>(numOfNodes);
        for(int i = 0; i < numOfNodes; i++) {
            graph.add(i, new ArrayList<>());
        }

        for(int i = 0; i < edgeArray.length; i++) {
            graph.get(edgeArray[i][0]).add(edgeArray[i][1]);
            graph.get(edgeArray[i][1]).add(edgeArray[i][0]);
        }

        boolean hasCycle = hasCycleDfsVertexes(graph);
        boolean isStronglyConnected = isSingleComponentDfsVertexes(graph);
        boolean isValidTree = !hasCycle && isStronglyConnected;

        System.out.println("Is Cycle Exist : " + hasCycle);
        System.out.println("Is Strongly Connected : " + isStronglyConnected);
        System.out.println("Is Valid Tree : " + isValidTree);
    }

    public static void main(String[] args) {

        int numOfNodes = 5;
        int[][] edgeList = {{0, 1}, {1, 2}, {2, 3}, {1, 4}, {1, 3}};
        isValidTree(numOfNodes, edgeList);
    }
}
