package mit;

public class CourseLabDP2Blackjack {
	
	/*
	 * Sub problems 		- # of subproblems
	 * Guess				- # of choices
	 * Recurrence			- time per sub problem
	 * Topological order	- 
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
	 * BLACK JACK:
	 * 
	 * Sub problem	: BJ(i) = best play of Ci...Cn-1 where i = # cards
	 * 					#subproblems - n. Only decision - what cards that remain.
	 * 
	 * Guess		: how many times player hits
	 * 					# number of choices = n.
	 * 
	 * Recurrence	: DP(i = cards left) = MAX { -1,0,1 + DP(i + # cards used) } --> O(n)
	 * 
	 * 
	 */

}
