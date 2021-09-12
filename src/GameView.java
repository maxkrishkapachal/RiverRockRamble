/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR ALL OF THE VISUAL ELEMENTS IN THE GAME AND STORING ALL THE INSTANCES OF EACH ITEM

package application;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

public class GameView extends StackPane{
	//formatting and other classes
	private StackPane stack = new StackPane(); //this is where all the screen elements are 
	private Pane charaPane = new Pane(); //for placing all the characters
	private Pane Roxie; //this is the layer that Roxie is on
	
	//everything here is for dialogue
	private Pane dialoguePane = new Pane(); //this is the layer where the dialogue is
	private Label dialogueBox = new Label(); //the box for hte actual dialogue
	private Label dialogueName = new Label(); //the name of the dinosaur speaking
	private Integer dialoguePointer = 0; //what line of dialogue is being printed
	private Boolean dialogueVisibility; //whether the dialogue is visible or not
	
	//this is the stuff for the first screen and then once you've won
	private Pane tutWinPane = new Pane(); //the layer where the tutorial and winning box is 
	private Region tutWinBack = new Region(); //the background for it, just for aesthetics
	private Label tutWinWords = new Label(); //whatever is written in the tutorial/winning book
	private Boolean tutWinVisibility = false; //whether the tutWin box is visible or not
	
	//this is all for the inventory and item exchanging
	private Pane itemPane = new Pane(); //for placing all the items
	//this is regular inventory
	private Pane inventoryPane = new Pane(); //the layer where the inventory is stored
	private Region inventoryBack = new Region(); //the back for the inventory
	private VBox inventoryButtons = new VBox(); //used to format the inventory buttons
	private Label inventoryItem = new Label(); //the inventory button that's used for the item
	private Label inventoryNothing = new Label(); //the inventory button that says "Goodbye."
	private Boolean inventoryButtonPointer = false; //which button is being pointed to
	private Integer selectedItemMatch = 1; //which line of dialogue the script goes to 
	private Boolean inventoryVisibility = false; //whether the inventory is visible or not
	private ItemNode itemInInven; //which item is in the inventory
	//this is for when you acquire a new item
	private Pane itemAcquiredPane = new Pane(); //the layer where the item acquired notice is stored
	private Region itemAcquiredBack = new Region(); //the backof hte item acquired notice
	private VBox itemAcquiredNoticeBox = new VBox(); //the box where the item name and description are stored
	private Label itemAcquiredName = new Label(); //the name of the item that gets acquired
	private Label itemAcquiredDescription = new Label(); //the description for the item that gets acquired
	private Boolean itemAcquiredVisibility = true; //whether the item acquired is visible or not
	
	//various numbers
	private Integer rooms = 3; //what rooms we're in, that's what I'm calling them even though we're outdoors
	private Boolean initial = true; //used to set up the toggle for what backgrounds are visible
	
	//the previous highlight
	private HighlightNode prevHigh; //the previous highlight and it dictates where the player comes out on the other screen
	
	//constants for the number of rooms, highlights, characters, and items
	private Integer ROOM_COUNT = 12, HIGHLIGHT_COUNT = 25, CHARACTER_COUNT = 10, ITEM_COUNT = 13;
	//the counts for the rooms, highlights, characters, and items
	
	//constants for the value of each room. the names make it easier on my human brain
	private Integer SANDY_DUNE = 0;
	private Integer SANDY_HILL = 1;
	private Integer CAVE = 2;
	private Integer RIVERSIDE = 3;
	private Integer FLAT_DIRT = 4;
	private Integer ROCKY_SHALE = 5;
	private Integer FOREST = 6;
	private Integer POND = 7;
	private Integer PRAIRIE = 8;
	private Integer FLOWER_MEADOW = 9;
	private Integer GRASSY_HILL = 10;
	private Integer SWAMP = 11;
	
	//the lists for different rooms, characers, items, and highlights
	private RoomNode allRooms[] = new RoomNode[ROOM_COUNT]; //stores all the nodes for the rooms
	private HighlightNode allHighs[] = new HighlightNode[HIGHLIGHT_COUNT]; //stores all the nodes for the highlights
	private CharacterNode allCharas[] = new CharacterNode[CHARACTER_COUNT]; //stores all the nodes for the characters
	private ItemNode allItems[] = new ItemNode[ITEM_COUNT]; //stores all the nodes for the items
	
	public GameView(Pane Roxie) {
		this.Roxie = Roxie; //the layer with Roxie in it
		this.getChildren().add(stack); //this sends the stack back so it doesn't need to be resent again
	}
	
