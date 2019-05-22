package code.Model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import code.pawn.Pawn;
import code.tiles.ElbowTile;
import code.tiles.StraightTiles;
import code.tiles.TTile;
import code.tiles.Tiles;
import code.tokens.Token;
import code.Model.FormulaCard;
import code.fileio.FileInputOutput;

/**
 * this is the class for model which is for the entire game
 * @author 
 */
public class Model {
		
	/**
	 * this sets the number of rows
	 */
	int rows = 7;
	
	/**
	 * this sets the number of columns
	 */
	int col = 7;
	
	/**
	 * this is the instance variable for observer
	 */
	private Observer _observer;
	
	/**
	 * this is the instance variable for arraylist of tokens
	 */
	private ArrayList<Token> tokens;
	
	/**
	 * this is boolean sets the value of first move 
	 */
	public Boolean firstMove = true;
	
	/**
	 * this is 2D arry that sets up the game board
	 */
	Tiles[][] board  = new Tiles[col][rows];
	
	/**
	 * this is the rotatable tile
	 */
	Tiles hold = null;
	
	/**
	 * this is array of players
	 */
	public Pawn[] pawns;
	
	/**
	 * this sets the value of tokencounter
	 */
	public int tokenCounter = 1;
	
	/**
	 * this sets the value of playerup
	 */
	public int playerUp = 1;
	
	/**
	 * this is the arraylist of formula card
	 */
	private ArrayList<FormulaCard> fCards;
	
	/**
	 * this sets the value of shiftedthisturn to false
	 */
	public static Boolean shiftedThisTurn = false;
	
	/**this is the instance variable for arralist tiles
	 * Ian, Satya 04-26
	 */
	private ArrayList<Tiles> _moveableTiles;
	
	/**
	 * this is the instance variable for last insert columns 
	 */
	private int _lastHoldCol;
	
	/**
	 * this is the instance variable for last insert rows
	 */
	private int _lastHoldRow;
	
	/**
	 * this is the instance variable for number of players
	 */
	private int _numOfPlayers;
	
	/**
	 * @author Wiechec - Constructor
	 * Calls method for board creation and setting the permanent tiles via the set static method.
	 * Previous version did not have a constructor and relied on tests calling the methods separately.
	 * This version of the constructor would automate board creation for the player. An additional 
	 * constructor can be added in the future for players to set the board layout by adding individual
	 * pieces.
	 */
	public Model(int argsLength){
		Token.setCount = 1;
		Token.pickUpCount=1;
		_numOfPlayers = argsLength;
		_lastHoldCol = -1;
		_lastHoldRow = -1; 
		_moveableTiles = new ArrayList<Tiles>();
		this.setStaticTiles();
		this.setBoard();
		pawns = new Pawn[argsLength];
		fCards = new ArrayList<FormulaCard>();
		this.populateFormulaCardArray();
	}
	
	/**
	 * This constructor is used only in restoring a game from a save file (extension .mls)
	 * restoreGame() method must be called directly after a new instance of Model is created
	 * with this constructor to avoid exceptions.
	 * @author Satya, Josh 04-30
	 */
	public Model(){
		
	}
	
