package mit;

import java.util.*;

public class CourseLabBFS {

    private static void bfsTraverse(int rootNode, List<Integer>[] adjList) {

        int numOfNodes = adjList.length;
        boolean[] visited = new boolean[numOfNodes];
        Queue<Integer> acrossFrontiers = new LinkedList<>();

        acrossFrontiers.add(rootNode);
        visited[rootNode] = true;

        /* nodes considered visited once it is added to queue */
        while(!acrossFrontiers.isEmpty()) {
            Integer rootIndex = acrossFrontiers.poll();
            System.out.println(rootIndex);
            adjList[rootIndex]
                    .stream()
                    .filter(oppositeIndex -> !visited[oppositeIndex])
                    .forEach(oppositeIndex -> {
                        visited[oppositeIndex] = true;
                        acrossFrontiers.offer(oppositeIndex);
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
            System.out.println("Array Solution - Level[" + i + "]=" + level[i]);
        }
        for(int i = 0; i < parent.length; i++) {
            System.out.println("Array Solution - Parent[" + i +"]=" + parent[i]);
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
        System.out.println("Map Solution - Levels - " + levelMap.toString());
        System.out.println("Map Solution - Parent - " + parentMap.toString());
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
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[numOfNodes];
        for (int nodeIndex = 0; nodeIndex < numOfNodes; nodeIndex++) {
            adj[nodeIndex] = new ArrayList<>();
        }
        for (int edgeIndex = 0; edgeIndex < numOfEdges; edgeIndex++) {
            int rootIndex, oppositeIndex;
            rootIndex = scanner.nextInt();
            oppositeIndex = scanner.nextInt();
            adj[rootIndex].add(oppositeIndex);
        }
        bfsWithoutQueueWithArrayForLevelAndParent(0, adj);
        bfsWithoutQueueWithMapForLevelAndParent(0, adj);
    }
}
