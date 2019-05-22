package code.Model;
	
	/**
	 * @author Wiechec
	 * @author Satya,Ian
	 */
public class FormulaCard {
	
	/**
	 * this instance variable for array value
	 */
	private int[] values;
	
	/**
	 * class constructor 
	 * @param v0
	 * @param v1
	 * @param v2
	 */
	
	public FormulaCard(int v0, int v1, int v2){
		values = new int[3];
		values[0] = v0 ; values[1] = v1; values[2] = v2;
	}
	
	/**
	 * this method returns every value
	 * @return
	 */
	public int[] getValues(){
		return values;
	}
	
}
