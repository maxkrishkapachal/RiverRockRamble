/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR CREATING UNIQUE INSTANCES OF EVERY HIGHLIGHT WITH THE PURPOSE OF DIRECTING THE PLAYER ON WHERE TO GO AND ALLOWING THEM TO MOVE FROM SCREEN TO SCREEN

package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HighlightNode {
	private Integer limit, roomFrom, roomTo;
	private Double newRoxieX;
	private Boolean visibility = false;
	private ImageView highlight;
	
	public HighlightNode(int roomFrom, int roomTo, int limit, double newRoxieX, String hlFileName) {
		this.roomFrom = roomFrom;
		this.roomTo = roomTo;
		this.limit = limit;
		this.newRoxieX = newRoxieX;
		
		highlight = new ImageView(new Image(hlFileName)); //sets up the highlight image
		highlight.setFitHeight(800.0); //sets the height
		highlight.setFitWidth(1050.0); //and the width of hte images
		setVisibility(visibility); //then makes them not visible for now
	}
	
	public void setVisibility(boolean visibility) { //this sets whether the highlight is visible or not
		this.visibility = visibility;
		highlight.setVisible(this.visibility);
	}
	
	public Boolean getVisibility() { //this returns wehther it's visible or not
		return visibility;
	}
	
	public ImageView getHighlight() { //this returns the image of the highlight
		return highlight;
	}
	
	public Integer getRoomFrom() { //this reutrns what room this highlight is found in
		return roomFrom;
	}
	
	public Integer getRoomTo() { //this returns what room this highlight leads to
		return roomTo;
	}
	
	public Double getNewRoxieX() { //this returns the new x value for Roxie when you move through the door
		return newRoxieX;
	}
	
	public Integer getLimit() { //this is the limit for when the highlight will be visible
		return limit;
	}

}
