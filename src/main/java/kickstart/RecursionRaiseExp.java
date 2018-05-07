package kickstart;

public class RecursionRaiseExp
{

    /*
        2^3 = 8
        raiseExp(2,3) -> raiseExp(2, 1) -> raiseExp

        T(n) = c+T(n/2) = c+c+T(n/4) = c+c+c+T(n/8) = 3c+T(n/2^3) = kc+T(n/2^k) = kc+T(n/2^log n) = kc+T(n/n) = kc+T(1)
             = O(k) + O(1) = O(k+1) = O(k)
     */

    public static int raiseExp(int base, int exp) {
        if(exp == 0) {
            return 1;
        }
        int half = raiseExp(base, exp / 2);
        int doubler = half * half;
        if(exp % 2 == 0) {
            return doubler;
        } else {
            return doubler * base;
        }
    }

    public static void main(String[] args) {
        System.out.println(raiseExp(2,0));
    }
}
