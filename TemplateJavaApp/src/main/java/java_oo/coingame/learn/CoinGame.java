package java_oo.coingame.learn;

import java.util.Random;

public class CoinGame {

	private User player1;

	private User player2;

	private User player;

	public void setPlayer1(User user) {
		this.player1 = user;
	}

	public void setPlayer2(User user) {
		this.player2 = user;
	}

	private int predit(int number) {
		Random r = new Random();
		int ran = r.nextInt(number);
		return ran;
	}

	public User choosePlayer() {
		if (predit(2) == 0) {
			this.player = player1;
			return player1;
		} else {
			this.player = player2;
			return player2;
		}
	}
	
	public User getPlayer() {
		return player;
	}

	private Coin coin;

	public void setCoin(Coin coin) {
		this.coin = coin;
	}
	
	public Coin getCoin() {
		return coin;
	}

	/**
	 * Issue 4 : if many user are playing then there could me many winner if the chosen player is a looser
	 * Issue 5 : flipping the coin responsibility must be inside Coin class not in CoinGame because any game can use flipping
	 * Issue 6 : system should ask each player if he/she wins because there could me more winner or more looser
	 * @param coinFace
	 * @return
	 */
	public User playGame(CoinFace coinFace) {

		if (predit(2) == 0) {
			this.coin.setCoinFace(CoinFace.HEAD);
		} else {
			this.coin.setCoinFace(CoinFace.TAIL);
		}

		if (this.coin.getCoinFace().equals(coinFace)) {
			return player;
		} else {
			return getOtherPlayer();
		}
	}

	public User getOtherPlayer() {
		if (this.player.equals(player1)) {
			return this.player2;
		} else if (this.player.equals(player2)) {
			return this.player1;
		}
		return null;
	}

}