	/**
	 * This method allows us to create players statically for testing
	 * @param names
	 * @author Weijin, Ken
	 */
	public void setStaticPlayers(String[] names){
		if(names.length != _numOfPlayers){System.out.println("Number of player names does not equal number of players!");}
		
		else if(_numOfPlayers == 2 ){
			this.pawns[0] = new Pawn(2,2, this,names[0],"BLACK");
			this.pawns[1] = new Pawn(4,4, this,names[1],"GREEN");
			this.dealFormulaCardsToPawns();
		}
		
		else if(_numOfPlayers == 3) {
			this.pawns[0] = new Pawn(2,2,this,names[0],"BLACK");
			this.pawns[1] = new Pawn(4,4, this,names[1],"GREEN");
			this.pawns[2] = new Pawn(2,4, this,names[2],"BLUE");
			this.dealFormulaCardsToPawns();
		}
		
		else if (_numOfPlayers == 4){
			pawns[0] = new Pawn(2,2,this, names[0],"BLACK");
			this.pawns[1] = new Pawn(4,4,this,names[1],"GREEN");
			this.pawns[2] = new Pawn(2,4, this,names[2],"BLUE");
			this.pawns[3] = new Pawn(4,2, this,names[3],"RED");
			this.dealFormulaCardsToPawns();
		}	
	}
	/**
	 * this method sets the static players to the game
	 * @param names
	 */
	public void setStaticPlayersWithoutFormulaCards(String[] names){
		if(names.length != _numOfPlayers){System.out.println("Number of player names does not equal number of players!");}
		
		else if(_numOfPlayers == 2 ){
			this.pawns[0] = new Pawn(2,2, this,names[0],"BLACK");
			this.pawns[1] = new Pawn(4,4, this,names[1],"GREEN");
		}
		
		else if(_numOfPlayers == 3) {
			this.pawns[0] = new Pawn(2,2,this,names[0],"BLACK");
			this.pawns[1] = new Pawn(4,4, this,names[1],"GREEN");
			this.pawns[2] = new Pawn(2,4, this,names[2],"BLUE");
		}
		
		else if (_numOfPlayers == 4){
			pawns[0] = new Pawn(2,2,this, names[0],"BLACK");
			this.pawns[1] = new Pawn(4,4,this,names[1],"GREEN");
			this.pawns[2] = new Pawn(2,4, this,names[2],"BLUE");
			this.pawns[3] = new Pawn(4,2, this,names[3],"RED");
		}	
	}
	
	
	/**
	 * @author Wiechec
	 * Constructor
	 * Tester board for Junit movement tests. Creates a board and sets static tiles
	 * but any additional tiles are the responsibility of the programmer.
	 * @param test
	 */
	public Model(String test){
		Token.setCount = 1;
		Token.pickUpCount=1;
		if (test == "test"){
			this.setStaticTiles();
			board[2][3] = new StraightTiles("East", "I");
			board[1][2] = new StraightTiles();
			board[3][2] = new TTile();
			board[2][1] = new StraightTiles();
			board[5][5] = new ElbowTile("West", "L");
			board[4][5] = new StraightTiles();
			board[5][4] = new TTile("West","T");
			board[3][3] = new StraightTiles("East","I");
			board[3][4] = new ElbowTile("West","L");
			board[4][3] = new StraightTiles();
			
			fCards = new ArrayList<FormulaCard>();
		}
		
		else if(test == "StaticBoard1_2Players"){
			this.setStaticTiles();
			board[0][1] = new TTile("North", "T");
			board[0][3] = new ElbowTile("West", "L");
			board[0][5] = new ElbowTile("North", "L");
			board[1][0] = new StraightTiles("East", "I");
			board[1][1] = new StraightTiles("East", "I");
			board[1][2] = new StraightTiles("North", "I");
			board[1][3] = new StraightTiles("North", "I");
			board[1][4] = new ElbowTile("West", "L");
			board[1][5] = new StraightTiles("North", "I");
			board[1][6] = new StraightTiles("East", "I");
			board[2][1] = new ElbowTile("West", "L");
			board[2][3] = new StraightTiles("North", "I");
			board[2][5] = new StraightTiles("East", "I");
			board[3][0] = new ElbowTile("South", "L");
			board[3][1] = new StraightTiles("North", "I");
			board[3][2] = new TTile("North", "T");
			board[3][3] = new StraightTiles("East", "I");
			board[3][4] = new ElbowTile("North", "L");
			board[3][5] = new TTile("North", "T");
			board[3][6] = new ElbowTile("North", "L");
			board[4][1] = new StraightTiles("East", "I");
			board[4][3] = new ElbowTile("North", "L");
			board[4][5] = new StraightTiles("North", "I");
			board[5][0] = new ElbowTile("North", "L");
			board[5][1] = new TTile("North", "T");
			board[5][2] = new ElbowTile("North", "L");
			board[5][3] = new ElbowTile("South", "L");
			board[5][4] = new TTile("West", "T");
			board[5][5] = new ElbowTile("South", "L");
			board[5][6] = new TTile("West", "T");
			board[6][1] = new ElbowTile("West", "L");
			board[6][3] = new ElbowTile("West", "L");
			board[6][5] = new StraightTiles("North", "I");
			
			_numOfPlayers = 2;
			_lastHoldCol = -1;
			_lastHoldRow = -1; 
			_moveableTiles = new ArrayList<Tiles>();
			pawns = new Pawn[2];
			fCards = new ArrayList<FormulaCard>();
			this.populateFormulaCardArray();
		}
		
		else if(test == "StaticBoard1_3Players"){
			this.setStaticTiles();
			board[0][1] = new TTile("North", "T");
			board[0][3] = new ElbowTile("West", "L");
			board[0][5] = new ElbowTile("North", "L");
			board[1][0] = new StraightTiles("East", "I");
			board[1][1] = new StraightTiles("East", "I");
			board[1][2] = new StraightTiles("North", "I");
			board[1][3] = new StraightTiles("North", "I");
			board[1][4] = new ElbowTile("West", "L");
			board[1][5] = new StraightTiles("North", "I");
			board[1][6] = new StraightTiles("East", "I");
			board[2][1] = new ElbowTile("West", "L");
			board[2][3] = new StraightTiles("North", "I");
			board[2][5] = new StraightTiles("East", "I");
			board[3][0] = new ElbowTile("South", "L");
			board[3][1] = new StraightTiles("North", "I");
			board[3][2] = new TTile("North", "T");
			board[3][3] = new StraightTiles("East", "I");
			board[3][4] = new ElbowTile("North", "L");
			board[3][5] = new TTile("North", "T");
			board[3][6] = new ElbowTile("North", "L");
			board[4][1] = new StraightTiles("East", "I");
			board[4][3] = new ElbowTile("North", "L");
			board[4][5] = new StraightTiles("North", "I");
			board[5][0] = new ElbowTile("North", "L");
			board[5][1] = new TTile("North", "T");
			board[5][2] = new ElbowTile("North", "L");
			board[5][3] = new ElbowTile("South", "L");
			board[5][4] = new TTile("West", "T");
			board[5][5] = new ElbowTile("South", "L");
			board[5][6] = new TTile("West", "T");
			board[6][1] = new ElbowTile("West", "L");
			board[6][3] = new ElbowTile("West", "L");
			board[6][5] = new StraightTiles("North", "I");
			
			_numOfPlayers = 3;
			_lastHoldCol = -1;
			_lastHoldRow = -1; 
			_moveableTiles = new ArrayList<Tiles>();
			pawns = new Pawn[3];
			fCards = new ArrayList<FormulaCard>();
			this.populateFormulaCardArray();
		}
		
		else if(test == "StaticBoard1_4Players"){
			this.setStaticTiles();
			board[0][1] = new TTile("North", "T");
			board[0][3] = new ElbowTile("West", "L");
			board[0][5] = new ElbowTile("North", "L");
			board[1][0] = new StraightTiles("East", "I");
			board[1][1] = new StraightTiles("East", "I");
			board[1][2] = new StraightTiles("North", "I");
			board[1][3] = new StraightTiles("North", "I");
			board[1][4] = new ElbowTile("West", "L");
			board[1][5] = new StraightTiles("North", "I");
			board[1][6] = new StraightTiles("East", "I");
			board[2][1] = new ElbowTile("West", "L");
			board[2][3] = new StraightTiles("North", "I");
			board[2][5] = new StraightTiles("East", "I");
			board[3][0] = new ElbowTile("South", "L");
			board[3][1] = new StraightTiles("North", "I");
			board[3][2] = new TTile("North", "T");
			board[3][3] = new StraightTiles("East", "I");
			board[3][4] = new ElbowTile("North", "L");
			board[3][5] = new TTile("North", "T");
			board[3][6] = new ElbowTile("North", "L");
			board[4][1] = new StraightTiles("East", "I");
			board[4][3] = new ElbowTile("North", "L");
			board[4][5] = new StraightTiles("North", "I");
			board[5][0] = new ElbowTile("North", "L");
			board[5][1] = new TTile("North", "T");
			board[5][2] = new ElbowTile("North", "L");
			board[5][3] = new ElbowTile("South", "L");
			board[5][4] = new TTile("West", "T");
			board[5][5] = new ElbowTile("South", "L");
			board[5][6] = new TTile("West", "T");
			board[6][1] = new ElbowTile("West", "L");
			board[6][3] = new ElbowTile("West", "L");
			board[6][5] = new StraightTiles("North", "I");
			
			_numOfPlayers = 4;
			_lastHoldCol = -1;
			_lastHoldRow = -1; 
			_moveableTiles = new ArrayList<Tiles>();
			pawns = new Pawn[4];
			fCards = new ArrayList<FormulaCard>();
			this.populateFormulaCardArray();
		}
		
		
	}
	/**
	 * Author: Wiechec
	 * Method returns the boolean value on the tile associated with the array x and y,
	 * Board[x][y] values. Tile calls get direction method in the tile class which indicates
	 * if that direction is free for movement.
	 * @param x horizontal movement in 2d array
	 * @param y vertical movement in 2d array
	 * @param direction: Direction pawn wishes to move to.
	 * @return :True or false depending on the field set in the Tile for that direction.
	 */
	public Boolean getTileBool(int x, int y, String direction){
		return board[x][y].getDirection(direction);
		
	}
	
	/**
	 * @author <jtmirfie>
	 * Sets an observer on the board to check when the board has been updated.
	 * @param o Observer to check if board has changed
	 */	
	public void setObserver(Observer o){
		_observer = o;
	}
	
	/**
	 * @author <jtmirfie>
	 * Update called on observer to send an update from Model to GUI.
	 */	
	public void gameChanged(){
		if (_observer != null) {
			_observer.update();
		}
	}
	
