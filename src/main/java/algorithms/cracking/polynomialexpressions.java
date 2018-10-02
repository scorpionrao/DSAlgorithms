package algorithms.cracking;

public class PolynomialExpressions
{

    public static class ExprTerm {
        public double coefficient;
        public double exponent;

        ExprTerm(double coefficient, double exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    ExprTerm[] sum(ExprTerm[] exp1, ExprTerm[] exp2) {

        ExprTerm[] result = new ExprTerm[exp1.length];
        for(int i = 0; i < exp1.length; i++) {
            double exponent = exp1[i].coefficient + exp2[i].coefficient;
            result[i] = new ExprTerm(exponent, exp1[i].exponent);
        }
        return result;
    }

    public static void main(String[] args) {
        ExprTerm term1 = new ExprTerm(5, 2);
        ExprTerm term2 = new ExprTerm(2, 1);
        ExprTerm term3 = new ExprTerm(12, 0);
        ExprTerm term4 = new ExprTerm(3, 2);
        ExprTerm term5 = new ExprTerm(15, 1);
        ExprTerm term6 = new ExprTerm(5, 0);

        ExprTerm[] exp1 = {term1, term2, term3};
        ExprTerm[] exp2 = {term4, term5, term6};
        ExprTerm[] result = new PolynomialExpressions().sum(exp1, exp2);

        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i].coefficient + " " + result[i].exponent);
        }
    }
}
