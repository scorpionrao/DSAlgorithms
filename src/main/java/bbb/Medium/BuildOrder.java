package bbb.Medium;

import java.util.*;
/* .,,.MAHATRU STREET BUS
 *  */
public class BuildOrder {

    public static void topologicalSortDriver(List<List<Integer>> adjList) {
        List<Integer> result = new ArrayList<>();
        dfsVertexes(adjList, result);
        System.out.println(result.toString());
    }

    private static void dfsVertexes(List<List<Integer>> adjList, List<Integer> result) {
        for(int i = 0; i < adjList.size(); i++) {
            if(!result.contains(i)) {
                dfsEdges(adjList, i, result);
            }
        }
    }

    private static void dfsEdges(List<List<Integer>> adjList, int index, List<Integer> result) {
        for(int opposite : adjList.get(index)) {
            if(!result.contains(opposite)) {
                dfsEdges(adjList, opposite, result);
            }
        }
        // add innermost node first
        result.add(index);
    }

    public static void main(String[] args) {
        List<List<Integer>> adjList = new LinkedList<>();
        for(int i = 0; i < 6; i++) {
            adjList.add(new LinkedList<>());
        }

        adjList.get(0);
        adjList.get(1).add(0);
        adjList.get(2).add(0);
        adjList.get(3).add(1);
        adjList.get(3).add(2);
        adjList.get(4).add(3);
        adjList.get(5);

        topologicalSortDriver(adjList);
    }
}