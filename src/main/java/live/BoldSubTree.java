package live;

import java.util.ArrayList;
import java.util.List;

/*
Test Cases: 
1: Retained only because of descendants.
2: Retained only because of descendants.
21: Leaf at parent level is eliminated.
22: Parent loses its child but retains itself.
221: Deepest child is eliminated.
3: Retained only because of descendants.
31: Retained because of bold child.
32: Parent level bold with no children.
41: Eliminated even though parent is bold.
42: Same as 31.
43 and 431: Only scenario where parent and child are useless.
*/
public class BoldSubTree {
/*
    Source Tree:
                            1
                2              3                      *4*

            21   *22*   31        *32*         41    42      43

            221        *311*		             *421*       431

    Result tree:
                            1
                2               3              *4*
            *22*          31      *32*           42
                        *311*		        *421*
*/

    public static class Node {

        int key;
        boolean isBold;
        List<Node> childNodes;

        Node(int key, boolean isBold) {
            this.key = key;
            this.isBold = isBold;
            this.childNodes = new ArrayList<>();
        }

        void addChild(Node node)
        {
            childNodes.add(node);
        }

        public void printPreOrder() {
            if (!childNodes.isEmpty()) {
                System.out.println(key + " --> " + childNodes.toString());
                for (Node node : childNodes) {
                    node.printPreOrder();
                }
            }
        }

        @Override
        public String toString() {
            return this.isBold ? "*" + key + "*" : key + "";
        }

        /*
            DFS - O(N)

            1 --> [2, 3, *4*, 5]
            2 --> [21, *22*]
            21 --> [221]
            3 --> [31, *32*]
            31 --> [*311*]
            4 --> [41, 42, 43]
            42 --> [*421*]
            43 --> [431]

            1 --> [2, 3, *4*]
            2 --> [*22*]
            3 --> [31, *32*]
            31 --> [*311*]
            4 --> [42]
            42 --> [*421*]

        */

        public static Node extractBoldSubTree(Node root) {

            // edge case.
            if(root == null) {
              return null;
            }
            // Post Order traversal.
            // Process the children, cleanup the purged nodes, process the root.
            for(int i = 0; i < root.children.size(); i++) {
              Node childNode = extractBoldSubTree(root.children.get(i));
              // In-place approach, saving memory. Above and beyond.
              root.children.set(i, childNode);
            }
            // Required only if using In-place approach.
            while (root.children.remove(null));
            if(!root.isSpecial && root.children.isEmpty()) {
              return null;
            }
            return root;
        }

      public static Node extractBoldSubTree(Node root) {
        if (root == null) {
            return root;
        }
        // Update the children so that parent can be evaluated in in-order traversal.
        root.childNodes =
          root.childNodes.stream()
              .map(node -> extractBoldSubTree(node))
              .filter(node -> node != null)
              .collect(Collectors.toList());

          // leaf and not bold - Get rid of it.
          if (root.childNodes.isEmpty() && !root.isBold) {
            return null;
          }
          return root;
        }
        /*
            2012 - Novice Answer.

            public TrieNode extractBoldSubTree (TrieNode rootNode) {
                node1 = rootNode;
                return extractBoldSubTree(rootNode, 0, 0);
            }

            private TrieNode extractBoldSubTree (TrieNode rootNode, int level, int childNumber) {

                TrieNode currentNode = rootNode;
                while (currentNode != null) {
                    if (currentNode.itemArray[childNumber].isBold != true &&
                            currentNode.isLeaf())
                        currentNode.getParent().disconnectChild(childNumber);
                    } else {
                        int numItems = rootNode.getNumItems();
                        for(int j=0; j<numItems+1; j++) {
                            TrieNode nextNode = rootNode.getChild(j);
                            if(nextNode != null)
                                extractBoldSubTree(nextNode, level+1, j);
                        }
                    }
                }
                return node1;
            }

         */
    }

    public static void main(String[] args) {
        Node one = new Node(1, false);
        Node two = new Node(2, false);
        Node three = new Node(3, false);
        Node four = new Node(4, true);
        Node five = new Node(5, false);
        Node twentyOne = new Node(21, false);
        Node twentyTwo = new Node(22, true);
        Node thirtyOne = new Node(31, false);
        Node thirtyTwo = new Node(32, true);
        Node fortyOne = new Node(41, false);
        Node fortyTwo = new Node(42, false);
        Node fortyThree = new Node(43, false);
        Node twoTwentyOne = new Node(221, false);
        Node threeEleven = new Node(311, true);
        Node fourTwentyOne = new Node(421, true);
        Node fourThirtyOne = new Node(431, false);

        one.addChild(two);
        one.addChild(three);
        one.addChild(four);
        one.addChild(five);

        two.addChild(twentyOne);
        two.addChild(twentyTwo);

        three.addChild(thirtyOne);
        three.addChild(thirtyTwo);

        four.addChild(fortyOne);
        four.addChild(fortyTwo);
        four.addChild(fortyThree);

        twentyOne.addChild(twoTwentyOne);
        thirtyOne.addChild(threeEleven);

        fortyTwo.addChild(fourTwentyOne);
        fortyThree.addChild(fourThirtyOne);

        one.printPreOrder();
        System.out.println();
        one.extractBoldSubTree(one);
        one.printPreOrder();
        System.out.println();
    }

}
