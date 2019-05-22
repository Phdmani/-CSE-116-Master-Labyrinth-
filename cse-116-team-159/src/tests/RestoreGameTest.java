package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Model.Model;
import code.fileio.FileInputOutput;
import code.tiles.ElbowTile;

public class RestoreGameTest {

	@Test
	public void restoreGame(){

		Model m = new Model("StaticBoard1_4Players");
		m.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m.setupTokens();
		m.populateTokensOnly();
		String iB0 = m.getBoard()[0][0].getIdentity();
		String iB1 = m.getBoard()[1][0].getIdentity();
		String iB2 = m.getBoard()[2][0].getIdentity();
		String iB3 = m.getBoard()[3][0].getIdentity();
		String iB4 = m.getBoard()[4][0].getIdentity();
		String iB5 = m.getBoard()[5][0].getIdentity();
		String iB6 = m.getBoard()[6][0].getIdentity();
		
		String rB0 = m.getBoard()[0][0].getRotation();
		String rB1 = m.getBoard()[1][0].getRotation();
		String rB2 = m.getBoard()[2][0].getRotation();
		String rB3 = m.getBoard()[3][0].getRotation();
		String rB4 = m.getBoard()[4][0].getRotation();
		String rB5 = m.getBoard()[5][0].getRotation();
		String rB6 = m.getBoard()[6][0].getRotation();
		
		ElbowTile et = new ElbowTile("North","L");
		m.setholdTile(et);
		
		String iB7 = m.getHoldTile().getIdentity();
		String rB7 = m.getHoldTile().getRotation();
		m.saveGame(m.pawns[m.playerUp-1], "save.mls");
		String[] s = FileInputOutput.readFileToStringArray("save.mls");
		Model model = new Model();
		model.restoreGame(s);
		//check row 1 of gameboard to see if it is what was expected after restore
		String iA0 = model.getBoard()[0][0].getIdentity();
		String iA1 = model.getBoard()[1][0].getIdentity();
		String iA2 = model.getBoard()[2][0].getIdentity();
		String iA3 = model.getBoard()[3][0].getIdentity();
		String iA4 = model.getBoard()[4][0].getIdentity();
		String iA5 = model.getBoard()[5][0].getIdentity();
		String iA6 = model.getBoard()[6][0].getIdentity();
		
		String rA0 = model.getBoard()[0][0].getRotation();
		String rA1 = model.getBoard()[1][0].getRotation();
		String rA2 = model.getBoard()[2][0].getRotation();
		String rA3 = model.getBoard()[3][0].getRotation();
		String rA4 = model.getBoard()[4][0].getRotation();
		String rA5 = model.getBoard()[5][0].getRotation();
		String rA6 = model.getBoard()[6][0].getRotation();
		
		String iA7 = model.getHoldTile().getIdentity();
		String rA7 = model.getHoldTile().getRotation();
		
		//tile
		
		Boolean sum1 = iB0.equals(iA0)&&iB0.equals(iA0)&&iB1.equals(iA1)&&iB2.equals(iA2)&&iB3.equals(iA3)&&iB4.equals(iA4)&&iB5.equals(iA5)&&iB6.equals(iA6);
		Boolean sum2 = rB0.equals(rA0)&&rB0.equals(rA0)&&rB1.equals(rA1)&&rB2.equals(rA2)&&rB3.equals(rA3)&&rB4.equals(rA4)&&rB5.equals(rA5)&&rB6.equals(rA6);
		Boolean sum3 = iB7.equals(iA7)&&rB7.equals(rA7);
		
		assertTrue("", sum1&&sum2&&sum3);
		
	}
	
}
