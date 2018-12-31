package bbb.Bits;

public class GrayNumber {

    private static boolean isGrayNumber(int a, int b) {
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        int onesForDifferingBits = a ^ b;
        int onesInLeastSignificantBit = 0;
        while(onesForDifferingBits > 0) {
            onesInLeastSignificantBit += onesForDifferingBits & 1;
            onesForDifferingBits = onesForDifferingBits >> 1;
        }
        return onesInLeastSignificantBit == 1;
    }

    public static void main(String[] args) {

        System.out.println("Is Gray Number : " + isGrayNumber(127, 126));
    }
}
