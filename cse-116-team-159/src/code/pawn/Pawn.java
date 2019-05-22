package code.pawn;

import java.util.ArrayList;
import code.Model.FormulaCard;
import code.Model.Model;
import code.GUI.GUI;
import code.tokens.Token;

/**
 * Class will create a Pawn object. Class contains methods for movement of the pawn, which
	  calls to the tile/board to verify movement is possible. There are method stubs
	  and some additional code beyond the scope of the project, but any additional coding can be
	  taken as ideas that were noted for possible future implementation.
 * @author Wiechec
 */
public class Pawn {
	
	/**
	 * this is the instance variable for color 
	 */
	private String _color;
	
	/**
	 * @author Wiechec
	 * wcount: Field containing the number of wands the player has. Future implementation.
	 */
	private int wcount = 3;
	
	/**
	 * @author Wiechec
	 * points: current point value player has for winning condition.
	 */
	private int points;
	
	/**
	 * @author Wiechec
	 * numOfTokens: Number of tokens the player current has. For winning condition. Future implementation.
	 */
	private int numOfTokens;
	
	/**
	 * @author Wiechec
	 * positionX: Player's current X position. Horizontal.
	 */
	private int positionX;
	
	/**
	 * @author Wiechec
	 * positionY: Player's current Y position. Vertical.
	 */
	private int positionY;
	
	/**
	 * @author Wiechec
	 * board: associates the board to the pawn for calling methods back to the board.
	 */
	private Model board;
	
	/**
	 * @author kuangkeming, Josh
	 * GUI: associates the GUI to the pawn for calling methods back to the GUI.
	 */
	private GUI gui;
	
	/** 
	 * @author Wiechec
	 * next: Storage field for testing the canMove method.
	 */
	private Boolean next = false;
	
	/**
	 * this is the instance variable for token counts 
	 */
	private ArrayList<Integer>tokenCounts;
	
	/**
	 * this is the instance variable for formula card
	 */
	private FormulaCard _fCard;
	
	/**
	 * this sets the value of validmove to false
	 */
	public Boolean validMove = false;
	
	/**
	 *  this is the integer of starting x coordinates
	 */
	public int startX;
	
	/**
	 * this is the integer of starting y coordinates 
	 */
	public int startY;
	
	/**
	 * This is the NAME of the player
	 */
	private String player ="";
	
	/**
	 * this method sets the value of pick up token this turn to false
	 */
	private boolean _pickedUpTokenThisTurn = false;
	
	/**
	 * this method sets the value of used wand this turn to false
	 */
	private boolean _usedWandThisTurn = false;
	
	/**
	 * this method sets the value of has moved this turn to false
	 */
	private boolean _hasMovedThisTurn = false;
	
	/**
	 * @author Wiechec
	 * Method can create a pawn at a specified position for game creation.
	 * @param y coordinate
	 * @param x coordinate
	 * @param board links to board.
	 */
	public Pawn(int y, int x, Model board,String player, String color){
		
		_color = color;
		this.board = board;
		this.positionX = x;
		this.positionY = y;
		this.player = player;
		tokenCounts = new ArrayList<Integer>();
		startX = x;
		startY = y;
	}
	
	/**
	 * @author <jtmirfie>
	 * @return checks 'validMove' if the pawn has made a movement that is not the same spot
	 * it has started from
	 */	
	public Boolean checkIfValidMove(){
		validMove = !board.getTile(positionX, positionY).equals(board.getTile(startX, startY));
		return validMove;
	}
	
	/**
	 * @author <jtmirfie>
	 * @return checks 'tokenCounts' to see how many tokens each pawn has collected.
	 */	
	public ArrayList<Integer> tokenCount(){
		return tokenCounts;
	}
	