	//***************************** ROXIE FUNCTIONS *****************************
	public void setRoxie(Pane Roxie) {
		this.Roxie = Roxie; //this is for every time roxie moves, it needs to be reset
	}
	
	public Double getNewRoxieY() {
		return allRooms[rooms].getNewRoxieY(); //this is for getting the new placement of Roxie 
	}
	
	public Double getNewRoxieX() {
		return prevHigh.getNewRoxieX(); //gets the value of the xvalue for hte next screen from the previous highlight location
	}
	
	//***************************** INITIALIZATION OF THE GAME ELEMENTS *****************************
	public void initFull() { //initializes all the different elements
		initBackgrounds(); //the rooms and their background pictures
		initHighlights(); //the arrows that indicate where you can move
		initItems(); //the items that get moved around
		initCharacters(); //the other dinosaurs
		itemInInven = allItems[10]; //the item taht is currently in the inventory when you start the game
		stack.getChildren().add(this.Roxie); //sets up roxie in the full layer
		initDialogue(); //the dialogue box
		initInventory(); //the inventory selection
		initItemAcquiredNotice(); //the notice for when you get a new item
		initTutWin(); //the tutorial and win screen
		prevHigh = allHighs[24]; //and then it inits the mini highlight that I made purely to set up the starting screen
	}
	
	private void initBackgrounds() { //this goes through the backgrounds
		RoomNode room = new RoomNode(SANDY_DUNE, 0.7, 0.5, 0.0, 1050.0, "images/backgrounds/SandyDune.jpg");
		allRooms[SANDY_DUNE] = room;
		room = new RoomNode(SANDY_HILL, 0.78, 0.3, 100.0, 800.0, "images/backgrounds/SandyHill.jpg");
		allRooms[SANDY_HILL] = room;
		room = new RoomNode(CAVE, 0.8, 0.5, 0.0, 1050.0, "images/backgrounds/EchoeyCave.jpg");
		allRooms[CAVE] = room;
		room = new RoomNode(RIVERSIDE, 0.7, 0.5, 0.0, 1050.0, "images/backgrounds/Riverside.jpg");
		allRooms[RIVERSIDE] = room;
		room = new RoomNode(FLAT_DIRT, 0.73, 0.3, 0.0, 1050.0, "images/backgrounds/FlatDirt.jpg");
		allRooms[FLAT_DIRT] = room;
		room = new RoomNode(ROCKY_SHALE, 0.8, 0.5, 0.0, 1050.0, "images/backgrounds/RockyShale.jpg");
		allRooms[ROCKY_SHALE] = room;
		room = new RoomNode(FOREST, 0.8, 0.5, 0.0, 1050.0, "images/backgrounds/Forest.jpg");
		allRooms[FOREST] = room;
		room = new RoomNode(POND, 0.7, 0.5, 0.0, 660.0, "images/backgrounds/Pond.jpg");
		allRooms[POND] = room;
		room = new RoomNode(PRAIRIE, 0.7, 0.5, 0.0, 1050.0, "images/backgrounds/Prairie.jpg");
		allRooms[PRAIRIE] = room;
		room = new RoomNode(FLOWER_MEADOW, 0.7, 0.5, 0.0, 1050.0, "images/backgrounds/FlowerMeadow.jpg");
		allRooms[FLOWER_MEADOW] = room;
		room = new RoomNode(GRASSY_HILL, 0.7, 0.5, 0.0, 1050.0, "images/backgrounds/GrassyHill.jpg");
		allRooms[GRASSY_HILL] = room;
		room = new RoomNode(SWAMP, 0.7, 0.5, 0.0, 1050.0, "images/backgrounds/Swamp.jpg");
		allRooms[SWAMP] = room;
		
		for(int i = 0; i < ROOM_COUNT; i++) { //then it goes through and puts them all into the stack
			stack.getChildren().add(allRooms[i].getBackground());
		}
	}
	
