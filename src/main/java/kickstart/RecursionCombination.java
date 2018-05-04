package kickstart;

    public class RecursionCombination
    {

        public static void main(String[] args) {
            wrapper("abc");
        }

    public static void wrapper(String str) {
        combinations(0, "L", "", str, 2);
    }

    public static void indent(int level) {
        for(int i = 0; i < level; i++) {
            System.out.print("\t");
        }
    }

    // Procedural recursion
    public static void combinations(int level, String soFar, String remaining, int remainingSize) {

        // System.out.println(String.format("Level: %d, SoFar: %s, " +
                // "Remaining: %s", level, soFar, remaining));
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
        //indent(level);
        //System.out.println(String.format("Level: %d, Path: %s, SoFar: %s, " +
                //"Remaining: %s", level, path, soFar, remaining));

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
