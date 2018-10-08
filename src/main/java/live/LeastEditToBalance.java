package live;

import java.util.Stack;

public class LeastEditToBalance {

    private static String solve(String str) {

        if(str == null) {
            throw new NullPointerException();
        }

        if(str.isEmpty()) {
            return str;
        }

        StringBuilder sb = new StringBuilder();

        // clean up closed paranthesis
        Stack<Character> openParanthesisStack = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(') {
                openParanthesisStack.push(ch);
                sb.append(ch);
            } else if (ch == ')') {
                if(!openParanthesisStack.isEmpty()) {
                    openParanthesisStack.pop();
                    sb.append(ch);
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input1 = "abc12())";
        System.out.println("Input: " + input1);
        System.out.println("Output: " + solve(input1));
    }
}
