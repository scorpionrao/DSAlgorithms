package kickstart;

/*
    Functional Recursion - Each recursion
 */
public class RecursiveCombination {

    public static void main(String[] args) {
        wrapper("typewriter");
    }

    public static void wrapper(String str) {
        // nC1, nC2 .... nCn
        combinations("", str, 3);
    }

    // Procedural recursion
    public static void combinations(String soFar, String remaining, int remainingSize) {

        if(remaining.length() < remainingSize) {
            return;
        }

        if(remainingSize == 0) {
            System.out.println(soFar);
            return;
        }
        String included = soFar + remaining.charAt(0);
        combinations(included, remaining.substring(1), remainingSize-1);
        String excluded = soFar;
        combinations(excluded, remaining.substring(1), remainingSize);
    }

}
