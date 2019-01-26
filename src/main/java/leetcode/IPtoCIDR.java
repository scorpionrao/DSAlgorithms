package leetcode;

import java.util.ArrayList;
import java.util.List;

public class IPtoCIDR {

    private static List<String> convertIpToLong(String ip, int ipCount) {
        String[] blocks = ip.split("\\.");

        // String to Long
        long sum = 0;
        for(String block : blocks) {
            sum = sum * 256 + Integer.parseInt(block);
        }

        List<String> result = new ArrayList<>();
        while(ipCount > 0) {
            int trailingZeros = Long.numberOfTrailingZeros(sum);
            int maxAvailable = (int) Math.pow(2, trailingZeros);
            int bitVal = 1;
            int count = 0;
            while(bitVal < ipCount && count < maxAvailable) {
                bitVal = bitVal * 2;
                count++;
            }
            if(bitVal > ipCount) {
                bitVal = bitVal / 2;
                count--;
            }
            result.add(toString(sum, 32 - count));
            ipCount = ipCount - bitVal;
            sum = sum + bitVal;
        }

        return result;
    }

    private static String toString(long number, int range){
        //convert every 8 into an integer
        final int WORD_SIZE = 8;
        StringBuilder sb = new StringBuilder();
        for(int i=3; i>=0; --i){
            sb.append(Long.toString(((number>>(i*WORD_SIZE))&255)));
            sb.append(".");
        }
        sb.setLength(sb.length()-1);
        sb.append("/");
        sb.append(Integer.toString(range));
        return sb.toString();
    }

    public static void main(String[] args) {
        String ip = "255.0.0.7";
        int ipCount = 10;

        System.out.println(convertIpToLong(ip, ipCount).toString());
    }
}
