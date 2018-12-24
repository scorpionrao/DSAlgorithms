package bbb.Hard;

public class SumWithoutArithmetricOperator {

    private static int sum(int a, int b) {
        if(b == 0) {
            return a;
        }
        int partialSum = a ^ b;
        int carry = (a & b) << 1;
        return sum(partialSum, carry);
    }

    public static void main(String[] args) {
        int a = 23, b = 47;
        System.out.println("Two Numbers : " + a + ", " + b);
        System.out.println("Sum : " + sum(a, b));
    }
}
