/* RIVER ROCK RAMBLE
 * In this game, you play as Roxie the Ankylosaurus who likes to smack rocks into the river using her clubbed tail
 * She's out of rocks though :( So you must help guide her and assist all the other dinosaurs in a quest style to get more rocks
 * MAX KRISHKA PACHAL
 */

//THIS FILE IS FOR TAKING IN KEY INPUT WITH LISTENERS

package application;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
	private BitSet keyboard = new BitSet();
	
	private KeyCode left = KeyCode.LEFT; //for moving left
	private KeyCode right = KeyCode.RIGHT; //for moving right
	private KeyCode up = KeyCode.UP; //for inventory buttons
	private KeyCode down = KeyCode.DOWN; //for inventory buttons
	private KeyCode walk = KeyCode.W; //for walking to the next screen
	private KeyCode speak = KeyCode.S; //for speaking to other characters
	
	private Scene scene;
	
	public Input(Scene scene) {
		this.scene = scene;
	}
	
	public void addListeners() {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressed);
		scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleased);
	}
	
	public void removeListeners() {
		scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressed);
		scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleased);
	}
	
	private EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() { //once the button is pressed, start reading about it, add it into the scene
		@Override
		public void handle(KeyEvent e) {
			keyboard.set(e.getCode().ordinal(), true);
		}
	};
	
	private EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() { //once the button is released, stop reading about it, take it out of hte scene
		@Override
		public void handle(KeyEvent e) {
			keyboard.set(e.getCode().ordinal(), false);
		}
	};
	
	public boolean isLeft() { //check if the key for going left and not right is being hit
		return keyboard.get(left.ordinal()) && !keyboard.get(right.ordinal());
	}
	
	public boolean isRight() { //check if the key for going right and not left is being hit
		return keyboard.get(right.ordinal()) && !keyboard.get(left.ordinal());
	}
	
	public boolean isUp() { //check if the key for going left and not right is being hit
		return keyboard.get(up.ordinal()) && !keyboard.get(down.ordinal());
	}
	
	public boolean isDown() { //check if the key for going right and not left is being hit
		return keyboard.get(down.ordinal()) && !keyboard.get(up.ordinal());
	}
	
	public boolean isSpeak() { //check if the key for talkign to characters is being hit
		return keyboard.get(speak.ordinal());
	}
	
	public boolean isWalk() { //check if the key for moving from scene to scene is being hit
		return keyboard.get(walk.ordinal());
	}
}
