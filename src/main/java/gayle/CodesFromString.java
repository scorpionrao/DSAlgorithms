package gayle;

import java.util.*;

public class CodesFromString {

    public static Set<String> decode(String prefix, String remaining) {

        System.out.println("Prefix: " + prefix + ", Remaining: " + remaining);
        Set<String> set = new HashSet<>();

        // nothing else to parse
        if (remaining.length() == 0) {
            set.add(prefix);
            System.out.println(prefix);
            return set;
        }

        if (remaining.charAt(0) == '0') {
            return set;
        }

        // solve single characters - left to right
        char firstChar = remaining.charAt(0);
        Set<String> singleCharParsing = decode(prefix + (char) (firstChar - '1' + 'a'), remaining.substring(1));
        set.addAll(singleCharParsing);

        // solve group of 2 characters, 10 - 19 - left to right
        if (remaining.length() >= 2 && firstChar == '1') {
            char secondChar = remaining.charAt(1);
            Set<String> tensCharParsing = decode(prefix + (char) (10 + secondChar - '1' + 'a'), remaining.substring(2));
            set.addAll(tensCharParsing);
        }

        // solve group of 2 characters, 20 - 26 - left to right
        if (remaining.length() >= 2 && firstChar == '2' && remaining.charAt(1) <= '6') {
            char secondChar = remaining.charAt(1);
            Set<String> twentiesCharParsing = decode(prefix + (char) (20 + secondChar - '1' + 'a'), remaining.substring(2));
            set.addAll(twentiesCharParsing);
        }

        return set;
    }

    public static void main(String[] args) {
        decode("", "1134");
    }
}
