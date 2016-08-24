package java_oo.changemachine;

import java.util.Comparator;

public class SortCoinSetByValue implements Comparator<CoinSet> {

	/**
	 * Order based on coin value
	 */
	public int compare(CoinSet o1, CoinSet o2) {

		return o2.getCoin().getValue().compareTo(o1.getCoin().getValue());
	}

}
