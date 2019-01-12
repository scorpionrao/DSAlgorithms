package codecamp;

import java.util.function.Consumer;

public class MethodReference {

    // https://www.codementor.io/eh3rrera/using-java-8-method-reference-du10866vx

    private static class Utility {
        public void execute(Long l) {
            System.out.println("Sleep " + l + " ms");
            System.out.println("Hello World");
        }

        private  void call(Long l, Consumer<Long> consumer) {
            consumer.accept(l);
        }
    }

    public static void main(String[] args) {

        Utility utility = new Utility();

        // anonymous class
        utility.call(5000L, new Consumer<Long>() {
            @Override
            public void accept(Long l) {
                utility.execute(l);
            }
        });
        // lambda expression
        utility.call(5000L, i -> utility.execute(i));
        // method reference
        utility.call(5000L, utility::execute);

    }
}
