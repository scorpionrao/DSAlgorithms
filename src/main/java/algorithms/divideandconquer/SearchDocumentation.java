package algorithms.divideandconquer;

public class SearchDocumentation {

    /*

        Divide and Conquer:
            Break into non-overlapping sub problems
            solve sub-problems
            Combine results

        Recursive: Takes up extra stack space
        Iterative: Takes constant additional stack space

        Recurrence Relation - equation of values

        F(n) = 0                if n = 0
               1                if n = 1
               F(n-1) + F(n-2)  if n > 1

        Recurrence Relation for worst case Time - equation of values for worst case time - T(n)

        Recurrence Relation for Linear Search worst case time:

        T(n) = T(n-1) + c
        T(0) = 1

        Recursion Tree - How much it takes ?

        Level       Size    Work   Alternative Work
        1           n       c       O(1)
        2           n-1     c       O(1)
        ...         n-2     c       O(1)
                    ...     ...     O(1)
        n           1       c       O(1)

        Total = c * N = O(n)

        ***Steps for Recursive (Divide and Conquer) problem:***

        Create Recursive solution
        Define Recurrence relation for values
        Define Recurrence relation for T(n)
        Create alternative iterative solution.



        Recurrence Relation for Binary Search worst case time:

        T(n) = T(n/2) + c
        T(0) = c

        Recursion Tree -

        Level       Size        Work
        1           n           c
        2           n/2         c
        3           n/4         c
        log N+1     0           c

        Total = c * log(n+1) = O(log N)

    */

}
