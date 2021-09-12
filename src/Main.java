/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR RUNNING THE GAME AND BRIDING THE CONTROLLER WITH THE GAME VIEW

package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private Stage primaryStage;
	private Scene scene;
	private Pane roxieLayer = new Pane();
	private GameView gameView;
	private Controller player;
	private Integer room, lastUD = 0, nextUD = 0;
	private Double ROXIE_WIDTH = 571.0, GAME_HEIGHT = 800.0, GAME_WIDTH = 1050.0, xLeftLimit, xRightLimit, roxieXValue, roxieYValue, roxieResize, newRoxieWidth;
	private Boolean start = true, lastS = false, nextS = false;
	
	/* Numbering of the rooms, characters, and items
	 * 0 - Sandy Dune, Ace, Foam Roller
	 * 1 - Sandy Hill, Empty, Leaf
	 * 2 - Echoey Cave, Riff, Dice
	 * 3 - Riverside, Roxie, Nothing
	 * 4 - Flat Dirt, Parcheesi, Gloves
	 * 5 - Rocky Shale, Scala, Coffee
	 * 6 - Tall Forest, Honey, Soup
	 * 7 - Small Pond, Mr. E, Recipe Book
	 * 8 - Prairie, Jaz, Stick
	 * 9 - Flower Meadow, Juniper, Flowers
	 * 10 - Grassy Hill, Vignette, Rocks
	 * 11 - Swamp, Dash, Water
	 */
	
	@Override
	public void start(Stage primaryStage) { //the game starts here
		this.primaryStage = primaryStage;
		primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) primaryStage.setMaximized(false); //this makes it so that the screen size cannot be changed which would mess up the photos
		});
		room = 3; //we start on screen 3, which is the riverside (see above)
		gameView = new GameView(roxieLayer); //this sets up the gameview which holds all the visual components and the different elements (characters, items, backgrounds, highlights)
		scene = new Scene(gameView, GAME_WIDTH, GAME_HEIGHT); //the game view gets put into the scene so it will be displayed
		primaryStage.setHeight(GAME_HEIGHT); //the primary stage width and height gets adjusted here so it doesn't have to be later
		primaryStage.setWidth(GAME_WIDTH); 
		gameView.initFull(); //this sets up the characters, items, highlights, backgrounds, and all internal functions
		setScene(); //this is used to switch between screens and backgrounds and such
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setScene() {
		roxieYValue = 0.0; //a placeholder number
		
		gameView.setRoom(room); //sets up what screen the character is moving into
		xLeftLimit = gameView.getXLeftLimit(); //stops the player from going too far left
		xRightLimit = gameView.getXRightLimit(); //stops the player from going too far right
		roxieYValue = gameView.getNewRoxieY(); //places the player vertically
		roxieXValue = gameView.getNewRoxieX(); //places the player horizontally
		roxieResize = gameView.getResize(); //resizes the player in case the background requires they be smaller
		newRoxieWidth = ROXIE_WIDTH * roxieResize; //the new width is dependent on how much Roxie is resized
		gameView.setRoxie(roxieLayer); //then she gets put into the layer to be displayed
		
		try {
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //adds the CSS file
			primaryStage.setScene(scene); //sets the scene
			primaryStage.show(); //and now it's visible to the player
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		createPlayer();
		AnimationTimer riverRockRambleLoop = new AnimationTimer() { //this loop runs anything that requires player input such as walking, talking, selecting, and visibility (looking is player input)
			@Override
			public void handle(long now) {
				//***************************** MOVEMENT BETWEEN SCENES *****************************
				if(player.processWalk() && changeRooms() && !gameView.getDialogueVisibility() && !gameView.getInventoryVisibility() && !gameView.getItemAcquiredVisibility()) {
					this.stop(); //if the room gets swapped, this loop stops and will resume in the new scene
					setScene(); //this will set the room to the new one and get everything moving there
				}
				
				//***************************** INTERACTING WITH CHARACTERS *****************************
				nextS = player.processSpeak(); //if the button for speaking is pressed
				nextUD = player.processUpDown(); //if the button for inventory swapping is pressed
				if(gameView.getSpeechVisibility() && !lastS && nextS) { //if the speech bubble is visible and the talk button has only been pressed for the first time since being released
					if(!gameView.getItemAcquiredVisibility() && !gameView.openDialogue() && !gameView.getInventoryVisibility()) { 
						//this is meant to cycle through dialogue (openDialogue) and it will continue to do so until it runs out, then, if the inventory isn't open and no item is being acquired
						if(gameView.itemExchange()) { //it will check if there is any item exchange that needs to happen, meaning you're giving the character and item or they're giving you an item
							gameView.setInventoryVisibility(true); //if you're supposed to give the dinosaur an item, this will open the inventory
							gameView.fillInventory(); //this sets up the name of the object currently in your inventory
							gameView.openInventory(-1); //and this sets the highlight up so you know which inventory object you're selecting (the item or "goodbye")
						}
						else {
							gameView.setDialogueVisibility(false); //if no item exchange it meant to take place, the dialogue goes away
						}
					}
					else if(gameView.getInventoryVisibility()) { //if the inventory has been opened and you're pressed the talk button
						boolean select = gameView.getInventoryButtonPointer(); //it'll take whatever the current highlight is pointing to
						gameView.itemExchange(); /*then it gets what item you need to give this dinosaur (each has a different one, obviously). 
						*If you chose the item they need, you get the dialogue to thank you, if you chose the wrong object, you get the dialogue telling you it's wrong, 
						*and if you chose "Goodbye" it simply recycles the dialogue asking for an item.*/
						if(!select) { //if the chosen item was "Goodbye"
							gameView.setDialogueVisibility(false); //the dialogue box simply ends and you can continue on
						}
						else {
							gameView.openDialogue(); //if you gave the dinosaur an item though, it'll cycle through the next dialogue, whether that's the right or wrong one
						}
						gameView.setInventoryVisibility(false); //at the end of this, it closes the inventory because a choice has been made
					}
					else if(gameView.getItemAcquiredVisibility() && !gameView.getInventoryVisibility()) { //another thing that happens when you press the S button is that you can take acquired items
						//if a dinosuar gives you an item (ie the item acquire box is open and the inventory is not)
						gameView.toggleItemAcquiredVisibility(); //you can remove the notice using the S button
					}
				}
				//***************************** INVENTORY INTERACTION *****************************
				else if(lastUD == 0 && nextUD != 0 && gameView.getInventoryVisibility()) { //this is to let you swap between options in the inventory
					gameView.openInventory(nextUD); //it passes whichever way you moved the highlight to the method that deals with the highlights
				}
				lastUD = nextUD; //this puts the last result of the up and down buttons into the other variable to keep track of whether or not this is the first time the button is being pressed after being released
				lastS = nextS; //this is the same but for the S button
				//I do this so it doesn't read the button as being pressed several times in a row
				
				//***************************** PLAYER MOVEMENT PRIVLEGES *****************************
				if(gameView.getDialogueVisibility() || gameView.getInventoryVisibility() || gameView.getItemAcquiredVisibility()) { //if the dialogue, item acquire, or inventory are open
					player.stopMove(); //it's made sure that you can't move
				}
				else {
					player.startMove(); //once they're closed, you can once again move
				}
				
				//***************************** PLAYER/UI UPDATES *****************************
				player.processLeftRight(); //this checks whether Roxie is being moved left or right
				player.move(); //then it adds to her X value
				player.update(); //and updates her placement on the screen
				gameView.compareCoords(player.getXValue(), player.getXValue() + newRoxieWidth); //compareCoords is so we can tell if the highlights and speech bubbles should be visible
			}
		};
		riverRockRambleLoop.start(); //the loop starts here
	}
	
	public void createPlayer() { //this sets up the player for the screen
		Input input = new Input(scene); //for the player's input of keys
		input.addListeners(); //it listens for the keys to be pressed when the player is on screen
		Double xValue, yValue = GAME_HEIGHT * roxieYValue; //the y value can be deduced from the room that we're in
		
		if(start) { //x is dependent on where in the room you've come from though
			xValue = ((GAME_WIDTH - newRoxieWidth) / 2.0); //it uses the new width as well
			start = false;
		}
		else {
			xValue = (roxieXValue - newRoxieWidth / 2.0);
		}
		
		//this actually creates the player and sets it up to be read in the loop
		player = new Controller(roxieLayer, xValue, yValue, 0, input, xLeftLimit, xRightLimit, roxieResize);
	}
	
	private void removeFromLayer() { //this takes the previous Roxie out of hte screen
		player.removeFromLayer();
	}
	
	private Boolean changeRooms() { //this switches through the rooms to see if we need to switch
		int temp = gameView.changeRooms(); //it saves the value of hte change rooms function here
		if(temp != 13) { //as long as this room exists
			removeFromLayer(); //it removes Roxie from the screen
			room = temp; //saves the temp room as the new room
			return true; //and tells the loop to stop going around because the room is chanigng
		}
		return false; //otherwise the loop keeps going
	}
}
