package zr;
import java.util.*;
import java.util.stream.Collectors;

public class IncomeEstimates {

	/*

		Things to look for:
		Does the candidate write modern Java (Examples: Does the candidate use the for each style
		for SinglyLinkedListLoop added in Java 5 or does the candidate use List indices, Use of Diamond Notation etc)

		Follow-up question:
		How do we make the code faster? The problem requires processing all brokers and all houses
		for sale. The best algorithmic complexity we can get is O(n2). The answer is to leverage concurrency.

		The are several ways to do this (ForkJoinPools etc) but the easiest simplest way is to solve
		the problem using the Java 8 Streams library. The solution below uses a parallel Stream to
		mergeKLists elements concurrently.

	 */
	
	public interface Agent {}
	
	public enum TransactionStatus {ACCEPTING_OFFERS, UNDER_OFFER, OFFER_CLOSED}
	
	public interface HouseForSale {
		Agent getAgent();
		Long getSalePrice();
		TransactionStatus getTransactionStatus();
	}
	
	public interface Broker {
		List<HouseForSale> getHousesForSale();
	}
	
	/*
	 * For each `Agent`, the estimated income that `Agent` currently has 
	 * is sum of the sale price of each `HouseForSale` that is UNDER_OFFER
	 * multiplied by 0.03 
	 */
	public Map<Agent, Double> getEstimatedIncome(List<Broker> brokers) {
		
		return brokers.parallelStream()
		   .map(broker -> broker.getHousesForSale())
		   .flatMap(housesForSale -> housesForSale.parallelStream())
		   .filter(houseForSale -> houseForSale.getTransactionStatus() == TransactionStatus.UNDER_OFFER)
		   .map(houseForSale -> new AbstractMap.SimpleEntry<>(houseForSale.getAgent(),
				   												houseForSale.getSalePrice() * 0.03))
		   .collect(
				   Collectors.groupingBy(entry -> entry.getKey(),
				   Collectors.summingDouble(entry -> entry.getValue())
				   )
		   );
		
		/*
				brokers.parallelStream()
			   
			   // Stream<List<HouseForSale>>
			   //.map(Broker::getHousesForSale)
			   .map(broker -> broker.getHousesForSale())
			   
			   // Stream<HouseForSale>
			   //.flatMap(List::stream)
			   .flatMap(housesForSale -> housesForSale.parallelStream())
			   
			   // filtered Stream<HouseForSale>
			   .filter(houseForSale -> houseForSale.getTransactionStatus() == TransactionStatus.UNDER_OFFER)
			   
			   // Stream<Map<Agent, Long>>
			   .map(houseForSale -> new AbstractMap.SimpleEntry<>(houseForSale.getAgent(),
			   														houseForSale.getSalePrice() * 0.03))
			   
			   // new mutable reduction container
			   .collect(
					   // grouping elements according to classifier
					   //Collectors.groupingBy(Map.Entry::getKey,
					   Collectors.groupingBy(entry -> entry.getKey(),
							   // Collector for sum of a double-valued function of input elements
							   //Collectors.summingDouble(Map.Entry::getValue)));
							   Collectors.summingDouble(entry -> entry.getValue())
					   )
			   );
		 */	   
	}
}