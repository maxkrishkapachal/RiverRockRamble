/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR READING THE SCRIPT LINES FROM THE TEXT FILES

package application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ScriptReader {
	public String scriptFileName;
	public ArrayList<String> lines;
	public Integer iterate;
	
	public ArrayList<String> readFile(String scriptSection) {
		try {
			lines = new ArrayList<String>(); //gets the array list for the lines ready
			InputStream inputStream = Main.class.getResourceAsStream(scriptFileName); 
			InputStreamReader inputReader = new InputStreamReader(inputStream);
			BufferedReader bufferR = new BufferedReader(inputReader);
			String currLine; //this is where the current line gets put
			
			do {
				currLine = bufferR.readLine(); //as long as the line doesn't equal a number for the script section, it'll keep going through the lines
				
			} while(!currLine.equals(scriptSection));
			
			currLine = bufferR.readLine(); //then it'll read the next line afterwards
			
			do {
				String cutLine = currLine.substring(2); //and it cuts the dashes off the begining of the line
				lines.add(cutLine);	 //puts them in the arraylist
				currLine = bufferR.readLine(); //and reads the next line
				
			} while(currLine != null && !currLine.equals("\n") && !currLine.equals("")); //which will continue until the line script lines end
			
			inputReader.close(); //then closes the input reader
		}
		catch(Exception e) {
		}
		return lines; //and returns the script array
	}
	
	public void setScriptFile(String scriptFileName) { //this just sets what the file name is 
		this.scriptFileName = scriptFileName;
	}

}
