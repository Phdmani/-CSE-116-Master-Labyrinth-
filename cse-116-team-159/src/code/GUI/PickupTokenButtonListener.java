package code.GUI;

import code.Model.*;
import code.pawn.Pawn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a listener for pick up token button
 */
public class PickupTokenButtonListener implements ActionListener {

	/**
	 * instance variable for model
	 */
	Model _model;
	
	/**
	 * This is constructor
	 * @param m
	 */
	public PickupTokenButtonListener(Model m){
		_model = m;
	}
	
	/**
	 * This is the method used to action performed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Pawn p = _model.pawns[_model.playerUp-1];
		if(p.checkIfValidMove()){
			p.pickUpToken();
		}
	}

}
