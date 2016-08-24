package java_oo.coingame;

import java.util.ArrayList;
import java.util.List;

public class CoinGame {

	private List<User> players = new ArrayList<User>();

	public void addPlayer(User user) {
		this.players.add(user);
	}

	public User choosePlayer() {
		return this.players.get(Util.predit(this.players.size()));
	}

	public List<User> getPlayers() {
		return players;
	}

	private Coin coin;

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public Coin getCoin() {
		return coin;
	}

	private CoinFace choosenCoinFace;

	public CoinFace getPayerSelectedCoinFace() {
		return this.choosenCoinFace;
	}

	public void playGame() {

		User preditedPlayer = choosePlayer();

		this.choosenCoinFace = preditedPlayer.getCoinFaceOptionToPlay();

		System.out.println(
				"Predited Player " + preditedPlayer.getName() + " ask for " + preditedPlayer.getCoinFaceOption());

		for (User player : players) {
			player.setCoinFaceOption(choosenCoinFace.getOppositeCoinFace());
		}

		this.coin.flip();

		System.out.println("Coin is flipped and it was " + this.coin.getCoinFace());

		this.printStatus();
	}

	public void printStatus() {

		for (User player : players) {
			System.out.println(player + " " + player.doWin(this.coin.getCoinFace()));
		}

	}

}
