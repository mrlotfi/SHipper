package game;

import BoardObjects.Mine;
import BoardObjects.Ship;

/**
 * 
 * @author M-L-N
 * JUnit balad nistam bekhoda :(
 */
public class TestConcepts {
	public static void main(String args[]) {
		Game game = new Game(50,50);
		game.players[1].addShip(new Ship('V',2,2,4,game.players[1]));
		game.players[0].addShip(new Ship('V',4,4,4,game.players[0]));
		game.addRadarStatement(2, 2, 0, 1);
		game.addAttackStatement(2, 2, 0, 1);
		game.addAttackStatement(4, 4, 1, 0);
		game.addRadarStatement(4, 4, 1, 0);
		System.out.print(game.shiftTimeAndRun()+"\n");
		System.out.print(game.shiftTimeAndRun()+"\n");
		System.out.print(game.shiftTimeAndRun()+"\n");
		/*
		p2.addShip(b);
		p1.addMine(0, 0);
		p1.addMine(2, 0);
		p2.addAntiAircraft(2);
		System.out.print(p2.attack(p1, 2, 0));
		//
		System.out.print(p2.attack(p1, 0, 0));
		System.out.print(p2.attack(p1, 0, 0));
		*/
		/* in vase attacke mamuli o radar bud
		System.out.print(p2.radar(p1, 1, 4));
		System.out.print(p2.attack(p1, 1, 5));
		System.out.print(p2.attack(p1, 1, 5));
		System.out.print(p2.attack(p1, 1, 4)); */
	}
}
