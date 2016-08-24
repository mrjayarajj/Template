package java_oo.coingame;

public class User {

	private String name;

	private CoinFace coinFaceOption;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "Player " + this.name + " has coin face option as " + this.coinFaceOption;
	}

	public boolean equals(Object obj) {
		return this.name.equals(((User) obj).getName());
	}

	public CoinFace getCoinFaceOption() {
		return this.coinFaceOption;
	}

	public void setCoinFaceOption(CoinFace coinFaceOption) {
		if (this.coinFaceOption == null) {
			this.coinFaceOption = coinFaceOption;
		}
	}

	public CoinFace getCoinFaceOptionToPlay() {
		this.coinFaceOption = (Util.predit(2) == 0 ? CoinFace.TAIL : CoinFace.HEAD);
		return this.coinFaceOption;
	}

	public boolean doWin(CoinFace winningFlip) {
		if (this.coinFaceOption != null) {
			return this.coinFaceOption.equals(winningFlip);
		}

		return false;
	}

}
