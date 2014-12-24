package console;

import java.util.*;

import game.Player;

public class ConsoleRunner {
	public static void main(String args[]) {

		Player a = new Player(10,10,0);

		Scanner input = new Scanner(System.in);
		
		for (int i = 0; i < 2; i++) {
			int m = input.nextInt();//  tool e safhe 
			int n = input.nextInt();//  arz e safhe
			input.nextLine();
			
			if(i==0)
				Player Player1 = new Player(m,n,0); 
			
			if(i==1)
				Player Player2 = new Player(m,n,1);
		}
		
		

	}
}
