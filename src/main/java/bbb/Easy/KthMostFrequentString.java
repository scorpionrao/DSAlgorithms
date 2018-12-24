package bbb.Easy;

import java.util.*;

public class KthMostFrequentString {

    /*
        Time: O(N) + O(N log N)
        Space: O(N) + O(N)
     */
    private static String kthMostFrequentString(String[] strings, int k) {

        Map<String, Integer> map = new HashMap<>();
        for(String str : strings) {
            Integer freq = map.get(str);
            if(freq == null) {
                freq = 0;
            }
            map.put(str, ++freq);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
           public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
               return entry1.getValue().compareTo(entry2.getValue());
            }
        });

        return (k - 1 < list.size()) ? list.get(k-1).getKey() : null;

    }

    public static void main(String[] args) {
        String[] strings = {"a", "a", "b"};
        String kthFreqString = kthMostFrequentString(strings, 1);
        System.out.println("Expected : b, Actual : " + kthFreqString);
    }
}
