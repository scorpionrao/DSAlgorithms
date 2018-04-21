package kickstart;

import java.util.LinkedHashMap;
import java.util.Map;

public class RecursionCryptarithmetic {

    interface Puzzle {
        Map<Character, Integer> getMap();
        boolean isAnswer(Map<Character, Integer> map);
    }
    /*
     SEND
    +MORE
    MONEY
    Assign letters to numbers: D E M N O R S Y, 0 - 9
    Check for puzzle answer
     */
    private static class Puzzle1 implements Puzzle{

        public Map<Character, Integer> getMap() {
            Map<Character, Integer> map = new LinkedHashMap<>();
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

        public boolean isAnswer(Map<Character, Integer> map) {

            System.out.println("All Assigned:" + map.toString());
            int firstValue =
                    map.get('S') * 1000 + map.get('E') * 100 + map.get('N') * 10 + map.get('D');
            int secondValue =
                    map.get('M') * 1000 + map.get('O') * 100 + map.get('R') * 10 + map.get('E');
            int sum =
                    map.get('M') * 10000 + map.get('O') * 1000 + map.get('N') * 100 + map.get('E') * 10 + map.get('Y');

            boolean check = firstValue + secondValue == sum;

            if(check) {
                System.out.println(String.format("S:%d, E:%d, N:%d, D:%d", map.get('S'), map.get('E'), map.get('N'), map.get('D')));
                System.out.println(String.format("M:%d, O:%d, R:%d, E:%d", map.get('M'), map.get('O'), map.get('R'), map.get('E')));
                System.out.println(String.format("M:%d, O:%d, N:%d, E:%d, Y:%d", map.get('M'), map.get('O'), map.get('N'), map.get('E'), map.get('Y')));
            }
            return check;
        }
    }

    /*
        All Assigned:{C=1, F=9, N=8, O=3, S=5, U=2, Y=0}

        EVERY COMBINATION OF NUMBERS IS THE NUMBER OF POSSIBILITIES.
     */
    private static class Puzzle2 implements Puzzle{

        public Map<Character, Integer> getMap() {
            Map<Character, Integer> map = new LinkedHashMap<>();
            map.put('C', null);
            map.put('F', null);
            map.put('N', null);
            map.put('O', null);
            map.put('S', null);
            map.put('U', null);
            map.put('Y', null);
            return map;
        }

        public boolean isAnswer(Map<Character, Integer> map) {

            System.out.println("All Assigned:" + map.toString());
            int firstValue = map.get('C') * 10 + map.get('S');
            int secondValue = map.get('Y') * 100 + map.get('O') * 10 + map.get('U');
            int sum = map.get('F') * 100 + map.get('U') * 10 + map.get('N');

            boolean check = firstValue + secondValue == sum;

            if(check) {
                System.out.println(String.format("C:%d, S:%d", map.get('C'), map.get('S')));
                System.out.println(String.format("Y:%d, O:%d, U:%d", map.get('Y'), map.get('O'), map.get('U')));
                System.out.println(String.format("F:%d, U:%d, N:%d", map.get('F'), map.get('U'), map.get('N')));
            }
            return check;
        }
    }


    private static boolean solve(Puzzle puzzle) {
        return solve(puzzle, puzzle.getMap(), 0);
    }

    private static boolean solve(Puzzle puzzle, Map<Character, Integer> map, int level) {
        Character[] ch = new Character[1];
        if(!hasUnassignedLetters(map, ch, level)) {
            return puzzle.isAnswer(map);
        }
        for(int option = 0; option <= 9; option++) {
            if(isSafeAssignment(map, option)) {
                map.put(ch[0], new Integer(option));
                if(solve(puzzle, map, level+1)) {
                    return true;
                }
                map.put(ch[0], null);
            }
        }
        return false;
    }

    private static boolean isSafeAssignment(Map<Character, Integer> map, int option) {
        // there are no characters with this option already
        if(map.values().contains(option)) {
            return false;
        }
        return true;
    }

    private static boolean hasUnassignedLetters(Map<Character, Integer> map, Character[] ch, int level) {
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == null) {
                ch[0] = entry.getKey();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solve(new Puzzle2()));
    }
}

