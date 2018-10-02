package algorithms.cracking;

public class Permutations {
    /*
        combinations of last char: ['A', 'B', 'C', 'D', 'E']
        combinations of last 2 char: ['AB', 'AC', 'AD', 'AE',
                                      'BA', 'BC', 'BD', 'BE',
                                      'CA', 'CB', 'CD', 'CE',
                                      'DA', 'DB', 'DC', 'DE',
                                      'EA', 'EB', 'EC', 'ED']

        how to reuse it ?

     */

    void permutation(String remaining) {
        System.out.println(permutation(remaining, ""));
    }

    int permutation(String remaining, String fixedPart) {
        int count = 0;
        // n C r = n C 0 = n!
        if(remaining.isEmpty() || fixedPart.length() == remaining.length()) {
        // n C r = n C 2 = n! / 2
        // if(str.length() <= 2) {
            System.out.println(fixedPart);
            count++;
        } else {
            for(int i = 0; i < remaining.length(); i++) {
                String rem = remaining.substring(0, i) + remaining.substring(i + 1);
                count = count + permutation(rem, fixedPart + remaining.charAt(i));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        new Permutations().permutation("ABC");
    }
}
