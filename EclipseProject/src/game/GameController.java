package game;

import BoardObjects.Ship;

public class GameController {
	private int currentTime;// In dasturaye go hamash sahihe dg :/
	private Player[] players;
	public GameController(int width, int height) {
		players = new Player[2];
		players[0] = new Player(width, height);
		players[1] = new Player(width, height);
	}
	
	
	/**
	 * Check mikone age yeki hame keshtiash pokid un yeki barandas
	 * @return The winner or null if no one wins yet
	 */
	public Player checkWinner() {
		boolean win0=true,win1=true;
		for(Ship ship:players[0].getShips()) {
			if(!ship.isDestroyed()) {
				win1 = false;
				break;
			}
		}
		for(Ship ship:players[1].getShips()) {
			if(!ship.isDestroyed()) {
				win0 = false;
				break;
			}
		}
		if(win0 && win1)
			return null;// alal osul nabayad be chenin vazi bokhorim
		if(win0)
			return players[0];
		if(win1)
			return players[1];
		return null;
	}
}
