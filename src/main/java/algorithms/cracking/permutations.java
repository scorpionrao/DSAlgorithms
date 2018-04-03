package algorithms.cracking;

public class permutations {
    /*
        combinations of last char: ['A', 'B', 'C', 'D', 'E']
        combinations of last 2 char: ['AB', 'AC', 'AD', 'AE',
                                      'BA', 'BC', 'BD', 'BE',
                                      'CA', 'CB', 'CD', 'CE',
                                      'DA', 'DB', 'DC', 'DE',
                                      'EA', 'EB', 'EC', 'ED']

        how to reuse it ?

     */

    void permutation(String str) {
        System.out.println(permutation(str, ""));
    }

    int permutation(String str, String prefix) {
        int count = 0;
        // n C r = n C 0 = n!
        if(str.isEmpty() || prefix.length() == str.length()) {
        // n C r = n C 2 = n! / 2
        // if(str.length() <= 2) {
            System.out.println(prefix);
            count++;
        } else {
            for(int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                count = count + permutation(rem, prefix + str.charAt(i));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        new permutations().permutation("A");
    }
}
