package kickstart;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by manushaonly on 4/15/18.
 */
public class RecursivePermutations {

    public static void permutations(String soFar, String remaining) {

        if(remaining.isEmpty()) {
            //System.out.println(soFar);
            return;
        }
        for(int i = 0; i < remaining.length(); i++) {
            String prefix = soFar + remaining.charAt(i);
            String suffix = remaining.substring(0, i) + remaining.substring(i+1);
            //System.out.println(String.format("Prefix: %s, Suffix: %s", prefix, suffix));
            permutations(prefix, suffix);
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

    public static void wrapper(String str) {
        permutations("", str);
        isAnagramInLexicon("", str, new HashSet<>());
    }

    public static void main(String[] args) {
        wrapper("cat");
    }
}
