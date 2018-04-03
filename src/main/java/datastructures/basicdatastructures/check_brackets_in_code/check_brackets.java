package datastructures.basicdatastructures.check_brackets_in_code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket1 {
    Bracket1(char type, int position) {
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
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket1> opening_brackets_stack = new Stack<Bracket1>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                Bracket1 bracket = new Bracket1(next, position + 1);
                opening_brackets_stack.push(bracket);
            }

            if (next == ')' || next == ']' || next == '}') {
                Bracket1 openingParanthesis = opening_brackets_stack.pop();
                if(!openingParanthesis.Match(next)) {
                    // 1-based index of unmatched closing bracket
                    System.out.println(position + 1);
                    return;
                }
            }
        }
        if(opening_brackets_stack.isEmpty()) {
            System.out.println("Success");
        } else {
            Bracket1 leftOutBracket = opening_brackets_stack.pop();
            // 1-based index of unmatched opening bracket
            System.out.println(leftOutBracket.position);
        }

    }
}
