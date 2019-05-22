package code.GUI;

import code.Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a use wand button listener
 * @author Ian, Josh 04-27-16
 */
public class UseWandButtonListener implements ActionListener {

	/**
	 * instance variable for model
	 */
	private Model _model;
	
	/**
	 * This is the constructor
	 * @param m
	 */
	public UseWandButtonListener(Model m){
		_model = m;
	}
	
	/**
	 * This method is used to call the use wand method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.pawns[_model.playerUp-1].useWand();
		_model.gameChanged();

	}

}
