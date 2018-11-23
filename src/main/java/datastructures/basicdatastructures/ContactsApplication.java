package datastructures.basicdatastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactsApplication {

    public static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int size = 0;

        public void putChildIfAbsent(Character ch) {
            children.putIfAbsent(ch, new TrieNode());
        }

        public TrieNode getChild(Character ch) {
            return children.get(ch);
        }
    }

    public static class Trie {
        TrieNode root = new TrieNode();

        public void add(String str) {
            TrieNode current = root;
            for(int i = 0; i < str.length(); i++) {
                Character ch = str.charAt(i);
                current.putChildIfAbsent(ch);
                current = current.getChild(ch);
                current.size++;
            }
        }

        public int find(String prefix) {
            TrieNode current = root;
            for(int i = 0; i < prefix.length(); i++) {
                Character ch = prefix.charAt(i);
                current = current.getChild(ch);
                if(current == null) {
                    return 0;
                }
            }
            return current.size;
        }
    }

    private static class Query {
        private String type;
        private String name;
        public Query(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }
    }

    static Trie trie = new Trie();

    private static void processQuery(Query query) {
        if("notSynchronizedMethod".equals(query.getType())) {
            trie.add(query.getName());
        } else if ("find".equals(query.getType())) {
            System.out.println(trie.find(query.getName()));
        }
    }

    private static Query readQuery(Scanner scanner) {
        String type = scanner.next();
        String name = scanner.next();
        Query query = new Query(type, name);
        return query;
    }

    private static void processQueries() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int numQueries = scanner.nextInt();
        for(int i = 0; i < numQueries; i++) {
            processQuery(readQuery(scanner));
        }
    }

    public static void main(String[] args) {
        new ContactsApplication().processQueries();
    }
}
