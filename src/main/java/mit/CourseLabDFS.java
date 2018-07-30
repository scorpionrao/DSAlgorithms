package mit;

import java.util.*;

public class CourseLabDFS {
	
	/*
     * Theta(Adj[V])
     * 
     * dfsOverEdges once for each vertex / few edges - pay O(Adj[V])
     *
     * INPUT - 8 8 0 1 0 2 0 3 1 2 1 4 4 7 2 5 3 6
     * 
     */
    public static void singleComponentDriver(int sourceVertex, List<Integer>[] adjList) {
        // PRE
        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);
    	int[] parent = new int[adjList.length];
    	Arrays.fill(parent, -1);

        // PROCESS
        visited[sourceVertex] = true;
        parent[sourceVertex] = -1;
    	dfsOverEdges(sourceVertex, adjList, parent, visited);

        // POST
        System.out.println("Parent: " + Arrays.toString(parent));
        System.out.println("Visited: " + Arrays.toString(visited));
    }
    
    public static void dfsOverEdges(int sourceVertex, List<Integer>[] adjList,
                                    int[] parent, boolean[] visited) {
    	// iterate over edges
    	for(Integer oppositeVertex : adjList[sourceVertex]) {
            System.out.println("Solving for: " + sourceVertex + " " + oppositeVertex);
            if(parent[oppositeVertex] == -1 && visited[oppositeVertex] == false) {
                parent[oppositeVertex] = sourceVertex;
                visited[oppositeVertex] = true;
                System.out.println("First Occurrence: " + oppositeVertex);
                dfsOverEdges(oppositeVertex, adjList, parent, visited);
            }

    	}
    }
    
    /*
     * Theta(V+E)
     * 
     * Visit once for each vertex - pay O(V)
     * 
     * dfsOverEdges once for each vertex / few edges - pay O(Adj[V])
     * 
     */
    public static void multipleComponentDriver(int[] allVertex, List<Integer>[] adjList) {
        // PRE PROCESSING
        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);
    	int[] parent = new int[1000];
    	Arrays.fill(parent, -1);

        // PROCESS
    	dfsOverVertexes(allVertex, adjList, parent, visited);

        // NO POST PROCESSING
    }
    // ALL COMPONENTS
    public static void dfsOverVertexes(int[] allVertex, List<Integer>[] adjList,
                                       int[] parent, boolean[] visited) {
    	// iterate over vertexes
    	for(int i = 0; i < allVertex.length; i++) {
    		int sourceVertex = allVertex[i];
    		if(parent[sourceVertex] == -1 && visited[sourceVertex] == false) {
                dfsOverEdges(sourceVertex, adjList, parent, visited);
    		}

    	}
    }


    public static void topologicalSortDriver(List<Integer>[] adjList) {
        // PRE PROCESSING
    	// using finishingTimes for visited
    	List<Integer> finishingTimes = new ArrayList<>();

        // PROCESS
    	dfsOverVertexes(adjList, finishingTimes);

        // POST PROCESSING
    	Collections.reverse(finishingTimes);
    	System.out.println(finishingTimes.toString());
    }
    // O(V)
    private static void dfsOverVertexes(List<Integer>[] adjList, List<Integer> finishingTimes) {
    	// iterate over vertexes
    	for(int i = 0; i < adjList.length; i++) {
            int sourceVertex = i;
    		if(!finishingTimes.contains(i)) {
    			dfsOverEdges(sourceVertex, adjList, finishingTimes);
    		}
    	}
    }
    // O(adjList[v] = O(E)
    private static void dfsOverEdges(int sourceVertex, List<Integer>[] adjList,
                                     List<Integer> finishingTimes) {
    	// iterate over edges
    	for(Integer oppositeVertex : adjList[sourceVertex]) {
    		if(!finishingTimes.contains(oppositeVertex)) {
    			dfsOverEdges(oppositeVertex, adjList, finishingTimes);
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
        singleComponentDriver(0, adjList);
    }
}
