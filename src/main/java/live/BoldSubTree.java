package live;

import java.util.ArrayList;
import java.util.List;

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

            if (root == null) {
                return root;
            }

            for (int i = 0; i < root.childNodes.size(); i++) {
                Node node = root.childNodes.get(i);
                root.childNodes.set(i, extractBoldSubTree(node));
            }
            while (root.childNodes.remove(null)) {}

            // leaf
            if (root.childNodes.isEmpty() && !root.isBold) {
                return null;
            }
            return root;
        }

        /*
            2012 - Novice Answer.

            public Node extractBoldSubTree (Node rootNode) {
                node1 = rootNode;
                return extractBoldSubTree(rootNode, 0, 0);
            }

            private Node extractBoldSubTree (Node rootNode, int level, int childNumber) {

                Node currentNode = rootNode;
                while (currentNode != null) {
                    if (currentNode.itemArray[childNumber].isBold != true &&
                            currentNode.isLeaf())
                        currentNode.getParent().disconnectChild(childNumber);
                    } else {
                        int numItems = rootNode.getNumItems();
                        for(int j=0; j<numItems+1; j++) {
                            Node nextNode = rootNode.getChild(j);
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
