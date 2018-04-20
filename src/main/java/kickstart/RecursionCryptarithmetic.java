package kickstart;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manushaonly on 4/20/18.
 */
public class RecursionCryptarithmetic {
    /*
     SEND
    +MORE
    MONEY

    Assign letters to numbers: D E M N O R S Y, 0 - 9
    Check for puzzle answer

     */
    private static class Puzzle {

        private Map<Character, Integer> getMap() {
            Map<Character, Integer> map = new HashMap<>();
            map.put('D', null);
            map.put('E', null);
            map.put('M', null);
            map.put('N', null);
            map.put('O', null);
            map.put('R', null);
            map.put('S', null);
            map.put('Y', null);
            return map;
        }

        private boolean isAnswer(Map<Character, Integer> map) {
            int firstValue =
                    map.get('S') * 1000 + map.get('E') * 100 + map.get('N') * 10 + map.get('D');
            int secondValue =
                    map.get('M') * 1000 + map.get('O') * 100 + map.get('R') * 10 + map.get('E');
            int sum =
                    map.get('M') * 10000 + map.get('O') * 1000 + map.get('N') * 100 + map.get('E') * 10 + map.get('Y');
            return firstValue + secondValue == sum;
        }
    }

    private static boolean solve(Puzzle puzzle) {
        return solve(puzzle, puzzle.getMap());
    }

    private static boolean solve(Puzzle puzzle, Map<Character, Integer> map) {
        Character ch = null;
        if(!hasUnassignedLetters(map, ch)) {
            return puzzle.isAnswer(map);
        }
        for(int option = 0; option <= 9; option++) {
            if(isSafeAssignment(map, option)) {
                map.put(ch, new Integer(option));
                if(solve(puzzle, map)) {
                    return true;
                }
                map.put(ch, null);
            }
        }
        return false;
    }

    private static boolean isSafeAssignment(Map<Character, Integer> map, int option) {
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == null) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasUnassignedLetters(Map<Character, Integer> map, Character ch) {
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == null) {
                ch = entry.getKey();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solve(new Puzzle()));
    }
}
