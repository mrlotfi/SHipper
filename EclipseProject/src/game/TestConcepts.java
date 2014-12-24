package game;

import BoardObjects.Ship;

public class TestConcepts {
	public static void main(String args[]) {
		Player p1 = new Player(10,10,0);
		Player p2 = new Player(10,10,1);
		Ship a = new Ship('H',1,3,4,p1.getBoard());
		p1.addShip(a);
		System.out.print(p2.radar(p1, 2, 3));
	}
}
