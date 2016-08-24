package java_oo.coingame;

public enum CoinFace {

	HEAD,TAIL;

	public CoinFace getOppositeCoinFace() {
		return this == CoinFace.HEAD ? CoinFace.TAIL : HEAD;
	}
}
