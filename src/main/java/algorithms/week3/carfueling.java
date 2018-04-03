package algorithms.week3;

public class carfueling {

    public int minRefills(int[] x, int L) {
        int numOfRefills = 0;
        int currentLocation = 0;
        while(currentLocation <= x.length) {
            // keep solving
            int temp = currentLocation;
            while(currentLocation <= x.length
                    && (x[currentLocation + 1] - x[currentLocation]) <= L) {
                currentLocation++;
            }
            if(currentLocation == temp) {
                return Integer.MAX_VALUE;
            }
            if(currentLocation <= x.length) {
                numOfRefills++;
            }
        }
        return numOfRefills;
    }
}