	/** @author bdlipp
	 * Method sets the tiles that are static on the board.
	 * These 'static tiles' should not be movable by the methods in this class.
	 * 
	 */
	public void setStaticTiles(){
		board [0][0] = new ElbowTile("East","L");
		board [0][0].isPermanent(true);
		board [0][2] = new TTile("East","T");
		board [0][2].isPermanent(true);
		board [0][4] = new TTile("East","T");
		board [0][4].isPermanent(true);
		board [0][6] = new ElbowTile("North","L");
		board [0][6].isPermanent(true);
		
		board [2][0] = new TTile("South","T");
		board [2][0].isPermanent(true);
		board [2][2] = new TTile("East","T");
		board [2][2].isPermanent(true);
		board [2][4] = new TTile("North","T");
		board [2][4].isPermanent(true);
		board [2][6] = new TTile("North","T");
		board [2][6].isPermanent(true);
		
		board [4][0] = new TTile("South","T");
		board [4][0].isPermanent(true);
		board [4][2] = new TTile("South","T");
		board [4][2].isPermanent(true);
		board [4][4] = new TTile("West","T");
		board [4][4].isPermanent(true);
		board [4][6] = new TTile("North","T");
		board [4][6].isPermanent(true);	
		
		board [6][0] = new ElbowTile("South","L");
		board [6][0].isPermanent(true);
		board [6][2] = new TTile("West","T");
		board [6][2].isPermanent(true);
		board [6][4] = new TTile("West","T");
		board [6][4].isPermanent(true);
		board [6][6] = new ElbowTile("West","L");
		board [6][6].isPermanent(true);
	}
	
	/**
	 * this method populate and shuffle moveable tiles
	 */
	public void populateAndShuffleMoveableTileArray(){
		int ttiles = 6;
		int elbow = 15;
		int straight = 13;
		
		for(int i = 0; i < ttiles; i++){
			Random r = new Random();
			int o = r.nextInt(3);
			String orientation = Tiles.validOrientations[o];
			TTile t = new TTile(orientation,"T");
			_moveableTiles.add(t);
		}
		
		for(int i = 0; i < elbow; i++){
			Random r = new Random();
			int o = r.nextInt(3);
			String orientation = Tiles.validOrientations[o];
			_moveableTiles.add(new ElbowTile(orientation,"L"));
		}
		
		for(int i = 0; i < straight; i++){
			Random r = new Random();
			int o = r.nextInt(3);
			String orientation = Tiles.validOrientations[o];
			_moveableTiles.add(new StraightTiles(orientation,"I"));
		}
		
		Collections.shuffle(_moveableTiles);
	}
	
	
	/**@author bdlipp
	 * @author Ian, Satya 04-26
	 * Method sets the board with tiles.
	 * These tiles are randomly chosen from Straight, Elbow and T groups and
	 * placed in the needed spots. The board should be completely full after 
	 * this method with one remaining tile which is our 'hold' tile.
	 * 
	 */
	public void setBoard(){
		setupTokens();
		populateAndShuffleMoveableTileArray();
		
		for(int i=0; i<col; i++){
			for(int j =0; j<rows; j++){
				if(board[i][j]==null){
					board[i][j] = _moveableTiles.get(0);
					_moveableTiles.remove(0);					
				}
			}	
		}
		setholdTile(_moveableTiles.get(0));

		int counter = 0;
		for(int col=1;col<6;col=col+2){
			for(int row=1;row<6;row++ ){
				board[col][row].setToken(tokens.get(counter));
				board[col][row].hasToken= true;
			    counter++;
			    }
			}
		for(int col=2;col<6;col=col+2){
			for(int row=1;row<6;row=row+2){
				board[col][row].setToken(tokens.get(counter));
				board[col][row].hasToken= true;
			    counter++;
			    }
			}
		
		//THIS IS JUST PRINTING TO CONSOLE I,S 04-26
		for(int col=0;col<7;col++){
			for(int row=0;row<7;row++){					
				if(board[col][row].checkToken())System.out.println("Col: "+col+ " " +"Row: "+ row + " Token: "+board[col][row].getToken().getValue());
			}
		}
	}
	
	/**
	 * this method populate tokens
	 */
	public void populateTokensOnly(){
		int counter = 0;
		for(int col=1;col<6;col=col+2){
			for(int row=1;row<6;row++ ){
				board[col][row].setToken(tokens.get(counter));
				board[col][row].hasToken= true;
			    counter++;
			    }
			}
		for(int col=2;col<6;col=col+2){
			for(int row=1;row<6;row=row+2){
				board[col][row].setToken(tokens.get(counter));
				board[col][row].hasToken= true;
			    counter++;
			    }
			}
		
		//THIS IS JUST PRINTING TO CONSOLE I,S 04-26
		for(int col=0;col<7;col++){
			for(int row=0;row<7;row++){					
				if(board[col][row].checkToken())System.out.println("Col: "+col+ " " +"Row: "+ row + " Token: "+board[col][row].getToken().getValue());
			}
		}
	}
	
