package codecamp;

public class SellAndBuyStock {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxSell = 0;
        for(int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxSell = Math.max(maxSell, prices[i] - minPrice);
        }
        return maxSell;
    }
}
