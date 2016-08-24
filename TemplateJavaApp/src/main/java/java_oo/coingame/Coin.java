package java_oo.coingame;

public class Coin {

	public CoinFace coinFace;

	public Coin() {
	}

	public CoinFace getCoinFace() {
		return coinFace;
	}

	public void flip() {
		if (Util.predit(2) == 0) {
			this.coinFace = CoinFace.HEAD;
		} else {
			this.coinFace = CoinFace.TAIL;
		}
	}
}
