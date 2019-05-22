package code.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import code.Model.Model;
import code.Model.Observer;
import code.pawn.Pawn;

/**
 * This is the GUI class which implements runnable and observer
 */
public class GUI implements Runnable, Observer {
	
	/**
	 * instance variable for board panel
	 */
	private JPanel _boardPanel;
	
	/**
	 * instance variable for  game panel
	 */
	private JPanel _gamePanel;
	
	/**
	 * instance variable for  off game panel
	 */
	private JPanel _offGamePanel;
	
	/**
	 * instance variable for west panel
	 */
	private JPanel _westBPanel;
	
	/**
	 * instance variable for north panel
	 */
	private JPanel _northBPanel;
	
	/**
	 * instance variable for east panel
	 */
	private JPanel _eastBPanel;
	
	/**
	 * instance variable for south panel
	 */
	private JPanel _southBPanel;

	/**
	 * instance variable for rotate button
	 */
	private JButton _rotateButton;
	
	/**
	 * instance variable for for pick up token button
	 */
	private JButton _pickupTokenButton;
	
	/**
	 * instance variable for use wand button
	 */
	private JButton _useWandButton;
	
	/**
	 * instance variable for save game button
	 */
	private JButton _saveGameButton;
	
	/**
	 * instance variable for end turn button
	 */
	private JButton _endTurnButton;
	
	/**
	 * instance variable for palyer
	 */
	private JButton _player;
	
	/**
	 * instance variable for card label
	 */
	private JLabel _cardLabel;
	
	/**
	 * instance variable for button panel 1
	 */
	private JPanel _buttonPanel1;
	
	/**
	 * instance variable for button panel 2
	 */
	private JPanel _buttonPanel2;
	
	/**
	 *  instance variable for button panel 3
	 */
	private JPanel _buttonPanel3;
	
	/**
	 * instance variable for hold panel
	 */
	private JPanel _holdPanel;
	
	/**
	 * instance variable for game frame
	 */
	private JFrame _gameFrame;
	
	/**
	 * instance variable for model
	 */
	private Model _model;
	
	/**
	 * instance variable for key
	 */
	private keyMovement key;
	
	/**
	 * instance variable for game info window
	 */
	private JFrame _gameInfoWindow;
	
	/**
	 * instance variable for game info pane
	 */
	private JTextPane _gameInfoPane;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Associates the Model class with this one and sets up the observer that to notify the model when to update.
	 * @param m Associated between this class and the model to get the information needed to display. 
	 */	
	public GUI(Model m){
		_model = m;
		_model.setObserver(this);
	}

