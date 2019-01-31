package gayle;

import java.util.*;

public class FourCubes {

    private static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void print(int input) {

        Map<Integer, List<Node>> map = new LinkedHashMap<>();
        for(int a = 0; a < input; a++) {
            for(int b = 0; b < input; b++) {
                int result = (int) (Math.pow(a,3) + Math.pow(b,3));
                if(!map.containsKey(result)) {
                    map.put(result, new ArrayList<>());
                }
                List<Node> nodes = map.get(result);
                nodes.add(new Node(a, b));
            }
        }

        for(Integer candidate : map.keySet()) {
            List<Node> list = map.get(candidate);
            for(int i = 0; i < list.size(); i++) {
                Node left = list.get(i);
                for(int j = i; j < list.size(); j++) {
                    Node right = list.get(j);
                    System.out.println(left.x + " " + left.y + " " + right.x + " " + right.y);
                }
            }
        }
    }

    public static void main(String[] args) {
        print(100);
    }
}