	private void initHighlights() { //this is for all the highlights that indicate where you can go, it makes a bit of a map
		//sandy dune
		HighlightNode high = new HighlightNode(SANDY_DUNE, SANDY_HILL, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[0] = high;
		
		//sandy hill
		high = new HighlightNode(SANDY_HILL, SANDY_DUNE, 150, 1050, "images/highlights/LeftArrow.png");
		allHighs[1] = high;
		high = new HighlightNode(SANDY_HILL, FLAT_DIRT, 750, 525, "images/highlights/RightArrow.png");
		allHighs[2] = high;
		
		//cave
		high = new HighlightNode(CAVE, ROCKY_SHALE, 525, 525, "images/highlights/ForwardArrow.png");
		allHighs[3] = high;
		
		//riverside
		high = new HighlightNode(RIVERSIDE, FLAT_DIRT, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[4] = high;
		
		//flat dirt
		high = new HighlightNode(FLAT_DIRT, SANDY_HILL, 525, 1000, "images/highlights/ForwardArrow.png");
		allHighs[5] = high;
		high = new HighlightNode(FLAT_DIRT, RIVERSIDE, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[6] = high;
		high = new HighlightNode(FLAT_DIRT, POND, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[7] = high;
		
		//rocky shale
		high = new HighlightNode(ROCKY_SHALE, CAVE, 525, 525, "images/highlights/ForwardArrow.png");
		allHighs[8] = high;
		high = new HighlightNode(ROCKY_SHALE, FOREST, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[9] = high;
		high = new HighlightNode(ROCKY_SHALE, PRAIRIE, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[10] = high;
		
		//forest
		high = new HighlightNode(FOREST, ROCKY_SHALE, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[11] = high;
		high = new HighlightNode(FOREST, FLOWER_MEADOW, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[12] = high;
		
		//pond
		high = new HighlightNode(POND, FLAT_DIRT, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[13] = high;
		high = new HighlightNode(POND, GRASSY_HILL, 610, 0.0, "images/highlights/RightArrow.png");
		allHighs[14] = high;
		
		//prairie
		high = new HighlightNode(PRAIRIE, ROCKY_SHALE, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[15] = high;
		high = new HighlightNode(PRAIRIE, FLOWER_MEADOW, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[16] = high;
		
		//flower meadow
		high = new HighlightNode(FLOWER_MEADOW, FOREST, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[17] = high;
		high = new HighlightNode(FLOWER_MEADOW, PRAIRIE, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[18] = high;
		high = new HighlightNode(FLOWER_MEADOW, GRASSY_HILL, 525, 525, "images/highlights/ForwardArrow.png");
		allHighs[19] = high;
		
		//grassy hill
		high = new HighlightNode(GRASSY_HILL, POND, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[20] = high;
		high = new HighlightNode(GRASSY_HILL, SWAMP, 1000, 0.0, "images/highlights/RightArrow.png");
		allHighs[21] = high;
		high = new HighlightNode(GRASSY_HILL, FLOWER_MEADOW, 525, 525, "images/highlights/ForwardArrow.png");
		allHighs[22] = high;
		
		//swamp
		high = new HighlightNode(SWAMP, GRASSY_HILL, 50, 1050, "images/highlights/LeftArrow.png");
		allHighs[23] = high;
		
		//init
		high = new HighlightNode(ROOM_COUNT, RIVERSIDE, -100, 100, "images/highlights/LeftArrow.png");
		allHighs[24] = high;
				
		for(int i = 0; i < HIGHLIGHT_COUNT; i++) { //they all get put into the stack
			stack.getChildren().add(allHighs[i].getHighlight());
		}
	}
	
	private void initCharacters() { //this is for all characters and their scripts and what items they need
		ScriptReader scriptReader = new ScriptReader();
		//Jaz
		CharacterNode chara = new CharacterNode("Jaz", PRAIRIE, 300.0, 300.0, 621.0 * 0.5, 617.0 * 0.5, "/scripts/JazDialogue.txt", "images/characters/JazSprite.png", scriptReader);
		chara.addItems(allItems[7], allItems[6]);
		allCharas[0] = chara;
		
		//Scala
		chara = new CharacterNode("Scala", ROCKY_SHALE, 100.0, 350.0, 557.0 * 0.8, 443.0 * 0.8, "/scripts/ScalaDialogue.txt", "images/characters/ScalaSprite.png", scriptReader);
		chara.addItems(allItems[10], allItems[9]);
		allCharas[1] = chara;
		
		//Juniper
		chara = new CharacterNode("Juniper", FLOWER_MEADOW, 50.0, 270.0, 542.0 * 0.7, 557.0 * 0.7, "/scripts/JuniperDialogue.txt", "images/characters/JuniperSprite.png",scriptReader);
		chara.addItems(allItems[3], allItems[12]);
		allCharas[2] = chara;
		
		//Dash
		chara = new CharacterNode("Dash", SWAMP, 850.0, 320.0, 570.0 * 0.3, 414.0 * 0.3, "/scripts/DashDialogue.txt", "images/characters/DashSprite.png", scriptReader);
		chara.addItems(allItems[11], allItems[2]);
		allCharas[3] = chara;
		
		//Ace
		chara = new CharacterNode("Ace", SANDY_DUNE, 100.0, 400.0, 538.0 * 0.7, 397.0 * 0.7, "/scripts/AceDialogue.txt", "images/characters/AceSprite.png", scriptReader);
		chara.addItems(allItems[9], allItems[11]);
		allCharas[4] = chara;
		
		//Honey
		chara = new CharacterNode("Honey", FOREST, 50.0, 400.0, 540.0 * 0.73, 485.0 * 0.73, "/scripts/HoneyDialogue.txt", "images/characters/HoneySprite.png", scriptReader);
		chara.addItems(allItems[5], allItems[3]);
		allCharas[5] = chara;
		
		//Riff
		chara = new CharacterNode("Riff", CAVE, 800.0, 620.0, 586.0 * 0.5, 260.0 * 0.5, "/scripts/RiffDialogue.txt", "images/characters/RiffSprite.png", scriptReader);
		chara.addItems(allItems[2], allItems[1]);
		allCharas[6] = chara;
		
		//Mr. E
		chara = new CharacterNode("Mr. E", POND, -20.0, 450.0, 571.0 * 0.5, 308.0 * 0.5, "/scripts/MrEDialogue.txt", "images/characters/MrESprite.png",scriptReader);
		chara.addItems(allItems[6], allItems[5]);
		allCharas[7] = chara;
		
		//Vignette
		chara = new CharacterNode("Vignette", GRASSY_HILL, 700.0, 450.0, 567.0 * 0.5, 264.0 * 0.5, "/scripts/VignetteDialogue.txt", "images/characters/VignetteSprite.png", scriptReader);
		chara.addItems(allItems[12], allItems[0]);
		allCharas[8] = chara;
		
		//Parcheesi
		chara = new CharacterNode("Parcheesi", FLAT_DIRT, 600.0, 550.0, 484.0 * 0.3, 307.0 * 0.3, "/scripts/ParcheesiDialogue.txt", "images/characters/ParcheesiSprite.png", scriptReader);
		chara.addItems(allItems[1], allItems[7]);
		allCharas[9] = chara;
		
		for(int i = 0; i < CHARACTER_COUNT; i++) { //then it cycles through and puts the speech bubble and character into them all
			charaPane.getChildren().addAll(allCharas[i].getCharacter(), allCharas[i].getSpeechBubble());
		}
		stack.getChildren().addAll(charaPane, itemPane);
	}
	
	private void initItems() { //this is for all the items and their descriptions 
		//rocks
		ItemNode item = new ItemNode("Rocks", GRASSY_HILL, 200, 500, 490 * 0.2, 370 * 0.2, "images/items/Rock.png", "A pile of rocks! Just what you were looking for! Now you can smack them into the river with your tail all day long!");
		allItems[0] = item; //check
		
		//dice
		item = new ItemNode("Dice", CAVE, 900, 750, 245 * 0.1, 250 * 0.1, "images/items/Dice.png", "This would come in very handy if you ever needed to make a six choice decision or you wanted to play a little game of some kind.");
		allItems[1] = item; //check
		
		//water
		item = new ItemNode("Water", SWAMP, 810, 300, 576 * 0.1, 1152 * 0.1, "images/items/WaterBottle.png", "Wow, that water looks delicious! It's much better than what's in the swamp... But you can't drink it now, you have rocks to catch!");
		allItems[2] = item; //check
		
		//soup
		item = new ItemNode("Soup", FOREST, 400, 650, 512 * 0.2, 307 * 0.2, "images/items/Soup.png", "It smells divine and it looks even better. It's definitely the type of soup you want to go back for.");
		allItems[3] = item; //check
		
		//fire
		item = new ItemNode("Campfire", FOREST, 350, 560, 500 * 0.4, 500 * 0.4, "images/items/Fire.gif", "Null");
		allItems[4] = item; 
		
		//recipe book
		item = new ItemNode("Recipe Book", POND, 200, 550, 480 * 0.25, 360 * 0.25, "images/items/RecipeBook.png", "It's only for soup. What a niche cookbook. I'm sure someone has a use for it though.");
		allItems[5] = item; //check
		
		//stick
		item = new ItemNode("The Perfect Stick", PRAIRIE, 520, 420, 280 * 0.4, 450 * 0.4, "images/items/Stick.png", "It's pointy, it's pokey, it's perfect! You're not gonna find a better stick than this one!");
		allItems[6] = item; //check
		
		//gloves
		item = new ItemNode("Gloves", FLAT_DIRT, 610, 600, 260 * 0.2, 220 * 0.2, "images/items/Gloves.png", "Stretchy, warm, and grippy. They're ideal for all sorts of things!");
		allItems[7] = item; //check
		
		//laptop
		item = new ItemNode("Laptop", ROCKY_SHALE, 300, 615, 500 * 0.4, 349 * 0.4, "images/items/Computer.gif", "Null");
		allItems[8] = item; //check
		
		//coffee
		item = new ItemNode("Coffee", ROCKY_SHALE, 250, 610, 250 * 0.4, 250 * 0.4, "images/items/Coffee.gif", "Still warm and good! As every coffee should be. This will definitely wake anyone up, not just a programmer");
		allItems[9] = item; //check
		
		//leaf
		item = new ItemNode("Cool Shaped Leaf", RIVERSIDE, -100, -100, 576 * 0.4, 1152 * 0.4, "images/items/Leaf.png", "What a fun shape. I don't what you'd call that shape but it's definitely interesting.");
		allItems[10] = item; //check
		
		//foam roller
		item = new ItemNode("Foam Roller", SANDY_DUNE, 240, 600, 530 * 0.4, 280 * 0.4, "images/items/FoamRoller.png", "Gets out those kinks in your back oh so smoothly and makes stretching 10x easier.");
		allItems[11] = item; //check
		
		//flowers
		item = new ItemNode("Flowers", FLOWER_MEADOW, 300, 550, 310 * 0.4, 260 * 0.4, "images/items/Flowers.png", "Bright, fresh, and delicately arranged. What more could you want in a bouquet? They're picture perfect!");
		allItems[12] = item; //check
		
		for(int i = 0; i < ITEM_COUNT; i++) { //cycles through all the items and puts them into the stack
			itemPane.getChildren().add(allItems[i].getItem());
		}		
	}
	
	private void initDialogue() { //this is for setting up the dialogue box
		dialogueBox.setId("dialogueBox"); //this is for the css file appearances
		dialogueBox.setAlignment(Pos.CENTER); //it centers the text
		dialogueBox.setWrapText(true); //makes sure the text will wrap although it never really seems to need that
		dialogueBox.setPrefSize(1000, 80); //the size of the dialogue box
		dialogueBox.relocate(33.5, 680.0); //the location on the pane of hte dialogue box
		
		dialogueName.setId("dialogueName"); //this is for the css file appearances
		dialogueName.setAlignment(Pos.CENTER); //it centers the text
		dialogueName.setPrefSize(300, 30); //the size of the dialogue name
		dialogueName.relocate(33.5, 650.0); //the location on the pane of hte dialogue name
		
		setDialogueVisibility(false); //sets the visibility
		
		dialoguePane.getChildren().addAll(dialogueBox, dialogueName); //shove those in the dialogue layer
		stack.getChildren().add(dialoguePane); //and you're off to the races
	}
	
	private void initInventory() {
		inventoryBack.setPrefSize(470, 240); //the size of the inventory backing
		inventoryBack.relocate(290, 200); //the location of the inventory backing
		inventoryBack.setStyle("-fx-background-color: #FFE5B4; -fx-background-radius: 2; -fx-border-width: 10px; -fx-border-radius: 2; -fx-border-color: IVORY;");
		
		inventoryItem.setId("inventoryButton"); //for the css file
		inventoryItem.setPrefWidth(400); //sets the width of the inventory button
		inventoryItem.setAlignment(Pos.CENTER); //centers it
		inventoryNothing.setId("inventoryButtonHighlight"); //highlights the Goodbye option in the css file
		inventoryNothing.setPrefWidth(400); //sets teh width
		inventoryNothing.setAlignment(Pos.CENTER); //centers it
		inventoryNothing.setText("Goodbye"); //and sets the text for that one
		
		setInventoryVisibility(false); //turns the visibility off
		
		inventoryButtons.relocate(300, 210); //the location of the inventory buttons vbox
		inventoryButtons.setPrefSize(450, 220); //the size of the inventory buttons backing
		inventoryButtons.setSpacing(40); //this just spaces the buttons out
		inventoryButtons.setAlignment(Pos.CENTER); //tehn centers the buttons
		inventoryButtons.getChildren().addAll(inventoryItem, inventoryNothing); //puts the buttons in the vbox
		
		inventoryPane.getChildren().addAll(inventoryBack, inventoryButtons); //slap those bad boys into the ivnentory button vbox
		stack.getChildren().add(inventoryPane); //and into the stack they go!
	}
	
	private void initItemAcquiredNotice() {
		itemAcquiredBack.setPrefSize(470, 240); //the size of the dialogue box
		itemAcquiredBack.relocate(290, 200); //the location of the item acquired back
		itemAcquiredBack.setStyle("-fx-background-color: #FFE5B4; -fx-background-radius: 2; -fx-border-width: 10px; -fx-border-radius: 2; -fx-border-color: IVORY;");
		
		itemAcquiredName.setId("itemAcquiredName"); //for the css file
		itemAcquiredName.setPrefWidth(400); //sets the width
		itemAcquiredName.setAlignment(Pos.CENTER); //centers it
		
		itemAcquiredDescription.setId("itemAcquiredDescription"); //for the css file
		itemAcquiredDescription.setPrefSize(430, 150); //the size of the itemacquired description
		itemAcquiredDescription.setAlignment(Pos.CENTER); //centers it
		itemAcquiredDescription.setTextAlignment(TextAlignment.CENTER); //centers the text too
		itemAcquiredDescription.setWrapText(true); //and wraps the text
		
		itemAcquiredNoticeBox.relocate(300, 210); //the location of the itemacquired notice box
		itemAcquiredNoticeBox.setPrefSize(450, 220); //the size of the item acquired notice box
		itemAcquiredNoticeBox.setSpacing(12); //this spaces the notice a little
		itemAcquiredNoticeBox.setAlignment(Pos.CENTER); //and centers it
		itemAcquiredNoticeBox.getChildren().addAll(itemAcquiredName, itemAcquiredDescription); //then adds the name and description to the box
		
		toggleItemAcquiredVisibility(); //turns the visibility off
		
		itemAcquiredPane.getChildren().addAll(itemAcquiredBack, itemAcquiredNoticeBox); //and puts the name and description box into the layer with the backing
		stack.getChildren().add(itemAcquiredPane); //then they go into the stack with everything else
	}
	
	private void initTutWin() {
		tutWinBack.setPrefSize(900, 150); //the size of the tutwin backing
		tutWinBack.relocate(75, 40); //the location of the tutorial and win screen
		tutWinBack.setStyle("-fx-background-color: #FFE5B4; -fx-background-radius: 2; -fx-border-width: 10px; -fx-border-radius: 2; -fx-border-color: IVORY;");
		
		tutWinWords.setId("tutWinWords"); //css file appearances
		tutWinWords.setPrefSize(870, 130); //the size of the tutwin words
		tutWinWords.setAlignment(Pos.CENTER); //centers the container of the label
		tutWinWords.setTextAlignment(TextAlignment.CENTER); //centers the text as well
		tutWinWords.setWrapText(true); //wraps the text
		tutWinWords.relocate(90, 50); //the location of the tutorial and win words 
		tutWinWords.setText("Being an Ankylosaurus is the best! You get to smack rocks into the river with your tail all day. But now you're out of rocks. All you have is this cool looking leaf. Maybe you can go find some more rocks."
				+ "\nUse the left and right arrows to move, the up and down to select options, S to speak to others and take new items, and W to move between screens."); //and this is what it says
		
		setTutWinVisibility(true); //turns the visibility on
		
		tutWinPane.getChildren().addAll(tutWinBack, tutWinWords); //puts the back and the words into the pane
		stack.getChildren().add(tutWinPane); //and puts that in the stack
	}
	
	//***************************** VISIBILITY FUNCTIONS *****************************
	public void toggle() {
		allRooms[rooms].toggleVisibility(); //switch the visibility of the current room
		int a = getRoomCharacter(); //gets the current room's character
		if(a < CHARACTER_COUNT) { //if there is one
			allCharas[a].toggleVisibility(); //and switches the visibility of them
			allCharas[a].setSpeechVisibility(false); //and turns the speech visibility off
		}
		for(int i = 0; i < ITEM_COUNT; i++) { //then it goes through all the items
			if(allItems[i].getRoom() == this.rooms) { //and when it finds ones that are in the current room
				allItems[i].toggleVisibility(); //it toggles them off or on as well
			}
		}
	}
	
	public void setDialogueVisibility(Boolean dialogueVisibility) {
		this.dialogueVisibility = dialogueVisibility; //sets the new visibility value
		dialogueBox.setVisible(this.dialogueVisibility); //then enacts it
		dialogueName.setVisible(this.dialogueVisibility); //then enacts it
	}
	
	public Boolean getDialogueVisibility() {
		return dialogueVisibility; //returns the visibility value of the dialogue screen
	}
	
	public Boolean getSpeechVisibility() {
		int a = getRoomCharacter(); //finds the character that is in this room
		if(a < CHARACTER_COUNT) { //if there is one
			return allCharas[a].getSpeechVisibility(); //returns the visibility value of the speech bubble
		}
		return false; //otherwise just return false anyway
	}
	
	public void setInventoryVisibility(Boolean inventoryVisibility) {
		this.inventoryVisibility = inventoryVisibility; //sets the new visibility value
		inventoryPane.setVisible(this.inventoryVisibility); //then enacts it
	}
	
	public Boolean getInventoryVisibility() {
		return inventoryVisibility; //returns the visibility value of the inventory screen
	}
	
	public void toggleItemAcquiredVisibility() {
		itemAcquiredVisibility = !itemAcquiredVisibility; //sets the new visibility value
		itemAcquiredPane.setVisible(this.itemAcquiredVisibility); //then enacts it
	}
	
	public Boolean getItemAcquiredVisibility() {
		return itemAcquiredVisibility; //returns the visibility value of the itemacquired screen
	}
	
	public void setTutWinVisibility(Boolean tutWinVisibility) {
		this.tutWinVisibility = tutWinVisibility; //sets the new visibility value
		tutWinPane.setVisible(this.tutWinVisibility); //then enacts it
	}
	
	public Boolean getTutWinVisibility() {
		return tutWinVisibility; //returns the visibility value of the tut win screen
	}
	
	//***************************** ROOM CHANGING FUNCTIONS *****************************
	public Integer changeRooms() {
		int toReturn = 13;
		for(int i = 0; i < HIGHLIGHT_COUNT; i++) {
			if(allHighs[i].getRoomFrom() == rooms && allHighs[i].getVisibility()) {
				prevHigh = allHighs[i];
				allHighs[i].setVisibility(false);
				toReturn = allHighs[i].getRoomTo();
				setTutWinVisibility(((allHighs[i].getRoomTo() == 3) ? true : false));
			}
		}
		return toReturn;
	}
	
	public void setRoom(int rooms) { 
		if(!initial) { //as long as this isn't the first room
			toggle(); //it'll turn the previous room not visible
		}
		this.rooms = rooms; //the new room is set
		toggle(); //then the new room is visible
		initial = false; //and the initial is false
	}
	
	public Double getResize() {
		return allRooms[rooms].getResize(); //just gets the resize value of the room
	}
	
	//***************************** MOVEMENT DEPENDANT FUNCTIONS *****************************
	public void compareCoords(double xStart, double xEnd) { //this is for checking if you need to highlight anything on the screen
		for(int i = 0; i < HIGHLIGHT_COUNT; i++) { 
			if(allHighs[i].getRoomFrom() == rooms) { //if the character is below an arrow, it'll light it up
				allHighs[i].setVisibility((xStart >= allHighs[i].getLimit() || xEnd <= allHighs[i].getLimit()) ? false : true);
			}
			if(i < CHARACTER_COUNT) {
				if(allCharas[i].getRoom() == rooms) { //if the character is close enough to another character, the speech bubble will light up, allowing you to talk to them
					allCharas[i].setSpeechVisibility((xStart - 110 >= allCharas[i].getLimit() || xEnd + 110 <= allCharas[i].getLimit()) ? false : true);
				}
			}
		}
	}
	
	public Double getXLeftLimit() {
		return allRooms[rooms].getXLeftLimit(); //returns the left limit of the screen
	}
	
	public Double getXRightLimit() {
		return allRooms[rooms].getXRightLimit(); //returns the right limit of the screen
	}
	
	//***************************** CHARACTER FUNCTIONS *****************************
	public Integer getRoomCharacter() { //I needed to find the current room's dino a lot so taht's what this is for
		for(int i = 0; i < CHARACTER_COUNT; i++) {//if cycles through all of them
			if(allCharas[i].getRoom() == this.rooms) { //and if the room they're in matches the current room
				return i; //return that one
			}
		}
		return CHARACTER_COUNT; //otherwise return the number of characters which indicates there are no characters in this room
	}
	
	//***************************** SCRIPT FUNCTIONS *****************************
	public void setScriptSelect() { //this sets the next script set
		int a = getRoomCharacter(); //finds the current room character
		if(a < CHARACTER_COUNT) { //as long as there is one
			int nextScript = -1; //makes an arbitary init
			switch(allCharas[a].getScriptSelect()) { //checks what the script is now
			case 0: nextScript = 1; break; //0 is the intro dialogue telling you what the dino is all about
			case 1: nextScript = selectedItemMatch; break; //1 is the request dialogue that will let you give the dino an item
			case 2: nextScript = 1; break; //2 is the wrong item dialogue
			case 3: nextScript = 4; break; //3 is the right item dialogue
			case 4: nextScript = 4; break; //4 is the final dialogue
			}
			allCharas[a].setScriptSelect(nextScript); //this sets up the new dialogue
			dialoguePointer = 0; //and resets the lines
		}
	}
	
	public Boolean openDialogue() { //opens the dialogue and goes through each round of dialogue until there isn't any
		setDialogueVisibility(true); //makes sure the dialogue is visible
		int a = getRoomCharacter(); //finds the current room character
		if(a < CHARACTER_COUNT) { //as long as there is one
			try {
				dialogueName.setText(allCharas[a].getName()); //it''s put the character name into place
				dialogueBox.setText(allCharas[a].getScript(dialoguePointer)); //and the line of dialogue
				dialoguePointer++; //moves to the next one
				return true; //this tells the other function that there are more lines
			} catch(Exception e) {
			}
		}
		return false; //once there aren't anymore lines it'll tell the other function to stop
	}
	
	public Integer getCharacterScript() { //this gets the script number of the current character 
		int a = getRoomCharacter(); //finds the current room character
		if(a < CHARACTER_COUNT) { //as long as there is one
			return allCharas[a].getScriptSelect(); //and grabs the script from it
		}
		return -1; //otherwise just spit back a non script number
	}
	
	//***************************** INVENTORY FUNCTIONS *****************************
	public void openInventory(Integer shift) {
		if(shift == 1) { inventoryButtonPointer = true; inventoryItem.setId("inventoryButtonHighlight"); inventoryNothing.setId("inventoryButton"); }
		else if(shift == -1) { inventoryButtonPointer = false; inventoryNothing.setId("inventoryButtonHighlight"); inventoryItem.setId("inventoryButton"); }
		//if the player selects the upper option, it highlights that, unhighlights the other one, and turns the pointer on
		//if the player selects the lower option, it highlights the Goodbye, unhighlights the other, and turns the pointer off
	}
	
	public void fillInventory() {
		inventoryItem.setText("Offer " + itemInInven.getName()); //changes the inventory option to offer the item that the player has
	}
	
	public Boolean getInventoryButtonPointer() {
		return inventoryButtonPointer;
	}
	
	public Boolean itemExchange() { //for checking if there are items leaving or entering the inventory
		boolean check = false; //this is to check if an item is selected or not
		int a = getRoomCharacter(); //finds the character for the room
		if(a < CHARACTER_COUNT) { //as long as there is a character in that room
			if(allCharas[a].getScriptSelect() == 1) { //and their script is on one
				selectedItemMatch = 1; //it'll automatically set the dialogue to 1 everytime
				check = true; //and this to true so you know that an item is needed
				if(allCharas[a].getItemNeed().equals(itemInInven) && getInventoryButtonPointer()) { //if the item selected matches the dino's needed item and the pointer is not null
					selectedItemMatch = 3; //it'll set the correct item dialogue
				}
				else if(!allCharas[a].getItemNeed().equals(itemInInven) && getInventoryButtonPointer()) { //if the item selected doesn't match the dino's needed item and the pointer is not null
					selectedItemMatch = 2; //it'll set the incorrect item dialogue
				}
			}
			else if(allCharas[a].getScriptSelect() == 3) { //if the script for this character is on the correct item dialogue
				ItemNode item = allCharas[a].getItemGive(); //it'll give the player an item
				itemAcquired(item); //it goes through the item acquired screen so you can see the name and description of the item
				toggleItemAcquiredVisibility(); //then it turns the item acquired visibility on
			}
			setScriptSelect(); //after all this, it goes through the script select to see if there are any changes to the dialogue
			selectedItemMatch = 1; //and this gets reset
			inventoryButtonPointer = false; //as does this
		}
		return check;
	}
	
	public void itemAcquired(ItemNode item) { //every time you get an item from another dinosaur
		item.toggleVisibility(); //it makes the items not visible in the world
		itemAcquiredName.setText(item.getName()); //sets the item acquired name to the new item
		itemAcquiredDescription.setText(item.getDescription()); //sets teh description as well
		itemInInven = item; //puts it into the inventory
		item.setRoom(-1); //makes sure it can't ever be visible again
		if(item == allItems[0]) { //and then if it's the rocks, it sets the ending win screen
			tutWinWords.setText("Congrats! You have more rocks! Now you can go back to being an Ankylosaurus that smacks rocks into the river all day! And you helped your friends. What more is there in life?\n\n"
					+ "Thanks for playing :)");
			
		}
	}
}
