package bbb.Easy;

public class ClockAngle {

    private static double clockShortAngle(int hour, int min) {

        double anglePerHour = 360.0 / 12;
        double anglePerMin = 360.0 / 60;

        double minHandAngleFrom12 = anglePerMin * min;
        double hourHandAngleFrom12 = (anglePerHour * hour) + (min / 60.0 * anglePerHour);
        double angleDifference = Math.abs(minHandAngleFrom12 - hourHandAngleFrom12);
        return angleDifference <= 180 ? angleDifference : 360 - angleDifference;
    }

    public static void main(String[] args) {
        int hour = 11;
        int min = 10;
        double angle = clockShortAngle(hour, min);
        // Expected : 85
        System.out.println("Time - " + hour + ":" + min);
        System.out.println("Expected : 85.0, Actual : " + angle);
    }
}
