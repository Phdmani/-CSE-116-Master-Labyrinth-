package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import code.Model.*;
import code.fileio.FileInputOutput;

public class SaveGameTests {
	
	
	@Test public void test_SaveGameWith2Players() {
		Model m = new Model("StaticBoard1_2Players");
		m.setStaticPlayers(new String[] {"Ian", "Satya"});
		m.setupTokens();
		m.populateTokensOnly();
		
		FormulaCard fc0 = m.pawns[0].getFormulaCard();
		FormulaCard fc1 = m.pawns[1].getFormulaCard();
		
		String fc0vals = fc0.getValues()[0] + "," + fc0.getValues()[1] + "," + fc0.getValues()[2] + "";
		String fc1vals = fc1.getValues()[0] + "," + fc1.getValues()[1] + "," + fc1.getValues()[2] + "";
		
		String[] expected = new String[3];
		expected[0] = "[Ian,BLACK,3,[" + fc0vals + "],[]],[Satya,GREEN,3,[" + fc1vals + "],[]]";
		expected[1] = "";
		expected[2] = "0";
		
		ArrayList<Integer> t = new ArrayList<Integer>();
		for(int row = 0; row < 7; row++){
			for(int col = 0; col < 7; col++){
				if(m.getBoard()[col][row].hasToken){
					t.add(m.getBoard()[col][row].getToken().getValue());
				}
				else{
					t.add(0);
				}
			}
		}
		
		expected[1] = "[L0," +t.get(0)+ ",[]],[I1,"+t.get(1)+",[]],[T0,"+t.get(2)+
				",[]],[L1,"+t.get(3)+",[]],[T0,"+t.get(4)+",[]],[L3,"+t.get(5)+
				",[]],[L1,"+t.get(6)+",[]],[T2,"+t.get(7)+",[]],[I1,"+t.get(8)+
				",[]],[L2,"+t.get(9)+",[]],[I0,"+t.get(10)+",[]],[I1,"+t.get(11)+
				",[]],[T2,"+t.get(12)+",[]],[L2,"+t.get(13)+",[]],[T3,"+t.get(14)+
				",[]],[I0,"+t.get(15)+",[]],[T3,"+t.get(16)+",[BLACK]],[T2,"+t.get(17)+
				",[]],[T0,"+t.get(18)+",[]],[L3,"+t.get(19)+",[]],[T1,"+t.get(20)+
				",[]],[L2,"+t.get(21)+",[]],[I0,"+t.get(22)+",[]],[I0,"+t.get(23)+
				",[]],[I1,"+t.get(24)+",[]],[L3,"+t.get(25)+",[]],[L1,"+t.get(26)+
				",[]],[L2,"+t.get(27)+",[]],[T3,"+t.get(28)+",[]],[L2,"+t.get(29)+
				",[]],[T2,"+t.get(30)+",[]],[L3,"+t.get(31)+",[]],[T1,"+t.get(32)+
				",[GREEN]],[T1,"+t.get(33)+",[]],[T1,"+t.get(34)+",[]],[L3,"+t.get(35)+
				",[]],[I0,"+t.get(36)+",[]],[I1,"+t.get(37)+",[]],[T2,"+t.get(38)+
				",[]],[I0,"+t.get(39)+",[]],[L1,"+t.get(40)+",[]],[I0,"+t.get(41)+
				",[]],[L3,"+t.get(42)+",[]],[I1,"+t.get(43)+",[]],[T2,"+t.get(44)+
				",[]],[L3,"+t.get(45)+",[]],[T2,"+t.get(46)+",[]],[T1,"+t.get(47)+
				",[]],[L2,"+t.get(48)+",[]]";
		
		String[] actual1 = m.writeCurrentGameStateToString();
		m.saveGame(m.pawns[m.playerUp-1], "save.mls");
		String[] actual2 = FileInputOutput.readFileToStringArray("save.mls");
		
		assertTrue("", expected[0].equals(actual1[0]) && expected[1].equals(actual1[1]) && expected[2].equals(actual1[2])
				&& expected[0].equals(actual2[0]) && expected[1].equals(actual2[1]) && expected[2].equals(actual2[2]));
	}
	
	
	@Test public void test_SaveGameWith3Players() {
		Model m = new Model("StaticBoard1_3Players");
		m.setStaticPlayers(new String[] {"Ian", "Satya", "Ken"});
		m.setupTokens();
		m.populateTokensOnly();
		
		FormulaCard fc0 = m.pawns[0].getFormulaCard();
		FormulaCard fc1 = m.pawns[1].getFormulaCard();
		FormulaCard fc2 = m.pawns[2].getFormulaCard();
		
		String fc0vals = fc0.getValues()[0] + "," + fc0.getValues()[1] + "," + fc0.getValues()[2] + "";
		String fc1vals = fc1.getValues()[0] + "," + fc1.getValues()[1] + "," + fc1.getValues()[2] + "";
		String fc2vals = fc2.getValues()[0] + "," + fc2.getValues()[1] + "," + fc2.getValues()[2] + "";
		
		String[] expected = new String[3];
		expected[0] = "[Ian,BLACK,3,[" + fc0vals + "],[]],[Satya,GREEN,3,[" + fc1vals + "],[]],[Ken,BLUE,3,[" + fc2vals + "],[]]";
		expected[1] = "";
		expected[2] = "0";
		
		ArrayList<Integer> t = new ArrayList<Integer>();
		for(int row = 0; row < 7; row++){
			for(int col = 0; col < 7; col++){
				if(m.getBoard()[col][row].hasToken){
					t.add(m.getBoard()[col][row].getToken().getValue());
				}
				else{
					t.add(0);
				}
			}
		}
		
		expected[1] = "[L0," +t.get(0)+ ",[]],[I1,"+t.get(1)+",[]],[T0,"+t.get(2)+
					",[]],[L1,"+t.get(3)+",[]],[T0,"+t.get(4)+",[]],[L3,"+t.get(5)+
					",[]],[L1,"+t.get(6)+",[]],[T2,"+t.get(7)+",[]],[I1,"+t.get(8)+
					",[]],[L2,"+t.get(9)+",[]],[I0,"+t.get(10)+",[]],[I1,"+t.get(11)+
					",[]],[T2,"+t.get(12)+",[]],[L2,"+t.get(13)+",[]],[T3,"+t.get(14)+
					",[]],[I0,"+t.get(15)+",[]],[T3,"+t.get(16)+",[BLACK]],[T2,"+t.get(17)+
					",[]],[T0,"+t.get(18)+",[BLUE]],[L3,"+t.get(19)+",[]],[T1,"+t.get(20)+
					",[]],[L2,"+t.get(21)+",[]],[I0,"+t.get(22)+",[]],[I0,"+t.get(23)+
					",[]],[I1,"+t.get(24)+",[]],[L3,"+t.get(25)+",[]],[L1,"+t.get(26)+
					",[]],[L2,"+t.get(27)+",[]],[T3,"+t.get(28)+",[]],[L2,"+t.get(29)+
					",[]],[T2,"+t.get(30)+",[]],[L3,"+t.get(31)+",[]],[T1,"+t.get(32)+
					",[GREEN]],[T1,"+t.get(33)+",[]],[T1,"+t.get(34)+",[]],[L3,"+t.get(35)+
					",[]],[I0,"+t.get(36)+",[]],[I1,"+t.get(37)+",[]],[T2,"+t.get(38)+
					",[]],[I0,"+t.get(39)+",[]],[L1,"+t.get(40)+",[]],[I0,"+t.get(41)+
					",[]],[L3,"+t.get(42)+",[]],[I1,"+t.get(43)+",[]],[T2,"+t.get(44)+
					",[]],[L3,"+t.get(45)+",[]],[T2,"+t.get(46)+",[]],[T1,"+t.get(47)+
					",[]],[L2,"+t.get(48)+",[]]";

		String[] actual1 = m.writeCurrentGameStateToString();
		m.saveGame(m.pawns[m.playerUp-1], "save.mls");
		String[] actual2 = FileInputOutput.readFileToStringArray("save.mls");
		
		assertTrue("", expected[0].equals(actual1[0]) && expected[1].equals(actual1[1]) && expected[2].equals(actual1[2])
				&& expected[0].equals(actual2[0]) && expected[1].equals(actual2[1]) && expected[2].equals(actual2[2]));
	}

