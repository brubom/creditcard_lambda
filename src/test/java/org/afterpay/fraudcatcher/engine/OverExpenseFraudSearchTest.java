package org.afterpay.fraudcatcher.engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.afterpay.fraudcatcher.cards.CreditCard;
import org.joda.time.DateTime;
import org.junit.Test;

public class OverExpenseFraudSearchTest {
	
	
	@Test
	public void should_no_fraud_to_be_found() {
		
		List<CardFraudSummary> cards = getSearchedCardList(new BigDecimal(1000000.00), 
				new DateTime(2017, 8, 1, 0, 0).toDate());
	
		assertThat(cards, hasSize(0));
	
		
	 }
	
	@Test
	public void should_one_fraud_to_be_found() {
		
		List<CardFraudSummary> cards = getSearchedCardList(new BigDecimal(1010.00), 
				new DateTime(2017, 8, 1, 0, 0).toDate());
		
		assertThat(cards, hasSize(1));

		
	 }
	

	@Test
	public void should_three_fraud_to_be_found() {
		
		List<CardFraudSummary> cards = getSearchedCardList(new BigDecimal(1.00), 
				new DateTime(2017, 8, 1, 0, 0).toDate());
		
		assertThat(cards, hasSize(3));

		
	 }
	
	private List<CardFraudSummary> getSearchedCardList(BigDecimal value, Date date){
		
		List<CreditCard> cards = createCardListFromString();
		
		OverExpenseFraudSearch fraudSearch = 
				new OverExpenseFraudSearch(value, date);
		
		return fraudSearch.findFraudlentCards(cards);
						
		
	}
	
	private List<CreditCard> createCardListFromString(){
		
		
		try {
			
			List<String> lines = Files.readAllLines(Paths.get("src/test/resources/cardlist.txt"));
			
			List<CreditCard> cards = (List<CreditCard>)lines.stream().map(data -> {
				try {
					return new CreditCard.Builder().fromString(data).build();
				} catch (ParseException e) {
					System.out.println(e.toString());
					return null;
				}
				
			}).collect(Collectors.toList());
			
			return cards;
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
	

}
