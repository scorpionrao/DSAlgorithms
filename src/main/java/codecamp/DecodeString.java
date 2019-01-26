package codecamp;

import java.util.Stack;

public class DecodeString {

    /*
        Assumptions:
            - A number always precedes '['
            - Brackets are well balanced
            - Numbers are allowed only outside brackets.
     */
    private static String decode(String input) {

        Stack<Integer> outerNumbers = new Stack<>();
        Stack<String> outerPrefixes = new Stack<>();
        String innerMost = "";

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if(Character.isDigit(ch)) {
                int count = 0;
                while(i < input.length() && Character.isDigit(ch)) {
                    count = (10 * count) + (ch - '0');
                    i++;
                    ch = input.charAt(i);
                }
                i--;
                outerNumbers.push(count);
            } else if(ch == '[') {
                outerPrefixes.push(innerMost);
                innerMost = "";
            } else if(ch == ']') {
                StringBuilder sb = new StringBuilder(outerPrefixes.pop());
                int times = outerNumbers.pop();
                for(int j = 0; j < times; j++) {
                    sb.append(innerMost);
                }
                innerMost = sb.toString();
            } else {
                innerMost = innerMost + ch;
            }
        }
        return innerMost;
    }

    public static void main(String[] args) {
        String input = "3[a2[bc]]";
        String expected = "abcbcabcbcabcbc";
        System.out.println("Expected : " + expected);
        String actual = decode(input);
        System.out.println("Actual : " + actual);
        System.out.println("Test Passed : " + expected.equals(actual));
    }
}
