package org.afterpay.fraudcatcher.engine;

import java.util.List;
import java.util.UUID;

import org.afterpay.fraudcatcher.cards.Card;

/**
 * 
 * @author brunomoreira
 *
 */
public interface CardFraudSerch <T extends Card>{

	/**
	 * Find a list of fraudulent cards in a given cards list
	 * @param cards
	 * @return
	 */
	List<CardFraudSummary> findFraudlentCards(List<T> cards);
	
}
