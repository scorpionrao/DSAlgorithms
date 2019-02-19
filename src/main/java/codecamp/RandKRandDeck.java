package codecamp;

import java.util.Arrays;
import java.util.Random;

public class RandKRandDeck {

    int k;

    public RandKRandDeck(int k) {
        this.k = k;
    }

    private int randk() {
        return new Random().nextInt(k);
    }

    private int rand(int length) {
        int randomNum = k * randk() + randk();
        int div = ((int) Math.pow(k, 2) + k) / length;
        if(randomNum <= ((div * length) - 1)) {
            return (randomNum % length) + 1;
        }
        return rand(length);
    }

    private void shuffle(int[] cards) {
        for (int index = 0; index < cards.length; index++) {
            int randomIndex = index + rand(cards.length - index);
            int temp = cards[randomIndex];
            cards[randomIndex] = cards[index];
            cards[index] = temp;
        }
    }

    public static void main(String[] args) {
        RandKRandDeck object = new RandKRandDeck(10);
        int[] cards = new int[52];
        for(int i = 0; i < cards.length; i++) {
            cards[i] = i;
        }
        System.out.println("Input : " + Arrays.toString(cards));
        object.shuffle(cards);
        System.out.println("Output : " + Arrays.toString(cards));

    }
}
