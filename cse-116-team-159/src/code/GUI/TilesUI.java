package code.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import code.Model.Model;
import code.tiles.Tiles;

/**
 * This is teh tiles ui class
 *
 */
public class TilesUI {
	
	/**
	 * instance variable for tiles
	 */
	private Tiles _tiles;
	
	/**
	 * instance variable for panel
	 */
	private JPanel _panel;
	
	/**
	 * instance variable for model
	 */
	private Model _model;
	
	/**
	 * instance variable for x
	 */
	private int x;
	
	/**
	 * instance variable for y
	 */
	private int y;
	
	/**
	 * instance variable for color count
	 */
	public static int colorCount=0;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Creates the tiles panel used to display the tiles from the model.
	 * A 3x3 JPanel is used that is filled with buttons to display the paths from the model.
	 * @param t associates the Tiles class with this one in order to figure out what type of tile is being used.
	 * @param m associates the Model class to retrieve the tile from the board.
	 * @param x value that is used for the location of the horizontal spot of the tile
	 * @param y value that is used for the location of the vertical spot of the tile
	 */	
	public TilesUI(Tiles t, Model m, int x, int y){
		_tiles = t;
		_model = m;
		this.x = x;
		this.y = y;
		this.drawTile();
	}
	
	/**
	 * @author <jtmirfie>
	 * Draws the tiles on a panel using JButtons.
	 * Each button is given colors to show whether its a valid path or a wall.
	 * It also finds the location of each pawn and draws a unique color for each one individually.
	 */	
	public void drawTile(){
		
		_panel = new JPanel();
		_panel.setFocusable(true);
		_panel.setLayout(new GridLayout(3,3));
		_panel.setPreferredSize(new Dimension(125,125));
		_panel.setBackground(Color.BLACK);
		
		for(int i=0;i<3;i++){
			for(int z=0;z<3;z++){
				JButton _j = new JButton();
				_j.setOpaque(true);
				_j.setFocusable(false);
				_j.setPreferredSize(new Dimension(125,125));
				_j.setBackground(Color.GRAY);
				
				if(i==1 && z==1){
					_j.setBackground(Color.ORANGE);
				}
				
				if(x!=10 || y!=10){
					if(i==1 && z==1){
							if (_model.pawns[0].getPositionX()==x && _model.pawns[0].getPositionY()==y) {

								if(_model.pawns[0].getColor().equals("BLACK")){
									_j.setBackground(Color.BLACK);
								}
								else if(_model.pawns[0].getColor().equals("GREEN")){
									_j.setBackground(Color.GREEN);
								}
								else if(_model.pawns[0].getColor().equals("BLUE")){
									_j.setBackground(Color.BLUE);
								}
								else if(_model.pawns[0].getColor().equals("RED")){
									_j.setBackground(Color.RED);
								}
							}
							if(_model.pawns[1].getPositionX()==x && _model.pawns[1].getPositionY()==y){

								if(_model.pawns[1].getColor().equals("BLACK")){
									_j.setBackground(Color.BLACK);
								}
								else if(_model.pawns[1].getColor().equals("GREEN")){
									_j.setBackground(Color.GREEN);
								}
								else if(_model.pawns[1].getColor().equals("BLUE")){
									_j.setBackground(Color.BLUE);
								}
								else if(_model.pawns[1].getColor().equals("RED")){
									_j.setBackground(Color.RED);
								}
							}
							if(_model.getNumOfPlayers() >=3){
								if(_model.pawns[2].getPositionX()==x && _model.pawns[2].getPositionY()==y){

									if(_model.pawns[2].getColor().equals("BLACK")){
										_j.setBackground(Color.BLACK);
									}
									else if(_model.pawns[2].getColor().equals("GREEN")){
										_j.setBackground(Color.GREEN);
									}
									else if(_model.pawns[2].getColor().equals("BLUE")){
										_j.setBackground(Color.BLUE);
									}
									else if(_model.pawns[2].getColor().equals("RED")){
										_j.setBackground(Color.RED);
									}
								}
							}
							if(_model.getNumOfPlayers() >=4){
								if(_model.pawns[3].getPositionX()==x && _model.pawns[3].getPositionY()==y){

									if(_model.pawns[3].getColor().equals("BLACK")){
										_j.setBackground(Color.BLACK);
									}
									else if(_model.pawns[3].getColor().equals("GREEN")){
										_j.setBackground(Color.GREEN);
									}
									else if(_model.pawns[3].getColor().equals("BLUE")){
										_j.setBackground(Color.BLUE);
									}
									else if(_model.pawns[3].getColor().equals("RED")){
										_j.setBackground(Color.RED);
									}
								}
							}

						if(_model.getBoard()[x][y].checkToken() && _model.getBoard()[x][y].hasToken){
							String temp = ""+_model.getBoard()[x][y].getToken().getValue();
							_j.setBorder(null);
							_j.setText(temp);
							}
						}
					}
				
				if(_tiles._north){
					if(i==0 && z==1)_j.setBackground(Color.ORANGE);
				}
				if(_tiles._east){
					if(i==1 && z==2)_j.setBackground(Color.ORANGE);
				}
				if(_tiles._south){
					if(i==2 && z==1)_j.setBackground(Color.ORANGE);
				}
				if(_tiles._west){
					if(i==1 && z==0)_j.setBackground(Color.ORANGE);
				}
				_panel.add(_j);		
			}
		}		
	}
	
	/**
	 * This returns the the panel
	 * @return JPanel
	 */
	public JPanel returnPanel(){
		return _panel;
	}
}
