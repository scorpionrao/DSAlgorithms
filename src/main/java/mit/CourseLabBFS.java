package mit;

import java.util.*;

/*
    Theory - Level order

    Eg: Size of Tree (DFS), Maximum of Tree (DFS), Min of Tree (DFS), Print left view (BFS)

    Time Complexity - O(N) for all 4 traversals.

    Space Complexity - O(W) - Maximum width of Binary Tree, stored in queue.

    Extra Space is WORST when tree is more balanced.

    Iterative code.

    Types of problem: Search something that is more likely to closer to root.
 */
public class CourseLabBFS {

    /*
        Time complexity:
        O(1) * V = O(V) for removing from queue.
        O(1) * E = O(E) for adding other end of queue.

        Total - O(V + E)
     */
    private static void bfsTraverse(int rootNode, List<Integer>[] adjList) {

        int numOfNodes = adjList.length;
        boolean[] visited = new boolean[numOfNodes];
        Queue<Integer> acrossFrontiers = new LinkedList<>();

        acrossFrontiers.offer(rootNode);
        visited[rootNode] = true;

        /* nodes considered visited once it is added to queue */
        while(!acrossFrontiers.isEmpty()) {
            Integer rootIndex = acrossFrontiers.poll();
            System.out.println(rootIndex);
            adjList[rootIndex]
                    .stream()
                    .filter(oppositeIndex -> !visited[oppositeIndex])
                    .forEach(oppositeIndex -> {
                        acrossFrontiers.offer(oppositeIndex);
                        visited[oppositeIndex] = true;
                    });
        }
    }

    public static void bfsWithoutQueueWithArrayForLevelAndParent(int rootNode, List<Integer>[] adjList) {

        int[] level = new int[adjList.length];
        int[] parent = new int[adjList.length];
        Arrays.fill(parent, -1);
        int currentLevel = 0;

        List<Integer> frontier = new ArrayList<>();
        frontier.add(rootNode);
        level[rootNode] = currentLevel;
        parent[rootNode] = -1;


        while(!frontier.isEmpty()) {
            List<Integer> nextLevelSet = new ArrayList<>();
            for(Integer rootVertex : frontier) {
                for(Integer oppositeVertex : adjList[rootVertex]) {
                    if(parent[oppositeVertex] == -1) {
                        level[oppositeVertex] = currentLevel + 1;
                        parent[oppositeVertex] = rootVertex;
                        nextLevelSet.add(oppositeVertex);
                    }
                }
            }
            frontier = nextLevelSet;
            currentLevel++;
        }
        for(int i = 0; i < level.length; i++) {
            System.out.println("Array ClosestXdestinations - Level[" + i + "]=" + level[i]);
        }
        for(int i = 0; i < parent.length; i++) {
            System.out.println("Array ClosestXdestinations - Parent[" + i +"]=" + parent[i]);
        }
    }

    public static void bfsWithoutQueueWithMapForLevelAndParent(int rootNode, List<Integer>[] adjList) {

        // represents both visited and level
        Map<Integer, Integer> levelMap = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        List<Integer> frontier = new ArrayList<>();
        int currentLevel = 0;

        frontier.add(rootNode);
        levelMap.put(rootNode, currentLevel);
        parentMap.put(rootNode, null);

        while(!frontier.isEmpty()) {
            List<Integer> nextLevelSet = new ArrayList<>();
            for(Integer u : frontier) {
                for(Integer v : adjList[u]) {
                    if(parentMap.get(v) == null) {
                        levelMap.put(v, currentLevel + 1);
                        parentMap.put(v, u);
                        nextLevelSet.add(v);
                    }
                }
            }
            frontier = nextLevelSet;
            currentLevel++;
        }
        System.out.println("Map ClosestXdestinations - Levels - " + levelMap.toString());
        System.out.println("Map ClosestXdestinations - Parent - " + parentMap.toString());
    }

    public static class Node {
        int data;
        int level;
        int parent;
        boolean visited;

        public Node(int data) {
            this.data = data;
            this.level = -1;
            this.parent = -1;
            this.visited = false;
        }
    }

    public static void bfsWithQueueForLevelAndParent(Node rootNode, List<Node>[] adjList) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(rootNode);
        rootNode.level = 0;
        rootNode.visited = true;
        rootNode.parent = -1;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.data);
            for(Node end : adjList[node.data]) {
                if(!end.visited) {
                    queue.add(end);
                    end.level = node.level + 1;
                    end.visited = true;
                    end.parent = node.data;
                }
            }
        }
    }

    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numOfNodes = scanner.nextInt();
        int numOfEdges = scanner.nextInt();
        List<Integer>[] adjList = new ArrayList[numOfNodes];
        for (int nodeIndex = 0; nodeIndex < numOfNodes; nodeIndex++) {
            adjList[nodeIndex] = new ArrayList<>();
        }
        for (int edgeIndex = 0; edgeIndex < numOfEdges; edgeIndex++) {
            int rootIndex, oppositeIndex;
            rootIndex = scanner.nextInt();
            oppositeIndex = scanner.nextInt();
            adjList[rootIndex].add(oppositeIndex);
        }
        bfsWithoutQueueWithArrayForLevelAndParent(0, adjList);
        bfsWithoutQueueWithMapForLevelAndParent(0, adjList);
    }
}
