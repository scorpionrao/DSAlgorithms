package algorithms.greedy;

/*
Greedy Algorithms

- Reduction to Subproblem
- Safe move - if there is an optimal solution consistent with this first move

- refill at closest station - not a safe move
- refill at farthest station - safe move

- currentRefill change from 0 to n and increments by 1.
- numOfRefills change from 0 to n and increments by 1.
 */
public class refills {

    /*
    Input: L - max distance, x[] distance from origin,

    Output: minRefills

    Psuedo Code:

    currentPosition = 0;
    while currentPosition <= target {

        keep going until a gas station is less than L {
            update currentPosition
        }
        if(currentPosition == lastRefill) not way
        if(currentPosition < target) numOfRefills++
    }
     */
    public static int minRefills(int[] x, int L) {
        int numOfRefills = 0;
        int currentRefill = 0;
        while(currentRefill <= x.length - 2) {
            int lastRefill = currentRefill;
            while((currentRefill <= (x.length - 2)) && ((x[currentRefill + 1] - x[lastRefill]) <= L)) {
                currentRefill++;
            }
            if(currentRefill == lastRefill) {
                return Integer.MAX_VALUE;
            }
            if(currentRefill <= x.length - 2) {
                numOfRefills++;
            }
        }
        return numOfRefills;
    }

    public static void main(String[] args) {
        int[] x = {0, 3, 5, 9, 11, 13, 14, 17, 19, 23};
        System.out.println(minRefills(x, 6));
    }
}
