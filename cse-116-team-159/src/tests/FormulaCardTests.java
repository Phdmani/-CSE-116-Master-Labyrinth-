package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Model.FormulaCard;
import code.Model.Model;
import code.pawn.Pawn;
import code.tokens.Token;

public class FormulaCardTests {

	@Test 
	public void test_checkPlayerGets20PointsPerMatchingTokenToSecretRecipe() {
		Model m = new Model("StaticBoard1_4Players");
		m.setStaticPlayersWithoutFormulaCards(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m.setupStaticNonRandomTokens();
		m.populateTokensOnly();
		Pawn p = m.pawns[0];
		p.setFormulaCard(new FormulaCard(1,10,13));
		int scoreBeforeActual = p.getScore();
		m.moveTiles(0, 5);
		p.move("East"); p.move("North"); p.move("North"); p.move("West"); p.move("South"); p.move("West");
		p.checkIfValidMove();
		p.pickUpToken();
		int scoreAfterActual = p.getScore();
		int scoreBeforeExpected = 9;
		int scoreAfterExpected = 30;
		boolean b1 = scoreAfterActual == scoreAfterExpected;
		boolean b2 = scoreBeforeActual == scoreBeforeExpected;
//		System.out.println(b1 + " " + b2);
//		System.out.println(b1 + " " + b2);
		assertTrue(b1 + " " + b2,b1&&b2);
	}
	
	@Test
	public void test_checkIfAssigningFormulaCardSuccessful(){
		Model m = new Model("StaticBoard1_4Players");
		m.setStaticPlayersWithoutFormulaCards(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m.setupStaticNonRandomTokens();
		m.populateTokensOnly();
		Pawn p = m.pawns[0];
		int val1 = 1;
		int val2 = 10;
		int val3 = 13;
		p.setFormulaCard(new FormulaCard(val1,val2,val3));
		FormulaCard fCard = p.getFormulaCard();
		int actVals[] = fCard.getValues();
		assertTrue("",val1==actVals[0] && val2==actVals[1] && val3==actVals[2]);
	}
}
