package code.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.Model.Model;

/**
 *  This class is a listener for end turn button 
 */
public class endTurnListener implements ActionListener{
	
	/**
	 * instance variable for model
	 */
	private Model _model;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Creates a button action listener for ending a turn
	 * @param m associates the Model class with this one
	 */	
	public endTurnListener(Model m) {
		_model = m;
	}

	/**
	 * @author <jtmirfie>
	 * Checks to see if a pawn has first shifted the board in order to end turn. After it checks to see 
	 * if a move is valid then saves the next coordinates to know for the next turn what that pawns 
	 * valid movements are. It then calls calls on the update method in the model class to show the recent changes.
	 * @param e 
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		 _model.endTurn();   
	}
}
