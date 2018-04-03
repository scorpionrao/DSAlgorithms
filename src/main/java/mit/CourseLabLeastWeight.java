package mit;

import java.util.Arrays;
import java.util.List;

public class CourseLabLeastWeight {
	
	public static class Node {
		int weight;
		public Node(int weight) {
			this.weight = weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
	}
	
	public void shortestPath(List<Integer>[] vertices, Integer sourceVertex, List<Integer>[] weights) {
		int[] leastWeightFromSource = new int[vertices.length];
		Arrays.fill(leastWeightFromSource, Integer.MAX_VALUE);
		
		int[] predecessor = new int[vertices.length];
		Arrays.fill(predecessor, -1);
		
		leastWeightFromSource[sourceVertex] = 0;
		
		while (true) { //all leastWeights(v) <= leastWeights(u) + weight(u,v)
			// RELAX EDGE
			for(Integer oppositeVertex : vertices[sourceVertex]) {
				if(leastWeightFromSource[oppositeVertex] > leastWeightFromSource[sourceVertex] + weights[sourceVertex].get(oppositeVertex)) {
					leastWeightFromSource[oppositeVertex] = leastWeightFromSource[sourceVertex] + weights[sourceVertex].get(oppositeVertex);
					predecessor[oppositeVertex] = sourceVertex;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		

	}

}
