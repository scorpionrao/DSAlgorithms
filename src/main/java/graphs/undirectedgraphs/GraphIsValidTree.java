package graphs.undirectedgraphs;

import java.util.ArrayList;
import java.util.List;

public class GraphIsValidTree {


    /*
        Has Cycle - check an edge - two cases

        1 - Opposite vertex is already visited + it is not a PARENT of Source (alternate way is found).
        2 - Opposite vertex is not visited + Has Cycle (opposite vertex is new source, source is new parent)
     */
    private static boolean hasCycleDfsEdges(List<List<Integer>> graph, boolean[] visited, int sourceVertex, int parentVertex) {

        visited[sourceVertex] = true;
        for(Integer oppositeVertex : graph.get(sourceVertex)) {
            boolean oppositeVisitedAndNotParent = visited[oppositeVertex] && oppositeVertex != parentVertex;
            boolean oppositeNotVisitedAndHasCycle = !visited[oppositeVertex] && hasCycleDfsEdges(graph, visited, oppositeVertex, sourceVertex);

            if(oppositeVisitedAndNotParent || oppositeNotVisitedAndHasCycle) {
                return true;
            }
        }
        return false;
    }

    private static boolean isStronglyConnected(boolean[] visited) {
        // only one component exist
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]) {
                return false;
            }
        }
        return true;
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

        boolean[] visited = new boolean[numOfNodes];

        boolean isCycle = hasCycleDfsEdges(graph, visited, 0, -1);
        System.out.println("Is Cycle Exist : " + isCycle);

        boolean isStronglyConnected = isStronglyConnected(visited);
        System.out.println("Is Strongly Connected : " + isStronglyConnected);

        boolean isValidTree = !isCycle && isStronglyConnected;
        System.out.println("Is Valid Tree : " + isValidTree);
    }

    public static void main(String[] args) {

        int numOfNodes = 5;
        int[][] edgeList = {{0, 1}, {1, 2}, {2, 3}, {1, 4}, {1, 3}};
        isValidTree(numOfNodes, edgeList);
    }
}
