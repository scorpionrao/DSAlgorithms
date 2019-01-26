package live;

import java.util.*;

public class Diplomacy {

    static Map<String, String> armyAndCity = new TreeMap<>();
    static Map<String, String> cityAndArmy = new HashMap<>();
    static Map<String, Integer> armyAndStrength = new HashMap<>();

    static void addArmyToCity(String city, String army) {

        String currentArmy = cityAndArmy.get(city);
        if(currentArmy == null) {
            cityAndArmy.put(city, army);
            armyAndCity.put(army, city);
            incrementStrength(army);
        } else {
            int currentOccupantStrength = armyAndStrength.get(currentArmy);
            int newStrength = armyAndStrength.get(army);
            if(currentOccupantStrength == newStrength) {
                armyAndCity.put(currentArmy, null);
                cityAndArmy.remove(city);
                armyAndStrength.put(currentArmy, 0);
                armyAndCity.put(army, null);
                armyAndStrength.put(army, 0);
            }
        }
    }

    static void removeArmyFromCity(String city, String army) {
        armyAndCity.remove(army);
        cityAndArmy.remove(city);
        armyAndStrength.remove(army);
    }

    static void incrementStrength(String army) {
        Integer currentStrength = armyAndStrength.get(army);
        if(currentStrength == null) {
            armyAndStrength.put(army, 1);
        } else {
            armyAndStrength.put(army, currentStrength + 1);
        }
    }

    static List<String> evaluateActions(List<String> actions) {
        for(String command : actions) {
            String[] words = command.split(" ");
            String army = words[0];
            String city = words[1];
            String action = words[2];
            if(action.equals("Hold")) {
                addArmyToCity(city, army);
            } else if(action.equals("Move")) {
                removeArmyFromCity(city, army);
                addArmyToCity(words[3], army);
            } else if(action.equals("Support")) {
                addArmyToCity(city, army);
                incrementStrength(words[3]);
            }
        }
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, String> entry : armyAndCity.entrySet()) {
            result.add(entry.getKey() + " " + entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        String action1 = "A Munich Hold";
        String action2 = "B Botswana Move Bohemia";
        String action3 = "C Warsaw Support B";
        String action4 = "D Frankfurt Move Munich";
        List<String> actions = Arrays.asList(action1, action2, action3, action4);
        List<String> result = evaluateActions(actions);
        for(String armyLocation : result) {
            System.out.println(armyLocation);
        }
    }
}
