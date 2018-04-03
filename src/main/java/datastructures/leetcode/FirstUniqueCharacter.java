package datastructures.leetcode;

public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        int a = 'a';
        System.out.println(str.charAt(0) + " = " + (str.charAt(0) - a));
        System.out.println(str.charAt(str.length() - 1) + " = " + (str.charAt(str.length() - 1) - a));
    }
}
