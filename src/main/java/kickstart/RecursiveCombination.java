package kickstart;

    /*
        OUTPUT SAMPLE:

        Level: 0, , abcd
        Level: 1, a, bcd
        a
        Level: 1, , bcd
            Level: 2, b, cd
            b
            Level: 2, , cd
                Level: 3, c, d
                c
                Level: 3, , d
                    Level: 4, d, 
                    d
                    Level: 4, , 
    *********************************
    Level: 0, , abcd
        Level: 1, a, bcd
            Level: 2, ab, cd
            ab
            Level: 2, a, cd
                Level: 3, ac, d
                ac
                Level: 3, a, d
                    Level: 4, ad, 
                    ad
                    Level: 4, a, 
        Level: 1, , bcd
            Level: 2, b, cd
                Level: 3, bc, d
                bc
                Level: 3, b, d
                    Level: 4, bd, 
                    bd
                    Level: 4, b, 
            Level: 2, , cd
                Level: 3, c, d
                    Level: 4, cd, 
                    cd
                    Level: 4, c, 
                Level: 3, , d
    *********************************
    Level: 0, , abcd
        Level: 1, a, bcd
            Level: 2, ab, cd
                Level: 3, abc, d
                abc
                Level: 3, ab, d
                    Level: 4, abd, 
                    abd
                    Level: 4, ab, 
            Level: 2, a, cd
                Level: 3, ac, d
                    Level: 4, acd, 
                    acd
                    Level: 4, ac, 
                Level: 3, a, d
        Level: 1, , bcd
            Level: 2, b, cd
                Level: 3, bc, d
                    Level: 4, bcd, 
                    bcd
                    Level: 4, bc, 
                Level: 3, b, d
            Level: 2, , cd
    *********************************
    Level: 0, , abcd
        Level: 1, a, bcd
            Level: 2, ab, cd
                Level: 3, abc, d
                    Level: 4, abcd, 
                    abcd
                    Level: 4, abc, 
                Level: 3, ab, d
            Level: 2, a, cd
        Level: 1, , bcd
    *********************************
     */
    public class RecursiveCombination {

        public static void main(String[] args) {
        /*
            String str = "abc";
            for(int i = 0; i < Math.pow(2, str.length()); i++) {
                System.out.println(Integer.toBinaryString(i));
            }
        */
        wrapper("abc");
    }

    public static void wrapper(String str) {
        /*
        for(int i = 0; i < str.length(); i++) {
            combinations(0, "L", "", str, i+1);
            System.out.println("*********************************");
        }
        */
        combinations(0, "L", "", "abc", 2);
    }

    public static void indent(int level) {
        for(int i = 0; i < level; i++) {
            System.out.print("\t");
        }
    }

    // Procedural recursion
    public static void combinations(int level, String soFar, String remaining, int remainingSize) {

        System.out.println(String.format("Level: %d, SoFar: %s, Remaining: %s", level, soFar, remaining));
        if(remaining.length() < remainingSize) {
            return;
        }

        if(remainingSize == 0) {
            indent(level);
            System.out.println(soFar);
            return;
        }
        combinations(level+1, soFar + remaining.charAt(0), remaining.substring(1), remainingSize-1);
        combinations(level+1, soFar, remaining.substring(1), remainingSize);
    }

    public static void combinations(int level, String path, String soFar,
                                    String remaining, int size) {
        indent(level);
        System.out.println(String.format("Level: %d, Path: %s, SoFar: %s, " +
                "Remaining: %s", level, path, soFar, remaining));

        // restrict which size range of leaves to display
        if(soFar.length() == size && remaining.isEmpty()) {
            // rest is empty --> LEAF --> level is length of original string
            indent(level);
            System.out.println(soFar);
            return;
        }
        if(remaining.isEmpty()) {
            return;
        }
        /* Left nodes represent - include */
        combinations(level+1, "L", soFar + remaining.charAt(0), remaining.substring(1), size);
        /* Right nodes represent - exclude */
        combinations(level+1, "R", soFar, remaining.substring(1), size);
    }
}
