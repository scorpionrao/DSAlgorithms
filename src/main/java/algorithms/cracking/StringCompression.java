package algorithms.cracking;

public class StringCompression {

    public static String compress(String str) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for(int i = 0; i < str.length(); i++) {
            counter++;
            if(str.length() <= i+1 || str.charAt(i) != str.charAt(i+1)) {
                sb.append(str.charAt(i));
                sb.append(counter);
                counter = 0;
            }
        }
        return sb.length() < str.length() ? sb.toString() : str;
    }

    public static void main(String[] args) {
        String str = "ab";
        System.out.println(str);
        System.out.println(compress(str));
    }
}
