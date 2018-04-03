package algorithms.week3;

import java.util.LinkedList;
import java.util.List;

public class pointscoversorted {

    public void pointscoversorted(int[] ageOfChildren) {

        int i = 0;
        List<List<Integer>> groups = new LinkedList<>();
        int row = 0;
        while(i <= ageOfChildren.length) {
            int l = ageOfChildren[i];
            int r = ageOfChildren[i] + 1;
            groups.set(row, new LinkedList<>());
            while(i <= ageOfChildren.length && ageOfChildren[i] < r) {
                groups.get(row).add(ageOfChildren[i]);
                i++;
            }
            row++;
        }

    }
}
