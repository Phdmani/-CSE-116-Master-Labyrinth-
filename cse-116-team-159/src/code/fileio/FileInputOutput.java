package code.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class provides the ability to write to or read from a file, line by line.
 * Citation: This class is modeled after Dr. Carl Alphonce's (U. Buffalo) 
 * FileIO class provided by instructor for course Introduction to Computer Science 
 * for Majors I (CSE115) for Lab #9, given during Fall semester 2015 at Univ. at Buffalo.
 *@author Weijin, Ken 04-29
 *@author Ken, Ian 04-30
 */
public class FileInputOutput {

	/**
	 * This method writes the Strings in String[] array lines to the file indicated by path.
	 * If the file doesn't exist, it's created
	 * If the file does exist, it is overwritten
	 * Citation: This method is modeled after Dr. Carl Alphonce's (U. Buffalo) 
	 * 		writeStringToFile method defined in FileIO class provided by instructor for 
	 * 		course Introduction to Computer Science for Majors I (CSE115) for Lab #9,
	 * 		given during Fall semester 2015 at Univ. at Buffalo.
	 * @param path - the name of the file to write to
	 * @param lines - the Strings (lines of text) to be written to the file
	 * @return true if writing the file was successful, else false
	 * @author Weijin, Ken 04-29
	 * @author Ken, Ian 04-30
	 */
	public static Boolean writeStringArrayToFile(String path, String[] lines) {
		PrintStream s = null;
		Boolean ans = false;
		try {
			s = new PrintStream(path);
			s.format("%s%n",lines[0]);
			s.format("%s%n",lines[1]);
			s.format("%s%n",lines[2]);
			ans = true;
		} catch (FileNotFoundException e) {
			System.out.println("File wasn't found and can't be created: "+path);
		}
		finally {
			s.close();
		}
		return ans;
	}
	
	/**
	 * This method reads from the file indicated by path and returns the contents as a 
	 * String array, where each element of the array is a line of text from the file.
	 * Citation: This method is modeled after Dr. Carl Alphonce's (U. Buffalo) 
	 * 		readFileToString method defined in FileIO class provided by instructor for 
	 * 		course Introduction to Computer Science for Majors I (CSE115) for Lab #9,
	 * 		given during Fall semester 2015 at Univ. at Buffalo.
	 * @param path
	 * @return
	 * @author Satya, Josh 04-30
	 */
	public static String[] readFileToStringArray(String path){
		String[] s = new String[3];
		s[0] = "";
		s[1] = "";
		s[2] = "";
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(path));
		} catch(FileNotFoundException e){
			System.out.println("The file not found: " + path);
		}
		try{
			s[0] = scanner.nextLine();
			s[1] = scanner.nextLine();
			s[2] = scanner.nextLine();
		} catch(NoSuchElementException e){
			System.out.println("The save file has not been correctly encoded!");
		}
		finally{
			if(scanner!=null){
				scanner.close();
			}
		}

		return s;
	}
}
