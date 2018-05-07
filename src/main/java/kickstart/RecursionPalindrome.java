package kickstart;

public class RecursionPalindrome {

    /*
        T(n) = c+T(n-2) = c+c+T(n-4) = c+c+c+T(n-6) = 3c+T(n-2*3) = kc+k/2*c+T(1) = c * (k + k/2 + 1) = c * (2k+k+2)
             = c * (3k+2)/2 = O(k)
     */

    public static boolean isPalindrome(String str) {
        if(str.length() <= 1) {
            return true;
        }
        return str.toLowerCase().charAt(0) == str.toLowerCase().charAt(str.length() - 1)
                && isPalindrome(str.substring(1, str.length() - 1));
    }

    public static void main(String[] args)
    {
        String str = "mAdam";
        System.out.println(str.substring(1, str.length() - 1));
        System.out.println(isPalindrome(str));
    }
}
