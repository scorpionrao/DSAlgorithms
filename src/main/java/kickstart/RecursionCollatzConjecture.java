package kickstart;

public class RecursionCollatzConjecture {
    public static int numOfSteps(int n) {
        if(n == 1) {
            return 0;
        }
        if(n % 2 == 0) {
            return 1 + numOfSteps(n/2);
        } else {
            return 1 + numOfSteps(3 * n + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(numOfSteps(27));
    }
}
