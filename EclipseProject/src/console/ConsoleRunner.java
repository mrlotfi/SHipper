package console;

import java.util.*;
import game.*;
import BoardObjects.*;

public class ConsoleRunner
{
	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) {
		
		
		int m = input.nextInt();
		input.nextLine();
		int n = input.nextInt(); 
		input.nextLine();
		
		GameController controll = new GameController(m, n);
		
		//Build Map 
		for (int i = 0; i < 2; i++) {
			
			System.out.println("Player number"+(i+1)+", please build your map");
			
			String inst = input.nextLine();
			while(!(inst.equals("DONE")))
			{
				switch(i)
				{
					case 0 :
						inst = buildReader(inst,controll.players[0]);
						break;
					case 1 :
						inst = buildReader(inst,controll.players[1]);
						break;
				}
				
			}
		}
		
		
		
		//Attack instructions 
		
		
		

	}
	// for recognize build instructions
	public static String buildReader(String s,Player p)
	{
		// if instruction equals "Anti aircraft"
		if(s.charAt(0)=='A')
		{
			while(!(s.equals("DONE"))&&!(s.equals("MINE")))
			{		
						s =  input.nextLine();
						int y = Integer.parseInt(s);
			
						p.addAntiAircraft(y);
						s = input.nextLine();
			}
		}
		
		// if instruction equals "Mine"
		if(s.charAt(0)=='M')
		{
			while(!(s.equals("DONE")))
			{
				s = input.nextLine();
				int num = s.indexOf(',');
				String s1 = s.substring(0,num);
				String s2 = s.substring(num+1,s.length());
			
				int x = Integer.parseInt(s1);
				int y = Integer.parseInt(s2);
			
				p.addMine(x, y);
			}
		}
		
		// for ship instruction 
		if(s.charAt(0)!='M'&&s.charAt(0)!='A')
		{
			int shipCounter = 0;
			while(!(s.equals("DONE"))&&!(s.equals("MINE"))&&!(s.equals("Anti aircraft")))
			{
				int num = s.indexOf(',');
				String s1 = s.substring(0,num);
				String s2 = s.substring(num+1,s.length()-2);
			
				int x = Integer.parseInt(s1);
				int y = Integer.parseInt(s2);
				char c = s.charAt(s.length()-1);
				
				int z = 4 ;
				switch(shipCounter){
					case 0:
						z = 4;
						break;
					
					case 1:
					case 2:
						z = 3;
						break;
					case 3:
					case 4:
						z = 2;
						break;
					case 5:
					case 6:
						z=1;
						break;
				}
				
				Ship ship = new Ship(c,x,y,z,p);
				p.addShip(ship);
				shipCounter++;
				
				s = input.nextLine();				
			}
		}	
		
	return s ;
	}
}
