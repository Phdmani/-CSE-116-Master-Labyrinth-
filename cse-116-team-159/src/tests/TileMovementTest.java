package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Model.Model;
import code.tiles.ElbowTile;
import code.tiles.StraightTiles;
import code.tiles.TTile;
import code.tiles.Tiles;

public class TileMovementTest {
	
	/**
	 * Version 1.1 of test01
	 * removed separate method calls for setting the board's tiles and permanent tiles as I 
	 * created a constructor in the Board class that will automate this.
	 * Removed "North" Parameters from tiles created in test01 and test02 as Tiles default 
	 * constructor automatically designates North as the orientation when no parameter is included.
	 * @author Team.
	 */
	@Test public void test01(){
		String expected = "North";
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		Tiles old_place = new StraightTiles();
		board.setholdTile(old_place);
		Tiles new_place = board.moveTiles(0, 1);
		String actual = board.getTile(0,1).getRotation();
		assertTrue("",expected.equals(actual));
	}
	@Test public void test02(){
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		Tiles new_place = new ElbowTile("West", "L");
		board.setholdTile(new_place);
		Tiles old_place = board.moveTiles(0, 6);
		assertTrue("",old_place.equals(new_place));
	}
	@Test public void test03(){
		Model board = new Model(4);
		board.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		Tiles new_place = new StraightTiles("South", "I");
		Tiles old_place = null;
		board.setholdTile(new_place);
		String s="";
		for(int i=0; i<6;i++){
			old_place = board.moveTiles(0, 1);
			new_place=old_place;
			s = board.getTile(0,1).getRotation();
		}
		assertTrue("",s.equals("South"));
	}
	
	@Test
	public void checkValidInsertionPoint() {
		Model m1 = new Model("StaticBoard1_4Players");
		m1.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m1.setupTokens();
		m1.populateTokensOnly();
		m1.setholdTile(new TTile("North", "T"));
		m1.moveTiles(0, 1);
		TTile t = (TTile) m1.getBoard()[0][1];
		assertTrue("",t.getIdentity().equals("T") && t.getRotation().equals("North"));
	}
	
	@Test
	public void checkInvalidInsertionPoint() {
		Model m1 = new Model("StaticBoard1_4Players");
		m1.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m1.setupTokens();
		m1.populateTokensOnly();
		m1.setholdTile(new TTile("North", "T"));
		StraightTiles tB = (StraightTiles) m1.getBoard()[1][1];
		m1.moveTiles(0,2);
		StraightTiles tA = (StraightTiles) m1.getBoard()[1][1];
		assertTrue("",tA.getIdentity().equals(tB.getIdentity()) && tA.getRotation().equals(tB.getRotation()));
	}
	
	@Test
	public void insertThenTryToInsertAgainFromOppositeSide() {
		Model m1 = new Model("StaticBoard1_4Players");
		m1.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m1.setupTokens();
		m1.populateTokensOnly();
		m1.setholdTile(new TTile("North", "T"));
		m1.moveTiles(0,1);
		TTile t = (TTile) m1.getBoard()[0][1];
		m1.endTurn();
		m1.moveTiles(6, 1);
		TTile tA = (TTile) m1.getBoard()[0][1];
		assertTrue("",t.getIdentity().equals("T") && t.getRotation().equals("North") &&
		t.getIdentity().equals(tA.getIdentity()) && t.getRotation().equals(tA.getRotation()));
	}
}
