package live;

import java.util.ArrayList;
import java.util.List;

public class FlipFlop {

    /**

     List of numbers

     Sequence such that

     a1 < a2 > a3 < a4 > a5...
     OR
     a1 > a2 < a3 > a4 < a5...

     Pick the longer sequence (not consecutive)
     Input: 1, 16, 4, 10, 13, 15, 10, 5, 16, 8
     Output 1, 16, 4, 15, 5, 16, 8
           [1, 16, 4, 10, 5, 16, 8]

     */
    static List<Integer> longestSequence(List<Integer> inputList) {

        if(inputList == null || inputList.size() <= 1) {
            return inputList;
        }

        List<Integer> resultList = new ArrayList<>();
        int totalWindowCount = 0;
        int processedWindowCount = 0;

        for(int windowBegins = 0; windowBegins < inputList.size(); windowBegins++) {
            for(int windowEnds = windowBegins; windowEnds < inputList.size(); windowEnds++) {
                System.out.println("Window: " + windowBegins + " " + windowEnds);
                totalWindowCount++;

                if(resultList.size() > (windowEnds - windowBegins + 1)) {
                    continue;
                }
                processedWindowCount++;

                /* EXECUTION RESPECTIVE TO EACH WINDOW */
                List<Integer> firstRuleList = new ArrayList<>();
                List<Integer> secondRuleList = new ArrayList<>();
                boolean lessThanFirstRule = true;
                boolean greaterThanSecondRule = true;
                for(int index = windowBegins; index <= windowEnds; index++) {
                    if(index == windowBegins) {
                        firstRuleList.add(inputList.get(index));
                        secondRuleList.add(inputList.get(index));
                        continue;
                    }
                    if(lessThanFirstRule) {
                        if(firstRuleList.get(firstRuleList.size()-1) < inputList.get(index)) {
                            firstRuleList.add(inputList.get(index));
                            lessThanFirstRule = !lessThanFirstRule;
                        }
                    } else {
                        if(firstRuleList.get(firstRuleList.size()-1) > inputList.get(index)) {
                            firstRuleList.add(inputList.get(index));
                            lessThanFirstRule = !lessThanFirstRule;
                        }
                    }
                    if(greaterThanSecondRule) {
                        if(secondRuleList.get(secondRuleList.size()-1) > inputList.get(index)) {
                            secondRuleList.add(inputList.get(index));
                            greaterThanSecondRule = !greaterThanSecondRule;
                        }
                    } else {
                        if(secondRuleList.get(secondRuleList.size()-1) < inputList.get(index)) {
                            secondRuleList.add(inputList.get(index));
                            greaterThanSecondRule = !greaterThanSecondRule;
                        }
                    }
                }
                System.out.println("First Rule: " + firstRuleList.toString());
                System.out.println("Second Rule: " + secondRuleList.toString());
                if(firstRuleList.size() > resultList.size() || secondRuleList.size() > resultList.size()) {
                    if(firstRuleList.size() > secondRuleList.size()) {
                        resultList = firstRuleList;
                    } else {
                        resultList = secondRuleList;
                    }
                }
            }
        }
        System.out.println("Total Window count: " + totalWindowCount);
        System.out.println("Processed Window count: " + processedWindowCount);
        return resultList;
    }

    public static void main(String args[] ) throws Exception {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(16);
        list.add(4);
        list.add(10);
        list.add(13);
        list.add(15);
        list.add(10);
        list.add(5);
        list.add(16);
        list.add(8);

        System.out.println(list.toString());
        List<Integer> outputSequence = longestSequence(list);
        System.out.println(outputSequence.toString());

    }
}
