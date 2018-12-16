package bbb.Strings;

import java.util.*;

public class Autocomplete {

    // map of words by length
    // map of words by prefixes - Trie


    /* Time: O(Number of words * Length of prefix), Space: O(1) */
    private static List<String> autocompleteApproach1(List<String> dictionary, String search) {
        List<String> result = new ArrayList<>();
        for(String word : dictionary) {
            if(word.startsWith(search)) {
                result.add(word);
            }
        }
        // just for print out comparison. Trie is naturally alphabetically sorted.
        Collections.sort(result);
        return result;
    }

    public static class TrieNode {

        char ch;
        String entirePrefix;
        Map<Character, TrieNode> childNodesMap;
        // ensures both sta"r" and sta"r"board are collected.
        boolean isNodeEndOfWord;

        public TrieNode(char ch, String entirePrefix) {
            this.ch = ch;
            this.entirePrefix = entirePrefix;
            this.childNodesMap = new HashMap<>();
            this.isNodeEndOfWord = false;
        }
    }

    public static class Trie {

        TrieNode root;

        public Trie() {
            this.root = new TrieNode('#', "");
        }

        public void preCompute(List<String> dictionary) {
            for(String word : dictionary) {
                addToTrie(word);
            }
        }

        private void addToTrie(String word) {

            TrieNode prefixTrieNode = root;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(!prefixTrieNode.childNodesMap.containsKey(ch)) {
                    prefixTrieNode.childNodesMap.put(ch, new TrieNode(ch, word.substring(0, i+1)));
                }
                prefixTrieNode = prefixTrieNode.childNodesMap.get(ch);
                if(i == word.length() - 1) {
                    prefixTrieNode.isNodeEndOfWord = true;
                }
            }
        }

        public List<String> searchWithPrefix(String prefix) {

            List<String> result = new ArrayList<>();

            // Traverse to the prefix
            TrieNode prefixTrieNode = root;
            for(char ch : prefix.toCharArray()) {
                if(prefixTrieNode.childNodesMap.containsKey(ch)) {
                    prefixTrieNode = prefixTrieNode.childNodesMap.get(ch);
                } else {
                    return new ArrayList<>();
                }
            }
            collectAllWordsFromHere(prefixTrieNode, result);
            return result;
        }

        public boolean searchExact(String prefix) {

            // Traverse to the prefix
            TrieNode prefixTrieNode = root;
            for(char ch : prefix.toCharArray()) {
                if(prefixTrieNode.childNodesMap.containsKey(ch)) {
                    prefixTrieNode = prefixTrieNode.childNodesMap.get(ch);
                } else {
                    return false;
                }
            }
            return prefixTrieNode != root && prefixTrieNode.isNodeEndOfWord;
        }


        private void collectAllWordsFromHere(TrieNode root, List<String> result) {
            // pre-order traversal
            if(root.isNodeEndOfWord) {
                result.add(root.entirePrefix);
            }
            // If leaf, calls will terminate itself
            for(Character character : root.childNodesMap.keySet()) {
                collectAllWordsFromHere(root.childNodesMap.get(character), result);
            }
        }
    }

    /* Time: O(L), Space: O(D) */
    private static List<String> autocompleteApproach2(List<String> dictionary, String search) {
        Trie trie = new Trie();
        trie.preCompute(dictionary);
        List<String> result = trie.searchWithPrefix(search);
        return result;
    }

    /* Time: O(L), Space: O(D) */
    private static boolean autocompleteApproach3(List<String> dictionary, String search) {
        Trie trie = new Trie();
        trie.preCompute(dictionary);
        return trie.searchExact(search);
    }

    private static void evaluate(List<String> dictionary, String search) {
        System.out.println("Dictionary : " + dictionary.toString());
        System.out.println("Search : " + search);

        List<String> result1 = autocompleteApproach1(dictionary, search);
        System.out.println("Approach1 : " + result1.toString());
        List<String> result2 = autocompleteApproach2(dictionary, search);
        System.out.println("Approach2 : " + result2.toString());
        boolean searchExact = autocompleteApproach3(dictionary, search);
        System.out.println("Approach3 : " + searchExact);
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(new String[]{"abc", "acd", "bcd", "def", "a", "aba"});
        evaluate(dictionary, "ab");
    }
}
