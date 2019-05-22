package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Model.Model;
import code.tiles.ElbowTile;
import code.tiles.StraightTiles;
import code.tiles.TTile;
import code.tiles.Tiles;
import code.tokens.Token;

public class BoardTest {
	/**@author Jmosca
	 * Checks to see if tile set is static
	 */
	
	@Test
	public void test12() {
		Tiles expected = new ElbowTile("East","L");
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		board.setStaticTiles();
		Tiles actual=board.getTile(0, 0);
		assertTrue("",expected.getRotation().equals(actual.getRotation()));
		
	}
	/**@author Jmosca
	 * Tests 13-24: For each specific tile and rotation, checks if the 
	 * new tile created matches matches contents of the old one 
	 * 
	 */
	@Test
	public void test13() {
		String expected = "North";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new ElbowTile("North", "L");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test14() {
		code.Driver d = new code.Driver();
		String expected = "South";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new ElbowTile("South","L");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test15() {
		String expected = "East";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new ElbowTile("East","L");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test16() {
		String expected = "West";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new ElbowTile("West","L");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test17() {
		String expected = "North";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new StraightTiles("North","I");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test18() {
		String expected = "South";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new StraightTiles("South","I");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	public void test19() {
		String expected = "West";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new StraightTiles("West","I");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test20() {
		String expected = "East";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new StraightTiles("East","I");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	
	
	@Test
	public void test21() {
		String expected = "North";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new TTile("North","T");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test22() {
		String expected = "South";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new TTile("South","T");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void test23() {
		String expected = "East";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new TTile("East","T");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	@Test	
	public void test24() {
		String expected = "West";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles old_place = new TTile("West","T");
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0, 1).getRotation();
		assertTrue("", expected.equals(actual));
	}
	/**@author Jmosca
	 * Test 25-36 :Checks to see if tiles are moved to correct location
	 * 
	 */
	@Test
	public void test25() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new ElbowTile("North","L");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	
	@Test
	public void test26() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new ElbowTile("South","L");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test27() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new ElbowTile("East","L");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test28() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new ElbowTile("West","L");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test29() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new StraightTiles("North","I");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test30() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new StraightTiles("South","I");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test31() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new StraightTiles("East","I");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test32() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new StraightTiles("West","I");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test33() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new TTile("North","T");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test34() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new TTile("South","T");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test35() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new TTile("East","T");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
	@Test
	public void test36() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles new_place = new TTile("West","T");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("", old_place.equals(new_place));
	}
/**@author Jmosca
 * Test 37-41: Checks if the held tile stays the same 
 */

	@Test
	public void test37() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles tile = new StraightTiles("East","I");
		board.setholdTile(tile);
		Tiles actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test38() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles tile = new ElbowTile("North","L");
		board.setholdTile(tile);
		Tiles actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test39() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles tile = new ElbowTile("South","L");
		board.setholdTile(tile);
		Tiles actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test40() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles tile = new ElbowTile("East","L");
		board.setholdTile(tile);
		Tiles actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
	}
	@Test
	public void test41() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles tile = new ElbowTile("West","L");
		board.setholdTile(tile);
		Tiles actual = board.getHoldTile();
		assertTrue("", actual.equals(tile));
				
	}
	/**@author Jmosca
	 * Tests 42-44: Checks if the specific tiles can be moved to certain orientations
	 * 
	 */
	@Test
	public void test42() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles hold = new TTile("East","T");
		board.setholdTile(hold);
		board.moveTiles(0, 1);// 1
		board.endTurn();
		board.moveTiles(0, 1);// 2
		board.endTurn();
		board.moveTiles(0, 1);// 3
		board.endTurn();
		board.moveTiles(0, 1);// 4
		board.endTurn();
		board.moveTiles(0, 1);// 5
		board.endTurn();
		board.moveTiles(0, 1);// 6
		board.endTurn();
		board.moveTiles(0, 1);// 7
		board.endTurn();
		board.moveTiles(0, 1);// 8
		board.endTurn();
		Tiles actual = board.getHoldTile();
		assertTrue("", actual.equals(hold));
	}

	@Test
	public void test43() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles hold = new StraightTiles("North", "I");
		Tiles expected = hold;
		System.out.println(expected.getIdentity() + expected.getOrientation());
		board.setholdTile(hold);
		board.moveTiles(0, 1);// 1
		board.endTurn();
		board.moveTiles(0, 1);// 2
		board.endTurn();
		board.moveTiles(0, 1);// 3
		board.endTurn();
		board.moveTiles(0, 1);// 4
		board.endTurn();
		board.moveTiles(0, 1);// 5
		board.endTurn();
		board.moveTiles(0, 1);// 6
		board.endTurn();
		board.moveTiles(0, 1);// 7
		board.endTurn();
		board.moveTiles(0, 1);// 8
		board.endTurn();
		Tiles actual = board.getHoldTile();
		System.out.println(actual.getIdentity() + actual.getOrientation());
		assertTrue("", expected==actual);
	}
	@Test
	public void test44() {
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Satya", "Ian", "Ken", "Josh"});
		Tiles hold = new ElbowTile("South","L");
		board.setholdTile(hold);
		board.moveTiles(0, 1);// 1
		board.endTurn();
		board.moveTiles(0, 1);// 2
		board.endTurn();
		board.moveTiles(0, 1);// 3
		board.endTurn();
		board.moveTiles(0, 1);// 4
		board.endTurn();
		board.moveTiles(0, 1);// 5
		board.endTurn();
		board.moveTiles(0, 1);// 6
		board.endTurn();
		board.moveTiles(0, 1);// 7
		board.endTurn();
		board.moveTiles(0, 1);// 8
		board.endTurn();
		Tiles actual = board.getHoldTile();
		assertTrue("", hold == actual);
	}
	
}