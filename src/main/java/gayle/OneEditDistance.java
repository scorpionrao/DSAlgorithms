package gayle;

public class OneEditDistance {

    private static boolean oneEdit(String s1, String s2) {

        // reverse
        if(s1.length() > s2.length()) {
            return oneEdit(s2, s1);
        }

        if(s1.length() + 1 < s2.length()) {
            return false;
        }

        /* s1.length() + 1 = s2.length() */
        for(int i = 0; i < s1.length(); i++) {
            // first chance of different characters
            if(s1.charAt(i) != s2.charAt(i)) {
                // length is same
                if(s1.length() == s2.length()) {
                    // remaining has to be same
                    return s1.substring(i+1).equals(s2.substring(i+1));
                } else {
                    // length is not same
                    return s1.substring(i).equals(s2.substring(i+1));
                }
            }
        }

        // all first s1.length() characters match
        return s1.length() == s2.length();
    }

    public static void main(String[] args) {

        System.out.println(oneEdit("xyz", "xyz"));

    }
}
