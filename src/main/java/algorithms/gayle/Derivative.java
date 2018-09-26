package algorithms.gayle;

import java.util.ArrayList;
import java.util.List;

public class Derivative {

    public static class Term {

        int exponent;
        int coefficient;

        public Term(int exponent, int coefficient) {
            if(exponent < 0) {
                throw new IllegalArgumentException("Negative exponents not allowed");
            }
            this.exponent = exponent;
            this.coefficient = coefficient;
        }

        public int getExponent() {
            return this.exponent;
        }

        public int getCoefficient() {
            return this.coefficient;
        }

        @Override
        public String toString() {
            return this.exponent + "-->" + this.coefficient;
        }
    }

    public static class Solution {

        public List<Term> derivative(List<Term> terms) {
            if(terms == null) {
                throw new IllegalArgumentException("terms is null");
            }

            List<Term> result = new ArrayList<>();
            for(Term term : terms) {
                if(term.getExponent() == 0) {
                    continue;
                }
                int exponent = term.getExponent() - 1;
                int coefficient = term.getCoefficient() * term.getExponent();
                result.add(new Term(exponent, coefficient));
            }
            return result;
        }

    }

    public static void main(String[] args) {
        Term term2 = new Term(2, 5); // 5 x^2
        Term term1 = new Term(1, 3); // 3 x^1
        Term term0 = new Term(0, 4); // 4 x^0
        //Term termNegative1 = new Term(-1, 7); // 7 x^-1

        List<Term> terms = new ArrayList<>();
        //terms.add(termNegative1);
        terms.add(term0);
        terms.add(term1);
        terms.add(term2);

        List<Term> derivative = new Solution().derivative(terms);
        System.out.println(derivative.toString());
    }
}