	/**
	 * @author <jtmirfie>
	 * Runs at the beginning of the code. JFrame is compiled and the setupBoard() method is called onto it.
	 * Various panels are placed on the frame which hold the tiles and the buttons around the board.
	 * A key listener is also implemented which is used for pawn movement respective to each players turn.
	 */	
	@Override
	public void run() {
		_gameInfoWindow = new JFrame("Game Info");
		_gameInfoPane = new JTextPane();
		_gameInfoWindow.add(_gameInfoPane);
		_gameInfoWindow.setSize(new Dimension(600,450));
		_gameInfoPane.setSize(new Dimension(600,450));
		_gameInfoPane.setBackground(Color.ORANGE);
		_gameInfoPane.setEditable(false);
		int numPlayers = _model.getNumOfPlayers();
		String s = "";
		for(int i = 0; i < numPlayers; i++){
			Pawn p = _model.pawns[i];
			s = s + (p.getPlayer() + " ("+ p.getColor() +" Pawn): \n\tCollected Tokens: " + p.tokenCount() 
			+ "\n\tNumber of Wands: " + p.getWandCount() + "\n\n");
		}
		int currentToken =_model.tokenCounter;
		_gameInfoPane.setText("\n"+s+ "Current Collectible Token : " + currentToken);
		
		_gameFrame = new JFrame("Master Labyrinth");
		_gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_gameFrame.getContentPane().setLayout(new FlowLayout());
		_gameFrame.setFocusable(true);
		_gameFrame.setResizable(false);
		_gameFrame.setLayout(new BorderLayout());
		setupBoard();
		
		_gamePanel = new JPanel();
		_gamePanel.setFocusable(true);
		_gamePanel.setLayout(new BorderLayout());
		
		_gamePanel.add(_westBPanel,BorderLayout.WEST);
		_gamePanel.add(_northBPanel,BorderLayout.NORTH);
		_gamePanel.add(_eastBPanel,BorderLayout.EAST);
		_gamePanel.add(_southBPanel,BorderLayout.SOUTH);
		
		_gamePanel.add(_boardPanel,BorderLayout.CENTER);

		_offGamePanel = new JPanel();
		_offGamePanel.setFocusable(true);
		_offGamePanel.setLayout(new GridLayout(6,1));
		
		_holdPanel = new TilesUI(_model.getHoldTile(),_model,10,10).returnPanel();
		_holdPanel.setFocusable(true);
		_offGamePanel.add(_holdPanel);
		
		_buttonPanel1= new JPanel();
		_buttonPanel1.setFocusable(true);
		_buttonPanel1.setLayout(new GridLayout(2,1));
		
		_buttonPanel2= new JPanel();
		_buttonPanel2.setFocusable(true);
		_buttonPanel2.setLayout(new GridLayout(2,1));
		
		_buttonPanel3= new JPanel();
		_buttonPanel3.setFocusable(true);
		_buttonPanel3.setLayout(new GridLayout(2,1));
		
		_rotateButton = new JButton("Rotate");
		_rotateButton.addActionListener(new rotateListener(_model,_model.getHoldTile()._rotation));
		_rotateButton.setFocusable(false);
		
		_pickupTokenButton = new JButton("Pickup Token");
		_pickupTokenButton.setFocusable(false);
		_pickupTokenButton.addActionListener(new PickupTokenButtonListener(_model));
		
		_useWandButton = new JButton("Use Wand");
		_useWandButton.setFocusable(false);
		_useWandButton.addActionListener(new UseWandButtonListener(_model));
		
		_endTurnButton = new JButton("End Turn");
		_endTurnButton.setFocusable(false);
		_endTurnButton.addActionListener(new endTurnListener(_model));
		
		_saveGameButton = new JButton("Save Game");
		_saveGameButton.setFocusable(false);
		_saveGameButton.addActionListener(new SaveGameButtonListener(_model, this));
		if(_model.firstMove==false){
			_saveGameButton.setEnabled(false);
		}
		else{
			_saveGameButton.setEnabled(true);
		}
		
		_player = new JButton(_model.pawns[0].getPlayer() + "'s Turn");
		_player.setOpaque(true);
		if(_model.pawns[_model.playerUp-1].getColor().equals("BLACK")){
			_player.setBackground(Color.BLACK);
			_player.setForeground(Color.WHITE);
		}
		else if(_model.pawns[_model.playerUp-1].getColor().equals("GREEN")){
			_player.setBackground(Color.GREEN);
		}
		else if(_model.pawns[_model.playerUp-1].getColor().equals("BLUE")){
			_player.setBackground(Color.BLUE);
		}
		else if(_model.pawns[_model.playerUp-1].getColor().equals("RED")){
			_player.setBackground(Color.RED);
		}
		
		Image img = null;
		try{
			Pawn p = _model.pawns[0];
			int[] values = p.getFormulaCard().getValues();
			String path ="images/";
			path = path + values[0] + "-";
			path = path + values[1] + "-";
			path = path + values[2] + ".png";
			System.out.println(path);
			img = ImageIO.read(getClass().getResource(path));
			
		}catch(IOException ex){}
		_cardLabel = new JLabel(new ImageIcon(img));
		_cardLabel.setPreferredSize(new Dimension(100,150));
		
		_buttonPanel1.add(_rotateButton);
		_buttonPanel1.add(_pickupTokenButton);
		
		_buttonPanel2.add(_useWandButton);
		_buttonPanel2.add(_saveGameButton);
		
		_buttonPanel3.add(_endTurnButton);
		_buttonPanel3.add(_player);
		
		_offGamePanel.add(_buttonPanel1);
		_offGamePanel.add(_buttonPanel2);
		_offGamePanel.add(_buttonPanel3);
		_offGamePanel.add(_cardLabel);
		
		key = new keyMovement(_model,_model.pawns[_model.playerUp-1]);
		_gameFrame.addKeyListener(key);
		_gameFrame.getContentPane().add(_gamePanel,BorderLayout.WEST);
		_gameFrame.getContentPane().add(_offGamePanel,BorderLayout.EAST);
		_gameFrame.pack();
		
		_gameFrame.setLocation(0,0);
		_gameInfoWindow.setLocation(1093,0);
		_gameFrame.setVisible(true);
		
		_gameInfoWindow.setVisible(true);
		
		//added this update 05-05 Ian, Ken
		update();
		
	}
		
