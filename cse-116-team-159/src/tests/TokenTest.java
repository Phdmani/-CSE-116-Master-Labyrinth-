package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import code.Model.Model;
import code.pawn.Pawn;

public class TokenTest {

	@Test
	public void playerCanPickupTokenAfterShiftingThenMoving() {
		Model m = new Model("StaticBoard1_4Players");
		m.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m.setupStaticNonRandomTokens();
		m.populateTokensOnly();
		Pawn p = m.pawns[0];
		int numTokensBefore = p.tokenCount().size();
		m.moveTiles(0, 5);
		p.move("East"); p.move("North"); p.move("North"); p.move("West"); p.move("South"); p.move("West");
		p.checkIfValidMove();
		p.pickUpToken();
		int numTokensAfter = p.tokenCount().size();
		System.out.println(p.tokenCount());
		int tokenValActual = p.tokenCount().get(0);
		int tokenValExpected = 1;
		assertTrue("",numTokensBefore==0 && numTokensAfter==1 && tokenValActual == tokenValExpected);	
	}

}
