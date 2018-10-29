package live;

class GeneralizedGCD {

    public static int generalizedGCD(int num, int[] arr){

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }

        // GCD cannot be more than the min
        int gcd = 1;
        for(int i = 1; i <= min; i++) {
            int j;
            for(j = 0; j < arr.length; j++) {
                if(arr[j] % i != 0) {
                    break;
                }
            }
            if(j == arr.length) {
                gcd = Math.max(gcd, i);
            }
        }
        return gcd;
    }

    public static void main(String[] args) {
        int[] input1 = {2, 3, 4, 5, 6};
        int result1 = generalizedGCD(5, input1);
        System.out.println("Expected: \t1");
        System.out.println("Actual: \t" + result1);

        int[] input2 = {2, 4, 6, 8, 10};
        int result2 = generalizedGCD(5, input2);
        System.out.println("Expected: \t2");
        System.out.println("Actual: \t" + result2);
    }
}
