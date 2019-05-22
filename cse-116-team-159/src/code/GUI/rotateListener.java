package code.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.Model.Model;

/**
 * This is a listener for rotate button
 */
public class rotateListener implements ActionListener{

	/**
	 * instance variable for direction
	 */
	private String _direction;
	
	/**
	 * instance variable for model
	 */
	private Model _model;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Runs whenever the rotate button is clicked.
	 * This takes the outside tile that is not on the board and rotates it clockwise.
	 * @param m associates the model class to figure out the piece that is off the board.
	 * @param d checks to see what direction the tile is initially orientated in.
	 */	
	public rotateListener(Model m,String d) {
		_model = m;
		_direction = d;
	}

	
	/**
	 * @author <jtmirfie>
	 * Finds what direction the tile is orientated then rotates it clockwise.
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(_direction.equals("North"))_model.getHoldTile().Rotate("East");
		if(_direction.equals("East"))_model.getHoldTile().Rotate("South");
		if(_direction.equals("South"))_model.getHoldTile().Rotate("West");
		if(_direction.equals("West"))_model.getHoldTile().Rotate("North");
		_model.gameChanged();
		
	}

}
