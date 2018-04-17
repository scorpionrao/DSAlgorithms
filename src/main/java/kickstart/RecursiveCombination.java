package kickstart;

/*
    Functional Recursion - Each recursion
 */
public class RecursiveCombination {

    public static int combinations(int n, int k) {
        if(k == 0 || k == n) {
            return 1;
        }
        return combinations(n-1, k-1) + combinations(n-1, k);
    }
}
