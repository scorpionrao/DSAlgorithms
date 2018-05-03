package kickstart;

import java.util.Stack;

public class Postfix {
    public static String infixToPostfix(String expression) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if(isOperand(ch)) {
                sb.append(ch);
            } else if(isOperator(ch)) {
                while(!stack.isEmpty() && hasHigherPrecedence(stack.peek(), ch)) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            } else {
                // not reachable
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private static boolean isOperand(Character ch) {
        return !isOperator(ch);
    }

    private static boolean isOperator(Character ch) {
        return ch == '-' || ch == '+' || ch == '*' || ch == '/';
    }

    private static boolean hasHigherPrecedence(Character top, Character ch) {
        if(ch == top) {
            return false;
        } else if (ch == '*' || ch == '/') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String expression = "A+B*C-D*E";
        String expected = "ABC*+DE*-";
        String actual = infixToPostfix(expression);
        System.out.println(String.format("Expected: \t%s,\nActual: \t%s", expected, actual));
    }
}
