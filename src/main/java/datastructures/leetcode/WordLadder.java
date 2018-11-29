package datastructures.leetcode;

import java.util.*;

public class WordLadder {

    public static class Item {
        String word;
        int distance;

        public Item(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
    }

    private boolean oneSubstitutionExactly(String target, String word) {

        if(target == null || word == null) {
            throw new NullPointerException("Input is null");
        }

        if(target.length() != word.length()) {
            return false;
        }

        int numOfEdits = 0;
        for(int i = 0; i < target.length(); i++) {
            if(target.charAt(i) != word.charAt(i)) {
                numOfEdits++;
            }
            if(numOfEdits > 1) {
                return false;
            }
        }
        // Same strings return false
        return numOfEdits == 1 ? true : false;
    }


    public int ladderLength(String startWord, String endWord, Set<String> dictionary) {

        if(startWord == null || endWord == null || dictionary == null) {
            throw new NullPointerException("Input argument is null");
        }

        Queue<Item> queue = new LinkedList<>();
        queue.offer(new Item(startWord, 1));

        while (!queue.isEmpty()) {
            Item item = queue.poll();
            Set<String> forDeletion = new HashSet<>();
            for(String neighbor : dictionary) {
                if(neighbor != null && oneSubstitutionExactly(item.word, neighbor)) {
                    if(neighbor.equals(endWord)) {
                        return item.distance + 1;
                    }
                    queue.offer(new Item(neighbor, item.distance + 1));
                    forDeletion.add(neighbor);
                }
            }
            dictionary.removeAll(forDeletion);
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> dictionary = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        WordLadder ladder = new WordLadder();
        int length = ladder.ladderLength(beginWord, endWord, dictionary);
        System.out.println("Expected: 5, Actual: " + length);

        /*
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
         */
        // List<List<String>> ladderSequences = ladder.ladders(beginWord, endWord, dictionary);


    }
}
