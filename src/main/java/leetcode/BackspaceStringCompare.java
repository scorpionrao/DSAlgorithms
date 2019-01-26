package leetcode;

import java.util.Stack;

public class BackspaceStringCompare {

    /* Time : O(N+M), Space : O(N+M) */
    public static boolean backspaceCompareApproach1(String S, String T) {
        if(S == T) {
            return true;
        } else if(S == null || T == null) {
            return false;
        }
        Stack<Character> stack1 = process1(S);
        Stack<Character> stack2 = process1(T);
        return isEqual(stack1, stack2);
    }

    private static Stack<Character> process1(String S) {
        Stack<Character> stack = new Stack<>();
        for(char ch : S.toCharArray()) {
            if(ch != '#') {
                stack.push(ch);
            } else {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return stack;
    }

    private static boolean isEqual(Stack<Character> stack1, Stack<Character> stack2) {
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            if(stack1.pop() != stack2.pop()) {
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    /* Time : O(N+M), Space : O(1) */
    public static boolean backspaceCompareApproach2(String S, String T) {
        String processedS = process2(S);
        String processedT = process2(T);
        return processedS.equals(processedT);
    }

    private static String process2(String str) {
        StringBuilder sb = new StringBuilder();
        int backSpaceCount = 0;
        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if(ch == '#') {
                backSpaceCount++;
            } else if(backSpaceCount == 0) {
                sb.append(ch);
            } else {
                backSpaceCount--;
            }
        }
        return sb.toString();
    }

    private static void evaluate(String S, String T) {
        System.out.println("Input : " + S + ", " + T);
        boolean result1 = backspaceCompareApproach1(S, T);
        System.out.println("Approach 1 = " + result1);
        boolean result2 = backspaceCompareApproach2(S, T);
        System.out.println("Approach 2 = " + result2);
    }

    public static void main(String[] args) {
        evaluate("ab#c", "ad#c");
        evaluate("ab##", "c#d#");
        evaluate("a##c", "#a#c");
        evaluate("a#c", "b");
    }
}