	/**@author bdlipp
	 * Method pushes the 'hold' tile across a row or down a column by passing reference from 
	 * one tile to the next. The last tile on the board will be pushed off and will become
	 * the 'hold tile.
	 * 
	 * @param col
	 * @param row
	 * @return the 'hold' tile weather that be the last one pushed out or the original
	 * 		   if the move fails
	 */
	//takes in two ints for points to move and one Tile which is the one tile not on the board
	//also returns the tile that fell off the board
	public Tiles moveTiles(int col, int row){
		if(_lastHoldCol == col && _lastHoldRow == row){	return hold;}
		else{
			
			Tiles tempTile = hold;							
			Tiles[][] tempBoard = new Tiles[7][];
			
			if(firstMove){
			for(int i=0;i<7;i++){
				tempBoard[i] = Arrays.copyOf(board[i], board[i].length);
			} 
			
			if(col==0 && row==1 || col==0 && row==3 || col==0 && row==5){//left
				_lastHoldCol = col+6;
				_lastHoldRow = row;
				
				hold=tempBoard[col+6][row];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}			
				board[col][row]=tempTile;
				board[col+1][row]=tempBoard[col][row];
				board[col+2][row]=tempBoard[col+1][row];
				board[col+3][row]=tempBoard[col+2][row];
				board[col+4][row]=tempBoard[col+3][row];
				board[col+5][row]=tempBoard[col+4][row];
				board[col+6][row]=tempBoard[col+5][row];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col+i && pawns[0].getPositionY()==row){
						if(pawns[0].getPositionX()+1>6){
							pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()-6);
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()+1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col+i && pawns[1].getPositionY()==row){
						if(pawns[1].getPositionX()+1>6){
							pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()-6);
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()+1);
						break;
					}
				}
				if(pawns.length >= 3){
					for(int i=0;i<7;i++){
						if(pawns[2].getPositionX()==col+i && pawns[2].getPositionY()==row){
							if(pawns[2].getPositionX()+1>6){
								pawns[2].setCurrentLocation(pawns[2].getPositionY(), pawns[2].getPositionX()-6);
								break;
							}
							pawns[2].setCurrentLocation(pawns[2].getPositionY(), pawns[2].getPositionX()+1);
							break;
						}
					}
				}
				if(pawns.length >= 4){
					for(int i=0;i<7;i++){
						if(pawns[3].getPositionX()==col+i && pawns[3].getPositionY()==row){
							if(pawns[3].getPositionX()+1>6){
								pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()-6);
								break;
							}
							pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()+1);
							break;
						}
					}
				}
				shiftedThisTurn = true;
				gameChanged();
			}
			else if(col==1 && row==0 || col==3 && row==0 || col==5 && row==0){//top
				_lastHoldCol = col;
				_lastHoldRow = row+6;
				
				hold=tempBoard[col][row+6];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}		
				board[col][row]=tempTile;
				board[col][row+1]=tempBoard[col][row];
				board[col][row+2]=tempBoard[col][row+1];
				board[col][row+3]=tempBoard[col][row+2];
				board[col][row+4]=tempBoard[col][row+3];
				board[col][row+5]=tempBoard[col][row+4];
				board[col][row+6]=tempBoard[col][row+5];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col && pawns[0].getPositionY()==row+i){
						if(pawns[0].getPositionY()+1>6){
							pawns[0].setCurrentLocation(pawns[0].getPositionY()-6, pawns[0].getPositionX());
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY()+1, pawns[0].getPositionX());					
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col && pawns[1].getPositionY()==row+i){
						if(pawns[1].getPositionY()+1>6){
							pawns[1].setCurrentLocation(pawns[1].getPositionY()-6, pawns[1].getPositionX());
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY()+1, pawns[1].getPositionX());
						break;
					}
				}
				if(pawns.length >= 3){
					for(int i=0;i<7;i++){
						if(pawns[2].getPositionX()==col && pawns[2].getPositionY()==row+i){
							if(pawns[2].getPositionY()+1>6){
								pawns[2].setCurrentLocation(pawns[2].getPositionY()-6, pawns[2].getPositionX());
								break;
							}
							pawns[2].setCurrentLocation(pawns[2].getPositionY()+1, pawns[2].getPositionX());
							break;
						}
					}
				}
				if(pawns.length >= 4){
					for(int i=0;i<7;i++){
						if(pawns[3].getPositionX()==col && pawns[3].getPositionY()==row+i){
							if(pawns[3].getPositionY()+1>6){
								pawns[3].setCurrentLocation(pawns[3].getPositionY()-6, pawns[3].getPositionX());
								break;
							}
							pawns[3].setCurrentLocation(pawns[3].getPositionY()+1, pawns[3].getPositionX());
							break;
						}
					}
				}
				shiftedThisTurn = true;
				gameChanged();
			}
			else if(col==6 && row==1 || col==6 && row==3 ||col==6 && row==5){//right
				_lastHoldCol = col-6;
				_lastHoldRow = row;
				
				hold=tempBoard[col-6][row];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}	
				board[col][row]=tempTile;
				board[col-1][row]=tempBoard[col][row];
				board[col-2][row]=tempBoard[col-1][row];
				board[col-3][row]=tempBoard[col-2][row];
				board[col-4][row]=tempBoard[col-3][row];
				board[col-5][row]=tempBoard[col-4][row];
				board[col-6][row]=tempBoard[col-5][row];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col-i && pawns[0].getPositionY()==row){
						if(pawns[0].getPositionX()-1<0){
							pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()+6);
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()-1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col-i && pawns[1].getPositionY()==row){
						if(pawns[1].getPositionX()-1<0){
							pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()+6);
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()-1);
						break;
					}
				}
				if(pawns.length >= 3){
					for(int i=0;i<7;i++){
						if(pawns[2].getPositionX()==col-i && pawns[2].getPositionY()==row){
							if(pawns[2].getPositionX()-1<0){
								pawns[2].setCurrentLocation(pawns[2].getPositionY(), pawns[2].getPositionX()+6);
								break;
							}
							pawns[2].setCurrentLocation(pawns[2].getPositionY(), pawns[2].getPositionX()-1);
							break;
						}
					}
				}
				if(pawns.length >= 4){
					for(int i=0;i<7;i++){
						if(pawns[3].getPositionX()==col-i && pawns[3].getPositionY()==row){
							if(pawns[3].getPositionX()-1<0){
								pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()+6);
								break;
							}
							pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()-1);
							break;
						}
					}
				}
				shiftedThisTurn = true;
				gameChanged();
			}
			else if(col==1 && row==6 || col==3 && row==6 || col==5 && row==6){//bottom
				_lastHoldCol = col;
				_lastHoldRow = row-6;
				
				hold=tempBoard[col][row-6];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}	
				board[col][row]=tempTile;
				board[col][row-1]=tempBoard[col][row];
				board[col][row-2]=tempBoard[col][row-1];
				board[col][row-3]=tempBoard[col][row-2];
				board[col][row-4]=tempBoard[col][row-3];
				board[col][row-5]=tempBoard[col][row-4];
				board[col][row-6]=tempBoard[col][row-5];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col && pawns[0].getPositionY()==row-i){
						if(pawns[0].getPositionY()-1<0){
							pawns[0].setCurrentLocation(pawns[0].getPositionY()+6, pawns[0].getPositionX());
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY()-1, pawns[0].getPositionX());
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col && pawns[1].getPositionY()==row-i){
						if(pawns[1].getPositionY()-1<0){
							pawns[1].setCurrentLocation(pawns[1].getPositionY()+6, pawns[1].getPositionX());
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY()-1, pawns[1].getPositionX());
						break;
					}
				}
				if(pawns.length >= 3){
					for(int i=0;i<7;i++){
						if(pawns[2].getPositionX()==col && pawns[2].getPositionY()==row-i){
							if(pawns[2].getPositionY()-1<0){
								pawns[2].setCurrentLocation(pawns[2].getPositionY()+6, pawns[2].getPositionX());
								break;
							}
							pawns[2].setCurrentLocation(pawns[2].getPositionY()-1, pawns[2].getPositionX());
							break;
						}
					}
				}
				if(pawns.length >= 4){
					for(int i=0;i<7;i++){
						if(pawns[3].getPositionX()==col && pawns[3].getPositionY()==row-i){
							if(pawns[3].getPositionY()-1<0){
								pawns[3].setCurrentLocation(pawns[3].getPositionY()+6, pawns[3].getPositionX());
								break;
							}
							pawns[3].setCurrentLocation(pawns[3].getPositionY()-1, pawns[3].getPositionX());
							break;
						}
					}
				}
				shiftedThisTurn = true;
				gameChanged();
			}
			else{
				System.out.println("I'm sorry, that's a permanent structure");
			}			
			}
		} //end of if(_lastHoldCol != col && _lastHoldRow != row)
		
		return hold;
	}
	
	/**@author bdlipp
	 * Method returns the tile at a specific spot in the graph.
	 * 
	 * @param col
	 * @param row
	 * @return Tiles object that is in a specific column and row.
	 */
	public Tiles getTile(int col, int row){
		return board[col][row];
	}
	
	/**@author bdlipp
	 * Method sets the 'hold' tile. Used for testing making it easier to follow a specific
	 * tile that is not randomly created.
	 * @param tile
	 */
	public void setholdTile(Tiles tile){
		hold = tile;
	}
	
	/**@author bdlipp
	 * Method returns the 'hold' tile. Used for testing making it easier to follow a specific
	 * tile that is not randomly created.
	 * 
	 * @return 'hold' tile. Used for testing making it easier to follow a specific
	 * tile that is not randomly created.
	 */
	public Tiles getHoldTile(){
		return hold;
	}
	
	/**
	 * @author <jtmirfie>
	 * Returns the 'board' tiles. Used to associate between other classes.
	 * @return 'board' tiles in order to access it from other classes.
	 */	
	public Tiles[][] getBoard(){
		return board;
	}
	
	/**
	 * @author <jtmirfie>
	 * Compiles an array of the tokens class and then shuffles them using a collection method.
	 */	
	public void setupTokens(){
		tokens = new ArrayList<Token>();
		for(int i=0;i<21;i++){
			tokens.add(new Token());
		}
		Collections.shuffle(tokens);
		
	}
	
	public void setupStaticNonRandomTokens(){
		tokens = new ArrayList<Token>();
		for(int i=0;i<21;i++){
			tokens.add(new Token());
		}
	}
	
	/**
	 * this method gets the number of players
	 * @return
	 */
	public int getNumOfPlayers() {
		return _numOfPlayers;
	}
	
	/**
	 * this method populates the formula cards
	 */
	public void populateFormulaCardArray(){
		FormulaCard f1 = new FormulaCard(1,10,13);
		FormulaCard f2 = new FormulaCard(10,12,16);
		FormulaCard f3 = new FormulaCard(11,3,14);
		FormulaCard f4 = new FormulaCard(12,1,9);
		FormulaCard f5 = new FormulaCard(13,15,12);
		FormulaCard f6 = new FormulaCard(14,4,10);
		FormulaCard f7 = new FormulaCard(15,2,4);
		FormulaCard f8 = new FormulaCard(16,9,7);
		FormulaCard f9 = new FormulaCard(17,5,6);
		FormulaCard f10 = new FormulaCard(18,11,19);
		FormulaCard f11 = new FormulaCard(19,7,15);
		FormulaCard f12 = new FormulaCard(2,8,17);
		FormulaCard f13 = new FormulaCard(20,17,3);
		FormulaCard f14 = new FormulaCard(25,16,2);
		FormulaCard f15 = new FormulaCard(3,18,1);
		FormulaCard f16 = new FormulaCard(4,13,20);
		FormulaCard f17 = new FormulaCard(5,25,18);
		FormulaCard f18 = new FormulaCard(6,14,8);
		FormulaCard f19 = new FormulaCard(7,6,25);
		FormulaCard f20 = new FormulaCard(8,19,5);
		FormulaCard f21 = new FormulaCard(9,20,11);
		fCards.add(f1);
		fCards.add(f2);
		fCards.add(f3);
		fCards.add(f4);
		fCards.add(f5);
		fCards.add(f6);
		fCards.add(f7);
		fCards.add(f8);
		fCards.add(f9);
		fCards.add(f10);
		fCards.add(f11);
		fCards.add(f12);
		fCards.add(f13);
		fCards.add(f14);
		fCards.add(f15);
		fCards.add(f16);
		fCards.add(f17);
		fCards.add(f18);
		fCards.add(f19);
		fCards.add(f20);
		fCards.add(f21);
		Collections.shuffle(fCards);
	}
	
	/**
	 * this method deal formula cards to pawns
	 */
	public void dealFormulaCardsToPawns(){
		for(int i=0;i<pawns.length;i++){
			FormulaCard f = fCards.get(i);
			pawns[i].setFormulaCard(f);
			int[] values =f.getValues();
			System.out.println(pawns[i].getPlayer()+ "'s Formula Card is : " + 
					values[0]+ ", "+ values[1]+", "+ values[2]+".");
		}
	}
	
	/**
	 * this method save the game
	 * @param p
	 * @param path
	 * @return
	 * @author Ken, Weijin 04-29 
	 * @author Ken, Ian 04-30
	 */
	public boolean saveGame(Pawn p, String path){
		boolean ans = false;
		if(p != pawns[playerUp-1]){return ans;}
		if(firstMove == true){
			String[] currentState = writeCurrentGameStateToString();
			FileInputOutput.writeStringArrayToFile(path, currentState);
			ans = true;
		}
		return ans;
	}	
	
	/**
	 * this method restore the game from the string 
	 * @param s
	 * @author Satya, Josh 04-30
	 * @author Weijin, Ian 04-30
	 * @author Ian, Ken 04-30
	 * @author Ken, Ian 05-05-16
	 */
	public boolean restoreGame(String[] s){
		boolean restoreSuccessful = false;
		
		if(s.length != 3){
			System.err.println("The save file has NOT been properly encoded: there\n"
					+ "are greater than three lines of text.");
			return restoreSuccessful;
		}
		
		int numOfPlayers = 0;
		int index = 0;
		ArrayList<String> pNames = new ArrayList<String>();
		ArrayList<String> pColors = new ArrayList<String>();
		ArrayList<Integer> numWands = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> tokenValues = new ArrayList<ArrayList<Integer>>();
		ArrayList<FormulaCard> fCards = new ArrayList<FormulaCard>();
		
		//state machine to parse and extract info from s[0]
		String tempName = "";
		String tempColor = "";
		String tempFCVal1 = "";
		String tempFCVal2 = "";
		String tempFCVal3 = "";
		String tempToken = "";
		
		System.out.println("Index: " + index);
		int state1 = 0;
		for(int i = 0; i < s[0].length(); i++){
			switch(state1){
			case 0:
				if(s[0].charAt(i) == '['){
					numOfPlayers++;
					tokenValues.add(new ArrayList<Integer>());
					state1 = 1;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 1:
				if(s[0].charAt(i) != ','){
					tempName = tempName + s[0].charAt(i);
					state1 = 1;
				}
				else if(s[0].charAt(i) == ',' && tempName.equals("")){
					System.out.println("Save file format error detected");
					return false;
				}
				else if(s[0].charAt(i) == ','){
					pNames.add(tempName);
					tempName = "";
					state1 = 2;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 2:
				if(('a' <= s[0].charAt(i) && s[0].charAt(i) <= 'z') ||
						('A' <= s[0].charAt(i) && s[0].charAt(i) <= 'Z')){
					tempColor = tempColor + s[0].charAt(i);
					state1 = 2;
				}
				else if(s[0].charAt(i) == ','){
					pColors.add(tempColor.toUpperCase());
					tempColor = "";
					state1 = 3;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 3:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '3'){
					numWands.add((int) (s[0].charAt(i) - '0'));
					state1 = 4;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 4:
				if(s[0].charAt(i) == ','){
					state1 = 5;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 5:
				if(s[0].charAt(i) == '['){
					state1 = 6;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 6:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempFCVal1 = tempFCVal1 + s[0].charAt(i);
					state1 = 7;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 7:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempFCVal1 = tempFCVal1 + s[0].charAt(i);
					state1 = 8;
				}
				else if(s[0].charAt(i) == ','){
					state1 = 9;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 8:
				if(s[0].charAt(i) == ','){
					state1 = 9;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 9:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempFCVal2 = tempFCVal2 + s[0].charAt(i);
					state1 = 10;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 10:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempFCVal2 = tempFCVal2 + s[0].charAt(i);
					state1 = 11;
				}
				else if(s[0].charAt(i) == ','){
					state1 = 12;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 11:
				if(s[0].charAt(i) == ','){
					state1 = 12;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 12:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempFCVal3 = tempFCVal3 + s[0].charAt(i);
					state1 = 13;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 13:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempFCVal3 = tempFCVal3 + s[0].charAt(i);
					state1 = 14;
				}
				else if(s[0].charAt(i) == ']'){
					fCards.add(new FormulaCard(Integer.parseInt(tempFCVal1),Integer.parseInt(tempFCVal2),Integer.parseInt(tempFCVal3)));
					tempFCVal1 = ""; tempFCVal2 = ""; tempFCVal3 = "";
					state1 = 15;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 14:
				if(s[0].charAt(i) == ']'){
					fCards.add(new FormulaCard(Integer.parseInt(tempFCVal1),Integer.parseInt(tempFCVal2),Integer.parseInt(tempFCVal3)));
					tempFCVal1 = ""; tempFCVal2 = ""; tempFCVal3 = "";
					state1 = 15;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 15:
				if(s[0].charAt(i) == ','){
					state1 = 16;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 16:
				if(s[0].charAt(i) == '['){
					state1 = 17;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 17:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempToken = tempToken + s[0].charAt(i);
					state1 = 18;
				}
				else if(s[0].charAt(i) == ']'){
					state1 = 21;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 18:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempToken = tempToken + s[0].charAt(i);
					state1 = 19;
				}
				else if(s[0].charAt(i) == ','){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 20;
				}
				else if(s[0].charAt(i) == ']'){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 21;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 19:
				if(s[0].charAt(i) == ','){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 20;
				}
				else if(s[0].charAt(i) == ']'){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 21;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 20:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempToken = tempToken + s[0].charAt(i);
					state1 = 22;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 21:
				if(s[0].charAt(i) == ']'){
					state1 = 24;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 22:
				if('0' <= s[0].charAt(i) && s[0].charAt(i) <= '9'){
					tempToken = tempToken + s[0].charAt(i);
					state1 = 23;
				}
				else if(s[0].charAt(i)==','){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 20;
				}
				else if(s[0].charAt(i)==']'){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 21;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 23:
				if(s[0].charAt(i)==','){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 20;
				}
				else if(s[0].charAt(i)==']'){
					tokenValues.get(index).add(Integer.parseInt(tempToken));
					tempToken = "";
					state1 = 21;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			case 24:
				if(s[0].charAt(i) == ','){
					if(s[0].charAt(i) == ',' && i == s[0].length()-1){
						System.out.println("Save file format error detected");
						return false;
					}
					index++;
					System.out.println("Index: " + index);
					state1 = 0;
				}
				else{
					System.out.println("Save file format error detected");
					return false;
				}
				break;
			}//end of switch statement
		}//end of for loop
		
		ArrayList<Integer> rotationD = new ArrayList<Integer>();
		ArrayList<Integer> tileTokenValue = new ArrayList<Integer>();
		ArrayList<ArrayList<String>> tilePlayerColor = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < 49; i++){
			tilePlayerColor.add(new ArrayList<String>());	
		}
	
		String[] tColors = new String[49];
		ArrayList<String> tShape = new ArrayList<String>();
		String temptColors = "";
		int indexcolor = 0;
		int tRotationD = 0;
		String tTokenValue = "";
		
		
		int state2 = 0;
		for(int i = 0; i < s[1].length(); i++){
			
			switch(state2){
			case 0:
				
				if(s[1].charAt(i) == '['){
					state2 = 1;
				}
				break;
			case 1:
				
				if(s[1].charAt(i) == 'I'){
					tShape.add(s[1].charAt(i)+"");
					state2 = 2;
				}
				else if(s[1].charAt(i) == 'L'){
					tShape.add(s[1].charAt(i)+"");
					state2 = 2;
				}
				else if(s[1].charAt(i) == 'T'){
					tShape.add(s[1].charAt(i)+"");
					state2 = 2;
				}
				break;
			case 2:
				
				if (s[1].charAt(i) >= '0' && s[1].charAt(i) <= '3'){
					tRotationD = (int)(s[1].charAt(i)-'0');
					rotationD.add(tRotationD);
					state2 = 3;
				}
				break;
			case 3:
				
				if (s[1].charAt(i) == ','){
					state2 = 4;
				}
				break;
			case 4:
				
				if (('0' <= s[1].charAt(i) && s[1].charAt(i) <= '9')){
					tTokenValue = tTokenValue + s[1].charAt(i);
					state2 = 10;
				}
				break;
			case 10:
				
				if (('0' <= s[1].charAt(i) && s[1].charAt(i) <= '9')){
					tTokenValue = tTokenValue + s[1].charAt(i);
					tileTokenValue.add(Integer.parseInt(tTokenValue));
					tTokenValue = "";
					state2 = 5;
				}
				else if(s[1].charAt(i) == ','){
					tileTokenValue.add(Integer.parseInt(tTokenValue));
					tTokenValue = "";
					state2 = 6;
				}
				
				break;
			case 5:
				
				if (s[1].charAt(i) == ','){
					state2 = 6;
				}
				break;
			case 6:
				
				if (s[1].charAt(i) == '['){
					state2 = 7;
				}
				break;
			case 7:
				if(('a' <= s[1].charAt(i) && s[1].charAt(i) <= 'z') ||
						('A' <= s[1].charAt(i) && s[1].charAt(i) <= 'Z')){
					temptColors = temptColors + s[1].charAt(i);
					tColors[indexcolor] = temptColors;
					state2 = 7;
				}
				else if(s[1].charAt(i) == ','){
					tColors[indexcolor] = temptColors;
					tilePlayerColor.get(indexcolor).add(tColors[indexcolor]);
					temptColors = "";
					state2 = 7;
				}
				else if(s[1].charAt(i) == ']'){
					tColors[indexcolor] = temptColors;
					tilePlayerColor.get(indexcolor).add(tColors[indexcolor]);
					temptColors = "";
					state2 = 8;
				}
			case 8:
				if (s[1].charAt(i) == ']'){
					state2 = 9;
				}
				break;
			case 9:
				if (s[1].charAt(i) == ','){
					state2 = 0;
					indexcolor++;
				}
				break;	
			}
		}
		
		//restore Tiles
		int count = 0;
		int numT = 18;
		int numL = 19;
		int numI = 13;
		for(int row = 0; row < 7; row++){
			for(int col = 0; col < 7; col++){
				String shape = tShape.get(count);
				int rotation = rotationD.get(count);
				if(shape.equals("T")){
					numT--;
						
					if(rotation == 0){
						board[col][row] = new TTile("South", "T");	
					}
					else if (rotation == 1){
						board[col][row] = new TTile("West", "T");		
					}
					else if (rotation == 2){
						board[col][row] = new TTile("North", "T");		
					}
					else if (rotation == 3){
						board[col][row] = new TTile("East", "T");		
					}
				}
				
				if(shape.equals("L")){
					numL--;
					if(rotation == 0){
						board[col][row] = new ElbowTile("East", "L");
					}
					else if (rotation == 1){
						board[col][row] = new ElbowTile("South", "L");
					}
					else if (rotation == 2){
						board[col][row] = new ElbowTile("West", "L");
					}
					else if (rotation == 3){
						board[col][row] = new ElbowTile("North", "L");
					}
				}
				
				if(shape.equals("I")){
					numI--;
					if(rotation == 0){
						board[col][row] = new StraightTiles("North", "I");
					}
					else if (rotation == 1){
						board[col][row] = new StraightTiles("East", "I");
					}
					else if (rotation == 2){
						board[col][row] = new StraightTiles("North", "I");
					}
					else if (rotation == 3){
						board[col][row] = new StraightTiles("East", "I");
					}
				}
				count++;
			}
		}
		
		//restore Hold Tile
		if(numT == 1){
			hold = new TTile("North", "T");
		}
		else if(numL == 1){
			hold = new ElbowTile("North", "L");
		}
		else if(numI == 1){
			hold = new StraightTiles("North", "I");
		}
		
		//State machine for extraction information from s[2]
		String temp = "";
		int illegalInsertableNum = 0;
		
		int state3 = 0;
		for(int i = 0; i < s[2].length(); i++){
			switch(state3){
			case 0:
				if('0' <= s[2].charAt(i) && s[2].charAt(i) <= '9'){
					temp = temp + s[2].charAt(i);
					state3 = 1;
				}
				break;
			case 1:
				if('0' <= s[2].charAt(i) && s[2].charAt(i) <= '9'){
					temp = temp + s[2].charAt(i);
				}
				break;
			}
		}
		illegalInsertableNum = Integer.parseInt(temp);
		
		
		if(illegalInsertableNum == 0){
			_lastHoldCol = -1;
			_lastHoldRow = -1;
		}
		
		//North triangle push points
		else if(illegalInsertableNum == 1){
			_lastHoldCol = 1;
			_lastHoldRow = 0;
		}
		else if(illegalInsertableNum == 2){
			_lastHoldCol = 3;
			_lastHoldRow = 0;
		}
		else if(illegalInsertableNum == 3){
			_lastHoldCol = 5;
			_lastHoldRow = 0;
		}
		
		//East triangle push points
		else if(illegalInsertableNum == 4){
			_lastHoldCol = 6;
			_lastHoldRow = 1;
		}
		else if(illegalInsertableNum == 5){
			_lastHoldCol = 6;
			_lastHoldRow = 3;
		}
		else if(illegalInsertableNum == 6){
			_lastHoldCol = 6;
			_lastHoldRow = 5;
		}
		
		//South triangle push points
		else if(illegalInsertableNum == 7){
			_lastHoldCol = 5;
			_lastHoldRow = 6;
		}
		else if(illegalInsertableNum == 8){
			_lastHoldCol = 3;
			_lastHoldRow = 6;
		}
		else if(illegalInsertableNum == 9){
			_lastHoldCol = 1;
			_lastHoldRow = 6;
		}
		
		//West triangle push points
		else if(illegalInsertableNum == 10){
			_lastHoldCol = 0;
			_lastHoldRow = 5;
		}
		else if(illegalInsertableNum == 11){
			_lastHoldCol = 0;
			_lastHoldRow = 3;
		}
		else if(illegalInsertableNum == 12){
			_lastHoldCol = 0;
			_lastHoldRow = 1;
		}
		
		//Restore pawns
		int pRow = 0; //y
		int pCol = 0; //x
		pawns = new Pawn[numOfPlayers];
		for(int i = 0; i < numOfPlayers; i++){
			String color = pColors.get(i);
			String name = pNames.get(i);
			FormulaCard fCard = fCards.get(i);
			int wands = numWands.get(i);
			System.out.println(tilePlayerColor);
			for(int j = 0; j < tilePlayerColor.size(); j++){
				if(tilePlayerColor.get(j).contains(color)){
					System.out.println("Here");
					pCol = j % 7;
					pRow = j / 7;
					Pawn p = new Pawn(pRow, pCol, this, name, color);
					pawns[i] = p;
					p.setFormulaCard(fCard);
					p.restoreWandCount(wands);
					p.restoreTokens(tokenValues.get(i));
					p.setHasMovedThisTurn(false);
					p.setPickedUpTokenThisTurn(false);
					p.setUsedWandThisTurn(false);
				}
			}
		}
		
		for(int i = 0; i < pawns.length; i++){
			String color = pawns[i].getColor();
			System.out.println(color);
		}
		
		_moveableTiles = new ArrayList<Tiles>();
		fCards = new ArrayList<FormulaCard>();
		FormulaCard f1 = new FormulaCard(1,10,13);
		FormulaCard f2 = new FormulaCard(10,12,16);
		FormulaCard f3 = new FormulaCard(11,3,14);
		FormulaCard f4 = new FormulaCard(12,1,9);
		FormulaCard f5 = new FormulaCard(13,15,12);
		FormulaCard f6 = new FormulaCard(14,4,10);
		FormulaCard f7 = new FormulaCard(15,2,4);
		FormulaCard f8 = new FormulaCard(16,9,7);
		FormulaCard f9 = new FormulaCard(17,5,6);
		FormulaCard f10 = new FormulaCard(18,11,19);
		FormulaCard f11 = new FormulaCard(19,7,15);
		FormulaCard f12 = new FormulaCard(2,8,17);
		FormulaCard f13 = new FormulaCard(20,17,3);
		FormulaCard f14 = new FormulaCard(25,16,2);
		FormulaCard f15 = new FormulaCard(3,18,1);
		FormulaCard f16 = new FormulaCard(4,13,20);
		FormulaCard f17 = new FormulaCard(5,25,18);
		FormulaCard f18 = new FormulaCard(6,14,8);
		FormulaCard f19 = new FormulaCard(7,6,25);
		FormulaCard f20 = new FormulaCard(8,19,5);
		FormulaCard f21 = new FormulaCard(9,20,11);
		fCards.add(f1);
		fCards.add(f2);
		fCards.add(f3);
		fCards.add(f4);
		fCards.add(f5);
		fCards.add(f6);
		fCards.add(f7);
		fCards.add(f8);
		fCards.add(f9);
		fCards.add(f10);
		fCards.add(f11);
		fCards.add(f12);
		fCards.add(f13);
		fCards.add(f14);
		fCards.add(f15);
		fCards.add(f16);
		fCards.add(f17);
		fCards.add(f18);
		fCards.add(f19);
		fCards.add(f20);
		fCards.add(f21);
	
		setNumOfPlayers(numOfPlayers);
		
		restoreTokens();
		
		//restore instance variables of model
		firstMove = true;
		shiftedThisTurn = false;
		
		//restore tokenCounter
		int greatestTokenSoFar = 0;
		for(int i = 0; i < tokenValues.size(); i++){
			for(int j = 0; j < tokenValues.get(i).size(); j++){
				int tokVal = tokenValues.get(i).get(j);
				if(tokVal > greatestTokenSoFar){
					greatestTokenSoFar = tokVal;
				}
			}
		}
		if(greatestTokenSoFar == 20){
			tokenCounter = 25;
		}
		else{
			tokenCounter = greatestTokenSoFar + 1;
		}
		
		
		//restore tokens to tiles
		for(int i = 0; i < tileTokenValue.size(); i++){
			if(tileTokenValue.get(i) != 0){
				int value = tileTokenValue.get(i);
				if(1 <= value && value <= 20){
					int col = i % 7;
					int row = i / 7;
					Token t = tokens.get(value-1);
					board[col][row].hasToken = true;
					board[col][row].setToken(t);
				}
				else if( value == 25){
					int col = i % 7;
					int row = i / 7;
					Token t = tokens.get(20);
					board[col][row].hasToken = true;
					board[col][row].setToken(t);
				}
			}
		}
		
		restoreSuccessful=true;
		
		return restoreSuccessful;
	}//end of restoreGame method
	
	/**
	 * this method sets the number of players
	 * @param numberPlayers
	 */
	public void setNumOfPlayers(int numberPlayers) {
		_numOfPlayers = numberPlayers;	
	}

	/**
	 * this method restore tokens
	 */
	public void restoreTokens() {
		tokens = new ArrayList<Token>();
		for(int i=0;i<21;i++){
			tokens.add(new Token());
		}	
	}
	
	/**
	 * this save the game and writes data to a string
	 * @return
	 * @author Ken, Weijin 04-29 
	 * @author Ken, Ian 04-30
	 */
	public String[] writeCurrentGameStateToString(){
		String[] s = new String[3];
		s[0] = "";
		s[1] = "";
		s[2] = "";
		//write pawn/player information to String
		int index = playerUp-1;
		int count = 0;
		while(count != pawns.length){
			Pawn p = pawns[index];
			FormulaCard fC = p.getFormulaCard();
			int[] values = fC.getValues();
			
			if(count == pawns.length - 1){
				s[0] = s[0] + "[" + p.getPlayer() + "," + p.getColor() + "," + p.getWandCount() + 
						",[" + values[0] + "," + values[1] + "," + values[2] + "],"; 
				
				String tokens = p.tokenCount().toString();
				String tokensWithoutSpaces = "";
				for(int i = 0; i < tokens.length(); i++){
					char c = tokens.charAt(i);
					if(c!=' '){
						tokensWithoutSpaces = tokensWithoutSpaces + c;
					}
				}
				s[0] = s[0] + tokensWithoutSpaces + "]";
			}
			else{
				s[0] = s[0] + "[" + p.getPlayer() + "," + p.getColor() + "," + p.getWandCount() + 
						",[" + values[0] + "," + values[1] + "," + values[2] + "],";
				
				String tokens = p.tokenCount().toString();
				String tokensWithoutSpaces = "";
				for(int i = 0; i < tokens.length(); i++){
					char c = tokens.charAt(i);
					if(c!=' '){
						tokensWithoutSpaces = tokensWithoutSpaces + c;
					}
				}
				s[0] = s[0] + tokensWithoutSpaces + "],";
			}
			
			index = ((index + 1) % pawns.length);
			count++;
		}
		
		//write tile information to string
		
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				Tiles t = board[j][i];
				s[1] = s[1] + "["+t.getIdentity()+t.getOrientation()+",";
				if(t.hasToken){
					s[1] = s[1] + t.getToken().getValue() + ",[";
				}
				else{
					s[1] = s[1] + "0,[";
				}
				
				int numPOnTile = 0;
				for(int k = 0; k < pawns.length; k++){
					Pawn p = pawns[k];
					if(p.getPositionX()==j && p.getPositionY() == i){
						numPOnTile++;
					}
				}
				for(int k = 0; k < pawns.length; k++){
					Pawn p = pawns[k];
					if(p.getPositionX()==j && p.getPositionY() == i){
						if(numPOnTile > 1){
							s[1] = s[1] + p.getColor() + ",";
						}
						else{
							s[1] = s[1] + p.getColor();
						}
					}
				}
				s[1] = s[1] + "]";
				
				if(i == 6 && j == 6){
					s[1] = s[1] + "]";
				}
				else{ s[1] = s[1] + "],";}
			}
		}
		
		//write shiftable tile information to string
		if(_lastHoldCol==-1 && _lastHoldRow==-1){s[2] = s[2] + "0";}
		else{
			//North triangle push points
			if(_lastHoldCol==1 && _lastHoldRow==0){
				s[2] = s[2] + "1";
			}
			else if(_lastHoldCol==3 && _lastHoldRow==0){
				s[2] = s[2] + "2";
			}
			else if(_lastHoldCol==5 && _lastHoldRow==0){
				s[2] = s[2] + "3";
			}
			
			//East triangle push points
			if(_lastHoldCol==6 && _lastHoldRow==1){
				s[2] = s[2] + "4";
			}
			else if(_lastHoldCol==6 && _lastHoldRow==3){
				s[2] = s[2] + "5";
			}
			else if(_lastHoldCol==6 && _lastHoldRow==5){
				s[2] = s[2] + "6";
			}
			
			//South triangle push points
			if(_lastHoldCol==5 && _lastHoldRow==6){
				s[2] = s[2] + "7";
			}
			else if(_lastHoldCol==3 && _lastHoldRow==6){
				s[2] = s[2] + "8";
			}
			else if(_lastHoldCol==1 && _lastHoldRow==6){
				s[2] = s[2] + "9";
			}
			
			//West triangle push points
			if(_lastHoldCol==0 && _lastHoldRow==5){
				s[2] = s[2] + "10";
			}
			else if(_lastHoldCol==0 && _lastHoldRow==3){
				s[2] = s[2] + "11";
			}
			else if(_lastHoldCol==0 && _lastHoldRow==1){
				s[2] = s[2] + "12";
			}
		}
		
		return s;
	}
	
	/**
	 * this method ends turn of current player
	 */
	public void endTurn(){
		Pawn p = pawns[playerUp-1];
	    boolean sameX = (p.startX==p.getPositionX());
	    boolean sameY = (p.startY == p.getPositionY());
	    
		if(!firstMove){
			if(!(p.getHasMovedThisTurn() && sameX && sameY)){
				System.out.println("End Turn Button Clicked!");
				if(pawns[playerUp-1].checkIfValidMove()){
					//pawns[playerUp-1].validMove = false;
					pawns[playerUp-1].startX = pawns[playerUp-1].getPositionX();
					pawns[playerUp-1].startY = pawns[playerUp-1].getPositionY();
				}
				firstMove = true;
				playerUp = playerUp + 1;
				if(playerUp>getNumOfPlayers()) {playerUp = 1;}
				pawns[playerUp-1].setPickedUpTokenThisTurn(false);
				pawns[playerUp-1].setUsedWandThisTurn(false);
				pawns[playerUp-1].setHasMovedThisTurn(false);
				gameChanged();
			}
		}
	}
}