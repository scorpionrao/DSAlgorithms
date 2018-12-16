package datastructures.disjointedsets;

public class disjointedsets {

    /*
        Problems: Maze - Is A reachable from B, Network - Is computer A connected to B

        Preprocess(maze) {
            for each cell c in maze {
                // to store all cells reachable from c
                make a single set
            }

            for each cell c in maze {
                for each reachable neighbor n in maze {
                    UnionNaive(c, n)
                }
            }
        }

        IsReachable(cell a, cell b) {
            return Find(a) == Find(b)
        }


        Naive ClosestXdestinations: Disjointed sets as arrays
        MakeSet(i) and Find(i) - O(1); UnionNaive(i,j) - O(n); bottle neck - merging. solution: linked list.

        Better ClosestXdestinations: Disjointed sets as linked list
        Use tail of linked list as ID. UnionNaive(i,j) - O(1); Find(i) - O(n) to get to tail.

        Better ClosestXdestinations: Disjointed sets as trees
        Implementation: array with parent indices at each index.
        MakeSet(i) --> O(1) --> {parent[i] = i;}
        Find(i) --> O(tree height) --> while i != parent[i] {i = parent[i]} return i;

        RANK HEURISTIC: rank array storing tree height with each index as root.
        MakeSet(i) --> O(1) --> {parent[i] = i; rank[i] = 0;}
        Find(i) --> O(log N) --> no change
        UnionNaive(i,j) --> O(log N) --> Find(i), Find(j), high of rank[id] is the new parent. handle equal scenario.

        PATH COMPRESSION HEURISTIC:
        Find(i) --> while i != parent[i] {i = parent[i]} return i;

        Makeset(i)
        1	2	3	4	5	6	7	8	9	10	11	12
        UnionNaive(2, 10) = {2, 10} = 2
        1	2	3	4	5	6	7	8	9	2	11	12
        UnionNaive(7, 5) = {7, 5} = 5
        1	2	3	4	5	6	5	8	9	2	11	12
        UnionNaive(6, 1) = {6, 1} = 1
        1	2	3	4	5	1	5	8	9	2	11	12
        UnionNaive(3, 4) = {3, 4} = 3
        1	2	3	3	5	1	5	8	9	2	11	12
        UnionNaive(5, 11) = {5, 11} = 5
        1	2	3	3	5	1	5	8	9	2	5	12
        UnionNaive(7, 8) = {5, 8} = 5
        1	2	3	3	5	1	5	5	9	2	5	12
        UnionNaive(7, 3) = {5, 3} = 3
        1	2	3	3	3	1	5	5	9	2	5	12
        UnionNaive(12, 2) = {12, 2} = 2
        1	2	3	3	3	1	5	5	9	2	5	2
        UnionNaive(9, 6) = {9, 1} = 1
        1	2	3	3	3	1	5	5	1	2	5	2
        printBoard(Find(6) Find(3) Find(11) Find(9))
        1 3 5 1

        Makeset(i), Rank(i)
        1	2	3	4	5	6	7	8	9	10	11	12
        0   0   0   0   0   0   0   0   0   0   0   0
        UnionNaive(2, 10)
        1	2	3	4	5	6	7	8	9	10	11	12
        0   0   0   0   0   0   0   0   0   1   0   0
        UnionNaive(7, 5)
        1	2	3	4	5	6	5	8	9	10	11	12
        0   0   0   0   1   0   0   0   0   1   0   0
        UnionNaive(6, 1)
        1	2	3	4	5	1	5	8	9	10	11	12
        1   0   0   0   1   0   0   0   0   1   0   0
        UnionNaive(3, 4)
        1	2	4	4	5	1	5	8	9	10	11	12
        1   0   0   1   1   0   0   0   0   1   0   0
        UnionNaive(5, 11)
        1	2	4	4	5	1	5	8	9	10	5	12
        1   0   0   1   1   0   0   0   0   1   0   0
        UnionNaive(7, 8)
        1	2	4	4	5	1	5	8	9	10	5	12
        1   0   0   1   1   0   0   5   0   1   0   0
        UnionNaive(7, 3)
        1	2	4	4	4	1	5	8	9	10	5	12
        1   0   0   2   1   0   0   5   0   1   0   0
        UnionNaive(12, 2)
        1	2	4	4	4	1	5	8	9	10	5	12
        1   1   0   2   1   0   0   5   0   2   0   0
        UnionNaive(9, 6)
        1	2	4	4	4	1	5	8	9	10	5	12
        1   1   0   2   1   0   0   5   0   2   0   0
        1 5 5 1
     */

    int[] smallestNaive;
    int[] parentRanking;
    int[] rank;

    public disjointedsets(int n) {
        smallestNaive = new int[n+1];
        parentRanking = new int[n+1];
        rank = new int[n+1];
    }

