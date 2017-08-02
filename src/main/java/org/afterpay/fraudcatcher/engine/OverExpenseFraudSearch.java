package org.afterpay.fraudcatcher.engine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.afterpay.fraudcatcher.cards.CreditCard;
import org.joda.time.DateTime;

/**
 * Find possible fraud match on credit card list
 * @author brunomoreira
 *
 */
public class OverExpenseFraudSearch implements CardFraudSerch<CreditCard> {

	private Date expenseDate;
	private BigDecimal threshold;
	
	public OverExpenseFraudSearch(BigDecimal threshold, Date expenseDate) {

		this.threshold = threshold;
		this.expenseDate = expenseDate;
	}

	public List<CardFraudSummary> findFraudlentCards(List<CreditCard> cards) {
		
		//Cards for expense date
		List<CreditCard> filtered = cards.stream()
				.filter(card -> isDateEqual(card.getCardUsageTimestamp(), expenseDate))
				.collect(Collectors.toList());
		
		//Get sum of expenses
		List<CardFraudSummary> sum = filtered.stream()
			.collect(
	                Collectors.groupingBy(CreditCard::getCardHash, 
	                		Collectors.reducing( BigDecimal.ZERO, CreditCard::getValue, BigDecimal::add )))
			.entrySet()
			.stream()
			.map(summary -> new CardFraudSummary(summary.getKey(), summary.getValue()))
			.collect(Collectors.toList());
		
		//Finally, the suspected cards
		return sum.stream()
			.filter(summary -> summary.getThresholdSum().compareTo(this.threshold) == 1)
			.collect(Collectors.toList());
		
		
		
	}
	
	private Boolean isDateEqual(Date first, Date second) {
		
		DateTime dateFirst = new DateTime(first);
		DateTime dateSecond = new DateTime(second);
		
		
		return dateFirst.getYear() == dateSecond.getYear() && 
				dateFirst.getMonthOfYear() == dateSecond.getMonthOfYear() &&
				dateFirst.getDayOfMonth() == dateSecond.getDayOfMonth();
				
				
		
	}

}
