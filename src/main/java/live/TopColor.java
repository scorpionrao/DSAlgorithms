package live;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopColor {

    private static List<String> topColors(List<List<String>> colors) {

        Map<String, Integer> frequencies = new HashMap<>();
        int max = 0;
        for(List<String> list : colors) {
            for(String str : list) {
                if(frequencies.get(str) == null) {
                    frequencies.put(str, 1);
                } else {
                    frequencies.put(str, frequencies.get(str) + 1);
                }
                max = Math.max(max, frequencies.get(str));
            }
        }

        List<String> result = new ArrayList<>();
        for(String str : frequencies.keySet()) {
            if(frequencies.get(str) == max) {
                result.add(str);
            }
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("red");
        //list.add("red");
        //list.add("green");
        //list.add("green");
        List<List<String>> input = new ArrayList<>();
        input.add(list);
        System.out.println(topColors(input));
    }
}