	/**
	 * @author <jtmirfie>
	 * Draws onto the JFrame of the board setting up all the tiles and other panels that are placed on the board.
	 * The buttons to shift the board are also implemented with their actionlisteners.
	 */	
	public void setupBoard(){
		_boardPanel = new JPanel();
		_boardPanel.setFocusable(true);
		_boardPanel.setLayout(new GridLayout(7,7));
		for (int a = 0; a<7;a++){			
			for (int b = 0; b<7;b++){
			
				_boardPanel.add(new TilesUI(_model.getTile(b, a),_model,b,a).returnPanel());
			}
		}
		
		
		JButton westB1 = new JButton(">");
		westB1.addActionListener(new shiftListener(_model,0,1));
		westB1.setFocusable(false);
		
		JButton westB2 = new JButton(">");
		westB2.addActionListener(new shiftListener(_model,0,3));
		westB2.setFocusable(false);
		JButton westB3 = new JButton(">");
		westB3.addActionListener(new shiftListener(_model,0,5));
		westB3.setFocusable(false);
		
		JButton northB1 = new JButton("v");
		northB1.addActionListener(new shiftListener(_model,1,0));
		northB1.setFocusable(false);
		
		JButton northB2 = new JButton("v");
		northB2.addActionListener(new shiftListener(_model,3,0));
		northB2.setFocusable(false);
		JButton northB3 = new JButton("v");
		northB3.addActionListener(new shiftListener(_model,5,0));
		northB3.setFocusable(false);
		
		JButton eastB1 = new JButton("<");
		eastB1.addActionListener(new shiftListener(_model,6,1));
		eastB1.setFocusable(false);
		JButton eastB2 = new JButton("<");
		eastB2.addActionListener(new shiftListener(_model,6,3));
		eastB2.setFocusable(false);
		JButton eastB3 = new JButton("<");
		eastB3.addActionListener(new shiftListener(_model,6,5));
		eastB3.setFocusable(false);
		
		JButton southB1 = new JButton("^");
		southB1.addActionListener(new shiftListener(_model,1,6));
		southB1.setFocusable(false);
		JButton southB2 = new JButton("^");
		southB2.addActionListener(new shiftListener(_model,3,6));
		southB2.setFocusable(false);
		JButton southB3 = new JButton("^");
		southB3.addActionListener(new shiftListener(_model,5,6));
		southB3.setFocusable(false);
		
		_westBPanel = new JPanel();
		_westBPanel.setFocusable(true);
		_westBPanel.setLayout(new GridLayout(7,1));
		
		_westBPanel.add(new JPanel());
		_westBPanel.add(westB1);
		_westBPanel.add(new JPanel());
		_westBPanel.add(westB2);
		_westBPanel.add(new JPanel());
		_westBPanel.add(westB3);
		_westBPanel.add(new JPanel());
		
		_northBPanel = new JPanel();
		_northBPanel.setFocusable(true);
		_northBPanel.setLayout(new GridLayout(1,7));
		
		_northBPanel.add(new JPanel());
		_northBPanel.add(northB1);
		_northBPanel.add(new JPanel());
		_northBPanel.add(northB2);
		_northBPanel.add(new JPanel());
		_northBPanel.add(northB3);
		_northBPanel.add(new JPanel());
		
		_eastBPanel = new JPanel();
		_eastBPanel.setFocusable(true);
		_eastBPanel.setLayout(new GridLayout(7,1));
		
		_eastBPanel.add(new JPanel());
		_eastBPanel.add(eastB1);
		_eastBPanel.add(new JPanel());
		_eastBPanel.add(eastB2);
		_eastBPanel.add(new JPanel());
		_eastBPanel.add(eastB3);
		_eastBPanel.add(new JPanel());
		
		_southBPanel = new JPanel();
		_southBPanel.setFocusable(true);
		_southBPanel.setLayout(new GridLayout(1,7));
		
		_southBPanel.add(new JPanel());
		_southBPanel.add(southB1);
		_southBPanel.add(new JPanel());
		_southBPanel.add(southB2);
		_southBPanel.add(new JPanel());
		_southBPanel.add(southB3);
		_southBPanel.add(new JPanel());
	}
		
