package codecamp;

import java.util.ArrayList;
import java.util.List;

public class NoRepeats {

    private static int permAlone(String input) {
        List<String> list = new ArrayList<>();
        permAlone(input, "", list);
        return list.size();
    }

    private static void permAlone(String remaining, String soFar, List<String> list) {
        if(remaining.isEmpty()) {
            list.add(soFar);
            return;
        }
        for(int i = 0; i < remaining.length(); i++) {
            String str = remaining.substring(0, i) + remaining.substring(i+1);
            if(soFar.isEmpty() || soFar.charAt(soFar.length() - 1) != remaining.charAt(i)) {
                permAlone(str, soFar + remaining.charAt(i), list);
            }
        }
    }
    private static void evaluate(String input, int expected) {
        System.out.println("Input = " + input + ", E: "
                + expected + ", A: " + permAlone(input));
    }

    public static void main(String[] args) {
        evaluate("aab", 2);
        evaluate("aaa", 0);
        evaluate("aabb", 8);
        evaluate("abcdefa", 3600);
        evaluate("abfdefa", 2640);
        evaluate("zzzzzzzz", 0);
        evaluate("a", 1);
        evaluate("aaab", 0);
        evaluate("aaabb", 12);
    }
}
