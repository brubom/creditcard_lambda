package org.afterpay.fraudcatcher.cards;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * 
 * @author brunomoreira
 *
 */
public interface Card {

	UUID getCardHash();
	
	void setCardHash(UUID cardnumber);

	Timestamp getCardUsageTimestamp() ;

	void setCardUsageTimestamp(Timestamp cardUsageTimestamp) ;

	BigDecimal getValue() ;

	void setValue(BigDecimal value) ;
	
}
