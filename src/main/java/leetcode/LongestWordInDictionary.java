package leetcode;

import java.util.*;

public class LongestWordInDictionary {

    /*
        Algorithm:
            - For each word in dictionary, ensure all substrings(0, length-1) are contained in the dictionary.

            - Optimization - Contains operation, preferred to be O(1) --> HashSet.
            - Optimization - Each word, will it improve existing answer - longer or later than current answer.

        N = number of words
        K = avg length of word

        Time : O(N * (K-1)^2) * O(1) - each word vs all sub strings vs contains in set
        Space : O(N * K^2) - Substrings
     */
    public static String longestWordApproach1(String[] dictionary) {
        String ans = "";
        Set<String> dictionarySet = new HashSet();
        dictionarySet.addAll(Arrays.asList(dictionary));
        for (String word : dictionary) {
            // Can this word improve answer ? - Longer or Later in equal length scenario
            if (isImproving1(word, ans)) {
                // We know full word is already there in the dictionary
                for (int index = 0; index < word.length() - 1; ++index) {
                    String substring = word.substring(0, index+1);
                    if (dictionarySet.contains(substring)) {
                        // is it end of word ?
                        if(index == word.length() - 2) {
                            ans = word;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private static boolean isImproving1(String word, String answer) {
        return word.length() > answer.length() ||
                (word.length() == answer.length() && word.compareTo(answer) < 0);
    }

    public static String longestWordApproach2(String[] dictionary) {
        String ans = "";
        Set<String> dictionarySet = new HashSet();
        dictionarySet.addAll(Arrays.asList(dictionary));

        Arrays.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return isValid2(str1, str2);
            }
        });

        for (String word : dictionary) {
            // We know full word is already there in the dictionary
            for (int index = 0; index < word.length() - 1; ++index) {
                String substring = word.substring(0, index+1);
                if (dictionarySet.contains(substring)) {
                    // is it end of word ?
                    if(index == word.length() - 2) {
                        ans = word;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    private static int isValid2(String str1, String str2) {
        return str1.length() == str2.length() ? str2.compareTo(str1) : str1.length() - str2.length();
    }

    public static class TrieNode {
        char ch;
        String entirePrefix;
        Map<Character, TrieNode> childNodes;
        boolean isEndOfWord;
        TrieNode(char ch, String entirePrefix) {
            this.ch = ch;
            this.entirePrefix = entirePrefix;
            this.childNodes = new HashMap<>();
            this.isEndOfWord = false;
        }
    }

    private static class Trie {

        TrieNode root;

        Trie() {
            root = new TrieNode('#', "");
        }

        private void preCompute(String[] dictionary) {
            for(String word : dictionary) {
                addToWord(word);
            }
        }

        private void addToWord(String word) {

            TrieNode prefixNode = root;
            for(int i = 0; i < word.length(); i++) {
                if(!prefixNode.childNodes.containsKey(word.charAt(i))) {
                    prefixNode.childNodes.put(word.charAt(i),
                            new TrieNode(word.charAt(i), word.substring(0, i+1)));
                }
                prefixNode = prefixNode.childNodes.get(word.charAt(i));
                if(i == word.length() - 1) {
                    prefixNode.isEndOfWord = true;
                }
            }
        }

        private String dfs() {
            String answer = "";
            Stack<TrieNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()) {
                TrieNode node = stack.pop();
                String word = node.entirePrefix;
                if (node == root || node.isEndOfWord) {
                    if(node != root) {
                        if(word.length() > answer.length()
                            || (word.length() == answer.length() && word.compareTo(answer) < 0)) {

                            answer = word;
                        }
                    }
                    for(TrieNode child : node.childNodes.values()) {
                        stack.push(child);
                    }
                }

            }
            return answer;
        }
    }

    public static String longestWordApproach3(String[] dictionary) {
        Trie trie = new Trie();
        trie.preCompute(dictionary);
        return trie.dfs();
    }

    private static void evaluate(String[] words) {
        System.out.println("Input : " + Arrays.toString(words));
        String result1 = longestWordApproach1(words);
        System.out.println("Approach 1 : " + result1);
        String result2 = longestWordApproach2(words);
        System.out.println("Approach 2 : " + result2);
        String result3 = longestWordApproach3(words);
        System.out.println("Approach 3 : " + result3);
    }

    public static void main(String[] args) {

        String[] words1 = {"world", "w","wo","wor","worl"};
        evaluate(words1);
        String[] words2 = {"apple", "a", "banana", "app", "appl", "ap", "apply"};
        evaluate(words2);
    }
}
