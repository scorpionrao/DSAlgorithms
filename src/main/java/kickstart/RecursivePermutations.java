package kickstart;

import java.util.Set;

/**
 * Created by manushaonly on 4/15/18.
 */
public class RecursivePermutations {

    public static void main(String[] args) {
      wrapper("abc");
    }

    public static void wrapper(String str) {
      permutations(0,"", str, str.length());
    }

    /*
    *   Prints all permutations upto size "R"
    *
    *   To print specific size ONLY, move print into end of recursion condition
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
    public static void permutations(int level, String soFar, String remaining, int staticR) {
        //System.out.println(String.format("Level: %d, SoFar: %s, Remaining: %s", inner, soFar, remaining));
        // always print, saving 1 scenario, not a huge help
        if(!soFar.isEmpty()) {
            System.out.println(soFar);
        }

        // remaining.isEmpty() -> soFar.length() == staticR
        if(soFar.length() == staticR) {
            // maximum required size of soFar reached, give it up
            return;
        }
        for(int i = 0; i < remaining.length(); i++) {
            String prefix = soFar + remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i+1);
            permutations(level+1, prefix, suffix, staticR);
        }
    }

    public static boolean isAnagramInLexicon(String soFar, String remaining, final Set<String> lexicon) {
        if(remaining.isEmpty()) {
            System.out.println(soFar);
            return lexicon.contains(soFar);
        }
        for(int i = 0; i < remaining.length(); i++) {
            String prefix = soFar + remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i+1);
            if(isAnagramInLexicon(prefix, suffix, lexicon)) {
                return true;
            }
        }
        return false;
    }
}