	/**
	 * @author Wiechec
	 * This method sets the current location of the pawn's instance variable at a specific position
	 * on the board using x and y coordinates. This method may be used for game creation as well as
	 * moving the player piece during the game play.
	 * @param x sets the pawns's positionX field
	 * @param y sets the pawn's positionY field
	 */
	public void setCurrentLocation(int y, int x){
		this.positionX = x;
		this.positionY = y;
	}
	/**
	 * @author Wiechec
	 * Returns pawn's positionX value, which is where it is horizontally on the board.
	 * @return
	 */
	public int getPositionX(){
		return positionX;
	}
	/**
	 * @author Wiechec
	 * Returns pawn's positionY value, which is where it is vertically on the board.
	 * @return
	 */
	public int getPositionY(){
		return positionY;
	}
	/**
	 * @author Wiechec
	*This method takes in, as a string, the direction of movement called on the pawn. The direction,
	*taken in as a String, is set to lowercase and only the zeroth index is checked for the direction.
	*Once the input is taken into the parameter, it is passed into the first if statement, which
	*calls on the canMove method to verify the movement is possible prior to any further action
	*taking place. If the canMove method returns true, the pawn's instance variables for
	*it's position are then incremented, which is determined by the direction in which the pawn
	* moves.
	*/
	public void move(String direction){
		if (this.canMove(direction)==true){
			switch (direction){
			case "North": positionY = positionY -1;
			setHasMovedThisTurn(true);
			break;
			case "West": positionX = positionX - 1;
			setHasMovedThisTurn(true);
			break;
			case "South": positionY = positionY + 1;
			setHasMovedThisTurn(true);
			break;
			case "East": positionX = positionX + 1;
			setHasMovedThisTurn(true);
			break;
			}
			
		}else{
			System.out.println("Path "+direction+" is blocked. Please choose another direction.");
		}
	}
	
	/**
	* @author Wiechec
	* @author Ken, Weijin 04-29 
	* @author Ken, Ian 04-30
	* This method takes in the direction the pawn is attempting to move, sends the information to the
	*board, which determines the location the pawn is on. It then checks the tile at that location,
	*verifies the boolean value set for the direction it wishes to go, then checks the target tile
	*to determine if it is accepting movement from that direction. If this method returns true, then 
	*and only then can the pawn move.
	*/
	public Boolean canMove(String direction){
		
		//Ian, Satya added this line 05-06-16
//		if(board.firstMove == true){return false;}
		
		Boolean current = board.getTileBool(positionX, positionY, direction);
		switch (direction){
		
		case "North": 
			if(positionY-1 == -1){return false;}
			next = board.getTileBool((positionX), positionY-1, "South");
			System.out.println("positionY:"+positionY);
			System.out.println("Current: "+current+"\nNext: "+next);
			return (current&&next);
		
		case "South": 
			if(positionY+1 == 7){return false;}
			next = board.getTileBool((positionX), positionY+1, "North");
			System.out.println("Current: "+current+"Next: "+next);
			return (current&&next);
		
		case "West": 
			if(positionX-1 == -1){return false;}
			next=board.getTileBool(positionX-1, (positionY), "East");
			System.out.println("Current: "+current+"Next: "+next);
			return (current&&next);
		
		case "East": 
			if(positionX+1 == 7){return false;}
			next = board.getTileBool(positionX+1, (positionY), "West");
			System.out.println("Current: "+current+"Next: "+next);
			return (current&&next);
		}
		return false;
		
	}
	
	/**
	 * @author Wiechec
	 * Method returns the boolean value of the next field.
	 * @return
	 */
	public Boolean getNext(){
		return next;
	}
	
	/**
	 * @author Wiechec
	 * This method will implement the wand actions for the pawn. The implementation of this method
	 * will be completed in the future.
	 */
	public void useWand(){
		if(!board.firstMove && wcount>0 && _usedWandThisTurn == false){
			wcount--;
			board.pawns[board.playerUp-1].startX = board.pawns[board.playerUp-1].getPositionX();
			board.pawns[board.playerUp-1].startY = board.pawns[board.playerUp-1].getPositionY();
			_usedWandThisTurn = true;
			_pickedUpTokenThisTurn = false;
			_hasMovedThisTurn = false;
			board.firstMove = true;
		}
	}
	
	/**
	 * @author Wiechec
	 * This method may be called to check the victory condition of the game on the pawn. As this
	 * implementation is not required at this stage, this method has not be completed.
	 */
	public void checkFormula(){
		
	}
	
	/**
	 * this method sets formular card
	 * @param fCard
	 */
	public void setFormulaCard(FormulaCard fCard){
		_fCard= fCard;
	}
	
	/**
	 * @author Wiechec
	 * Future method implementation for initial game creation. Pawn will get the formula in the future,
	 * once that portion of the code has been implemented.
	 * @return 
	 */
	public  FormulaCard getFormulaCard(){
		return _fCard;
	}
	
	/**
	 * @author Wiechec
	 * Future method stub for the player seeing what the victory condition actually is for their pawn.
	 */
	public void displayFormula(){
		
	}
	
	/**
	 * @author Wiechec
	 * Method stub, partially written, for the pawn being able to pick up the token at the position
	 * he is currently in. Implementation has not been finalized. Any code contained in the method
	 * at this time can be used only for a notation of a thought process for future implementation.
	 */
	private void getToken(Token token){
		if (token.getValue()==token.getPickUpCount()){
			
		}
		
	}
	
