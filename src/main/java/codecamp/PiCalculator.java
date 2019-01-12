package codecamp;

public class PiCalculator {

    private static final int INTERVAL = 10;
    private static final double EPSILON = 0.1;

    private static double calculate() {

        int countInsideCircle = 0;

        for(int i = 0; i < INTERVAL; i++) {
            double x = Math.random();
            double y = Math.random();
            double radius = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
            if(radius <= 1.0) {
                countInsideCircle++;
            }
        }
        return 4.0 * countInsideCircle / (double) INTERVAL;

    }

    private static boolean isRandom() {
        int left = 0;
        for(int i = 0; i < INTERVAL; i++) {
            if(Math.random() <= 0.49) {
                left++;
            }
        }
        double percentage = (double) left / (double) INTERVAL;
        return Math.abs(0.5 - percentage) <= EPSILON;
    }

    public static void main(String[] args) {
        double pi = calculate();
        System.out.println("Pi = " + pi);
        System.out.println("Is Random = " + isRandom());
    }
}
