package zr;

import java.util.*;

public class FirstNonRepeatingCharacter {

    public static class Context {

        public int frequency;
        public int firstOccurence;

        public Context(int firstOccurence) {
            this.frequency = 1;
            this.firstOccurence = firstOccurence;
        }

        public void incrementFrequency() {
            this.frequency++;
        }

        public int getFrequency() {
            return this.frequency;
        }

    }

    public static class QueueSolution {

        private static final int MAX_CHARS = 26;

        /*
            Time complexity - O(N + N)
            Space complexity - Map O(N), QUEUE O(N)
         */
        public void queueBasedFirstNonRepeatingCharacter(String str) {

            Map<Character, Integer> frequencyMap = new HashMap<>();
            Queue<Character> queue = new LinkedList<>();

            /* O(N) */
            for(int i = 0; i < str.length(); i++) {
                if (frequencyMap.containsKey(str.charAt(i))) {
                    frequencyMap.put(str.charAt(i), frequencyMap.get(str.charAt(i)) + 1);
                } else {
                    frequencyMap.put(str.charAt(i), 1);
                }
                queue.add(str.charAt(i));
            }

            while(!queue.isEmpty()) {
                Character ch = queue.poll();
                if(frequencyMap.get(ch) == 1) {
                    System.out.println("QUEUE: " + str + " --> " + ch);
                    return;
                }
            }
            System.out.println("QUEUE: " + str + " --> N/A");
        }
    }

    public static class DLLSolution {

        private static final int MAX_CHARS = 26;

        /*
            Time complexity - O(N)
            Space complexity - Array O(N), DLL O(N)
         */
        public void dllBasedFirstNonRepeatingCharacter(String str) {

            boolean[] isRepeated = new boolean[MAX_CHARS];
            Arrays.fill(isRepeated, false);

            Set<Character> linkedHashSet = new LinkedHashSet<>();

            /* O(N) */
            for(int i = 0; i < str.length(); i++) {
                System.out.println("DLL: Reading Stream --> " + str.charAt(i));
                /* Reading Array - O(1) */
                if(!isRepeated[str.charAt(i) - 'a']) {
                    /* Reading - O(N) */
                    if(linkedHashSet.contains(str.charAt(i))) {
                        /* Reading - O(N) */
                        linkedHashSet.remove(str.charAt(i));
                        isRepeated[str.charAt(i) - 'a'] = true;
                    } else {
                        /* O(1) add at the end */
                        linkedHashSet.add(str.charAt(i));
                    }
                }

                if(!linkedHashSet.isEmpty()) {
                    System.out.println("DLL: " + str + " --> " + linkedHashSet.iterator().next());
                } else {
                    System.out.println("DLL: " + str + " --> N/A");
                }
            }
        }
    }

    public static class MapSolution {

        private static final int MAX_CHARS = 26;

        Map<Character, Context> contextMap = new HashMap<>(MAX_CHARS);

        /*
            O(N + N)
         */
        public void mapBasedFirstNonRepeatingCharacter(String str) {

            if(str == null || str.isEmpty()) {
                return;
            }
            loadFrequency(str);

            for(int i = 0; i < str.length(); i++) {
                if(contextMap.get(str.charAt(i)).getFrequency() == 1) {
                    System.out.println("MAP: " + str + " --> " + str.charAt(i));
                    return;
                }
            }
        }

        public void loadFrequency(String str) {

            if(str == null || str.isEmpty()) {
                return;
            }

            for(int i = 0; i < str.length(); i++) {
                if(contextMap.containsKey(str.charAt(i))) {
                    contextMap.get(str.charAt(i)).incrementFrequency();
                } else {
                    contextMap.put(str.charAt(i), new Context(i));
                }
            }
        }
    }

    public static class ArraySolution {

        private static final int MAX_CHARS = 26;

        int[] frequency = new int[MAX_CHARS];
        Context[] contexts = new Context[MAX_CHARS];

        /*
            O(N + N)
         */
        public void arrayBasedFirstNonRepeatingCharacter(String str) {
            if(str == null || str.isEmpty()) {
                return;
            }
            loadFrequency(str);

            for(int i = 0; i < str.length(); i++) {
                if(frequency[str.charAt(i) - 'a'] == 1) {
                    System.out.println("ARRAY: " + str + " --> " + str.charAt(i));
                    return;
                }
            }
        }

        /*
            O(N + N)
         */
        public void arrayBasedFirstNonRepeatingCharacterOptimized(String str) {
            if(str == null || str.isEmpty()) {
                return;
            }

            for(int i = 0; i < str.length(); i++) {
                if(contexts[str.charAt(i) - 'a'].getFrequency() == 1) {
                    System.out.println("ARRAY: " + str + " --> " + str.charAt(i));
                    return;
                }
            }
        }

        public void loadFrequency(String str) {
            if(str == null || str.isEmpty()) {
                return;
            }

            for(int i = 0; i < str.length(); i++) {
                frequency[str.charAt(i) - 'a']++;

                if(contexts[str.charAt(i) - 'a'] == null) {
                    contexts[str.charAt(i) - 'a'] = new Context(i);
                } else {
                    contexts[str.charAt(i) - 'a'].incrementFrequency();
                }
            }
        }
    }

    public static void main(String[] args) {

        ArraySolution arraySolution = new ArraySolution();
        arraySolution
                .arrayBasedFirstNonRepeatingCharacter("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        arraySolution
                .arrayBasedFirstNonRepeatingCharacterOptimized("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");

        MapSolution mapSolution = new MapSolution();
        mapSolution
                .mapBasedFirstNonRepeatingCharacter("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");

        DLLSolution dllSolution = new DLLSolution();
        dllSolution
                .dllBasedFirstNonRepeatingCharacter("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");

        QueueSolution queueSolution = new QueueSolution();
        queueSolution
                .queueBasedFirstNonRepeatingCharacter("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
    }
}
