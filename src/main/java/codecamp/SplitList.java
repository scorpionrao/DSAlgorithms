package codecamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitList {
    private static List<String> split(String input, int length) {
        List<String> result = new ArrayList<>();
        String[] words = input.split(" ");
        int i = 0;
        while(i < words.length) {
            StringBuilder sb = new StringBuilder();
            int prefixLength = 0;
            while(i < words.length && sb.length() + prefixLength + words[i].length() <= length) {
                sb.append(prefixLength == 0 ? "" : " ");
                sb.append(words[i]);
                i++;
                prefixLength = 1;
            }
            if(sb.length() == 0) {
                return null;
            } else {
                result.add(sb.toString());
            }
        }
        return result;
    }
    private static void evaluate(String input, int length, List<String> expected) {
        System.out.println("Input : " + input);
        System.out.println("Length : " + length);
        System.out.println("Expected : " + expected.toString());
        List<String> result = split(input, length);
        System.out.println("Actual   : " + result.toString());
    }

    public static void main(String[] args) {
        String input = "the quick brown fox jumps over the lazy dog";
        int length = 10;
        List<String> expected = Arrays.asList(new String[]{"the quick", "brown fox", "jumps over", "the lazy", "dog"});
        evaluate(input, length, expected);
    }
}
