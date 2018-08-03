package mit;

import java.util.*;

public class CourseLabDijkstra {

	/*
		Dijsktra - works for graph with cycles but all edges have to zero or positive

		Shortest Path from source to all vertices. Similar MST in terms of relaxation.

		Dynamic Programming - Relaxation - One pass over vertices in sorted order
											relaxing each edge that leaves each vertex.

		This algorithm - does not work for cycles but can have negative edges



	 */
	public static void dijkstraForDAG(List<Integer>[] adjList, List<Integer>[] weights, int sourceVertex) {
		// No pre-processing
		int[] leastPathWeight = new int[adjList.length];
		Arrays.fill(leastPathWeight, Integer.MAX_VALUE);
		
		Set<Integer> processingCompletedVertices = new LinkedHashSet<>();
		
		PriorityQueue<Integer> toBeProcessedVertices = new PriorityQueue<>();
		// add all vertices.
		
		leastPathWeight[sourceVertex] = 0;
		toBeProcessedVertices.add(sourceVertex);

		while(!toBeProcessedVertices.isEmpty()) {
			// extract Min
			Integer rootVertex = toBeProcessedVertices.poll();
			processingCompletedVertices.add(rootVertex);
			for(Integer oppositeVertex : adjList[rootVertex]) {
				//relax(rootVertex, oppositeVertex, weight);
			}
		}
	}

}
