package kickstart;

import java.util.Set;

public class RecursionPermutations
{

    public static void main(String[] args) {
      wrapper("abc");
    }

    public static void wrapper(String str) {
      permutations(0, "", str, 3);
    }

    public static void indent(int level) {
        for(int i = 0; i < level; i++) {
            System.out.print("\t");
        }
    }

    /*
    *
    *   Eg:
    *                       ""
    *       |               |               |
    *       a(1)            b(6)            c(11)
    *    |     |        |         |         |         |
    *    ab(2) ac(4)    ba(7)     bc(9)     ca(12)    cb(14)
    *    |     |        |         |         |         |
    *   abc(3) acb(5)   bac(8)    bca(10)   cab(13)   cba(15)
    */
    public static void permutations(int level, String soFar,
                                    String remaining, int size) {
        // indent(level);
        // System.out.println(String.format("Level: %d, SoFar: %s, Remaining: %s", level, soFar, remaining));

        if(soFar.length() == size) {
            indent(level);
            System.out.println(soFar);
            return;
        }
        for(int i = 0; i < remaining.length(); i++) {
            String prefix = soFar + remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i+1);
            permutations(level+1, prefix, suffix, size);
        }
    }

    /*
        Decision    : Can we take a character ?
        Options     : Add each character
        Design      : return true/false
        Backtracking: choose one option, recursionBacktracking further
        UnMake      : revert the change
        Base Case   : TRUE / Is it in Lexicon ?
        No options  : FALSE
    */
    public static boolean isAnagramInLexicon(String soFar, String remaining,
                                             final Set<String> lexicon) {
        if(remaining.isEmpty()) {
            System.out.println(soFar);
            // Base Case: TRUE / Is it in Lexicon ?
            return lexicon.contains(soFar);
        }
        // Option - Each character in remaining
        for(int i = 0; i < remaining.length(); i++) {
            String prefix = soFar + remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i+1);
            // Choose one option and recursionBacktracking
            if(isAnagramInLexicon(prefix, suffix, lexicon)) {
                return true;
            }
        }
        // No options - False
        return false;
    }
}