	/**
	 * @author Wiechec
	 * Method stub. Will, once completed, act as the boolean check to ensure that the token
	 * will be obtainable via the getToken method in combination with a data structure
	 * such as a hashmap for the token values and a method, such as currentAvailableToken, implemented
	 * in either the Board class or the Tile structures.
	 * @param token unused at this time.
	 */
	public Boolean ablePickup(Token token){
		
		return false;
	}
	
	/**
	 * @author Wiechec
	 * Method for future implementation, which will enable the player to see the current inventory 
	 * for his pawn object. Code currently contained is unfinished and can only be considered as
	 * noted ideas for the implementation of the code.
	 */
	public void inventory(){
		System.out.println("Number of wands: "+wcount);
		System.out.println("Number of tokens: " +numOfTokens);
		System.out.println("Tokens in possession: " ); 	
	}
	
	/**
	 * @author <jtmirfie>
	 * Runs after each pawn movement and checks if the move was valid. Following that it checks if a tile is
	 * in place and picks it up if that it is the next token in order.
	 */	
	public void pickUpToken(){
		if(validMove){
			if(board.getTile(positionX, positionY).hasToken){
				if(board.tokenCounter==board.getTile(positionX, positionY).getToken().getValue()){
					tokenCounts.add(board.getTile(positionX, positionY).getToken().getValue());
					board.getTile(positionX, positionY).hasToken=false;
					board.tokenCounter = board.tokenCounter + 1;
					if(board.tokenCounter == 21){
						board.tokenCounter=25;
					}
					if(!board.firstMove){
						_pickedUpTokenThisTurn = true;
						board.gameChanged();
					}
				}
			}
		}
	}
	
	/**
	 * @author <jtmirfie>
	 * @return accesses 'player' in order to know the name of the player using the pawn.
	 */	
	public String getPlayer(){
		return this.player;
	}
	
	/**
	 * this is getter for color 
	 * @return
	 */
	public String getColor(){
		return _color;
	}
	
	/**
	 * this is getter for wandcount
	 * @return
	 */
	public int getWandCount(){
		return wcount;
	}
	
   /**this method sets pick up token this turn 
   * @author satya, Weijin
   * @param b
   */
	public void setPickedUpTokenThisTurn(boolean b) {
		_pickedUpTokenThisTurn = b;	
	}
	
	/**this method sets get pick up token this turn
	 * @author satya, Weijin
	 * @return _pickedUpTokenThisTurn
	 */
	public boolean getPickedUpTokenThisTurn() {
		return _pickedUpTokenThisTurn;
	}
	
	/**this method set used wand this turn
	 * @author satya, Weijin
	 * @param b
	 */
	public void setUsedWandThisTurn(boolean b){
		_usedWandThisTurn = b;
	}
   
	/**this method sets get used wand this turn
	 * @author satya, Weijin
	 * @return _usedWandThisTurn
	 */
	public boolean getUsedWandThisTurn(){
		return _usedWandThisTurn;
	}
	
	/**
	 * this method sets has moved this turn
	 */
	public void setHasMovedThisTurn(boolean b){
		_hasMovedThisTurn = b;
	}
	
	/**
	 * this method sets get has moved this turn
	 */
	public boolean getHasMovedThisTurn(){
		return _hasMovedThisTurn;
	}
	
	/**
	 * sets the score at the end of game
	 * @return
	 * @Josh, Weijin 04-29-16
	 */
	public int getScore(){
		int playerscore=0;
		for(int i=0;i<tokenCount().size();i++){
			playerscore= playerscore +tokenCount().get(i);
		}
		int wand = getWandCount();
		playerscore= playerscore + wand*3;
		for(int k=0;k<_fCard.getValues().length;k++){
			int v1 = _fCard.getValues()[k];
			for(int j = 0;j<tokenCount().size();j++){
				if(v1 == tokenCount().get(j)){
					playerscore = playerscore + 20;
				}
			}
		}
		return playerscore;
	}
	
	/**this method saves the gmae to a string
	 * @author Ken, Weijin 04-29 
	 * @author Ken, Ian 04-30
	 */
	public void saveGame(){
		board.saveGame(this, "save.mls");
	}
	
	/**
	 * this method restore wand count 
	 * @param wands
	 */
	public void restoreWandCount(int wands){
		wcount = wands;
	}
	
	/**
	 * this method restore tokens
	 * @param tokens
	 */
	public void restoreTokens(ArrayList<Integer> tokens){
		tokenCounts = tokens;
	}
		
}
