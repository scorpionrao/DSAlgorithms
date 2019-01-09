package codecamp;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class UpdateInventory {

    private static class Item {
        int quantity;
        String name;
        Item(int quantity, String name) {
            this.quantity = quantity;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[" + quantity + ", \"" + name + "\"]";
        }
    }

    private static Item[] updateInventory(Item[] existing, Item[] fresh) {
        Map<String, Integer> inventory = new TreeMap<>();
        for(Item item : existing) {
            inventory.put(item.name, item.quantity);
        }
        for(Item item : fresh) {
            if(inventory.containsKey(item.name)) {
                inventory.put(item.name, inventory.get(item.name) + item.quantity);
            } else {
                inventory.put(item.name, item.quantity);
            }
        }

        Item[] result = new Item[inventory.size()];
        int i = 0;
        for(Map.Entry<String, Integer> entry : inventory.entrySet()) {
            result[i] = new Item(entry.getValue(), entry.getKey());
            i++;
        }
        return result;
    }

    public static void main(String[] args) {

        Item item2 = new Item(2, "Dirty Sock");
        Item item3 = new Item(1, "Hair Pin");
        Item item4 = new Item(5, "Microphone");
        Item item1 = new Item(21, "Bowling Ball");

        Item[] existing = new Item[]{item1, item2, item3, item4};
        Item[] fresh = new Item[]{};

        Item[] result = updateInventory(existing, fresh);
        System.out.println(Arrays.toString(result));

    }
}
