package codecamp;

public class RegexPatternMatching {
    /*
        regex = .*at
        string = chats

        firstRegexCh = .
        secondRegexCh = *
        firstStrCh = c


     */
    private static boolean isMatched(String regex, String str) {
        if (str.isEmpty()) {
            return regex.isEmpty() || regex.endsWith("*");
        } else if (regex.isEmpty()) {
            // String is not empty
            return false;
        }

        Character firstRegexCh = regex.charAt(0);
        Character secondRegexCh = regex.length() >= 2 ? regex.charAt(1) : null;
        Character firstStrCh = str.charAt(0);

        // one character remaining or no second special character
        if (secondRegexCh == null || secondRegexCh != '*') {
            // second char exists and
            return isCharMatched(firstRegexCh, firstStrCh) && isMatched(regex.substring(1), str.substring(1));
        } else {
            // Second character = *
            // One occurrence - keep regex and remove string
            return (isCharMatched(firstRegexCh, firstStrCh) && isMatched(regex, str.substring(1)))
                    // Zero occurrence - remove regex and keep string
                    || isMatched(regex.substring(2), str);
        }
    }

    private static boolean isCharMatched(Character regexCh, Character strCh) {
        return regexCh == strCh || (regexCh == '.' && Character.isLetter(strCh));
    }

    private static void evaluate(String regex, String str, boolean expected) {
        System.out.println("Regex : " + regex);
        System.out.println("String : " + str);
        System.out.println("Expected : " + expected + ", Actual : " + isMatched(regex, str));
    }

    public static void main(String[] args) {
        evaluate("ra.", "ray", true);
        evaluate(".*at", "chat", true);
        evaluate(".*at", "chats", false);
    }
}
