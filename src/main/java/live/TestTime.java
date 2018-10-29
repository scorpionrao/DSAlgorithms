package live;

/*
    We're given a n x 2 2-D array, where each of the n rows represents a test.
    The first element of the row is the index of the project in which tests lies,
    and the second element is the time it takes for the test to run. In each project,
    tests can only run in the order in which they are present in the list; however,
    tests of different projects are independent of each other, and can be run in any
    order. We have a single machine and only one test can run at a time.
    Given the array of tests and the total CPU time available to run the tests,
    find the maximum number of tests that can be run.

    Your output should be a list of integers, where each index in the list is the
    project index, and the value at that index is the number of tests that should
    be run in that project. For example, if you have 3 projects, then the output
    [2, 0, 1] indicates that you'll run 2 tests from the first project,
    0 from the second and 1 from the third.

    Notes:
    First number of tests in each project

    Brute Force:
    {3, 8},
    {1, 4},
    {2, 5},
    {2, 10},
    {4, 12},
    {1, 3}
    Time=10
    Data structure
    1,4; 1,3
    2,5; 2,10
    3,8
    4,12

    ClosestXdestinations: Look at the head of each bucket grouped by project id with least time

    until time is exhausted or time is less than time required for tests at the head of each bucket

    Tracker1: remainingTime
    Tracker2: Incrementing the project whose test case was run
    1,4; remainingTime = 10-4 = 6
    1,3: remainingTime = 6-3 = 3

    return tracker2

    Time complexity: O(N) --> N = input size (number of rows)
    Space complexity: O(N) --> same as up

 */

import java.util.*;

public class TestTime {

    /*
        N - number of test cases

        O(N) - Time

        Output Map:
        1 : 4,3
        2 : 5,2,10
        3 : 8
        4 : 12
    */
    public static Map<Integer, LinkedList<Integer>> buildDS(int[][] testDetails) {
        if(testDetails == null) {
            throw new NullPointerException("Test Details is null");
        }

        // Order matters
        Map<Integer, LinkedList<Integer>> groupByProject = new HashMap<>();

        // O(N)
        for(int i = 0; i < testDetails.length; i++) {
            int projectId = testDetails[i][0];
            int testTime = testDetails[i][1];
            if(groupByProject.get(projectId) == null) {
                groupByProject.put(projectId, new LinkedList<>());
            }
            groupByProject.get(projectId).add(testTime);
        }
        return groupByProject;
    }

    public static class Node {
        int projectId;
        int time;
        Node next;
        Node(int time) {
            this.time = time;
            this.next = null;
        }
        Node(int projectId, int time) {
            this.projectId = projectId;
            this.time = time;
            this.next = null;
        }
    }

    public static Map<Integer, Node> buildDS1(int[][] testDetails) {
        if(testDetails == null) {
            throw new NullPointerException("Test Details is null");
        }

        Map<Integer, Node> groupByProject = new HashMap<>();

        // O(N)
        for(int i = 0; i < testDetails.length; i++) {
            int projectId = testDetails[i][0];
            int testTime = testDetails[i][1];

            Node node = groupByProject.get(projectId);
            if(node == null) {
                groupByProject.put(projectId, new Node(projectId, testTime));
            } else {
                while (node.next != null) {
                    node = node.next;
                }
                node.next = new Node(testTime);
            }
        }
        return groupByProject;
    }

    /*
        Until the time is exhausted or all the tests are used

        O(P * N) - Time

        Input Map:
        1 : 4,3
        2 : 5,2,10
        3 : 8
        4 : 12

    */
    public static Map<Integer, Integer> mergeKLists(Map<Integer, LinkedList<Integer>> groupByProject, int testTime) {

        if(groupByProject == null) {
            throw new NullPointerException("Group by project is null");
        }

        Map<Integer, Integer> resultMap = new HashMap<>();

        while(testTime > 0) {
            /* find the list with least head time */
            int shortestHead = Integer.MAX_VALUE;
            int projectIdToChoose = -1;
            // O(P)
            for(Map.Entry<Integer, LinkedList<Integer>> entry : groupByProject.entrySet()) {
                    LinkedList<Integer> list = entry.getValue();
                    if(list != null && list.peek() != null && list.peek() < shortestHead) {
                        projectIdToChoose = entry.getKey();
                        shortestHead = list.peek();
                    }
            }

            /* Project has been identified && min time is within available time */
            if(projectIdToChoose != -1 && shortestHead < testTime) {
                if(resultMap.get(projectIdToChoose) == null) {
                    resultMap.put(projectIdToChoose, 0);
                }
                resultMap.put(projectIdToChoose, resultMap.get(projectIdToChoose)+1);
                // clean up
                testTime = testTime - shortestHead;
                groupByProject.get(projectIdToChoose).removeFirst();
            } else {
                // no more solutions are useful
                break;
            }
        }
        return resultMap;
    }

