/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR CREATING UNIQUE INSTANCES OF EVERY ROOM

package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoomNode {
	private Integer roomNum; //the number of the room
	private ImageView background; //this is hte background image
	private Boolean visibility = false; //we're just gonna toggle the visibility of them all
	private Double xLeftLimit, xRightLimit, newRoxieY, roxieResize; //how far left you can move, how far right you can move, where on hte page you are on the y, and how big you are
	
	public RoomNode(int roomNum, double newRoxieY, double roxieResize, double xLeftLimit, double xRightLimit, String backFileName) {
		this.roomNum = roomNum;
		this.newRoxieY = newRoxieY;
		this.roxieResize = roxieResize;
		this.xLeftLimit = xLeftLimit;
		this.xRightLimit = xRightLimit;
		
		background = new ImageView(new Image(backFileName)); //sets the background image for this room
		background.setFitHeight(800.0); //and sets the size of the photo
		background.setFitWidth(1050.0);
		
		setVisibility(); //then turns the visibility off
	}
	
	public void setVisibility() { //this is the method that it gets turned off through
		background.setVisible(visibility);
	}
	
	public void toggleVisibility() { //this toggles it for when you change rooms
		visibility = !visibility;
		setVisibility();
	}
	
	public ImageView getBackground() { //this returns the image for the background
		return background;
	}
	
	public Double getNewRoxieY() { //this returns the y value for this room
		return newRoxieY;
	}
	
	public Double getXLeftLimit() { //returns the left side of hte screen limit
		return xLeftLimit;
	}
	
	public Double getXRightLimit() { //returns the right side of the screen limit
		return xRightLimit;
	}
	
	public Double getResize() { //returns the resizing info for Roxie
		return roxieResize;
	}

}
