package gayle;

public class PatternMatching {

    /*
        TRUE - if regex is empty or last char is *

        "a*b", "b"

        curRegexCh = 'a'
        nextRegexCh = '*'
        curStrCh = 'b'



     */

    private static boolean isMatched(String regex, String str) {
        if (str.isEmpty()) {
            return regex.isEmpty() || regex.charAt(regex.length() - 1) == '*';
        }

        if (regex.isEmpty()) { // String is not empty
            return false;
        }

        Character firstRegexCh = regex.charAt(0);
        Character secondRegexCh = regex.length() >= 2 ? regex.charAt(1) : null;
        Character firstStrCh = !str.isEmpty() ? str.charAt(0) : null;

        if (secondRegexCh == null || (secondRegexCh != '*' && secondRegexCh != '+')) {
            // This is a simple match - just take the first char from both regex and str and recurse IFF current match is detected
            return isCharMatched(firstRegexCh, firstStrCh) && isMatched(regex.substring(1), str.substring(1));
        } else {
            if (secondRegexCh == '*') {
                return (isCharMatched(firstRegexCh, firstStrCh) && isMatched(regex, str.substring(1)))
                        || isMatched(regex.substring(2), str);
            } else if (secondRegexCh == '+') {
                // The current regex char is followed by "+" - reduce to 1 branch with "*" instead of "+"
                return isCharMatched(firstRegexCh, firstStrCh) && isMatched(firstRegexCh + "*" + regex.substring(2), str.substring(1));
            } else {
                return false;
            }
        }
    }

    private static boolean isCharMatched(Character regexCh, Character strCh) {
        return regexCh == strCh || (regexCh == '.' && Character.isLetter(strCh));
    }

    private static void evaluate(String regex, String str) {
        System.out.println(String.format("Regex: %s \t String: %s = ", regex, str) + isMatched(regex, str));
    }

    /*
        * - Zero or more
     */
    public static void main(String[] args) {
        System.out.println(" * -> Zero or more : TRUE CASES");
        evaluate("a*b", "b");
        evaluate("a*b", "ab");
        evaluate("a*b", "aab");
        evaluate("a*b", "aaab");
        System.out.println();
        System.out.println(" + -> One or more : TRUE CASES");
        evaluate("a+aabc", "aaabc");
        evaluate("a+aabc", "aaaabc");
        System.out.println();
        System.out.println(" + -> One or more : FALSE CASES");
        evaluate("a+aabc", "aabc");
        System.out.println();
        System.out.println(" * and + : TRUE CASES");
        evaluate("aa*b*ab+", "aab");
        evaluate("aa*b*ab+", "aabab");
        evaluate("aa*b*ab+", "aaaaaaaaabbbbbbbbbbbab");
        System.out.println();
        System.out.println("TRUE CASES");
        evaluate("a+a*b*", "a");
        evaluate("a+a*b*", "ab");
        evaluate("a+a*b*", "aab");
        evaluate("a+a*b*", "aaabb");
    }
}