    /*
      Until the time is exhausted or all the tests are used

      O(P * N) - Time
    */
    public static Map<Integer, Integer> mergeKLists1(List<Node> list, int remainingTime) {

        if(list == null) {
            throw new IllegalArgumentException();
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(list.size(), new Comparator<Node>(){
            @Override
            public int compare(Node node1, Node node2) {
                if(node1.time > node2.time) {
                    return 1;
                } else if (node1.time < node2.time) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });



        for(Node node : list) {
            if(node != null) {
                priorityQueue.add(node);
            }
        }

        Map<Integer, Integer> resultMap = new HashMap<>();
        while (!priorityQueue.isEmpty() && remainingTime > 0) {
            // Priority Queue has lost this entire list.
            Node node = priorityQueue.poll();
            if(node.time <= remainingTime) {
                if(resultMap.get(node.projectId) == null) {
                    resultMap.put(node.projectId, 0);
                }
                resultMap.put(node.projectId, resultMap.get(node.projectId) + 1);

                // Bringing the entire list back to Priority queue
                if(node.next != null) {
                    priorityQueue.add(node.next);
                }
                remainingTime = remainingTime - node.time;
            } else {
                break;
            }
        }
        return resultMap;
    }

    public static class MatrixNode {
        int value;
        int row;
        int col;

        MatrixNode(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }
    public static List<Integer> mergeKRows(int[][] matrix) {

        PriorityQueue<MatrixNode> pq = new PriorityQueue<>(matrix.length, new Comparator<MatrixNode>(){

            @Override
            public int compare(MatrixNode o1, MatrixNode o2) {
                if(o1.value > o2.value) {
                    return 1;
                } else if (o1.value < o2.value) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for(int i = 0; i < matrix.length; i++) {
            pq.add(new MatrixNode(matrix[i][0], i, 0));
        }

        List<Integer> sortedList = new ArrayList<>();

        while(!pq.isEmpty()) {
            MatrixNode node = pq.poll();
            sortedList.add(node.value);
            if(node.col < matrix.length - 1) {
                pq.add(new MatrixNode(matrix[node.row][node.col+1], node.row, node.col+1));
            }
        }

        return sortedList;
    }


    public static void main(String[] args) {
        int[][] testDetails = {
                {3, 8},
                {1, 4},
                {2, 5},
                {2, 10},
                {4, 12},
                {1, 3},
                {2, 7},
                {3, 14},
                {3, 2},
                {4, 1}
        };
        int testTime = 19;

        Map<Integer, LinkedList<Integer>> groupByProjectMap = buildDS(testDetails);
        Map<Integer, Integer> resultMap = mergeKLists(groupByProjectMap, testTime);
        System.out.println(resultMap);

        Map<Integer, Node> groupByProjectMap1 = buildDS1(testDetails);
        Map<Integer, Integer> resultMap1 =
                mergeKLists1(new ArrayList<>(groupByProjectMap1.values()), testTime);
        System.out.println(resultMap1);

        // Ouput: [2, 1, 0, 0]

        int[][] matrix = {
                {20, 40, 80},
                {5, 60, 90},
                {45, 50, 55}
        };

        System.out.println(mergeKRows(matrix));

        int x = 2;
        int y = 2;

        double hypotenuse = Math.sqrt(Math.pow(x,2) + Math.pow(y, 2));
        System.out.println(hypotenuse);
    }

}
