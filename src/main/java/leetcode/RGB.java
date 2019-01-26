package leetcode;

public class RGB {

    private static String nearestRGB(String color) {
        StringBuilder sb = new StringBuilder("#");
        for(int i = 1; i < color.length(); i = i+2) {
            sb.append(getClosestHex(color.charAt(i), color.charAt(i+1)));
        }
        String actual = sb.toString();
        System.out.println("Actual : " + actual);
        return actual;
    }

    private static String getClosestHex(char c1, char c2) {
        int d1 = Character.isDigit(c1) ? c1 - '0' : 10 + c1 - 'a';
        int d2 = Character.isDigit(c2) ? c2 - '0' : 10 + c2 - 'a';

        // Range - 0 to 255
        int sum = d1 * 16 + d2;
        /* {0 -> {0,0} at 0; 1 -> {1,1} at 17; 2 -> {2,2} at 34, ..., 14 -> {e,e} at 238; 15 -> {f,f} at 255 */
        int index = sum / 17;
        int remainder = sum % 17;
        index = remainder > 8 ? index + 1 : index;
        // int to hex
        char c = 0 <= index && index <= 9 ? (char)('0' + index) : (char)('a' + index - 10);
        return String.valueOf(c) + String.valueOf(c);
    }
    public static void main(String[] args) {
        String color1 = "#09f166";
        System.out.println("Expected : #11ee66");
        nearestRGB(color1);
    }
}
