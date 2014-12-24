package game;


import java.util.LinkedList;
import java.util.Queue;

import BoardObjects.Ship;
/**
 * fromate reshte haye dastue:
 * statement,Delay,(Player), current time
 * statement:
 *	 1: aircraft 2:attack 3:radar
 * delay:
 * 	 1 ya 2
 * Player:
 * 	0 ya 1 ya -1 age dastur player nadasht
 * @author M-L-N
 *
 */
public class Game {
	private int currentTime;// In dasturaye go hamash sahihe dg :/
	private Queue<int[]> statements;
	private Player[] players;
	public Game(int width, int height) {
		currentTime = 0;
		players = new Player[2];
		players[0] = new Player(width, height, 0);
		players[1] = new Player(width, height, 1);
		statements = new LinkedList<int[]>();
		
	}
	public String shiftTimeAndRun(int x) {
		// dasturaii ke be ejra miresan ro bar hasbe zaman morattab miknim ejra mikonim reshteshuno jam mikonim return mikonim
		return null;
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
