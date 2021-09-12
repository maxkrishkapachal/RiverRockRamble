/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR CONTROLLING ROXIE AND READING USER INPUT

package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Controller {
	private Image RoxieLeftImage, RoxieRightImage;
	private ImageView RoxieView;
	private Pane layer;
	private Double xValue, yValue, tempStep, xLeftLimit, xRightLimit, STEP = 4.5, step, ROXIE_WIDTH = 571.0, ROXIE_HEIGHT = 261.0;
	private Boolean canMove = true;
	private Input input;
	
	public Controller(Pane layer, double xValue, double yValue, double tempStep, Input input, double xLeftLimit, double xRightLimit, double resize) {
		this.layer = layer;
		this.xValue = xValue;
		this.yValue = yValue;
		this.tempStep = tempStep;
		this.input = input;
		step = STEP + resize;
		
		RoxieLeftImage = new Image("images/characters/RoxieLeftSprite.png", ROXIE_WIDTH * resize, ROXIE_HEIGHT * resize, true, false); //these are the images for when Roxie turns 
		RoxieRightImage = new Image("images/characters/RoxieRightSprite.png", ROXIE_WIDTH * resize, ROXIE_HEIGHT * resize, true, false); //one for both ways you can face
		
		this.RoxieView = new ImageView(RoxieLeftImage); //it sets the left one up automatically 
		this.RoxieView.relocate(this.xValue, this.yValue); //then sets Roxie into her spot
		
		this.xLeftLimit = xLeftLimit; //the limit on the left side of hte screen
		this.xRightLimit = xRightLimit - RoxieLeftImage.getWidth(); //the limit on the right side of the screen
		
		addToLayer(); //then into the layer Roxie goes
	}
	
	public void processLeftRight() { //reads the left and right movement
		removeFromLayer(); //it takes Roxie out of hte thing first
		
		if(input.isLeft()) { //if the player moves left
			tempStep = -step; //step that way
			RoxieView = new ImageView(RoxieLeftImage); //and set the image
		}
		else if(input.isRight()) { //if the player moves right
			tempStep = step; //step that way
			RoxieView = new ImageView(RoxieRightImage); //and set the right image
		}
		else { //if there's no movement
			tempStep = 0.0; //we go nowhere
		}
		addToLayer(); //and add Roxie to the layer again
	}
	
	public Integer processUpDown() { //this reads if the user is moving up or down in the inventory
		if(input.isUp()) { //the numbers tell which way to move the highlight
			return 1;
		}
		else if(input.isDown()) {
			return -1;
		}
		return 0;
	}
	
	public Boolean processSpeak() { //this reads if hte user is trying to talk to the dinosaurs
		if(input.isSpeak()) { //if they are
			return true; //it returns true
		}
		return false;
	}
	
	public Boolean processWalk() { //this is for reading if the user is going through a door to another screen
		if(input.isWalk()) {
			return true;
		}
		return false;
	}
	
	public void move() { //this reads the user's movements (if they can move)
		if(!canMove) {
			return;
		}
		xValue += tempStep; //as long as they're able to move, it adds to the xvalue of Roxie
		boundaries(); //and checks that it's within the bounds
	}
	
	public void boundaries() { //the bounds are based on the screen limits
		if(Double.compare(xValue, xLeftLimit) < 0) { //if the value is beyond the left screen limit
			xValue = xLeftLimit; //make the value the limit
		}
		else if(Double.compare(xValue, xRightLimit) > 0) { //if the value is beyond the right screen limit
			xValue = xRightLimit; //make the value the limit
		}
	}
	
	public void addToLayer() { //adds roxie to the layer
		this.layer.getChildren().add(this.RoxieView);
	}
	
	public void removeFromLayer() { //removes roxie from the layer
		this.layer.getChildren().remove(this.RoxieView);
	}
	
	public void update() { //updates the location of Roxie
		RoxieView.relocate(xValue, yValue);
	}
	
	public void stopMove() { //makes Roxie unable to move
		canMove = false;
	}
	
	public void startMove() { //makes Roxie able to move
		canMove = true;
	}
	
	public Double getXValue() { //returns the xValue for Roxie
		return xValue;
	}
}
