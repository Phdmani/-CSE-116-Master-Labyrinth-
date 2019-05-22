package code.GUI;

import code.Model.*;
import code.pawn.Pawn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a listener for save game button
 * @author Ken, Weijin 04-29 
 * @author Ken, Ian 04-30
 *
 */
public class SaveGameButtonListener implements ActionListener {

	/**
	 * instance variable for model
	 */
	private Model _model;
	
	/**
	 * instance variable for gui
	 */
	private GUI _gui;
	
	/**
	 * This is the constructor
	 * @param m
	 * @param gui
	 */
	public SaveGameButtonListener(Model m, GUI gui){
		_model = m;
		_gui = gui;
	}
	
	/**
	 * This is the method for action performed to save the game
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Pawn p = _model.pawns[_model.playerUp-1];
		p.saveGame();
		_gui.exitOnSave();
	}

}
