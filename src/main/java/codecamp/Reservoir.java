package codecamp;

import java.util.*;

public class Reservoir {

    private static final int STREAM_SIZE = 10;
    private static final int K_SIZE = 1;

    /*
        Not suited for STREAM
        Time: O(N^2), Space: O(1) - search is in the list.
        Time: O(N), Space: O(N) - search is in the set.
    */
    private static void approach1Naive(int[] stream) {

        List<Integer> kRandomElements = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        Random random = new Random();

        /* SAME range each time */
        int min = 0;
        int max = STREAM_SIZE - 1;

        // O(K)
        while(kRandomElements.size() < K_SIZE) {
            int randomNum = random.nextInt(max - min + 1) + min;
            // O(1)
            if(!set.contains(stream[randomNum])) {
                set.add(stream[randomNum]);
                kRandomElements.add(stream[randomNum]);
            }
        }
        System.out.println(kRandomElements.toString());
    }

    /*
        Suited for STREAM
        Time: O(N), Space: O(1) - No search
    */
    private static void approach2Optimized(int[] stream) {

        int[] kRandomElements = new int[K_SIZE];
        for(int i = 0; i < K_SIZE; i++) {
            kRandomElements[i] = stream[i];
        }

        Random random = new Random();

        int min = 0;

        // This represents INFINITE STREAM
        for(int receivedSoFar = K_SIZE; receivedSoFar < stream.length; receivedSoFar++) {
            int max = receivedSoFar;
            int randomNum = random.nextInt(max - min + 1) + min;
            if(randomNum < K_SIZE) {
                kRandomElements[randomNum] = stream[receivedSoFar];
            }
        }
        System.out.println(Arrays.toString(kRandomElements));
    }



    private static int result = 0;
    private static int count = 0;


    private static void approach3OneElement(int[] stream) {

        for(int i = 0; i < stream.length; i++) {
            approach3OneElement(stream[i]);
        }
        System.out.println("Result = " + result);
    }

    static int min = 0;
    /* This element does not remember the previous element or how many more elements to come */
    private static void approach3OneElement(int next) {
        count++;
        if(count == 1) {
            result = next;
        } else {

            int max = count;
            int randomNum = new Random().nextInt(max - min + 1) + min;
            // if(randomNum == count) is the same probability
            if(randomNum == 0) {
                result = next;
            } // else ignore
        }
    }

    private static void evaluate(int[] stream) {
        //approach1Naive(stream);
        //approach2Optimized(stream);
        for(int i = 0; i < 10; i++) {
            approach3OneElement(stream);
        }
    }

    public static void main(String[] args) {
        int[] stream = new int[STREAM_SIZE];
        for(int i = 0; i < stream.length; i++) {
            stream[i] = i;
        }
        evaluate(stream);
    }
}