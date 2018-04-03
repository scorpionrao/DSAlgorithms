package mit;

import java.util.*;

public class CourseLabBFS {

    public static void bfs(Integer root, List<Integer>[] adjList) {
        // represents both visited and level
        Map<Integer, Integer> levelMap = new HashMap<>();
        levelMap.put(root, new Integer(0));
        
        Map<Integer, Integer> parentMap = new HashMap<>();
        parentMap.put(root, null);
        
        int i = 1;
        List<Integer> frontier = new ArrayList<>();
        frontier.add(root);
        
        while(!frontier.isEmpty()) {
            List<Integer> nextLevelSet = new ArrayList<>();
            for(Integer u : frontier) {
                for(Integer v : adjList[u]) {
                    if(parentMap.get(v) == null) {
                        levelMap.put(v, new Integer(i));
                        parentMap.put(v, u);
                        nextLevelSet.add(v);
                    }
                }
            }
            frontier = nextLevelSet;
            i++;
        }
        System.out.println(levelMap.toString());
        System.out.println(parentMap.toString());
    }
    
    public static void bfs(int numOfInputs, List<Integer>[] adjList) {
        
        int[] level = new int[numOfInputs];
        int[] parent = new int[numOfInputs];
        Arrays.fill(parent, -1);
        
        int nextLevel = 1;
        List<Integer> frontier = new ArrayList<>();
        frontier.add(new Integer(0));
        
        while(!frontier.isEmpty()) {
            List<Integer> nextLevelSet = new ArrayList<>();
            for(Integer u : frontier) {
                for(Integer v : adjList[u.intValue()]) {
                    if(parent[v.intValue()] == -1) {
                        level[v.intValue()] = nextLevel;
                        parent[v.intValue()] = u.intValue();
                        nextLevelSet.add(v);
                    }
                }
            }
            frontier = nextLevelSet;
            nextLevel++;
        }
        for(int i = 0; i < level.length; i++) {
            System.out.println("Level[" + i + "]=" + level[i]);
        }
        for(int i = 0; i < parent.length; i++) {
            System.out.println("Parent[" + i +"]=" + parent[i]);
        }
    }
    
    public static void bfsVisit(int numOfInputs, List<Integer>[] adjList) {
        
        boolean[] visited = new boolean[numOfInputs];
        visited[0] = true;
        
        Queue<Integer> acrossFrontiers = new LinkedList<>();
        acrossFrontiers.add(new Integer(0));
        
        while(!acrossFrontiers.isEmpty()) {
            Integer u = acrossFrontiers.poll();
            for(Integer v : adjList[u.intValue()]) {
            	if(!visited[v.intValue()]) {
            		visited[v.intValue()] = true;
            		acrossFrontiers.offer(v);
                }
            }
        }
    }
    
    
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x].add(y);
        }
        bfs(new Integer(0), adj);
        bfs(n, adj);
    }
}
