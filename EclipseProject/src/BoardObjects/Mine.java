package BoardObjects;

import game.Player;

public class Mine extends BaseGameObject {
	public Mine(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void explode(Player player) {
		if(player.getBoard().get(this.x, this.y).getClass().equals(Ship.class)) {
			Ship a = (Ship) player.getBoard().get(this.x, this.y);
			a.damagePart(this.x, this.y);
			return;
		}
		if(player.getBoard().get(this.x, this.y).getClass().equals(Mine.class)) 
			player.getBoard().set(null, this.x, this.y);
	}
}
