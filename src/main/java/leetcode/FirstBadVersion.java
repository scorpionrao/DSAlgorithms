package leetcode;

public class FirstBadVersion {

    private static long firstBadVersion;

    public static int firstBadVersion(int n) {
        long low = 1;
        long high = n;
        while(low < high) {
            long mid = (low + high) / 2;
            if(isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int) low;
    }

    private static boolean isBadVersion(long version) {
        return version == firstBadVersion;
    }

    private static void evaluate(int n, int version) {
        firstBadVersion = version;
        System.out.println("Input = " + n + ", First Bad Version = " + version);
        int result = firstBadVersion(n);
        System.out.println("Result : " + (firstBadVersion == result));
    }

    public static void main(String[] args) {
        evaluate(5, 4);
        evaluate(2126753390, 1702766719);
    }
}
