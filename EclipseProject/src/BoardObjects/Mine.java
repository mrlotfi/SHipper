package BoardObjects;

import game.Player;

public class Mine extends BaseGameObject {
	private Player owner;
	public Mine(int x, int y, Player owner) {
		super(x,y);
		this.owner = owner;
	}
	
	/**
	 * @author M-L-N
	 * @param x
	 * @param y
	 * @param player  
	 * @return
	 */
	public String explode(int x, int y, Player attacker) {
		if(attacker.getBoard()[x][y] == null)
			return "";
		if(attacker.getBoard()[x][y].getClass().equals(Ship.class)) {
			Ship s = (Ship) attacker.getBoard()[x][y];
			if(s.damagePart(x, y)) {
				return "team " +owner.toChar()+" explode " + (x+1) +","+ (y+1) +"\n" 
						+ "team " + attacker.toChar() + " mine trap " + (x+1) + "," + (y+1) +"\n";
			}
			return "";
		}
		if(attacker.getBoard()[x][y].getClass().equals(AntiAircraft.class)) {
			attacker.getBoard()[x][y] = null; // un anti aircraftesh mipoke
			return "team " + attacker.toChar()  +" anti aircraft row " +  (x+1) + " exploded"+ "\n" 
					+"team " + attacker.toChar() + " mine trap " + (x+1) + "," + (y+1) +"\n";
		}
		if(attacker.getBoard()[x][y].getClass().equals(Mine.class)) {
			attacker.getBoard()[x][y] = null;
			return "";// ino motmaen nistam hanuz. yademun bashe
		}
		return "";
	}
}
