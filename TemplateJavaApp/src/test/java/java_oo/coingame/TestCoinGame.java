package java_oo.coingame;

import org.junit.Assert;
import org.junit.Test;


public class TestCoinGame {

	@Test
	public void testCoinGame() {

		System.out.println("---------------------");

		CoinGame game = new CoinGame();
		game.addPlayer(new User("Jay"));
		game.addPlayer(new User("Prema"));
		game.addPlayer(new User("Anirudh"));
		game.setCoin(new Coin());
		
		game.playGame();
		
		for(User player : game.getPlayers()){
			Assert.assertEquals(player.getCoinFaceOption().equals(game.getPayerSelectedCoinFace()), player.doWin(game.getCoin().getCoinFace()) );
		}		

		System.out.println("---------------------");
		System.out.println();
		System.out.println();
	}

}
