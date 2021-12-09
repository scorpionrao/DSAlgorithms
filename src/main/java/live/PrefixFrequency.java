package live;

import java.util.Arrays;

final class PrefixFrequency {
  
  public static void main(String[] args) {
    String prefix = "ba";
    String[] input = {"b", "b", "ba", "bac", "bax", "bb", "bc", "c"};
    System.out.println(bruteforce(input, prefix));
    System.out.println(binarySearch(input, prefix));
    System.out.println(trieNodeSearch(input, prefix));
  }

  static class Node {
    Node[] nodes = new Node[26];
    int frequency = 0;
  }

  static Node buildTrieNode(String[] input) {
    Node root = new Node();
    for(String str : input) {
      Node current = root;
      for(char ch : str.toCharArray()) {
        int index = ch - 'a';
        if(current.nodes[index] == null) {
          current.nodes[index] = new Node();
        }
        current.nodes[index].frequency++;
        current = current.nodes[index];
      }
    }
    return root;
  }

  static long trieNodeSearch(String[] input, String prefix) {
    Node root = buildTrieNode(input);
    for(char ch : prefix.toCharArray()) {
      int index = ch - 'a';
      if(root.nodes[index] == null) {
        return 0;
      }
      root = root.nodes[index];
    }
    return root.frequency;
  }

  static long bruteforce(String[] input, String prefix) {
    return Arrays.stream(input).filter(str -> str.startsWith(prefix)).count();
  }

  static long binarySearch(String[] input, String prefix) {
    int matchIndex = getIndex(input, prefix);
    if(matchIndex == -1) {
      return 0;
    }
    int start = matchIndex;
    while(start >= 0 && input[start].startsWith(prefix)) {
      start--;
    }
    int end = matchIndex;
    while(end <= input.length - 1 && input[end].startsWith(prefix)) {
      end++;
    }
    return end - (start + 1);
  }

  static int getIndex(String[] input, String prefix) {
    int low = 0;
    int high = input.length - 1;
    int mid = -1;
    while(low < high) {
      mid = (low + high) / 2;
      if(input[mid].startsWith(prefix)) {
        break;
      } else if(input[mid].compareTo(prefix) < 0){
        low = mid;
      } else {
        high = mid;
      }
    }
    return mid;
  }
}
