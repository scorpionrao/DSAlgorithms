package codecamp;

import java.util.*;

public class SymmetricDifference {

    /*
        1, 2, 3
        5, 2, 1, 4

        Set1 - {1, 2, 3}
        Set2 - {5, 2, 1, 4}

        1, 2, 3, 4, 5
     */

    private static List<Integer> symmetricDifference(List<List<Integer>> lists) {

        List<Integer> diff = new ArrayList<>();
        for(int i = 0; i < lists.size(); i++) {
            diff = symmetricDifference(diff, lists.get(i));
        }
        return diff;
    }

    private static List<Integer> symmetricDifference(List<Integer> list1, List<Integer> list2) {

        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);

        Set<Integer> union = new HashSet<>(list1);
        union.addAll(list2);

        Set<Integer> intersection = new HashSet<>();
        for(int num : set1) {
            if(set2.contains(num)) {
                intersection.add(num);
            }
        }

        union.removeAll(intersection);

        return new ArrayList<>(union);

    }


    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(new Integer[]{3, 3, 3, 2, 5});
        List<Integer> list2 = Arrays.asList(new Integer[]{2, 1, 5, 7});
        List<Integer> list3 = Arrays.asList(new Integer[]{3, 4, 6, 6});
        List<Integer> list4 = Arrays.asList(new Integer[]{1, 2, 3});
        List<Integer> list5 = Arrays.asList(new Integer[]{5, 3, 9, 8});
        List<Integer> list6 = Arrays.asList(new Integer[]{1});

        List<Integer> result = symmetricDifference(Arrays.asList(list1, list2, list3, list4, list5, list6));
        System.out.println(result.toString());

    }
}
