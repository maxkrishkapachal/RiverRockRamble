/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR CREATING UNIQUE INSTANCES OF EVERY CHARACTER

package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.HashMap;

public class CharacterNode {
	private String name; //the name of the dinosaur character
	private Integer room, scriptSelect = 0; //to find what room the dino is and what script line is being used
	private Double xValue, width, height, limit, WIDTH_SPEECH = 177.0, HEIGHT_SPEECH = 185.0, MULTIPLIER = 0.3; //the x location, y location, width, height, and size of the speech bubble
	private Boolean visibility = false, speechVisibility = false;
	//private Image charaLeftImage, charaRightImage;
	private ImageView character = new ImageView(); //the image of the character
	private ImageView speech = new ImageView(new Image("images/symbols/SpeechBubbleSprite.png", WIDTH_SPEECH * MULTIPLIER, HEIGHT_SPEECH * MULTIPLIER, true, false)); //the set up of the speech bubble
	private ArrayList<ArrayList<String>> script = new ArrayList<ArrayList<String>>(); //the script lines are stored here 
	private ItemNode itemNeed, itemGive;

	public CharacterNode(String name, int room, double xValue, double yValue, double width, double height, String scriptFileName, String picFileName, ScriptReader scriptReader) {
		this.name = name;
		this.room = room;
		this.xValue = xValue;
		this.width = width;
		this.height = height;
		this.limit = (this.width / 2) + this.xValue;
		
		
		scriptReader.setScriptFile(scriptFileName); //this is for the script 
		try {
			for(int i = 0; i < 5; i++) { //it puts each set of lines into their own array, which is also an array of inner lines
				ArrayList<String> temp = scriptReader.readFile(i + ""); //the script reader parses that for me
				if(!temp.isEmpty()) { //as long as the line isn't empty
					script.add(temp); //it adds the line to the array 
				}
			}
		} catch(Exception e) {
		}
		
		character = new ImageView(new Image(picFileName, this.width, this.height, true, false)); //this makes the image for each character
		
		character.relocate(xValue, yValue); //then puts the character into spot
		speech.relocate(((xValue + (width / 2)) - ((WIDTH_SPEECH * MULTIPLIER) / 2)), yValue - 80); //and relocates the speech bubble to be above the character's head
		
		setVisibility(); //it sets the visibility to off to start
		setSpeechVisibility(speechVisibility); //and turns the speech bubble off too
	}
	
	public void setVisibility() { //sets whether the dinosaur is visible
		character.setVisible(this.visibility);
	}
	
	public void toggleVisibility() { //toggles the character's visibility
		visibility = !visibility;
		setVisibility();
	}
	
	public void setSpeechVisibility(boolean speechVisibility) { //this is for setting the visibility of hte speech bubble
		this.speechVisibility = speechVisibility;
		speech.setVisible(this.speechVisibility);
	}
	
	public Boolean getSpeechVisibility() { //returns the speech bubble's visibility 
		return speechVisibility;
	}
	
	public ImageView getCharacter() { //returns the actual image of the character
		return character;
	}
	
	public ImageView getSpeechBubble() { //returns the image of hte speech bubble
		return speech;
	}
	
	public String getName() { //returns the name of the character
		return name;
	}
	
	public Integer getRoom() { //returns the room that the character belongs to
		return room;
	}
	
	public Double getLimit() { //returns the limit for the dinosaur to check when the speech bubble should be visible
		return limit;
	}
	
	public String getScript(int i) { //returns the specific script line that is needed
		String toReturn = script.get(scriptSelect).get(i);
		return toReturn;
	}
	
	public Integer getScriptSelect() { //returns the script section
		return scriptSelect;
	}
	
	public void setScriptSelect(int scriptSelect) { //returns number of script that we're on
		this.scriptSelect = scriptSelect;
	}
	
	public void addItems(ItemNode itemNeed, ItemNode itemGive) { //sets up the items that will be needed and given for each character
		this.itemNeed = itemNeed;
		this.itemGive = itemGive;
	}
	
	public ItemNode getItemNeed() { //returns the item the character needs
		return itemNeed;
	}
	
	public ItemNode getItemGive() { //returns the item the character gives
		return itemGive;
	}
}
