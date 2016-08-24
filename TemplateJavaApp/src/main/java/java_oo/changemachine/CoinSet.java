package java_oo.changemachine;

import java.math.BigDecimal;

public class CoinSet {

	private int numberOfCoin;

	private Coin coin;

	public CoinSet(int numberOfCoin, String coinValue) {
		this.numberOfCoin = numberOfCoin;
		this.coin = new Coin(coinValue);
	}

	public BigDecimal getValue() {
		return new BigDecimal(this.numberOfCoin).multiply(this.coin.getValue());
	}

	public int getNumberOfCoin() {
		return numberOfCoin;
	}

	public Coin getCoin() {
		return coin;
	}

	public String toString() {
		return this.numberOfCoin + "*" + this.getCoin().getValue() + "=" + this.getValue();
	}

	public CoinSet getValue(BigDecimal remainingValue) {
		int numberOfCoin = remainingValue.divide(getCoin().getValue()).intValue();
		return new CoinSet(numberOfCoin,getCoin().getValue().toString());
	}

	public void resetNumberOfCoin(int numberOfCoin2Clear) {
		this.numberOfCoin = this.numberOfCoin - numberOfCoin2Clear;
	}
	
	public boolean isCoinSetLessThanReturnValue(BigDecimal remainingValue){
		return this.getValue().compareTo(remainingValue) == -1;
	}

}
