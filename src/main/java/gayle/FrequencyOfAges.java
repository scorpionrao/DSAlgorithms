package gayle;

public class FrequencyOfAges {

    private static int[] frequency(int[] input) {
        int lastElement = input[input.length - 1];
        int maxSize = lastElement + 1;

        int[] freq = new int[maxSize];
        frequency(input, 0, input.length - 1, freq);
        return freq;
    }

    /*
        Time complexity - M Log N

        M - number of unique elements.
        N - number of elements.
    */
    private static void frequency(int[] input, int begin, int end, int[] freq) {
        System.out.println("Solving: Begin = " + begin + ", End = " + end);
        if(input[begin] == input[end]) {
            int value = input[begin];
            freq[value] = end - begin + 1;
        } else {
            frequency(input, begin, (begin+end)/2, freq);
            frequency(input, (begin+end)/2 + 1, end, freq);
        }
    }

    public static void main(String[] args) {
        int[] inputArray = {8, 8, 8, 9, 9, 11, 15, 16, 16, 16};
        int[] outputArray = frequency(inputArray);
        for(int i = 0; i < outputArray.length; i++) {
            if(outputArray[i] != 0) {
                System.out.println(i + " : " + outputArray[i]);
            }
        }
    }
}
