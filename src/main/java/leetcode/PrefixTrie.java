package leetcode;

public class PrefixTrie {

    public static class TrieNode {

        private final static int SIZE = 26;
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            this.children = new TrieNode[SIZE];
        }

        public void push(char x) {
            children[x-'a'] = new TrieNode();
        }

        public boolean contain(char x) {
            return children[x-'a'] != null;
        }

        public TrieNode get(char x) {
            return children[x-'a'];
        }
    }

    TrieNode root;

    public PrefixTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if(word.isEmpty()) {
            return;
        }
        TrieNode current = root;
        char[] letters = word.toCharArray();
        for(int i = 0; i < letters.length; i++) {
            if(!current.contain(letters[i])) {
                current.push(letters[i]);
            }
            current = current.get(letters[i]);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (!current.contain(letters[i])) {
                return false;
            }
            current = current.get(letters[i]);
        }
        return current.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (!current.contain(letters[i])) {
                return false;
            }
            current = current.get(letters[i]);
        }
        return true;
    }
}