package java_oo.changemachine;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ChangeMachineTest {

	@Test
	public void testGetChangeWhenMachineHasLessValue() {

		ChangeMachine machine = new ChangeMachine();
		machine.fillCoins(new CoinSet(4, "1"));

		try {
			machine.getChange(new Money("5"));
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), ChangeMachine.MACHINE_CANNOT_GIVE_CHANGE);
		}

	}

	@Test
	public void testGetChangeWhenMachineHasEnoughChange() {

		ChangeMachine machine = new ChangeMachine();
		machine.fillCoins(new CoinSet(4, "1"));
		machine.fillCoins(new CoinSet(5, "0.25"));
		machine.fillCoins(new CoinSet(20, "0.25"));

		List<CoinSet> changes = machine.getChange(new Money("5"));

		Assert.assertEquals(2, changes.size());

		// 4 * 1$ = 4$
		Assert.assertEquals(4, changes.get(0).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("1"), changes.get(0).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("4"), changes.get(0).getValue());

		// 4 * 0.25 = 1$
		Assert.assertEquals(4, changes.get(1).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.25"), changes.get(1).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("1.00"), changes.get(1).getValue());

	}

	@Test
	public void testGetChangeWhenMachineHasMoreNumberOfCoinOnLessCoinValue() {

		ChangeMachine machine = new ChangeMachine();
		machine.fillCoins(new CoinSet(3, "0.25"));
		machine.fillCoins(new CoinSet(5, "0.05"));
		machine.fillCoins(new CoinSet(85, "0.01"));

		List<CoinSet> changes = machine.getChange(new Money("0.86"));

		Assert.assertEquals(3, changes.size());

		Assert.assertEquals(3, changes.get(0).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.25"), changes.get(0).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.75"), changes.get(0).getValue());

		Assert.assertEquals(2, changes.get(1).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.05"), changes.get(1).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.10"), changes.get(1).getValue());

		Assert.assertEquals(1, changes.get(2).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.01"), changes.get(2).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.01"), changes.get(2).getValue());

	}

	@Test
	public void testGetChangeWhenMachineHasMoreNumberOfCoinOnHigherCoinValue() {

		ChangeMachine machine = new ChangeMachine();
		machine.fillCoins(new CoinSet(85, "0.25"));
		machine.fillCoins(new CoinSet(5, "0.05"));
		machine.fillCoins(new CoinSet(3, "0.01"));

		List<CoinSet> changes = machine.getChange(new Money("0.86"));

		Assert.assertEquals(3, changes.size());

		Assert.assertEquals(3, changes.get(0).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.25"), changes.get(0).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.75"), changes.get(0).getValue());

		Assert.assertEquals(2, changes.get(1).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.05"), changes.get(1).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.10"), changes.get(1).getValue());

		Assert.assertEquals(1, changes.get(2).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.01"), changes.get(2).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.01"), changes.get(2).getValue());
	}

	@Test
	public void testGetChangeWhenMachineCanNotGiveChange() {

		ChangeMachine machine = new ChangeMachine();
		machine.fillCoins(new CoinSet(85, "0.25"));
		machine.fillCoins(new CoinSet(5, "0.05"));
		machine.fillCoins(new CoinSet(2, "0.01"));

		List<CoinSet> changes = machine.getChange(new Money("21.33"));

		Assert.assertEquals(3, changes.size());

		Assert.assertEquals(85, changes.get(0).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.25"), changes.get(0).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("21.25"), changes.get(0).getValue());

		Assert.assertEquals(1, changes.get(1).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.05"), changes.get(1).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.05"), changes.get(1).getValue());

		Assert.assertEquals(2, changes.get(2).getNumberOfCoin());
		Assert.assertEquals(new BigDecimal("0.01"), changes.get(2).getCoin().getValue());
		Assert.assertEquals(new BigDecimal("0.02"), changes.get(2).getValue());

	}
}
