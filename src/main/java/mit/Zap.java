package mit;
import java.util.*;
import java.util.stream.Collectors;

public class Zap {
	
	public interface Agent {}
	
	public enum TransactionStatus {
		ACCEPTING_OFFERS, UNDER_OFFER, OFFER_CLOSED
	}
	
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
		   .map(houseForSale -> new AbstractMap.SimpleEntry<>(houseForSale.getAgent(), houseForSale.getSalePrice() * 0.03))
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
			   .map(houseForSale -> new AbstractMap.SimpleEntry<>(houseForSale.getAgent(), houseForSale.getSalePrice() * 0.03))
			   
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