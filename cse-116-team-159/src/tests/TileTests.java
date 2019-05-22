package tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import code.tiles.StraightTiles;
import code.tiles.TTile;
import code.tiles.Tiles;
import code.tokens.Token;

public class TileTests {
	/**
	 * @author bdlipp
	 * all tests
	 */
	@Test public void test01(){
		String expected="North";
		code.tiles.ElbowTile ebt = new code.tiles.ElbowTile("North", "L");
		String actual = ebt.getRotation();
		assertTrue(""+actual,actual.equals(expected));
	}
	/**
	 * Creates a new tile and rotates it 'South'.
	 * Checkes if the rotation of the tile is correct
	 */
	@Test public void test02(){
		String expected = "South";
		code.tiles.TTile tt = new code.tiles.TTile();
		tt.Rotate("South");
		String actual = tt.getRotation();
		assertTrue("",expected.equals(actual));
	}
	
//	/**
//	 * Creates a tile and makes that tile static.
//	 * Tries to rotate a static tile and should fail
//	 * because its a static tile.
//	 */
//	@Test public void test03(){
//		Tiles tt = new TTile("South", "T");
//		tt.isPermanent(true);
//		tt.Rotate("North");
//		String expected = "North";
//		String actual = tt.getRotation();
//		assertTrue("", expected.equals(actual));
//	}
	
	/**
	 * Creates a tile and rotates it twice.
	 * Checks to see if the tile is rotated the last chosen rotation.
	 */
	@Test public void test04(){
		Tiles st = new StraightTiles("East", "I");
		st.Rotate("North");
		st.Rotate("East");
		String actual = st.getRotation();
		String expected = "East";
		assertTrue("string: " + actual, expected.equals(actual));
	}
	/*@Test public void test05(){
		Token t25 = new Token(25);
		String expected = "Mistletoe";
		String actual = t25.GetType();
		
		assertTrue("Expected: "+ expected + " Actual: "+actual,expected.equals(actual));
	}
	*/
}

