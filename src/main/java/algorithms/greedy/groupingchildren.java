package algorithms.greedy;

public class groupingchildren {

    /*
    Input: ages array
    Output: minGroups

    Psuedo code:

    MinGroups(A) {
        int numOfGroups = 0;

        int i = 0;
        while(i <= A.length) {
            currentGroupStart = A[currentGroupStart];
            while(i <= n && (A[i+1] - currentGroupStart) <= 1) {

            }
        }
    }

     */
    public static int minGroups(float[] x, int L) {
        int minGroups = 0;
        int i = 0;
        while(i < x.length) {
            float threshold = x[i] + L;
            minGroups++;
            i++;
            while(i < x.length && x[i] <= threshold) {
                i++;
            }
        }
        return minGroups++;
    }

    public static void main(String[] args) {
        float[] ages = new float[6];
        ages[0] = 3;
        ages[1] = (float) 3.2;
        ages[2] = (float) 3.4;
        ages[3] = (float) 4.2;
        ages[4] = (float) 5.1;
        ages[5] = (float) 5.3;
        System.out.println(minGroups(ages, 1));
    }
}
