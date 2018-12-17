package bbb.Strings;

public class AnagramChecker {

    private static boolean anagramChecker(String str1, String str2) {

        if(str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        // ignore alphanumeric char set case sensitivity
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // char set in java
        int[] frequency = new int[1 << 8];
        for(char ch : str1.toCharArray()) {
            frequency[ch]++;
        }

        for(char ch : str2.toCharArray()) {
            frequency[ch]--;
            if(frequency[ch] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str1 = "abcd", str2 = "efgh";
        System.out.println("Input : " + str1 + ", " + str2);
        System.out.println("Output : " + anagramChecker(str1, str2));
    }
}
