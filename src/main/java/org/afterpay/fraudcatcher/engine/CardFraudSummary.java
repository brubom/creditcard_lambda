package org.afterpay.fraudcatcher.engine;

import java.math.BigDecimal;
import java.util.UUID;

public class CardFraudSummary {

	private UUID cardHash;
	private BigDecimal thresholdSum;
	
	public CardFraudSummary(UUID cardHash, BigDecimal thresholdSum) {
		this.cardHash = cardHash;
		this.thresholdSum = thresholdSum;
	}
	
	public UUID getCardHash() {
		return cardHash;
	}
	public void setCardHash(UUID cardHash) {
		this.cardHash = cardHash;
	}
	public BigDecimal getThresholdSum() {
		return thresholdSum;
	}
	public void setThresholdSum(BigDecimal thresholdSum) {
		this.thresholdSum = thresholdSum;
	}
	
}
