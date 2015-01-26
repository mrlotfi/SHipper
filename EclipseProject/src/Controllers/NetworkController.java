package Controllers;

import Graphics.GameScene.NetworkedScene;
import game.Game;

public class NetworkController extends Game {
	private NetworkedScene scene;
	public NetworkController(int width, int height) {
		super(width, height);
		
	}

	public void setScene(NetworkedScene scene) {
		this.scene = scene;
	}
}
