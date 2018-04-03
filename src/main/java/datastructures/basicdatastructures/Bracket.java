package datastructures.basicdatastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;

    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                Bracket bracket = new Bracket(next, position + 1);
                opening_brackets_stack.push(bracket);
            }

            if (next == ')' || next == ']' || next == '}') {
                if(!opening_brackets_stack.isEmpty()) {
                    Bracket openingParanthesis = opening_brackets_stack.pop();
                    if(!openingParanthesis.Match(next)) {
                        // 1-based index of unmatched closing bracket
                        System.out.println(position + 1);
                        return;
                    }
                } else {
                    System.out.println(position + 1);
                    return;
                }

            }
        }
        if(opening_brackets_stack.isEmpty()) {
            System.out.println("Success");
        } else {
            Bracket leftOutBracket = opening_brackets_stack.pop();
            // 1-based index of unmatched opening bracket
            System.out.println(leftOutBracket.position);
        }

    }
}
