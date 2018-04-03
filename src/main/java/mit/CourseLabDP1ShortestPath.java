package mit;

public class CourseLabDP1ShortestPath {
	
	/*
	 * Subproblems: min of (S,V) using <= k edges
	 * # of subproblems - V^2
	 * 
	 * # Guesses: indegreeEdges(v) + 1
	 * 
	 * Recurrence: (s,v) = min((s,u) + w(u,v))
	 * time/subproblem:	Theta(1) + indegree(v)
	 * 
	 * Topological order: for k = 1 to |V| - 1, Theta(V*E) + Theta(V^2)
	 */

}
