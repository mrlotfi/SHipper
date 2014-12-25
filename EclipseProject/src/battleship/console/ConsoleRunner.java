package battleship.console;
import game.Game;

import java.util.Scanner;

import BoardObjects.Ship;
public class ConsoleRunner {
	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		int width, height;
		width = reader.nextInt();
		height = reader.nextInt();
		reader.nextLine();
		Game myGame = new Game(width, height);
		String input;
		for(int playerIndex=0;playerIndex<2;playerIndex++) {
			System.out.println("Player number " + (playerIndex+1) + ", please build your map");
			for(int i=0;i<7;i++) {
				input  = reader.nextLine();
				char pol = input.charAt(input.length()-1);
				int length;
				length = (8-i) /2;
				int place = input.indexOf(",");
				int x = Integer.parseInt(input.substring(0, place)) - 1;
				int y = Integer.parseInt(input.substring(place+1,input.length()-2)) - 1;
				myGame.players[playerIndex].addShip(
						new Ship(pol,x,y,length,myGame.players[playerIndex]));
			}
			input = reader.nextLine();
			if(input.charAt(0) != 'm' && input.charAt(0) != 'd') {
				input = reader.nextLine();
				while(input.charAt(0) != 'm' && input.charAt(0) != 'd') {
					int y = Integer.parseInt(input) - 1;
					myGame.players[playerIndex].addAntiAircraft(y);
					input = reader.nextLine();
				}
			}
			if(input.charAt(0) != 'd') {
				input = reader.nextLine();
				while(input.charAt(0) != 'd') {
					int place = input.indexOf(",");
					int x = Integer.parseInt(input.substring(0,place)) - 1;
					int y = Integer.parseInt(input.substring(place+1)) - 1;
					myGame.players[playerIndex].addMine(x, y);
					input = reader.nextLine();
				}
			}
		}
		// now game is ready for instructions
		while(reader.hasNext()) {
			input = reader.nextLine();
			if(input.charAt(0) == 't') {
				char team = input.charAt(5);
				int index = 0;
				if(team == 'b')
					index = 1;
				int index2;
				index2 = 1-index;
				if(input.charAt(7) == 'r') {
					int place = input.indexOf(",");
					int x = Integer.parseInt(input.substring(13, place)) - 1;
					int y = Integer.parseInt(input.substring(place+1 )) - 1;
					myGame.addRadarStatement(x, y, index, index2);
				}
				if(input.charAt(8) == 'i') {
					int x = Integer.parseInt(input.substring(16)) - 1;
					myGame.addAircraftStatement(x, index, index2);
				}
				if(input.charAt(8) == 't') {
					int place = input.indexOf(",");
					int x = Integer.parseInt(input.substring(14, place)) - 1;
					int y = Integer.parseInt(input.substring(place+1 )) - 1;
					myGame.addAttackStatement(x, y, index, index2);
				}
				
			}
			else {
					int time = Integer.parseInt(input.substring(3));
					for(int i=0;i<time;i++)
						System.out.print(myGame.shiftTimeAndRun());
			}
		}
	}
}
