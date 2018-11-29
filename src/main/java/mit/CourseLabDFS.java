package mit;

import java.util.*;

/*
    Theory - PreOrder, InOrder, PostOrder

    Eg: Size of Tree (DFS), Maximum of Tree (DFS), Min of Tree (DFS), Print left view (BFS)

    Time Complexity - O(N) for all 4 traversals.

    Space Complexity - O(h) - Maximum height of Binary Tree.

    Where are ancestor nodes stored ? - Stack OR Function call stack stores.

    Extra Space is WORST when tree is less balanced.

    Recursive code.

    Types of problem: Search something that is close to a leaf.
 */
public class CourseLabDFS {
	
	/*
     * Time Complexity - O(E) - O(1) * E
     * 
     * dfsOverEdgesRecursion once for each vertex / few edges - pay O(Adj[V]) = O(E)
     *
     * INPUT - 8 8 0 1 0 2 0 3 1 2 1 4 4 7 2 5 3 6
     * 
     */
    public static void singleComponentDriver(int sourceVertex, List<Integer>[] adjList) {
        // PRE
        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);
    	int[] parent = new int[adjList.length];
    	Arrays.fill(parent, -1);

        // PROCESS
        visited[sourceVertex] = true;
        parent[sourceVertex] = -1;
    	dfsOverEdgesRecursion(sourceVertex, adjList, parent, visited);

