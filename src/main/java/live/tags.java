package live;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tags {

    public static List<Integer> getShortestContinuousSubsequence(List<String> targetTagsList, List<String> availableTagsList) {

        int[] result = new int[]{0, Integer.MAX_VALUE - 1};

        for(int windowBegins = 0; windowBegins < availableTagsList.size() - targetTagsList.size() + 1; windowBegins++) {
            for(int windowEnds = windowBegins + targetTagsList.size() - 1; windowEnds < availableTagsList.size(); windowEnds++) {
                Set<String> examSet = new HashSet<>(targetTagsList);
                List<String> windowList = availableTagsList.subList(windowBegins, windowEnds + 1);
                examSet.removeAll(windowList);
                if(examSet.isEmpty()) {
                    int candidateLength = windowEnds - windowBegins + 1;
                    if(candidateLength < (result[1] - result[0] + 1)) {
                        result[0] = windowBegins;
                        result[1] = windowEnds;
                    }
                }
            }
        }

        if(result[1] == Integer.MAX_VALUE - 1) {
            result = new int[] {0};
        }

        List<Integer> resultIndexes = new ArrayList<>();
        for(int i : result) {
            resultIndexes.add(i);
        }
        return resultIndexes;
    }

    public static void main(String[] args) {
        String[] availableTags = {"dbc", "2abc", "cab", "bcd", "bcb"};
        List<String> availableTagsList = Arrays.asList(availableTags);

        String[] targetTags = {"2abc", "bcd", "cab"};
        List<String> targetTagsList = Arrays.asList(targetTags);

        List<Integer> shortestContinuousSubsequence =
                getShortestContinuousSubsequence(targetTagsList, availableTagsList);
        System.out.println(shortestContinuousSubsequence.toString());
    }
}
