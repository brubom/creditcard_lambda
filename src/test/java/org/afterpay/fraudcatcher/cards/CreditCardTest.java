package org.afterpay.fraudcatcher.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.afterpay.fraudcatcher.utils.ConvertionUtil;
import org.junit.Test;



public class CreditCardTest {

	
	@Test
	public void should_create_card() {
		
		CreditCard card = new CreditCard.Builder().build();
		assertNotNull(card);

		
	 }
	
	@Test
	public void should_card_recipe_creation_used() {
		
		UUID guid = ConvertionUtil.getUUIDfromPlainString("384000008cf011bdb23e10b96e4ef00d");
		Timestamp now = new Timestamp(System.currentTimeMillis());
		BigDecimal value = new BigDecimal(50.01);
		
		CreditCard card = new CreditCard.Builder()
				.cardId(guid)
				.cardUsageTimestamp(now)
				.value(value).build();
		
		assertEquals(guid, 
				card.getCardHash());
		
		assertEquals(now, 
				card.getCardUsageTimestamp());
		
		assertEquals(value, 
				card.getValue());
		
	}
	
	@Test
	public void should_load_card_from_string() {
		
		try {
			String data = "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00";
			
			List<String> items = Arrays.asList(data.split("\\s*,\\s*"));
			
			UUID cardId = ConvertionUtil.getUUIDfromPlainString(items.get(0));
			Timestamp time = ConvertionUtil.getTimestampFromString(items.get(1));
			BigDecimal value = new BigDecimal(items.get(2));
			
			CreditCard card;
		
			card = new CreditCard.Builder()
					.fromString(data)
					.build();
			
			assertEquals(cardId, 
					card.getCardHash());
			
			assertEquals(time, 
					card.getCardUsageTimestamp());
			
			assertEquals(value, 
					card.getValue());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			org.junit.Assert.fail();
		}
		
		
		
		
	}
	
	
}
