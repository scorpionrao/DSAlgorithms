package bbb.Strings;

public class StringCompression {

    /* Time: O(N), Space: O(1) */
    public static String compress(String original) {

        if(original == null) {
            return original;
        }

        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for(int i = 0; i < original.length() - 1; i++) {
            if (original.charAt(i) == original.charAt(i+1)) {
                counter++;
            } else {
                sb.append(original.charAt(i));
                sb.append((counter > 1) ? counter : "");
                counter = 1;
            }
        }
        sb.append(original.charAt(original.length() - 1));
        sb.append((counter > 1) ? counter : "");
        return sb.toString();
    }

    public static void main(String[] args) {
        String original = "aaabccc";
        System.out.println("Original : " + original);
        System.out.println("Compressed : " + compress(original));
    }
}
