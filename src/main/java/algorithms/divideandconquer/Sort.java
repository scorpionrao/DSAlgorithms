package algorithms.divideandconquer;

public class Sort {

    /*

        Input: Unsorted array of N integers
        Source: from user in-memory

        Output: Sorted array of N integers

        Type of Data set - Random, Nearly Sorted, Reversed, Few Unique

        Algorithm - Running Time, In place (space optimization), Type of data set above

        PSUEDO CODE of Selection Sort (Comparison based)

        A[1....n]

        for i --> 1 to N {
            minIndex = i
            for j --> i+1 to N {
                if(A[minIndex] > A[j] {
                    minIndex = j
                }
            }
            // A[minIndex] = min A[j...N]
            swap(A[minIndex], A[i])
            // A[i] position has been finalized
        }

        Selection Sort - O(n^2), In place (constant extra memory).

        PSUEDO CODE of Merge Sort (Comparison based)

        MergeSort(A[1....n]) {
            if(n == 1) return A;
            m = n/2
            B = MergeSort(A[1...m])
            C = MergeSort(A[m+1...n])
            A' = merge(BC)
            return A'
        }

        Merge(B[1...p] C[1...q]) {
            D -> array of size p+q
            while B and C are both non-empty
                b -> first element of B
                c -> first element of C
                if(b >= c) {
                    move b from B to end of D
                } else {
                    move c from C to end of D
                }

            append B and C to the end of D
            return D;
        }

        T(n) = 2 T(N/2) + O(n) (merge run time)
        d = 1, log a = log 2 to base 2 = 1

        T(n) = O(n^d log n) = O(n log n)

        Merge Sort - O(n log n), extra memory,

        TREE DEPTH

        1) numOfLeaves = n!
        2) maxNumOfComparisons = worst case is depth d
        3) d > log l --> 2^d >= l

        3.1) d > log(n!) = sigma(n log n)

        Count Sort - O(n) - Non-comparison based algorithm

        QUICK SORT - Comparison based, O(n log n) on average, O(n^2) on worst case

        PSUEDO CODE

        QuickSort(A, l, r) {
            if(l >= r) return;
            // Randomized quick sort
            k = random value between l and r
            swap A[l] and A[k]
            m = Partition(A, l, r)
            // To deal with equal elements
            (m1,m2) = Partition3(A,l,r)
            QuickSort(A, m+1, r)
            QuickSort(A, l, m-1)
        }

        Partition(A, l, r) {
            pivot = A[l]
            j = l; // no values less than i identified
            // implicitly i represents end of larger partition set that will eventually merge with r
            for( i = l+1 to r) {
                if(pivot < A[j]) {
                    j++;
                    swap(A[i], A[j])
                }
            }
            swap(A[l], A[i])
        }

        Unbalanced Sorting1:
        T(n) = n + T(n-1) -> O(n^2)
        Balanced Sorting1:
        T(n) = 2 T(n/2) + n -> O(n log n)

        n = work at each level for all sub-problems.

        Recursion: Storage on stack.

        Recursion tree depth: Increases, memory on stack increases (becomes heap sort).

     */
}
