package algorithms.cracking;

public class LongestSubArrayOfEqualLettersAndNumbers {

    static int times = 0;

    /*
        Time - O(N^2)
     */
    public static char[] findLongestSubArray(char[] array) {
        for( int exploringLength = array.length; exploringLength > 1; exploringLength--) {
            // System.out.println("Exploring length = " + exploringLength);
            for(int rangeBegin = 0; rangeBegin <= array.length - exploringLength; rangeBegin++) {
                // System.out.println("IndexRange Begin = " + rangeBegin);
                int start = rangeBegin;
                int end = rangeBegin + exploringLength - 1;
                // System.out.println("Start = " + start + ", End = " + end);
                if(hasEqualLettersNumbers(array, start, end)) {
                    return extractSubArray(array, start, end);
                }
            }
            System.out.println("****************************************");
        }
        return null;
    }

    public static boolean hasEqualLettersNumbers(char[] array, int start, int end) {
        times++;
        int counter = 0;
        for(int i = start; i <= end; i++) {
            if(Character.isLetter(array[i])) {
                counter++;
            } else if (Character.isDigit(array[i])) {
                counter--;
            }
        }
        return counter == 0;
    }

    public static char[] extractSubArray(char[] array, int start, int end) {
        char[] subArray = new char[end - start + 1];
        for(int i = start; i <= end; i++) {
            subArray[i - start] = array[i];
        }
        return subArray;
    }

    public static void main(String[] args) {
        String str = "AB1AAA111A1";
        char[] result = findLongestSubArray(str.toCharArray());
        System.out.println(new String(result));
        System.out.println(times);
    }
}
