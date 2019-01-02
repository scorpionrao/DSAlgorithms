package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MagicDictionary {

    public boolean oneSubstitution(String target) {
        for(String word : vocabulary) {
            if(oneSubstitutionExactly(target, word)) {
                return true;
            }
        }
        return false;
    }

    private boolean oneSubstitutionExactly(String target, String word) {

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
        return numOfEdits == 1 ? true : false;
    }

    Set<String> vocabulary;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        vocabulary = new HashSet<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        vocabulary.addAll(Arrays.asList(dict));
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return oneSubstitution(word);
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        String[] dictionary = { "hello", "leetcode" };
        magicDictionary.buildDict(dictionary);
        System.out.println("Expected : false, Actual : " + magicDictionary.search("hello"));
        System.out.println("Expected : true, Actual : " + magicDictionary.search("hhllo"));
        System.out.println("Expected : false, Actual : " + magicDictionary.search("hell"));
        System.out.println("Expected : false, Actual : " + magicDictionary.search("leetcoded"));

    }
}

