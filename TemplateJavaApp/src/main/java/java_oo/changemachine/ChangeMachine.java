package java_oo.changemachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChangeMachine {

	private List<CoinSet> availableCoinSets = new ArrayList<CoinSet>();

	public static String MACHINE_CANNOT_GIVE_CHANGE = "Machine have less amount than requested";

	/**
	 * An input will be a set of available coins (coin value and number of each
	 * coin value)
	 * 
	 * @param coinSet
	 */
	public void fillCoins(CoinSet coinSet) {
		this.availableCoinSets.add(coinSet);
	}

	private BigDecimal getTotalAvailableCoinValue() {

		BigDecimal totAvailableCoinValue = new BigDecimal("0");
		for (CoinSet coinSet : availableCoinSets) {
			totAvailableCoinValue = totAvailableCoinValue.add(coinSet.getValue());
		}
		return totAvailableCoinValue;
	}

	/**
	 * input is the change that needs to be made from the coins.
	 * 
	 * @param requestCoin
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public List<CoinSet> getChange(Money requestAmount) {

		BigDecimal totAvailableCoinValue = getTotalAvailableCoinValue();

		if (totAvailableCoinValue.compareTo(requestAmount.getValue()) == -1) {
			// Machine have less amount than requested, so can not give
			// change , please enter less amount
			throw new RuntimeException(MACHINE_CANNOT_GIVE_CHANGE);
		}

		Collections.sort(this.availableCoinSets, new SortCoinSetByValue());

		for (CoinSet coinSet : availableCoinSets) {

			BigDecimal remainingValue = getRemainingValue(requestAmount);

			// if all processed even before iterating all the available set the
			// stop processing
			if (remainingValue.compareTo(new BigDecimal("0")) == 0) {
				break;
			}

			// if current coin set less than the remaining value
			if (coinSet.isCoinSetLessThanReturnValue(remainingValue)) {
				addReturnCoinSets(new CoinSet(coinSet.getNumberOfCoin(), coinSet.getCoin().getValue().toString()));
				continue;
			} else if (coinSet.getValue().compareTo(remainingValue) == 1) {
				addReturnCoinSets(coinSet.getValue(remainingValue));
				continue;
			}

		}

		return getReturnCoinSets(requestAmount);
	}

	private BigDecimal getRemainingValue(Money requestAmount) {
		return requestAmount.getValue().subtract(getReturnCoinSetsValue());
	}

	private List<CoinSet> returnCoinSets = new ArrayList<CoinSet>();

	private void addReturnCoinSets(CoinSet coinSet) {
		this.returnCoinSets.add(coinSet);
	}

	private List<CoinSet> getReturnCoinSets(Money requestAmount) {
		System.out.println("Pre Available Coin Sets: "+this.availableCoinSets + " = " + getTotalAvailableCoinValue());
		System.out.println("Return Coin Sets: "+this.returnCoinSets + " = " + getReturnCoinSetsValue());
		if (getReturnCoinSetsValue().compareTo(requestAmount.getValue()) != 0) {
			// If there is not enough coins, then give away everything.
			// if need to inform the requester that the machine has not filled
			// all the change then do code logic here
		}
		updateAvailableCoinSet(returnCoinSets);
		System.out.println("Post Available Coin Sets: "+this.availableCoinSets + " = " + getTotalAvailableCoinValue());
		return this.returnCoinSets;
	}

	private void updateAvailableCoinSet(List<CoinSet> returnCoinSets) {

		for (CoinSet availableCoinSet : availableCoinSets) {
			for (CoinSet returnCoinSet : returnCoinSets) {
				if (availableCoinSet.getCoin().getValue().equals(returnCoinSet.getCoin().getValue())) {
					availableCoinSet.resetNumberOfCoin(returnCoinSet.getNumberOfCoin());
				}
			}
		}

	}

	private BigDecimal getReturnCoinSetsValue() {
		BigDecimal sets = new BigDecimal("0");
		for (CoinSet coinSet : this.returnCoinSets) {
			sets = sets.add(coinSet.getValue());
		}
		return sets;
	}

}
