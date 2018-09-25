package mit;

import java.util.Arrays;

public class CourseLabDP3EditDistance {
	
	/*
	 * Remember, dynamic programming in five easy steps. 
	 * 
	 * 1. You define what your sub problems are and count how many there are, to solve a sub problem.
	 *
	 * 2. You guess some part of the solution, where there's not too many different possibilities for that guess.
	 * You count them, better be polynomial. 
	 * Then you, using that guess-- this is sort of optional, but I think it's a useful way to think about things.
	 *
	 * 3. You write a recurrence relating the solution to the subproblem you want to solve, in terms of smaller
	 * subproblem, something that you already know how to solve, but it's got to be within this guess list.
	 * And when you do that, you're going to get a min or a max of a bunch of options, those correspond to your guesses. 
	 * And you get some running time, in order to compute that recurrence, ignoring the recursion,
	 * that's time for subproblem.
	 *
	 * 4. Then, to make a dynamic program, you either just make that a recursive algorithm and memoize everything,
	 * or you write the bottom up version of the DP.
	 * They do exactly the same computations, more or less, and you need to check that this recurrence is acyclic, 
	 * that you never end up depending on yourself, otherwise these will be infinite algorithms or incorrect algorithms. 
	 * Either way is bad. From the bottom up, you really like to explicitly know a topological order on the subproblems, 
	 * and that's usually pretty easy, but you've got make sure that it's acyclic. 
	 * And then, to compute the running time of the algorithm, you just take the number of subproblems from part 1 and
	 * you multiply it by the time it takes per subproblem,
	 * ignoring recursion, in part 3. 
	 * That gives you your running time. I've written this formula by now three times are more, remember it. We use it
	 * all the time.
	 *
	 * 5. And then you need to double check that you can actually solve the original problem you cared about, either it
	 * was one of your subproblems or a combination of them.
	 * 
	 */
	
	// Can be more space optimized
	/*
	 * 0 1 2 3 
	 * 1 1 2 3 
	 * 2 2 2 3 
	 * 3 3 3 3 
	 * iterative	: result = 3, time taken = 1904772
	 */
	public static int iterativeEditDistance(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i <= word1.length(); i++) {
            memo[i][0] = i;
        }
        for(int j = 1; j <= word2.length(); j++) {
            memo[0][j] = j;
        }

