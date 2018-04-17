package kickstart;

import java.util.Set;

/**
 * Created by manushaonly on 4/15/18.
 */
public class RecursivePermutations {

    public static void main(String[] args) {
        wrapper("type");
    }

    public static void wrapper(String str) {
        // nP1, nP2 .... nPn
        for(int i = 0; i < str.length(); i++) {
            permutations("", str, i+1);
        }
    }

    public static void permutations(String soFar, String remaining, int r) {
        // nPn
        // if(remaining.isEmpty())
        // nPr
        if(soFar.length() == r) {
            System.out.println(soFar);
            return;
        }
        for(int i = 0; i < remaining.length(); i++) {
            String prefix = soFar + remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i+1);
            permutations(prefix, suffix, r);
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
