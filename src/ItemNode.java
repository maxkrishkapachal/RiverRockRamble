/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR CREATING UNIQUE INSTANCES OF EVERY ITEM

package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemNode {
	private String name, description;
	private Integer room;
	private Boolean visibility;
	private Double xValue, yValue, width, height;
	private ImageView item;
	
	public ItemNode(String name, int room, double xValue, double yValue, double width, double height, String itemFileName, String description) {
		this.name = name;
		this.room = room;
		this.description = description;
		this.width = width;
		this.height = height;
		this.visibility = true;
		
		item = new ImageView(new Image(itemFileName, this.width, this.height, true, false)); //sets the image item
		relocateItem(xValue, yValue); //puts the image in the right spot
		
		toggleVisibility(); //turns the visibility off
	}
	
	public void toggleVisibility() { //for swapping the image visibility
		visibility = !visibility;
		item.setVisible(this.visibility);
	}
	
	public Boolean getVisibility() { //returns the item visibility
		return visibility;
	}
	
	public void relocateItem(double xValue, double yValue) { //moves the item into its spot
		this.xValue = xValue; //it also sets the x and y values
		this.yValue = yValue;
		item.relocate(this.xValue, this.yValue);
	}
	
	public String getDescription() { //returns the item description
		return description;
	}
	
	public ImageView getItem() { //returns the item image
		return item;
	}
	
	public String getName() { //returns the name of hte item
		return name;
	}
	
	public void setRoom(int room) { //sets what room the item is in
		this.room = room;
	}
	
	public Integer getRoom() { //returns the room that the item is in
		return room;
	}
}
