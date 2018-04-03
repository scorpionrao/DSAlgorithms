package algorithms.cracking;

public class StringRotation {

    private static boolean isEqual(String s1, String s2) {
        return s1.equals(s2);
    }

    private static boolean isSubString(String s1, String s2) {
        for(int i = 0; i < s1.length() - s2.length() + 1; i++) {
            if(isEqual(s1.substring(i, i + s2.length()), s2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRotation(String s1, String s2) {
        return isSubString(s1 + s1, s2);
    }

    private static boolean isSubStringLibrary(String longerString, String shorterString) {
        return longerString.indexOf(shorterString) >= 0;
    }

    public static void main(String[] args) {
        System.out.println(isRotation("waterbottle", "erbottlewat"));
    }
}
