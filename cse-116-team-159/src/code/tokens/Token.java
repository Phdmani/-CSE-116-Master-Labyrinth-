package code.tokens;

/**
 * this is a token class
 * @author 
 *
 */
public class Token {
	
	/**@author bdlipp cwiechec
	 * @field tokenName = name of token
	 */
	String tokenName=null;
	
	/**
	 *  @field tokenValue = sets the value of the token.
	 */
	int tokenValue=0;
	
	/**
	  * @field setCount = used in token creation. Ensures everytime a new token in instantiated that the next token is created. 
	 */
	public static int setCount=1;
	
	/**
	  * @field pickUpCount = variable used to control which token can be picked up.  
	 */
	public static int pickUpCount=1;
	//unfinish var. names the owner of this token. need association relationship setup.
	
	/**
	 * 
	 */
	String owner ="";
	
	/**
	 * @author bdlipp cwiechec
	 * Creates a token, using a helper method to set the string name of the token. Also increments setCount so the next token is created. 
	 * 
	 */
	public Token(){
		this.tokenName(setCount);
		setCount++;
	}
	/**
	 * @author bdlipp cwiechec
	 * @param tokenNum sets the tokenName of the token that's instantiated. Also sets that token's tokenValue to be equal to the
	 * tokenValue of when it was called. cwiechec contributed to Mistletoe token value and final else statement only.
	 */
	
	public void tokenName(int tokenNum){
		tokenValue=tokenNum;
		
		if(tokenValue==1){
			tokenName="Crab Apples";
		}
		else if(tokenValue==2){
			tokenName="Pine Cone";
		}
		else if(tokenValue==3){
			tokenName="Oak Leaves";
		}
		else if(tokenValue==4){
			tokenName="Slug Oil";
		}
		else if(tokenValue==5){
			tokenName="Four Leaf Clover";
		}
		else if(tokenValue==6){
			tokenName="Garlic";
		}
		else if(tokenValue==7){
			tokenName="Ravens Feather";
		}
		else if(tokenValue==8){
			tokenName="Henbane";
		}
		else if(tokenValue==9){
			tokenName="Spiders";
		}
		else if(tokenValue==10){
			tokenName="Skull Moss";
		}
		else if(tokenValue==11){
			tokenName="Blindworm";
		}
		else if(tokenValue==12){
			tokenName="Quartz Crystal";
		}
		else if(tokenValue==13){
			tokenName="Toads";
		}
		else if(tokenValue==14){
			tokenName="Fire Salamandars";
		}
		else if(tokenValue==15){
			tokenName="Weasle Spit";
		}
		else if(tokenValue==16){
			tokenName="Silver Thistle";
		}
		else if(tokenValue==17){
			tokenName="Snake";
		}
		else if(tokenValue==18){
			tokenName="Emerarlds";
		}
		else if(tokenValue==19){
			tokenName="Mandrake Root";
		}
		else if(tokenValue==20){
			tokenName="Basilisk";
		}
		else if(tokenValue==21){
			tokenName="Mistletoe";
			tokenValue=25;
		}
		else{
			System.out.println("You've run out of tokens!");
		}
	}
	
	/**getter for token name
	 * @author bdlipp
	 * @returns token name
	 */
	public String getName(){
		return tokenName;
	}
	
	/**getter for token value
	 * @author bdlipp
	 * @return value of the token.
	 */
	public int getValue(){
		return tokenValue;
	}
	
	/**
	 * this method set the token value
	 * @param change
	 */
	public void setValue(int change){
		tokenValue = change;
	}
	
	/**
	 * this method counts the value of tokens
	 * @return
	 */
	public int getPickUpCount(){
		return pickUpCount;
	}
	
	/**
	 * this method increases pick up by 1 
	 */
//Unfinished method for picking up the token.
	public void pickUp(){
		
		pickUpCount = pickUpCount +1;
		
	}
	
}
