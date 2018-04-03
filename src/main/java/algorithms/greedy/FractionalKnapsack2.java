package algorithms.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class FractionalKnapsack2 {

    public static double getMaxValue(int capacity, int[] values, int[] weights) {

        double totalValue = 0;
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            Item item = new Item(values[i], weights[i]);
            items.add(item);
        }
        Collections.sort(items, Collections.reverseOrder());
        Iterator<Item> itemIterator = items.iterator();
        while(capacity > 0 && itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if(capacity >= item.weight) {
                capacity = capacity - item.weight;
                totalValue = totalValue + item.value;
            } else {
                totalValue = capacity * item.unitPrice;
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getMaxValue(capacity, values, weights));

    }
}

class Item implements Comparable<Item>{
    public int weight, value;
    public double unitPrice;
    Item(int value, int weight){
        this.value = value;
        this.weight = weight;
        unitPrice = (double)value/weight;
    }

    public int compareTo(Item other){
        if (this.unitPrice > other.unitPrice) return 1;
        if (this.unitPrice < other.unitPrice) return -1;
        return 0;
        // return (int)(this.unitPrice - other.unitPrice);
    }
}

