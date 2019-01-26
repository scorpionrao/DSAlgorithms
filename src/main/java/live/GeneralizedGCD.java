package live;

class GeneralizedGCD {

    public static int generalizedGCD(int[] arr){

        int gcd = arr[0];
        for(int i = 0; i < arr.length; i++) {
            gcd = gcdFast(gcd, arr[i]);
        }
        return gcd;
    }

    private static int gcdFast(int a, int b) {
        if(b > a) {
            return gcdFast(b, a);
        }
        if(b == 0) {
            return a;
        }

        int c = a % b;
        return gcdFast(b, c);
    }

    private static void evaluate(int[] input, int expected) {
        int result = generalizedGCD(input);
        System.out.println("Expected: \t" + expected);
        System.out.println("Actual: \t" + result);
    }

    public static void main(String[] args) {
        int[] input1 = {2, 3, 4, 5, 6};
        evaluate(input1, 1);
        int[] input2 = {2, 4, 6, 8, 10};
        evaluate(input2, 2);
    }


}
