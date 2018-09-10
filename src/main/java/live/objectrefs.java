package live;

import java.util.*;

    // obj1 = {'id': 1,  'name': 'Joe',     'refs': [20, 21]}
    // obj2 = {'id': 20, 'name': 'Harvard', 'refs': [80]}
    // obj3 = {'id': 21, 'name': 'MIT',     'refs': [80]}
    // obj4 = {'id': 80, 'name': 'Boston',  'refs': []}

    // Given the input as an array of objects,
    // input = [obj1, obj2, obj3, obj4]
    // dereference the reference id to actual object content.
    // Below is the expected output.

    // expected = {
    //   'id': 1,
    //   'name': 'Joe',
    //   'refs': [
    //     {
    //       'id': 20,
    //       'name': 'Harvard',
    //       'refs': [
    //         {
    //           'id': 80,
    //           'name': 'Boston',
    //           'refs': []
    //         }
    //       ]
    //     },
    //     {
    //       'id': 21,
    //       'name': 'MIT',
    //       'refs': [
    //         {
    //           'id': 80,
    //           'name': 'Boston',
    //           'refs': []
    //         }
    //       ]
    //     }
    //   ]
    //}
    /*
        Psuedo code:
            start with root node -> initial assumption is the first node
            store this data in a HashMap -> O(1) retrieval
            recursive call to print the obj(root node id, hashmap) {

                print id
                print name
                for(each refs) {
                    recursive call(root node id, hashmap)
                }
            }
    */

public class ObjectRefs {

    public static class Input {
        int id;
        String name;
        List<Integer> refs;
        // constructor
        public Input(int id, String name) {
            this.id = id;
            this.name = name;
            this.refs = new ArrayList();
        }
        public void add(Integer ref) {
            this.refs.add(ref);
        }

        public void print(Map<Integer, Input> map) {
            System.out.println("Id:" + id);
            System.out.println("Name:" + name);
            for(Integer ref : this.refs) {
                // O(1)
                Input input = map.get(ref);
                if(input != null) {
                    input.print(map);
                }
            }
        }



        public void loadRefMap(Map<Integer, List<Integer>> map) {
            if(this.refs.isEmpty()) {
                List<Integer> list = map.get(0);
                if(list == null) {
                    list = new ArrayList<>();
                    map.put(0, list);
                }
                map.get(0).add(id);
            }
            for(Integer ref : this.refs) {
                List<Integer> list = map.get(ref);
                if(list == null) {
                    list = new ArrayList<>();
                    map.put(ref, list);
                }
                map.get(ref).add(id);
            }
        }
    }

    public static List<Integer> bfs(Map<Integer, List<Integer>> refMap) {

        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = refMap.get(0);
        for(Integer id : list) {
            queue.offer(id);
        }
        while(!queue.isEmpty()) {
            Integer integer = queue.poll();
            for(Integer id : refMap.get(integer)) {
                if(id != null) {
                    queue.offer(id);
                }
            }
            order.add(integer);
        }
        return order;
    }

    public static void main(String[] args) {

        // obj1 = {'id': 1,  'name': 'Joe',     'refs': [20, 21]}
        // obj2 = {'id': 20, 'name': 'Harvard', 'refs': [80]}
        // obj3 = {'id': 21, 'name': 'MIT',     'refs': [80]}
        // obj4 = {'id': 80, 'name': 'Boston',  'refs': []}

        Input input1 = new Input(1, "Joe");
        input1.add(20);
        input1.add(21);

        Input input2 = new Input(20, "Harvard");
        input2.add(80);

        Input input3 = new Input(21, "MIT");
        input3.add(80);

        Input input4 = new Input(80, "Boston");

        Map<Integer, Input> map = new HashMap<>();
        map.put(input1.id, input1);
        map.put(input2.id, input2);
        map.put(input3.id, input3);
        map.put(input4.id, input4);

        Map<Integer, List<Integer>> refMap = new HashMap<>();
        input1.loadRefMap(refMap);
        input2.loadRefMap(refMap);
        input3.loadRefMap(refMap);
        input4.loadRefMap(refMap);


        List<Integer> order = bfs(refMap);
        Collections.reverse(order);
        Integer rootId = order.get(0);
        map.get(rootId).print(map);

    }
}














