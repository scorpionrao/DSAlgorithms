package codecamp;

public class EmailValidation {

    //final static String regularExpression = "^[a-z]{1,6}_[1-9]{0,4}@hackerrank.com$";

    final static String regularExpression = "^[a-z]{1,6}_?[0-9]{0,4}@hackerrank.com$";

    /*
            julia@hackerrank.com - T
            julia_@hackerrank.com - T
            julia_0@hackerrank.com - T
            julia0_@hackerrank.com - F
            julia@gmail.com - F
     */
    public static void main(String[] args) {
        boolean test = "julia1@hackerrank.com".matches(regularExpression);
        System.out.println(test);
    }
}
