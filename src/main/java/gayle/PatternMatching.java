package gayle;

public class PatternMatching {

    private static boolean isMatched(String regex, String str) {
        //System.out.println("isMatched - " + regex + " : " + str);
        if (str.isEmpty()) {
            // Match is true when regex is exhausted or it's last char is "*" - allowing optional str
            return regex.isEmpty() || regex.charAt(regex.length() - 1) == '*';
        }

        if (regex.isEmpty()) {
            // Match is true only if str is fully consumed
            // previous check for string is empty failed to reach here.
            return false;
        }

        Character curRegCh = regex.charAt(0);
        Character nextRegCh = regex.length() >= 2 ? regex.charAt(1) : null;
        Character curStrCh = str.length() != 0 ? str.charAt(0) : null;

        if (nextRegCh == null || (nextRegCh != '*' && nextRegCh != '+')) {
            // This is a simple match - just take the first char from both regex and str and recurse IFF current match is detected
            return isCharMatched(curRegCh, curStrCh) && isMatched(regex.substring(1), str.substring(1));
        } else {
            if (nextRegCh == '*') {
                // The current regex char is followed by "*" - create 2 branches:
                // - MORE branch - one with unmodified regex and reduced str IF current match detected - meaning to continue repetition if possible
                // - ZERO branch - the other one with reduced regex and unmodified str - meaning to try out the optional nature of "*"
                return (isCharMatched(curRegCh, curStrCh) && isMatched(regex, str.substring(1)))
                        || isMatched(regex.substring(2), str);
            } else if (nextRegCh == '+') {
                // The current regex char is followed by "+" - reduce to 1 branch with "*" instead of "+"
                return isCharMatched(curRegCh, curStrCh) && isMatched(curRegCh + "*" + regex.substring(2), str.substring(1));
            } else {
                return false;
            }
        }
    }

    private static boolean isCharMatched(Character regexCh, Character strCh) {
        //System.out.println("isCharMatched - " + regexCh + " : " + strCh);
        return regexCh == strCh || (regexCh == '.' && strCh >= 'a' && strCh <= 'z');
    }

    private static void evaluate(String regex, String str) {
        System.out.println(String.format("Regex: %s \t String: %s = ", regex, str) + isMatched(regex, str));
    }

    public static void main(String[] args) {
        System.out.println("TRUE CASES");
        evaluate("a*b", "aaab");
        evaluate("a*b", "b");
        evaluate("a*b", "ab");
        evaluate("a*b", "aab");
        System.out.println("FALSE CASES");
        evaluate("a+aabc", "ab");
        evaluate("a+aabc", "aabc");
        evaluate("a+aabc", "aaabc");
        evaluate("a+aabc", "aaaabc");
        System.out.println("TRUE CASES");
        evaluate("aa*b*ab+", "aab");
        evaluate("aa*b*ab+", "aabab");
        evaluate("aa*b*ab+", "aaaabbab");
        System.out.println("TRUE CASES");
        evaluate("a+a*b*", "a");
        evaluate("a+a*b*", "ab");
        evaluate("a+a*b*", "aab");
        evaluate("a+a*b*", "aaabb");
    }
}
