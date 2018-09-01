package kickstart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorSorting {

    public static void main(String[] args) {

        List<String> animals = new ArrayList<>();
        animals.add("tiger");
        animals.add("lion");
        animals.add("cat");
        animals.add("snake");
        animals.add("mongoose");
        animals.add("elephant");

        Collections.sort(animals, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                // REVERSE ALPHABETICAL ORDER
                return s2.compareTo(s1);
            }
        });
        for(String name : animals) {
            System.out.println(name);
        }

        Collections.sort(animals, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                // DESCENDING ORDER OF LENGTH
                return ((Integer) s2.length()).compareTo(s1.length());
            }
        });
        for(String name : animals) {
            System.out.println(name);
        }



        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(36);
        numbers.add(73);
        numbers.add(40);
        numbers.add(1);

        Collections.sort(numbers, new Comparator<Integer>(){
            @Override
            public int compare(Integer num1, Integer num2) {
                // DESCENDING ORDER
                return num2.compareTo(num1);
            }
        });
        for(Integer number : numbers) {
            System.out.println(number);
        }

    }
}
