package others;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

    public static class Staff {
        private String name;
        private int age;
        public Staff(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
    }

    public static class Strings {

        public void StringsToUppercase() {
            List<String> alpha = Arrays.asList("a", "b", "c", "d");
            List<String> collect = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
            System.out.println(collect);
        }

        public void IntegersDouble() {
            List<Integer> alpha = Arrays.asList(1, 2, 3, 4, 5);
            List<Integer> collect = alpha.parallelStream().map(i -> i * 2).collect(Collectors.toList());
            System.out.println(collect);
        }

        public void StaffsToName() {
            List<Staff> staffs = Arrays.asList(new Staff("mahatru", 4), new Staff("anusha", 33));
            List<String> names = staffs.parallelStream().map(Staff::getName).collect(Collectors.toList());
            System.out.println(names);
        }
    }

    public static void main(String[] args) {
        new Strings().StringsToUppercase();
        new Strings().IntegersDouble();
    }
}
