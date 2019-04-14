package codecamp;

public class Exponent {

    private static long approach1(int base, int power) {
        long result = 1;
        for(int i = 0; i < power; i++) {
            result = result * base;
        }
        return result;
    }

    private static long approach2(int base, int power) {
        if(power == 0) {
            return 1;
        } else if(power == 1) {
            return base;
        }

        long result = approach2(base, power/2) * approach2(base, power/2);
        return (power % 2 == 0) ? result : result * base;
    }

    private static void evaluate(int base, int power) {
        System.out.println("Base : " + base);
        System.out.println("Power : " + power);
        long result1 = approach1(base, power);
        System.out.println("Approach 1 : " + result1);
        long result2 = approach2(base, power);
        System.out.println("Approach 2 : " + result2);
    }

    public static void main(String[] args) {
        evaluate(2, 10);
    }
}
