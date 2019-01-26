package codecamp;

import java.util.Random;

public class PiCalculator {

    private static final int INTERVAL = Integer.MAX_VALUE;
    private static final double EPSILON = 0.1;

    private static Random random = new Random();

    private static double calculate() {

        int countInsideCircle = 0;

        for(int i = 0; i < INTERVAL; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            double distanceFromOrigin = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
            if(distanceFromOrigin <= 1.0) {
                countInsideCircle++;
            }
        }
        return 4.0 * countInsideCircle / (double) INTERVAL;
    }

    private static boolean isRandom() {

        int left = 0;
        for(int i = 0; i < INTERVAL; i++) {
            if(random.nextDouble() <= 0.49) {
                left++;
            }
        }
        double percentage = (double) left / (double) INTERVAL;
        return Math.abs(0.5 - percentage) <= EPSILON;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        double pi = calculate();
        long end = System.currentTimeMillis();
        System.out.println("Pi = " + pi + " in " + (end - start) / 1000 + " secs.");

        start = System.currentTimeMillis();
        boolean isRandom = isRandom();
        end = System.currentTimeMillis();
        System.out.println("Is Random = " + isRandom + " in " + (end - start) / 1000 + " secs.");

    }
}
