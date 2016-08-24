package java_oo.coingame.learn;

import org.junit.Assert;
import org.junit.Test;

public class TestCoinGame {

	/**
	 * Issue 1 : only two player , tightly coupled with two players
	 * Issue 2 : player has not even provided his input its hard coded as CoinFace.TAIL 
	 * Issue 3 : choosing a player and getting his input responsibility must be inside the CoinGame not in TestCoinGame
	 * goto CoinGame.playGame(CoinFace) for further issue
	 */
	@Test
	public void testCoinGame() {
		User player1 = new User("Jay");
		User player2 = new User("Prema");
		CoinGame game = new CoinGame();
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.setCoin(new Coin());

		for (int i = 0; i < 10; i++) {
			System.out.println("---------------------");

			User player = game.choosePlayer();
			User winner = game.playGame(CoinFace.TAIL);
			System.out.println(
					"Predited Player=" + game.getPlayer() + " ask for=" + CoinFace.TAIL + " The coin face happed was="
							+ game.getCoin().getCoinFace() + " So the game winner is =" + winner);

			if (CoinFace.TAIL.equals(game.getCoin().getCoinFace())) {
				Assert.assertEquals(winner, player);
			} else {
				Assert.assertEquals(winner, getOtherPlayer(player, player1, player2));
			}

			System.out.println("---------------------");
			System.out.println();
			System.out.println();
		}

	}

	private User getOtherPlayer(User player, User player1, User player2) {
		if (player.equals(player1)) {
			return player2;
		} else {
			return player1;
		}
	}

}
