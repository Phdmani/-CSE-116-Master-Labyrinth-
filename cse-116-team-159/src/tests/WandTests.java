package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import code.Model.Model;
import code.pawn.Pawn;

public class WandTests {

	@Test
	public void test_UseOneWand() {
		Model m = new Model("StaticBoard1_4Players");
		m.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m.setupTokens();
		m.populateTokensOnly();
		Pawn p = m.pawns[0];
		m.firstMove = false;
		int scoreb = p.getScore();
		int wb = p.getWandCount();
		boolean b1 = p.getUsedWandThisTurn();
		boolean b2 = p.getPickedUpTokenThisTurn();
		boolean b3 = p.getHasMovedThisTurn();
		boolean b4 = m.firstMove;
		p.useWand();
		boolean a1 = p.getUsedWandThisTurn();
		boolean a2 = p.getPickedUpTokenThisTurn();
		boolean a3 = p.getHasMovedThisTurn();
		boolean a4 = m.firstMove;
		int wa = p.getWandCount();
		int scorea = p.getScore();
		assertTrue("",b1==false && b2==false && b3==false && b4==false && wb==3 && scoreb==9
				&& a1==true && a2==false && a3==false && a4==true && wa==2 && scorea==6);
	}
}
