package leetcode;

public class IntegerReplacement {

    /*
        Repeat below steps until N does not reach 1

        So the logic is:
            If n is even, halve it.
            N is odd --> decrement (3 or less ones for decrement).
            N is odd --> increment (less ones for increment).

        Optimized:
            If n is even, halve it.
            Odd ends with *01 or *11
            *01 >> *0 (since even) -> decrement.
            *11 >> *1 (since odd) -> increment is same as decrement.
     */
    public static int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            // n & 1 <--> n % 2
            if ((n & 1) == 0) {
                // n >>> 1 <--> n / 2
                n = n >> 1;
            //} else if (n == 3 || Integer.bitCount(n - 1) < Integer.bitCount(n + 1))
            } else if (n == 3 || ((n >> 1) & 1) == 0) {
                --n;
            } else {
                ++n;
            }
            // count is always common
            ++count;
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(integerReplacement(Integer.MAX_VALUE - 1));
    }
}
