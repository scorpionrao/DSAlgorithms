package mit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class CourseLabDijkstra
{

	// Topological sort - O(V+E)
	// Can decide what is starting vertex
	// Dynamic Programming - Relaxation - One pass over vertices in sorted order relaxing each edge that leaves
	// each vertex
	
	// Dijsktra - works for graph with cycles but all edges have to zero or positive
	// This algorithm - does not work for cycles but can have negative edges
	
	public static void dijkstra(List<Integer>[] adjList, List<Integer>[] weights, int sourceVertex) {
		// No pre-processing
		int[] leastPathWeight = new int[adjList.length];
		Arrays.fill(leastPathWeight, Integer.MAX_VALUE);
		
		Set<Integer> processingCompletedVertices = new LinkedHashSet<>();
		
		PriorityQueue<Integer> toBeProcessedVertices = new PriorityQueue<>();
		// add all vertices.
		
		leastPathWeight[sourceVertex] = 0;
		while(!toBeProcessedVertices.isEmpty()) {
			// extract Min
			Integer rootVertex = toBeProcessedVertices.poll();
			processingCompletedVertices.add(rootVertex);
			for(Integer oppositeVertex : adjList[rootVertex]) {
				//relax(rootVertex, oppositeVertex, weight);
			}
		}
	}
	
	public static void nonDijkstra(List<Integer>[] adjList) {
		List<Integer> sorted = topologicalSort(adjList);
		relax(sorted);
	}
	
	public static void relax(List<Integer> sorted) {
		for(Integer rootVertex : sorted) {
			// relax(rootVertex, oppositeVertex
		}
	}
	
	public static List<Integer> topologicalSort(List<Integer>[] adjList) {
    	// using finishingTimes for boolean[] visited
    	List<Integer> finishingTimes = new ArrayList<>();
    	dfsIterateVertexes(adjList, finishingTimes);
    	Collections.reverse(finishingTimes);
    	System.out.println(finishingTimes.toString());
    	return finishingTimes;
    }
    // O(V)
    public static void dfsIterateVertexes(List<Integer>[] adjList, List<Integer> finishingTimes) {
    	for(int sourceVertex = 0; sourceVertex < adjList.length; sourceVertex++) {
    		if(!finishingTimes.contains(new Integer(sourceVertex))) {
    			dfsVisitEdges(sourceVertex, adjList, finishingTimes);
    		}
    	}
    }
    // O(adjList[v] = O(E)
    public static void dfsVisitEdges(int sourceVertex, List<Integer>[] adjList, List<Integer> finishingTimes) {
    	for(Integer oppositeVertex : adjList[sourceVertex]) {
    		if(!finishingTimes.contains(oppositeVertex)) {
    			dfsVisitEdges(oppositeVertex, adjList, finishingTimes);
    		}
    	}
    	finishingTimes.add(new Integer(sourceVertex));
    }

}