	/**
	 * @author kuangkeming,Josh
	 * This method is used to end the game
	 */
	public void endGame() {
		_gameFrame.removeKeyListener(key);
		_buttonPanel1.setEnabled(false);
		_buttonPanel2.setEnabled(false);
		_buttonPanel3.setEnabled(false);
		_offGamePanel.setEnabled(false);
		
		_rotateButton.setEnabled(false);
		_pickupTokenButton.setEnabled(false);
		_useWandButton.setEnabled(false);
		_saveGameButton.setEnabled(false);
		_endTurnButton.setEnabled(false);
		_player.setEnabled(false);   
	}
	
	/**
	 * @author <jtmirfie>
	 * Runs whenever a change is made to the model and redraws all of the modifications.
	 * All of the panels are removed and it redraws the entire board according to the new model.
	 */	
	@Override public void update() {
		System.out.println("update has run");
		_gameFrame.removeKeyListener(key);
		
		_gamePanel.remove(_boardPanel);
		
		_buttonPanel1.remove(_rotateButton);
		_buttonPanel1.remove(_pickupTokenButton);
		
		_buttonPanel2.remove(_useWandButton);
		_buttonPanel2.remove(_saveGameButton);
	
		_buttonPanel3.remove(_endTurnButton);
		_buttonPanel3.remove(_player);
		
		_offGamePanel.remove(_holdPanel);
		_offGamePanel.remove(_buttonPanel1);
		_offGamePanel.remove(_buttonPanel2);
		_offGamePanel.remove(_buttonPanel3);
		_offGamePanel.remove(_cardLabel);
		
		_player = new JButton(_model.pawns[_model.playerUp-1].getPlayer() + "'s Turn");
		_player.setOpaque(true);
		
		if(_model.pawns[_model.playerUp-1].getColor().equals("BLACK")){
			_player.setBackground(Color.BLACK);
			_player.setForeground(Color.WHITE);
		}
		else if(_model.pawns[_model.playerUp-1].getColor().equals("GREEN")){
			_player.setBackground(Color.GREEN);
		}
		else if(_model.pawns[_model.playerUp-1].getColor().equals("BLUE")){
			_player.setBackground(Color.BLUE);
		}
		else if(_model.pawns[_model.playerUp-1].getColor().equals("RED")){
			_player.setBackground(Color.RED);
		}

		_holdPanel = new TilesUI(_model.getHoldTile(),_model,10,10).returnPanel();
		_holdPanel.setFocusable(true);
		_boardPanel = new JPanel();
		_boardPanel.setFocusable(true);
		_boardPanel.setLayout(new GridLayout(7,7));
		for (int a = 0; a<7;a++){			
			for (int b = 0; b<7;b++){
			
				_boardPanel.add(new TilesUI(_model.getTile(b, a),_model,b,a).returnPanel());
		}
	}
		_gamePanel.add(_boardPanel,BorderLayout.CENTER);
		
		_rotateButton = new JButton("Rotate");
		_rotateButton.addActionListener(new rotateListener(_model,_model.getHoldTile()._rotation));
		_rotateButton.setFocusable(false);
		
		_pickupTokenButton = new JButton("Pickup Token"); //Ian, Josh 04-26
		_pickupTokenButton.setFocusable(false);
		_pickupTokenButton.addActionListener(new PickupTokenButtonListener(_model));
		
		_useWandButton = new JButton("Use Wand");
		_useWandButton.setFocusable(false);
		_useWandButton.addActionListener(new UseWandButtonListener(_model));
		
		_endTurnButton = new JButton("End Turn");
		_endTurnButton.setFocusable(false);
		_endTurnButton.addActionListener(new endTurnListener(_model));
		
		_saveGameButton = new JButton("Save Game");
		_saveGameButton.setFocusable(false);
		_saveGameButton.addActionListener(new SaveGameButtonListener(_model, this));
		if(_model.firstMove==false){
			_saveGameButton.setEnabled(false);
		}
		else{
			_saveGameButton.setEnabled(true);
		}
				
		Image img = null;
		try{
			Pawn p = _model.pawns[_model.playerUp-1];
			int[] values = p.getFormulaCard().getValues();
			String path ="images/";
			path = path + values[0] + "-";
			path = path + values[1] + "-";
			path = path + values[2] + ".png";
			System.out.println(path);
			img = ImageIO.read(getClass().getResource(path));
			
		}catch(IOException ex){}
		_cardLabel = new JLabel(new ImageIcon(img));
		_cardLabel.setPreferredSize(new Dimension(100,150));
		
		_buttonPanel1.add(_rotateButton);
		_buttonPanel1.add(_pickupTokenButton);
		
		_buttonPanel2.add(_useWandButton);
		_buttonPanel2.add(_saveGameButton);
		
		_buttonPanel3.add(_endTurnButton);
		_buttonPanel3.add(_player);
		
		_offGamePanel.add(_holdPanel);
		_offGamePanel.add(_buttonPanel1);
		_offGamePanel.add(_buttonPanel2);
		_offGamePanel.add(_buttonPanel3);
		_offGamePanel.add(_cardLabel);
		
		key = new keyMovement(_model,_model.pawns[_model.playerUp-1]);
		_gameFrame.addKeyListener(key);
		_gameFrame.pack();
		_gameFrame.repaint();	
		
		int numPlayers = _model.getNumOfPlayers();
		String s = "";
		for(int i = 0; i < numPlayers; i++){
			Pawn p = _model.pawns[i];
			s = s + (p.getPlayer() + " ("+ p.getColor() +" Pawn): \n\tCollected Tokens: " + p.tokenCount() 
			+ "\n\tNumber of Wands: " + p.getWandCount() + "\n\n");
		}
		int currentToken =_model.tokenCounter;
		_gameInfoPane.setText("\n"+s+ "Current Collectible Token : " + currentToken);

		//This is like an endGameScoreCounter method -- if the tokenCounter is 26, it prints the score
		//on the Game inform Panel.   
		if(_model.tokenCounter == 26){
			
			ArrayList<Integer> scores = new ArrayList<Integer>() ;
			String s1 = "\t***GAME OVER***\n\n";
			for(int j = 0;j<_model.getNumOfPlayers();j++){
				Pawn p = _model.pawns[j];
				int[] values = p.getFormulaCard().getValues();
				s1 = s1 + (p.getPlayer() + " ("+ p.getColor() +" Pawn): \n\tCollected Tokens: " + p.tokenCount()+ 
						"\n\tFormula Card: "+ values[0] + ", " + values[1] + ", " + values[2] + "\n\tNumber of Wands: " + p.getWandCount() +
						"\n\tScore: " + p.getScore() + "\n");
//				s1 = s1 + "Player: " + p.getPlayer() + " -- Formula Card: "+
//						values[0] + ", " + values[1] + ", " + values[2] + "." +
//						"\n\tScore: " + p.getScore() + "\n";
			 	scores.add(_model.pawns[j].getScore());
			}
			
			//sort scores from lowest to highest
			Collections.sort(scores); 
			
			s1 = s1 + "\n The Winner and Master Wizard is: ";
			for(int i = 0; i < _model.getNumOfPlayers(); i++){
				Pawn p = _model.pawns[i];
				if(p.getScore() == scores.get(scores.size()-1)){
					s1 = s1 + p.getPlayer() + " ";
				}
			}
			s1 = s1 + "!";
			
			_gameInfoPane.setText(s1);

			endGame();
		}
	}
	
	/**
	 * This method is used to exit the game when saved
	 */
	public void exitOnSave(){
		System.exit(0);
	}	
}