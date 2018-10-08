package gayle;

public class NBitBinary {

    public static void grayCode(int number) {
        double total = Math.pow(2, number)-1;
        for(int i = 0; i <= total; i++){
            int toGrayCode = (i >>> 1) ^ i;
            //System.out.println(i + " : " + Integer.toBinaryString(i));
            System.out.println(i + " : " + Integer.toBinaryString(toGrayCode));
        }
    }

    public static void main(String[] args) {
        grayCode(5);
    }
}