	@Test public void test_SaveGameWith4Players() {
		Model m = new Model("StaticBoard1_4Players");
		m.setStaticPlayers(new String[] {"Ian", "Satya", "Ken", "Josh"});
		m.setupTokens();
		m.populateTokensOnly();
		
		FormulaCard fc0 = m.pawns[0].getFormulaCard();
		FormulaCard fc1 = m.pawns[1].getFormulaCard();
		FormulaCard fc2 = m.pawns[2].getFormulaCard();
		FormulaCard fc3 = m.pawns[3].getFormulaCard();
		
		String fc0vals = fc0.getValues()[0] + "," + fc0.getValues()[1] + "," + fc0.getValues()[2] + "";
		String fc1vals = fc1.getValues()[0] + "," + fc1.getValues()[1] + "," + fc1.getValues()[2] + "";
		String fc2vals = fc2.getValues()[0] + "," + fc2.getValues()[1] + "," + fc2.getValues()[2] + "";
		String fc3vals = fc3.getValues()[0] + "," + fc3.getValues()[1] + "," + fc3.getValues()[2] + "";
		
		String[] expected = new String[3];
		expected[0] = "[Ian,BLACK,3,[" + fc0vals + "],[]],[Satya,GREEN,3,[" + fc1vals + "],[]],[Ken,BLUE,3,[" + fc2vals + "],[]],[Josh,RED,3,[" + fc3vals + "],[]]";
		expected[1] = "";
		expected[2] = "0";
		
		ArrayList<Integer> t = new ArrayList<Integer>();
		for(int row = 0; row < 7; row++){
			for(int col = 0; col < 7; col++){
				if(m.getBoard()[col][row].hasToken){
					t.add(m.getBoard()[col][row].getToken().getValue());
				}
				else{
					t.add(0);
				}
			}
		}
		
		expected[1] = "[L0," +t.get(0)+ ",[]],[I1,"+t.get(1)+",[]],[T0,"+t.get(2)+
					",[]],[L1,"+t.get(3)+",[]],[T0,"+t.get(4)+",[]],[L3,"+t.get(5)+
					",[]],[L1,"+t.get(6)+",[]],[T2,"+t.get(7)+",[]],[I1,"+t.get(8)+
					",[]],[L2,"+t.get(9)+",[]],[I0,"+t.get(10)+",[]],[I1,"+t.get(11)+
					",[]],[T2,"+t.get(12)+",[]],[L2,"+t.get(13)+",[]],[T3,"+t.get(14)+
					",[]],[I0,"+t.get(15)+",[]],[T3,"+t.get(16)+",[BLACK]],[T2,"+t.get(17)+
					",[]],[T0,"+t.get(18)+",[BLUE]],[L3,"+t.get(19)+",[]],[T1,"+t.get(20)+
					",[]],[L2,"+t.get(21)+",[]],[I0,"+t.get(22)+",[]],[I0,"+t.get(23)+
					",[]],[I1,"+t.get(24)+",[]],[L3,"+t.get(25)+",[]],[L1,"+t.get(26)+
					",[]],[L2,"+t.get(27)+",[]],[T3,"+t.get(28)+",[]],[L2,"+t.get(29)+
					",[]],[T2,"+t.get(30)+",[RED]],[L3,"+t.get(31)+",[]],[T1,"+t.get(32)+
					",[GREEN]],[T1,"+t.get(33)+",[]],[T1,"+t.get(34)+",[]],[L3,"+t.get(35)+
					",[]],[I0,"+t.get(36)+",[]],[I1,"+t.get(37)+",[]],[T2,"+t.get(38)+
					",[]],[I0,"+t.get(39)+",[]],[L1,"+t.get(40)+",[]],[I0,"+t.get(41)+
					",[]],[L3,"+t.get(42)+",[]],[I1,"+t.get(43)+",[]],[T2,"+t.get(44)+
					",[]],[L3,"+t.get(45)+",[]],[T2,"+t.get(46)+",[]],[T1,"+t.get(47)+
					",[]],[L2,"+t.get(48)+",[]]";

		String[] actual1 = m.writeCurrentGameStateToString();
		m.saveGame(m.pawns[m.playerUp-1], "save.mls");
		String[] actual2 = FileInputOutput.readFileToStringArray("save.mls");
		
		assertTrue("", expected[0].equals(actual1[0]) && expected[1].equals(actual1[1]) && expected[2].equals(actual1[2])
				&& expected[0].equals(actual2[0]) && expected[1].equals(actual2[1]) && expected[2].equals(actual2[2]));
	}

}