        // Bottom-up approach - no recursion
        // Loop helps 3 ways - indexes are valid, required cells are precomputed, each index is explored only once.
        for(int i = 0; i < word1.length(); i++) {
            for(int j = 0; j < word2.length(); j++) {
                /* *** memo[i+1][j+1] *** */
                if(word1.charAt(i) == word2.charAt(j))
                    memo[i + 1][j + 1] = memo[i][j];
                else {
                    int guessR = memo[i][j];
                    int guessD = memo[i][j + 1];
                    int guessIn = memo[i + 1][j];
                    memo[i+1][j+1] = guessR < guessD ? (guessR < guessIn ? guessR : guessIn) : (guessD < guessIn ? guessD : guessIn);
                    memo[i+1][j+1]++;
                }
            }
        }
        print(memo);
        return memo[word1.length()][word2.length()];
    }
	
	/*
	 * 3 3 3 3 
	 * 3 2 2 2 
	 * 3 2 1 1 
	 * 3 2 1 0 
	 * iterativeRev	: result = 3, time taken = 2153723
	 */
	public static int iterativeEditDistanceReverse(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        int lastRowIndex = word1.length();
        int lastColIndex = word2.length();
        
        for(int row = lastRowIndex; row >= 0; row--) {
            memo[row][lastColIndex] = lastRowIndex - row;
        }
        for(int col = lastColIndex; col >= 0; col--) {
            memo[lastRowIndex][col] = lastColIndex - col;
        }

        // Bottom-up approach - no recursion
        // Loop helps 3 ways - indexes are valid, required cells are precomputed, each index is explored only once.
        for(int i = lastRowIndex - 1; i >= 0; i--) {
            for(int j = lastColIndex - 1; j >= 0; j--) {
                /* *** memo[i+1][j+1] *** */
                if(word1.charAt(i) == word2.charAt(j))
                    memo[i][j] = memo[i+1][j+1];
                else {
                    int gReplace = memo[i + 1][j + 1];
                    int gDelete = memo[i][j + 1];
                    int gInsert = memo[i + 1][j];
                    memo[i][j] = gReplace < gDelete ? (gReplace < gInsert ? gReplace : gInsert) : (gDelete < gInsert ? gDelete : gInsert);
                    memo[i][j]++;
                }
            }
        }
        print(memo);
        return memo[0][0];
    }


	/* 
	 * Functional Regression
	 * 
	 * Subproblem: 
	 * 		distance(i,j) = editDistance(x[:i] * y[:i])
	 * 		# of subproblems = Theta(|x| * |y|)
	 * 
	 * Guess:
	 * 		whether to turn x to y
	 * 		x[i] deleted
	 * 		y[j] inserted
	 * 		x[i] replaced by y[j]
	 * 
	 * 		# of guesses = 3
	 * 
	 * Recurrence:
	 * 		distance(i,j) = 1 + min {
	 * 							distance(delete x[i]) 			+ distance(i-1,j)		if i < |x|
	 * 							distance(insertAtHead y[j]) 	+ distance(i,j+1)		if j < |y|
	 * 							distance(replace x[i] -> y[j]) 	+ distance(i-1,j-1) 	if i < |x|, if j < |y|
	 * 						}
	 * 
	 * 		base case: 
	 * 				distance(0,j) = j
	 * 				distance(i,0) = i
	 * 
	 * 		time/subproblem:	Theta(1)
	 * 
	 * Topological order:
	 * 		DAG in 2D table
	 * 		bottom-up + right to left
	 * 		only needs last 2 rows / columns
	 * 		linear space
	 * 		total time = Theta(1) * Theta(|x| * |y|) = Theta(|x| * |y|)
	 * 
	 * Original problem:	distance(|x|,|y|) --> 0 based, so require data structure to store [x+1][y+1] 
	 * 
	 * 
	 */
	
	/*
	 * regressionOnly	: result = 3, time taken = 129170
	 */
	private static int recursionOnlyEditDistance(String word1, String word2) {
		// int[] counter = {0};
		int result = recursionOnlyEditDistance(word1, word2, word1.length(), word2.length());
		// System.out.println("NonMemoized counter: " + counter[0]);
		return result;
	}
	
	private static int recursionOnlyEditDistance(String word1, String word2, int row, int col) {
		
		//counter[0]++;
		
		if(row == 0) {
			return col;
		} else if(col == 0) {
			return row;
		}
		
		if(word1.charAt(row-1) == word2.charAt(col-1)) {
			return recursionOnlyEditDistance(word1, word2, row-1, col-1);
		} else {
			int replaceGuess = recursionOnlyEditDistance(word1, word2, row-1, col-1);
			int deleteGuess = recursionOnlyEditDistance(word1, word2, row-1, col);
			int insertGuess = recursionOnlyEditDistance(word1, word2, row, col-1);
		
			int finalResult = Math.min(deleteGuess, insertGuess);
			return 1 + Math.min(finalResult, replaceGuess);
		}
	}
	
	/* Again Functional Regression - DFS */
	
	/*
	 * regressionMemo	: result = 3, time taken = 93829
	 */
	private static int recursionMemoizationEditDistance(String word1, String word2) {
		int[][] memo = new int[word1.length()+1][word2.length()+1];
		for(int[] array : memo) {
			Arrays.fill(array, -1);
		}
		//int[] counter = {0};
		int result = recursionMemoizationEditDistance(word1, word2, word1.length(), word2.length(), memo);
		//System.out.println("Memoized counter: " + counter[0]);
		//printBoard(memo);
		return result;
	}
	
	private static int recursionMemoizationEditDistance(String word1, String word2, int i, int j, int[][] memo) {
		
		if(memo[i][j] != -1) {
			return memo[i][j];
		}
		
		//counter[0]++;
		
		if(i == 0) {
			memo[i][j] = j;
			return memo[i][j];
		} else if(j == 0) {
			memo[i][j] = i;
			return memo[i][j];
		}
		
		if(word1.charAt(i-1) == word2.charAt(j-1)) {
			memo[i][j] = recursionMemoizationEditDistance(word1, word2, i-1, j-1, memo);
		} else {
			int replaceGuess = recursionMemoizationEditDistance(word1, word2, i-1, j-1, memo);
			int deleteGuess = recursionMemoizationEditDistance(word1, word2, i-1, j, memo);
			int insertGuess = recursionMemoizationEditDistance(word1, word2, i, j-1, memo);
	
			int finalResult = Math.min(deleteGuess, insertGuess);
			memo[i][j] = 1 + Math.min(finalResult, replaceGuess);
		}
		return memo[i][j];
	}
	
	
	public static void print(int[][] memo) {
		for(int i = 0; i < memo.length; i++) {
        	for(int j = 0; j < memo[i].length; j++) {
        		System.out.print(memo[i][j] + " ");
        	}
        	System.out.println();
        }
	}
	
	public static void timeTaken(int result, long start, long end, String testType) {
		System.out.println(testType + "\t: result = " + result + ", time taken = " + (end - start));
	}
	
	public static void main(String[] args) {
		String word1 = "ARC", word2 = "NUM";
		long start, end;
		
		start = System.nanoTime();
		int iterativeCost = iterativeEditDistance(word1, word2);
		end = System.nanoTime();
		timeTaken(iterativeCost, start, end, "iterative");
		
		start = System.nanoTime();
		int iterativeReverseCost = iterativeEditDistanceReverse(word1, word2);
		end = System.nanoTime();
		timeTaken(iterativeReverseCost, start, end, "iterativeRev");
		
		start = System.nanoTime();
		int regressionOnlyCost = recursionOnlyEditDistance(word1, word2);
		end = System.nanoTime();
		timeTaken(regressionOnlyCost, start, end, "regressionOnly");
		
		start = System.nanoTime();
		int regressionMemoizationCost = recursionMemoizationEditDistance(word1, word2);
		end = System.nanoTime();
		timeTaken(regressionMemoizationCost, start, end, "regressionMemo");
	}

}