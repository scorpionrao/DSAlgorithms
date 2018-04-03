package mit;

public class CourseLabDP2TextJustification {
	
	/*
	 * Sub problems 				- # of subproblems
	 * Guess						- # of choices
	 * Relate subproblem solution 	- time per sub problem
	 * Topological sort				- Recurse / Memoize, Bottom-up
	 * Solve original problem
	 */
	
	/*
	 * Algorithm : Analysis (Fibonacci, Shortest Path)
	 * 
	 * Define Sub problems				: # of subproblems (n, V vertexes * V edges)		
	 * Guess (part of solution)			: # of choices for guesses (nothing, last edges to V)
	 * Recurrence - Relate subproblem	: time / subproblem (definition itself, min/max(s->u)+w(u,v)
	 * Recurse & Memoize OR Bottom up	: check sub problem recurrence is acyclic / has topological order
	 * Solve the original problem		: O(N), O(VE)
	 * 
	 * Total time = 1 * 3
	 * 
	 * TEXT JUSTIFICATION:
	 * 
	 * Sub problem	: suffixes of words. #subproblems - N. Only decision - what the words that remain.
	 * (if the decision were - is it in or out (2^N), is it at the beginning of line or not (2^N))
	 * 
	 * Guess		: where to start 2nd line. # of choices <= n - i -> O(n)
	 * Recurrence	: DP(i) = MIN { D(j) + badness(i, j) for j ~ i+1, n+1 }
	 * 				  time / subproblem -> O(n)
	 * 				  base case: DP(n) = 0
	 * Topological	: i = n, n-1,..., 0. Total time	: O(n^2)
	 * Solve original problem : DP(0) => solve from 0th word onwards
	 * 
	 * Parent pointers:	remember which guess was best.
	 * 							
	 * 							1st line begins at: 0
	 * 							2nd line begins at: parent(0)
	 * 							3rd line begins at: parent(parent(0))
	 * 
	 * i = 0
	 * while i is not None
	 * 		start line before word i
	 * 		i = DP[i][i]
	 * 	
	 */

}
