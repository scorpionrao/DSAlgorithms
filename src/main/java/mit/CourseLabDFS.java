package mit;

import java.util.*;

public class CourseLabDFS {
	
	/*
     * Theta(Adj[V])
     * 
     * dfsVisit once for each vertex / few edges - pay O(Adj[V])
     * 
     */
    public static void singleComponent(int sourceVertex, List<Integer>[] adjList) {
        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);
    	int[] parent = new int[adjList.length];
    	Arrays.fill(parent, -1);

        visited[sourceVertex] = true;

    	dfsVisit(sourceVertex, adjList, parent, visited);
        System.out.println("Parent: " + Arrays.toString(parent));
        System.out.println("Visited: " + Arrays.toString(visited));
    }
    
    public static void dfsVisit(int sourceVertex, List<Integer>[] adjList,
								int[] parent, boolean[] visited) {
    	// iterate over edges
    	for(Integer oppositeVertex : adjList[sourceVertex]) {
    		if(parent[oppositeVertex] == -1) {
                parent[oppositeVertex] = sourceVertex;
                visited[oppositeVertex] = true;
                System.out.println("Processing: " + oppositeVertex);
            }
            dfsVisit(oppositeVertex, adjList, parent, visited);
    	}
    }
    
    /*
     * Theta(V+E)
     * 
     * Visit once for each vertex - pay O(V)
     * 
     * dfsVisit once for each vertex / few edges - pay O(Adj[V])
     * 
     */
    public static void multipleComponent(int[] allVertex, List<Integer>[] adjList) {
        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);
    	int[] parent = new int[1000];
    	Arrays.fill(parent, -1);
    	dfs(allVertex, adjList, parent, visited);
    }
    // ALL COMPONENTS
    public static void dfs(int[] allVertex, List<Integer>[] adjList,
						   int[] parent, boolean[] visited) {
    	// iterate over vertexes
    	for(int i = 0; i < allVertex.length; i++) {
    		int sourceVertex = allVertex[i];
    		if(parent[sourceVertex] == -1) {
    			// do nothing
    		}
    		dfsVisit(sourceVertex, adjList, parent, visited);
    	}
    }


    public static void topologicalSort(List<Integer>[] adjList) {
    	// using finishingTimes for visited
    	List<Integer> finishingTimes = new ArrayList<>();
    	dfs(adjList, finishingTimes);
    	Collections.reverse(finishingTimes);
    	System.out.println(finishingTimes.toString());
    }
    // O(V)
    private static void dfs(List<Integer>[] adjList, List<Integer> finishingTimes) {
    	// iterate over vertexes
    	for(int i = 0; i < adjList.length; i++) {
    		if(!finishingTimes.contains(new Integer(i))) {
    			int sourceVertex = i;
    			dfsVisit(sourceVertex, adjList, finishingTimes);
    		}
    	}
    }
    // O(adjList[v] = O(E)
    private static void dfsVisit(int sourceVertex, List<Integer>[] adjList,
								List<Integer> finishingTimes) {
    	// iterate over edges
    	for(Integer oppositeVertex : adjList[sourceVertex]) {
    		if(!finishingTimes.contains(oppositeVertex)) {
    			dfsVisit(oppositeVertex, adjList, finishingTimes);
    		}
    	}
    	finishingTimes.add(new Integer(sourceVertex));
    }
    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numOfNodes = scanner.nextInt();
        int numOfEdges = scanner.nextInt();
        ArrayList<Integer>[] adjList = new ArrayList[numOfNodes];
        for (int node = 0; node < numOfNodes; node++) {
            adjList[node] = new ArrayList<>();
        }
        for (int edge = 0; edge < numOfEdges; edge++) {
            int rootVertex = scanner.nextInt();
            int endVertex = scanner.nextInt();
            // directed graph
            adjList[rootVertex].add(endVertex);
        }
        scanner.close();
        //topologicalSort(adjList);
        singleComponent(0, adjList);
    }
}
