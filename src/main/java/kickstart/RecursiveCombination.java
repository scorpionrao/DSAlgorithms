package kickstart;

/*
    Functional Recursion - Each recursion
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
        for(int i = 0; i < str.length(); i++) {
            combinations(0,"", str, i+1);
            System.out.println("*********************************");
        }
    }

    // Procedural recursion
    public static void combinations(int level, String soFar, String remaining, int remainingSize) {

        System.out.println(String.format("Level: %d, SoFar: %s, Remaining: %s", level, soFar, remaining));
        if(remaining.length() < remainingSize) {
            return;
        }

        if(remainingSize == 0) {
            System.out.println(soFar);
            return;
        }
        String included = soFar + remaining.charAt(0);
        combinations(level+1, included, remaining.substring(1), remainingSize-1);
        String excluded = soFar;
        combinations(level+1, excluded, remaining.substring(1), remainingSize);
    }
}
