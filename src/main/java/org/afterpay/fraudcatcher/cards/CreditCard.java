package org.afterpay.fraudcatcher.cards;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.afterpay.fraudcatcher.utils.ConvertionUtil;

/**
 * 
 * @author brunomoreira
 *
 */
public class CreditCard extends BaseCard {

	
	public static class Builder{
		private UUID cardnumber = UUID.randomUUID();
		private Timestamp cardUsageTimestamp = new Timestamp(System.currentTimeMillis()); 
		private BigDecimal value = new BigDecimal(0);
		
		/**
		 * Creates a credit card from a string line
		 * @param cardstring
		 * @return
		 * @throws ParseException 
		 */
		public CreditCard.Builder fromString(String cardstring) throws ParseException{
			
			List<String> items = Arrays.asList(cardstring.split("\\s*,\\s*"));
			
			return this
					.cardId(ConvertionUtil.getUUIDfromPlainString(items.get(0)))
					.cardUsageTimestamp(ConvertionUtil.getTimestampFromString(items.get(1)))
					.value(new BigDecimal(items.get(2)));
					
					
		}
		
		
		public CreditCard.Builder cardId (UUID cardnumber){
			this.cardnumber = cardnumber;
			return this;
		}
		
		public CreditCard.Builder cardUsageTimestamp (Timestamp cardUsageTimestamp){
			this.cardUsageTimestamp = cardUsageTimestamp;
			return this;
		}
		
		public CreditCard.Builder value(BigDecimal value){
			this.value = value;
			return this;
		}
		
		public CreditCard build () {
			return new CreditCard(this);
		}
		
	}
	
	private CreditCard(CreditCard.Builder builder) {
		this.cardnumber = builder.cardnumber;
		this.cardUsageTimestamp = builder.cardUsageTimestamp;
		this.value = builder.value;
		
	}

	@Override
	public String toString() {

		try {
			
			StringBuilder builder = new StringBuilder();
			
			builder.append(this.cardnumber.toString().replaceAll("-", ""));
			builder.append(",");
		
			builder.append(ConvertionUtil.getStringFromTimestamp(this.cardUsageTimestamp));
			builder.append(",");
			
			builder.append(this.value.setScale(2));
			
			return builder.toString();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	

}
