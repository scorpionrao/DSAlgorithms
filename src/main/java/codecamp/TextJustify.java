package codecamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextJustify {

    public static void main(String[] args) {
        String[] words = {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};

        List<String> lines = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        final int count = 16;
        int index = words.length - 1;
        while(index >= 0) {
            while(index >= 0 && words[index].length() + 1 + sb.length() <= count) {
                sb.insert(0, words[index] + " ");
                index--;
            }
            int rightSpaces = (count - sb.length()) / 2;
            int leftSpaces = count - sb.length() - rightSpaces;
            for(int i = 0; i < rightSpaces; i++) {
                sb.append(" ");
            }
            for(int i = 0; i < leftSpaces; i++) {
                sb.insert(0, " ");
            }
            lines.add(sb.toString());
            sb = new StringBuilder();
        }
        Collections.reverse(lines);
        for(int i = 0; i < count; i++) {
            System.out.print("-");
        }
        System.out.println();
        for(String str : lines) {
            System.out.println(str);
        }
    }
}