    public void MakeSet(int i) {
        smallestNaive[i] = i;
        parentRanking[i] = i;
        rank[i] = 0;
    }

    public int FindNaive(int i) {
        return smallestNaive[i];
    }

    public int FindRankHeuristic(int i) {
        while (i != parentRanking[i]) {
            i = parentRanking[i];
        }
        return i;
    }

    public int FindRankHeuristicPathCompression(int i) {
        while (i != parentRanking[i]) {
            parentRanking[i] = FindRankHeuristicPathCompression(parentRanking[i]);
        }
        return parentRanking[i];
    }

    public void UnionNaive(int i, int j) {
        int i_id = FindNaive(i);
        int j_id = FindNaive(j);
        if(i_id == j_id) {
            return;
        }
        int m = Math.min(i_id, j_id);
        for(int k = 0; k < smallestNaive.length; k++) {
            if(smallestNaive[k] == i_id || smallestNaive[k] == j_id) {
                smallestNaive[k] = m;
            }
        }
    }

    public void UnionRankHeuristic(int i, int j) {
        int i_id = FindRankHeuristic(i);
        int j_id = FindRankHeuristic(j);
        if(i_id == j_id) {
            return;
        }
        if(rank[i_id] > rank[j_id]) {
            parentRanking[j_id] = i_id;
        } else {
            parentRanking[i_id] = j_id;
            if(rank[i_id] == rank[j_id]) {
                rank[j_id] = rank[j_id] + 1;
            }
        }
    }

    public void UnionRankHeuristicPathCompression(int i, int j) {
        int i_id = FindRankHeuristicPathCompression(i);
        int j_id = FindRankHeuristicPathCompression(j);
        if(i_id == j_id) {
            return;
        }
        if(rank[i_id] > rank[j_id]) {
            parentRanking[j_id] = i_id;
        } else {
            parentRanking[i_id] = j_id;
            if(rank[i_id] == rank[j_id]) {
                rank[j_id] = rank[j_id] + 1;
            }
        }
    }



    public static void main(String[] args) {

        /*
        int n = 12;
        disjointedsets d = new disjointedsets(n);
        for(int i = 0; i < n; i++) {
            d.MakeSet(i);
        }

        Question 1

        d.UnionNaive(1, 9);
        d.UnionNaive(6, 4);
        d.UnionNaive(5, 0);
        d.UnionNaive(2, 3);
        d.UnionNaive(4, 10);
        d.UnionNaive(6, 7);
        d.UnionNaive(6, 2);
        d.UnionNaive(11, 1);
        d.UnionNaive(8, 5);
        System.out.printBoard(d.FindNaive(5) + 1);
        System.out.printBoard(d.FindNaive(2) + 1);
        System.out.printBoard(d.FindNaive(10) + 1);
        System.out.printBoard(d.FindNaive(8) + 1);
        System.out.println();
        for(int i = 0; i < n; i++) {
            System.out.printBoard(d.smallestNaive[i] + " ");
        }
        */

        /*
        Question 2

        d.UnionRankHeuristic(1, 9);
        d.UnionRankHeuristic(6, 4);
        d.UnionRankHeuristic(5, 0);
        d.UnionRankHeuristic(2, 3);
        d.UnionRankHeuristic(4, 10);
        d.UnionRankHeuristic(6, 7);
        d.UnionRankHeuristic(6, 2);
        d.UnionRankHeuristic(11, 1);
        d.UnionRankHeuristic(8, 5);
        */

        /*
        Question 3

        for(int i = 0; i < n - 1; i++) {
            d.UnionRankHeuristic(i, i + 1);
        }
        */

        /*
        Question 4
         */

        int n4 = 60;
        disjointedsets d4 = new disjointedsets(n4);
        for(int i = 1; i < n4; i++) {
            d4.MakeSet(i);
        }
        for(int i = 1; i <= n4/2; i++) {
            d4.UnionRankHeuristicPathCompression(i, 2*i);
        }
        for(int i = 1; i <= n4/3; i++) {
            d4.UnionRankHeuristicPathCompression(i, 3*i);
        }
        for(int i = 1; i <= n4/5; i++) {
            d4.UnionRankHeuristicPathCompression(i, 5*i);
        }
        for(int i = 1; i <= n4; i++) {
            d4.FindRankHeuristicPathCompression(i);
        }

        System.out.print("TrieNode\t:\t");
        for(int i = 1; i <= n4; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.print("Parent\t:\t");
        for(int i = 1; i <= n4; i++) {
            System.out.print(d4.parentRanking[i] + "\t");
        }
        System.out.println();
        System.out.print("Height\t:\t");
        for(int i = 1; i <= n4; i++) {
            System.out.print(d4.rank[i] + "\t");
        }

    }
}
