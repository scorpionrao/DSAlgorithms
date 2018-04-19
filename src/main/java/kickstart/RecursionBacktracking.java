package kickstart;

import java.util.List;

public class RecursionBacktracking {

    public static class Configuration {
        public static List choices;
        public static boolean isGoalState;
        public static void makeChoice(Object object){};
        public static void unMakeChoice(Object object){};
    }

    public static boolean recurse(Configuration configuration) {

        // BASE CASE
        if(configuration.choices.isEmpty()) {
            return configuration.isGoalState;
        }

        for(Object obj : configuration.choices) {
            configuration.makeChoice(obj);
            if(recurse(configuration)) {
                return true;
            }
            configuration.unMakeChoice(obj);
        }
        return false;
    }
}
