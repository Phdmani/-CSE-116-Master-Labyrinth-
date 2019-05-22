package code.tiles;

import code.tokens.Token;

/**
 * Class is the parent class for all the Tile classes in the game. File is fully defined with the exception of
 * the rotation method, which is unique to each tile and must be written by each tile. Class is
 * abstract so that it cannot be called.
 * @author Wiechec
 *
 */
public abstract class Tiles {
	
	/**
	 * strin of valid directions 
	 */
	public static String[] validOrientations = {"North", "South", "East", "West"};
	
	/**this is a string 
	 * @author Weijin, Ken 04-29
	 */
	protected String _identity;
	
	/**
	 * this is a string for rotation 
	 */
	public String _rotation;
	
	/**
	 * @author Wiechec
	 * _staticVert: Field for flag indicating whether or not the specific tile can be move vertically.
	 * Field is off as false and on to prevent movement as True.
	 */
	public Boolean _staticVert = false;
	
	/**
	 * @author Wiechec
	 *  _staticHoriz: Field for flag indicating whether or not the specific tile can be move horizontally.
	 * Field is off as false and on to prevent movement as True.
	 */
	public Boolean _staticHoriz = false;
	
	/**
	 * @author Wiechec
	 * _north: Field storing whether or not the tile allows movement in the north direction.
	 */

	public Boolean _north = false;
	
	/**
	 * @author Wiechec
	 *  _east: Field storing whether or not the tile allows movement in the east direction.
	 */
	
	public Boolean _east = false;
	
	/**
	 * @author Wiechec
	 * _south: Field storing whether or not the tile allows movement in the south direction.
	 */
	public Boolean _south = false;
	
	/**
	 * @author Wiechec
	 * _west: Field storing whether or not the tile allows movement in the west direction.
	 */
	public Boolean _west = false;
	
	/**
	 * @author Wiechec
	 *_permanency: Field for checking if the tile piece is a permanent tile.
	 */
	public Boolean _permanency=false;
	
	/**
	 * this is the token 
	 */
	public Token token;
	
	/**
	 * this sets the value of has token to be false
	 */
	public Boolean hasToken = false;
	
	/**
	 * this sets the value of player to be false
	 */
	public Boolean player = false;
	
	/**
	 * this sets the value of is start to be false
	 */
	public boolean isStart = false;

	/**
	 * @author Wiechec
	 * method getRotation();
	 * returns the current rotation of the object.
	 * @return: Current rotation of the object.
	 */
	public String getRotation(){
		return _rotation;
	}
	

	/**
	 * @author Wiechec
	 * method Rotate();
	 * abstract method stub. Method is used to rotate the directional fields on the tiles.
	 * code is the responsibility of the subclasses.
	 * @return: no return.
	 */
	public abstract void Rotate(String direction);

	/**
	 * @author Wiechec
	 * Method turns on or off the flag indicator for whether or not the tile can be moved 
	 * north or south. Method first checks if the tile's field has _permanency on, which would
	 * indicate the tile is a permanent structure. Method returns println string if _permanency
	 * is true. else statement sets the _staticVert to parameter value.
	 * @param freeze		Boolean value for setting _staticVert field to true or false.
	 */
	public void setVert(Boolean freeze){
		if (_permanency = true){
			System.out.println("I'm sorry, that's a permanent structure");
		}
		else{
			_staticVert = freeze;
		}
	}
	
	/**
	 * @author Wiechec
	 * Method turns on or off the flag indicator for whether or not the tile can be moved 
	 * east or west. Method first checks if the tile's field has _permanency on, which would
	 * indicate the tile is a permanent structure. Method returns println string if _permanency
	 * is true. else statement sets the _staticVert to parameter value.
	 * @param freeze		Boolean value for setting _staticVert field to true or false.
	 */
	public void setHoriz(Boolean freeze){
		if (_permanency = true){
			System.out.println("I'm sorry, that's a permanent structure");
		}
		else{
			_staticHoriz = freeze;
		}
	}
	
	/**
	 * @author Wiechec
	 * Method returns the value of the tile's _staticVert field.
	 * @return _staticVert field value
	 */
	public Boolean getVert(){
		return _staticVert;
		
	}
	
	/**
	 * @author Wiechec
	 * Method returns the value of the tile's _staticHoriz field.
	 * @return _staticHoriz field value
	 */
	public Boolean getHoriz(){
		return _staticHoriz;
	}
	
	/**
	 * @author Wiechec
	 * This method sets a tile to indicate a permanent structure. _staticVert and _staticHoriz fields
	 * also set to the same value as a matter of preventing any runtime errors.
	 * @param value: Field for setting _permanency, _staticVert, and _staticHoriz fields with one method.
	 */
	public void isPermanent(Boolean value){
		_permanency = value;
		_staticVert = value;
		_staticHoriz = value;

	}
	
	/**
	 * @author Wiechec
	 * Method returns the boolean values of the tile for the indicated direction of the parameter.
	 * Method returns false as a default if an incorrect value is entered into parameter.
	 * @param dir is the direction being checked
	 * @return the value of the direction being checked.
	 */
	public Boolean getDirection(String dir){
		switch(dir){
		case "North":
			return _north;
		case "South":
			return _south;
		case "East":
			return _east;
		case "West":
			return _west;
		}
		return false;
		
	}
	
	/**
	 * this method sets the token 
	 * @param _token
	 */
	public void setToken(Token _token){
		token = _token;
	}
	
	/**
	 * getter for token
	 * @return
	 */
	public Token getToken(){
		return token;
	}
	
	/**
	 * this method checks has token 
	 * @return
	 */
	public boolean checkToken(){
		return hasToken;
	}
	
	/**
	 * this method makes token null 
	 * @return temp
	 */
	public Token pickUpToken(){
		Token temp=token;
		token=null;
		return temp;
	}
	
	/**
	 * this method sets the value of is start to be true
	 */
	public void startTile(){
		isStart=true;
	}
	
	/**
	 *  this method returns is start
	 * @return
	 */
	public boolean isStart(){
		return isStart;
	}
	
	/**
	 * getter for identity 
	 * @return
	 * @author Weijin, Ken 04-29
	 */
	public String getIdentity(){
		return _identity;
	}
	
	/**
	 * getter for tile orientation 
	 * @return
	 */
	public String getOrientation(){
		String ans = "";
		if(_identity == "L"){
			if(_rotation == "North"){
				return "3";
			}
			else if(_rotation == "South"){
				return "1";
			}
			else if(_rotation == "East"){
				return "0";
			}
			else if(_rotation == "West"){
				return "2";
			}
		}
		else if(_identity == "T"){
			if(_rotation == "North"){
				return "2";
			}
			else if(_rotation == "South"){
				return "0";
			}
			else if(_rotation == "East"){
				return "3";
			}
			else if(_rotation == "West"){
				return "1";
			}	
		}
		else if(_identity == "I"){
			if(_rotation == "North"){
				return "0";
			}
			else if(_rotation == "South"){
				return "0";
			}
			else if(_rotation == "East"){
				return "1";
			}
			else if(_rotation == "West"){
				return "1";
			}
		}
		return ans;
	}

	/**
	 * this method set has token 
	 * @param b
	 */
	protected void setHasToken(boolean b) {
		hasToken = b;
	}

}


