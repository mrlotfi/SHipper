package battleship.console;
import game.Game;

import java.util.Scanner;
public class TestConsole {
	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		int width, height;
		width = reader.nextInt();reader.nextLine();
		height = reader.nextInt();reader.nextLine();
		Game myGame = new Game(width,height);
		System.out.println("Player number 1, please build your map");
		String input = reader.nextLine();
		while(Character.isDigit(input.charAt(0))) {
			char polarity = 'j';
		}
	}
}
