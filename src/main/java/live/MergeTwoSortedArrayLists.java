package live;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSortedArrayLists {

    public static class Solution {

        public List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
            List<Integer> result = new ArrayList<>();
            int pointer1 = 0;
            int pointer2 = 0;

            while (pointer1 < list1.size() && pointer2 < list2.size()) {
                if(list1.get(pointer1) < list2.get(pointer2)) {
                    result.add(list1.get(pointer1));
                    pointer1++;
                } else if (list1.get(pointer1) > list2.get(pointer2)) {
                    result.add(list2.get(pointer2));
                    pointer2++;
                } else {
                    result.add(list1.get(pointer1));
                    pointer1++;
                    result.add(list2.get(pointer2));
                    pointer2++;
                }
            }

            while (pointer1 < list1.size()) {
                result.add(list1.get(pointer1));
                pointer1++;
            }
            while (pointer2 < list2.size()) {
                result.add(list2.get(pointer2));
                pointer2++;
            }
            return result;
        }

    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(5);
        list1.add(17);
        list1.add(23);
        System.out.println("List1 : " + list1.toString());
        System.out.println("Size : " + list1.size());

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(4);
        list2.add(5);
        list2.add(16);
        list2.add(23);
        list2.add(34);
        System.out.println("List2 : " + list2.toString());
        System.out.println("Size : " + list2.size());

        List<Integer> result = solution.mergeSortedLists(list1, list2);
        System.out.println("Result : " + result.toString());
        System.out.println("Size : " + result.size());

    }
}
