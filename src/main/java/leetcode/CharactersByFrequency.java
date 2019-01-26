package leetcode;

import java.util.*;

public class CharactersByFrequency {

    private static class Node {
        char ch;
        int freq;
        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    /*
        N -> Length of string

        Time : O(N) + O(N log N)
        Space : O(N)
     */
    public static String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : s.toCharArray()) {
            if(!freq.containsKey(ch)) {
                freq.put(ch, 0);
            }
            freq.put(ch, freq.get(ch)+1);
        }


        List<Node> list = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : freq.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }

        Collections.sort(list, new Comparator<Node>(){
            public int compare(Node node1, Node node2) {
                return new Integer(node2.freq).compareTo(new Integer(node1.freq));
            }
        });

        StringBuilder sb = new StringBuilder();
        for(Node node : list) {
            for(int times = 0; times < node.freq; times++) {
                sb.append(node.ch);
            }
        }
        return sb.toString();
    }

    /*  */
    public static String frequencySort1(String s) {
        int[] frequencies = new int['z' - 'A' + 1];
        for(char ch : s.toCharArray()) {
            frequencies[ch - 'A']++;
        }

        // max frequency = s.length()
        List<Character>[] buckets = new List[s.length()];
        for(int i = 0; i < frequencies.length; i++) {
            int freqForAtoz = frequencies[i];
            if(buckets[freqForAtoz] == null) {
                buckets[freqForAtoz] = new ArrayList<>();
            }
            buckets[freqForAtoz].add((char)('A' + i));
        }

        StringBuilder sb = new StringBuilder();
        for(int index = buckets.length-1; index >= 0; index--) {
            if(buckets[index] != null) {
                for (char ch : buckets[index]) {
                    for(int times = 0; times < index; times++) {
                        sb.append(ch);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void evaluate(String input) {
        System.out.println("Input : " + input);
        String result1 = frequencySort(input);
        System.out.println("Approach 1 : " + result1);
        String result2 = frequencySort1(input);
        System.out.println("Approach 2 : " + result2);
    }

    public static void main(String[] args) {
        evaluate("tree");
        Map<Integer, Integer> freq = new HashMap<>();
        Integer i = freq.getOrDefault(20, 1);
        System.out.println(i);
    }
}
