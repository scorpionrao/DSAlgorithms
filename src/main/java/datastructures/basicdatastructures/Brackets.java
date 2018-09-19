package datastructures.basicdatastructures;

import java.util.*;

public class Brackets {

    static String isBalanced(String s) {

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> precompute = new HashMap<Character, Character>(){
            {
                put('{', '}');
                put('(', ')');
                put('[', ']');
            }
        };

        char[] charArray = s.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i] == '{' || charArray[i] == '[' || charArray[i] == '(') {
                stack.push(precompute.get(charArray[i]));
            } else {
                if(stack.isEmpty() || stack.pop() != Character.getNumericValue(charArray[i])) {
                    return "NO";
                }
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

    /**
     * This function determines if the braces ('(' and ')') in a string are properly matched.
     * it ignores non-brace characters.
     * Some examples:
     * "()()()()"   -> true
     * "((45+)*a3)" -> true
     * "(((())())"  -> false
     */
    public static boolean matched(String s) throws IllegalArgumentException {
        // Thoughts
        // simple example: () -> true
        /*
        Pseudocode:
        check for valid string
        traverse through the string,
            - whenever you see open parantheses, store the matching close parantheses

       Data structure to be - Stack

       *******
       Runtime Complexity:
       N -> length of the String
       Big O --> O(N)
       Number of comparisons is directly proportional to the N

       Space Complexity:
       String - O(N)
       counter - O(1)

        Extension problem example: ({[a]})

        Psuedocode:
            Have a Stack
            traverse through the entire string one character at a time
                char ch = getChar at i
                if('(') {
                    push ')' into the stack
                else if('{')
                    push '}' into the stack
                else if('[')
                    push ']' into the stack

                else if(')' or '}' or ']') {
                    if(charAt(i) != stack.pop()) {
                        return false;
                    }
                }
            return true;


         */
        if(s == null) {
            throw new IllegalArgumentException("Argument passed is null");
        } else if (s.isEmpty()) {
            return true;
        }

        int counter = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                // allowed counter values here - 0 or above
                counter++;
            } else if (s.charAt(i) == ')') {
                // allowed counter values here - 1 or above
                counter--;
                if(counter < 0) {
                    return false;
                }

            }
        }
        return counter == 0;
    }





    public static void main(String[] args) {
        /*
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            String s = in.next();
            String result = isBalanced(s);
            System.out.println(result);
        }
        in.close();
        */
/*
        System.out.println(matched("()()()()") + ); --> 0,1,0,1,0,1,0,1,0 --> true
        System.out.println()((45+)*a3) --> 0,1,2,1,0 --> true
        System.out.println()(((())()) --> 0,1,2,3,4,3,2,3,2,1 --> false
        System.out.println()
            )( --> 0,-1 --> false
        ;
*/
    }
}
