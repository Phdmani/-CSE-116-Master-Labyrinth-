package code;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import code.Model.Model;
import code.fileio.FileInputOutput;
import code.GUI.GUI;
import code.pawn.Pawn;

/**
 * This Driver class runs the game
 *
 */
public class Driver {
	
	/**
	 * This is a static main method to run the game
	 * @param args
	 * @author Satya, Josh 04-30
	 */
	public static void main(String[] args) {
	
		//The following try and catch fixes the problem of coloring buttons on OSx and makes the GUI platform neutral
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//A single command line arg corresponds to the pathname of a save file (extension .mls)
		if(args.length==1){
			String path = args[0];
			int l = path.length();
			if(path.charAt(l-4) != '.' || path.charAt(l-3) != 'm' ||
					path.charAt(l-2) != 'l' || path.charAt(l-1) != 's'){
				System.err.println("The provided filepath/file does NOT have extension .mls !");
			}
			String[] s = FileInputOutput.readFileToStringArray(path);
			Model model = new Model();
			model.restoreGame(s);
			SwingUtilities.invokeLater(new GUI(model));
		}
		
		//if (args.length != 1), then do:
		else{
			
			if(args.length < 1 || args.length > 4){
				System.err.println("Sorry -- This game only supports 2, 3, or 4 players!");
			}
			
			else if(args.length == 2 ){
				Model model = new Model(args.length);
				model.pawns[0] = new Pawn(2,2, model,args[0],"BLACK");
				model.pawns[1] = new Pawn(4,4, model,args[1],"GREEN");
				model.dealFormulaCardsToPawns();
				SwingUtilities.invokeLater(new GUI(model));
			}
			
			else if(args.length == 3) {
				Model model = new Model(args.length);
				model.pawns[0] = new Pawn(2,2, model,args[0],"BLACK");
				model.pawns[1] = new Pawn(4,4, model,args[1],"GREEN");
				model.pawns[2] = new Pawn(2,4, model,args[2],"BLUE");
				model.dealFormulaCardsToPawns();
				SwingUtilities.invokeLater(new GUI(model));
			}
			
			else if (args.length == 4){
				Model model = new Model(args.length);
				model.pawns[0] = new Pawn(2,2, model,args[0],"BLACK");
				model.pawns[1] = new Pawn(4,4, model,args[1],"GREEN");
				model.pawns[2] = new Pawn(2,4, model,args[2],"BLUE");
				model.pawns[3] = new Pawn(4,2, model,args[3],"RED");
				model.dealFormulaCardsToPawns();
				SwingUtilities.invokeLater(new GUI(model));
			}	
		}	
	}
}