        // POST
        System.out.println("Parent: " + Arrays.toString(parent));
        System.out.println("Visited: " + Arrays.toString(visited));
    }

    /*
        Time Complexity - O(E) --> O(1) * E
     */
    public static void dfsOverEdgesRecursion(int sourceVertex, List<Integer>[] adjList,
                                             int[] parent, boolean[] visited) {
    	// iterate over edges
    	for(Integer oppositeVertex : adjList[sourceVertex]) {
            System.out.println("Solving for: " + sourceVertex + " " + oppositeVertex);
            if(parent[oppositeVertex] == -1 && visited[oppositeVertex] == false) {
                parent[oppositeVertex] = sourceVertex;
                visited[oppositeVertex] = true;
                System.out.println("First Occurrence: " + oppositeVertex);
                dfsOverEdgesRecursion(oppositeVertex, adjList, parent, visited);
            }

    	}
    }
    
    /*
     * Time Complexity - O(V+E)
     * 
     * O(1) * V + O(1) * E
     * 
     * dfsOverEdgesRecursion once for each vertex / few edges - pay O(Adj[V])
     * 
     */
    public static void multipleComponentDriver(int[] allVertex, List<Integer>[] adjList) {
        // PRE PROCESSING
        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);
    	int[] parent = new int[1000];
    	Arrays.fill(parent, -1);

        // PROCESS
    	dfsOverVertexes(allVertex, adjList, parent, visited);

        // NO POST PROCESSING
    }


    /*
        Time Complexity - O(V)

        ALL COMPONENTS
     */
    public static void dfsOverVertexes(int[] allVertex, List<Integer>[] adjList,
                                       int[] parent, boolean[] visited) {
    	// iterate over vertexes
    	for(int i = 0; i < allVertex.length; i++) {
    		int sourceVertex = allVertex[i];
    		if(parent[sourceVertex] == -1 && visited[sourceVertex] == false) {
                dfsOverEdgesRecursion(sourceVertex, adjList, parent, visited);
    		}

    	}
    }


    public static void topologicalSortDriver(List<Integer>[] adjList) {

        // PRE PROCESSING
    	// using finishingTimes for visited
    	List<Integer> visitingTimes = new ArrayList<>();

        // PROCESS
    	dfsOverVertexes(adjList, visitingTimes);

        // POST PROCESSING
    	Collections.reverse(visitingTimes);
    	System.out.println(visitingTimes.toString());
    }

    // O(V)
    private static void dfsOverVertexes(List<Integer>[] adjList, List<Integer> visitingTimes) {
    	// iterate over vertexes
    	for(int listIndex = 0; listIndex < adjList.length; listIndex++) {
    		if(!visitingTimes.contains(listIndex)) {
    			dfsOverEdgesRecursion(listIndex, adjList, visitingTimes);
    		}
    	}
    }

    // O(adjList[v] = O(E)
    private static void dfsOverEdgesRecursion(int sourceVertex, List<Integer>[] adjList, List<Integer> visitingTimes) {
    	// iterate over edges, DFS
        for(int oppositeVertex : adjList[sourceVertex]) {
            if(!visitingTimes.contains(oppositeVertex)) {
                dfsOverEdgesRecursion(oppositeVertex, adjList, visitingTimes);
            }
        }
        // adding pre-req at the end
    	visitingTimes.add(sourceVertex);
    }




    private static boolean dfsOverEdgesRecursionSearch(int sourceVertex, int target, List<Integer>[] adjList) {

        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);

        return dfsOverEdgesRecursionSearch(sourceVertex, target, adjList, visited);
    }

    // O(adjList[v] = O(E)
    private static boolean dfsOverEdgesRecursionSearch(int sourceVertex, int target, List<Integer>[] adjList, boolean[] visited) {

        // End when target node is met
        if(sourceVertex == target) {
            return true;
        }
        // iterate over edges, DFS
        visited[sourceVertex] = true;
        for(int oppositeVertex : adjList[sourceVertex]) {
            if(!visited[oppositeVertex]) {
                boolean search = dfsOverEdgesRecursionSearch(oppositeVertex, target, adjList, visited);
                if(search) {
                    // found, report back
                    return search;
                }
                // continue searching
            }
        }
        return false;
    }

    private static boolean dfsOverEdgesIterativeSearch(int sourceVertex, int target, List<Integer>[] adjList) {

        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(visited, false);

        return dfsOverEdgesIterativeSearch(sourceVertex, target, adjList, visited);
    }

    // O(adjList[v] = O(E)
    private static boolean dfsOverEdgesIterativeSearch(int sourceVertex, int target, List<Integer>[] adjList, boolean[] visited) {

        Stack<Integer> stack = new Stack<>();
        stack.push(sourceVertex);
        visited[sourceVertex] = true;

        while(!stack.isEmpty()) {
            Integer candidate = stack.pop();
            visited[candidate] = true;
            for(Integer oppositeVertex : adjList[candidate]) {
                if(!visited[oppositeVertex]) {
                    stack.push(oppositeVertex);
                }
            }
            if(candidate == target) {
                return true;
            }
        }
        return false;
    }


    /*
        4
        3
        0 3
        1 2
        2 3
        [1, 2, 0, 3]
     */
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numOfNodes = scanner.nextInt();
        int numOfEdges = scanner.nextInt();
        ArrayList<Integer>[] adjList = new ArrayList[numOfNodes];
        for (int node = 0; node < numOfNodes; node++) {
            adjList[node] = new ArrayList<>();
        }

        for (int edge = 0; edge < numOfEdges; edge++) {
            int rootVertex = scanner.nextInt();
            int endVertex = scanner.nextInt();
            // directed graph
            adjList[rootVertex].add(endVertex);
        }

        scanner.close();
        topologicalSortDriver(adjList);
        //singleComponentDriver(0, adjList);

        /*

        int numOfNodes = 4;
        ArrayList<Integer>[] adjList = new ArrayList[numOfNodes];
        for (int node = 0; node < numOfNodes; node++) {
            adjList[node] = new ArrayList<>();
        }

        adjList[0].add(3);
        adjList[1].add(2);
        adjList[2].add(3);

        boolean[][] result = {
                {true, false, false, true},
                {false, true, true, true},
                {false, false, true, true},
                {false, false, false, true}
        };
        System.out.println("RECURSIVE");
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                boolean searchAllPermutations = dfsOverEdgesRecursionSearch(i, j, adjList);
                System.out.println(i + " " + j + ", Expected: " + result[i][j] + ", \tActual: " + searchAllPermutations + ", \tOVERALL : " + (result[i][j] == searchAllPermutations));
            }
        }
        System.out.println("ITERATIVE");
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                boolean searchAllPermutations = dfsOverEdgesIterativeSearch(i, j, adjList);
                System.out.println(i + " " + j + ", Expected: " + result[i][j] + ", \tActual: " + searchAllPermutations + ", \tOVERALL : " + (result[i][j] == searchAllPermutations));
            }
        }
        */
    }

    /*
DEPEND TELNET TCPIP NETCARD
DEPEND TCPIP NETCARD
DEPEND NETCARD TCPIP
TCPIP depends on NETCARD, ignoring command
DEPEND DNS TCPIP NETCARD
DEPEND BROWSER TCPIP HTML
INSTALL NETCARD
Installing NETCARD
INSTALL TELNET
Installing TCPIP
Installing TELNET
INSTALL foo
Installing foo
REMOVE NETCARD
NETCARD is still needed
INSTALL BROWSER
Installing HTML
Installing BROWSER
INSTALL DNS
Installing DNS
LIST
NETCARD
TCPIP
TELNET
foo
HTML
BROWSER
DNS
REMOVE TELNET
Removing TELNET
REMOVE NETCARD
NETCARD is still needed
REMOVE DNS
Removing DNS
REMOVE NETCARD
NETCARD is still needed
INSTALL NETCARD
NETCARD is already installed
REMOVE TCPIP
TCPIP is still needed
REMOVE BROWSER
Removing BROWSER
Removing TCPIP
Removing HTML
REMOVE TCPIP
TCPIP is not installed
LIST
NETCARD
foo
END


     */
}
