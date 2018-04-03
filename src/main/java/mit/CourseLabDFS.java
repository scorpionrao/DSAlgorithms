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
    	int[] parentOrVisited = new int[1000];
    	Arrays.fill(parentOrVisited, -1);
    	dfsVisit(sourceVertex, adjList, parentOrVisited);
    }
    
    public static void dfsVisit(int sourceVertex, List<Integer>[] adjList, int[] parentOrVisited) {
    	// iterate over edges
    	for(Integer oppositeVertex : adjList[sourceVertex]) {
    		if(parentOrVisited[oppositeVertex] == -1) {
    			parentOrVisited[oppositeVertex] = sourceVertex;
    		}
    		dfsVisit(oppositeVertex, adjList, parentOrVisited);
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
    	int[] parentOrVisited = new int[1000];
    	Arrays.fill(parentOrVisited, -1);
    	dfs(allVertex, adjList, parentOrVisited);
    }
    // ALL COMPONENTS
    public static void dfs(int[] allVertex, List<Integer>[] adjList, int[] parentOrVisited) {
    	// iterate over vertexes
    	for(int i = 0; i < allVertex.length; i++) {
    		int sourceVertex = allVertex[i];
    		if(parentOrVisited[sourceVertex] == -1) {
    			// do nothing
    		}
    		dfsVisit(sourceVertex, adjList, parentOrVisited);
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
    public static void dfs(List<Integer>[] adjList, List<Integer> finishingTimes) {
    	// iterate over vertexes
    	for(int i = 0; i < adjList.length; i++) {
    		if(!finishingTimes.contains(new Integer(i))) {
    			int sourceVertex = i;
    			dfsVisit(sourceVertex, adjList, finishingTimes);
    		}
    	}
    }
    // O(adjList[v] = O(E)
    public static void dfsVisit(int sourceVertex, List<Integer>[] adjList, List<Integer> finishingTimes) {
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
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            // directed graph
            adj[x].add(y);
        }
        scanner.close();
        topologicalSort(adj);
        
    }
}
