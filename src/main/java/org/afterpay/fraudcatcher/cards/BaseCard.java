package org.afterpay.fraudcatcher.cards;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * 
 * @author brunomoreira
 * Represent an abstraction of a default card, given most cards behave same way and have similar properties
 */
abstract public class BaseCard implements Card {

	protected UUID cardnumber;
	protected Timestamp cardUsageTimestamp; 
	protected BigDecimal value;

	
	public UUID getCardHash() {
		
		return cardnumber;
	}

	public void setCardHash(UUID cardnumber) {
		this.cardnumber = cardnumber;
		
	}

	public Timestamp getCardUsageTimestamp() {
		return cardUsageTimestamp;
	}

	public void setCardUsageTimestamp(Timestamp cardUsageTimestamp) {
		this.cardUsageTimestamp = cardUsageTimestamp;
	}

	public BigDecimal getValue() {
		return value;
	}

	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	abstract public String toString();

}
