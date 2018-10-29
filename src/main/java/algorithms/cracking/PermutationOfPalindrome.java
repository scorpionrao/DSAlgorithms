package algorithms.cracking;

public class PermutationOfPalindrome
{


    static int a = 'a';
    static int z = 'z';

    public static int getCharNumber(Character c) {
        if (a <= c && c <= z) {
            return c - a;
        }
        return -1;
    }

    public static int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int['z' - 'a' + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    public static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for(int i = 0; i < table.length; i++) {
            if(table[i] % 2 != 0) {
                if(foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    /*

        ClosestXdestinations: Phrase is palindrome if ONE or NO odd entries exist in frequency table.

        N - Length of Phrase
        M - Length of Charset

        Time - O(N + M)
        Space - O(M)

     */
    public static boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    public static void main(String[] args) {
        System.out.println(isPermutationOfPalindrome("ayllamama"));
    }

}
