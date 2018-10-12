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
                    sb.append(openParanthesisStack.pop());
                } else {
                    // do nothing --> clean up closed parenthesis
                }
            } else {
                sb.append(ch);
            }
        }

        str = sb.toString();

        // clean up open paranthesis
        Stack<Character> closedParanthesisStack = new Stack<>();
        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if(ch == ')') {
                closedParanthesisStack.push(ch);
                sb.insert(0, ch);
            } else if (ch == '(') {
                if(!closedParanthesisStack.isEmpty()) {
                    sb.insert(0, closedParanthesisStack.pop());
                } else {
                    // do nothing --> clean up closed parenthesis
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
