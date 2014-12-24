package battleship.console;

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
		
		Game controll = new Game(m, n);
		
		//Build Map 
		for (int i = 0; i < 2; i++) {
			
			System.out.println("Player number "+(i+1)+", please build your map");
			
			String inst = input.nextLine();
			while(!(inst.equals("done")))
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
		
		
		
		
		while (controll.gameFinished==false)
		{
			String s = input.nextLine();
			// Monazam beshe 
			//
			//
			if(s.charAt(0)=='g')
			{
				String s1 = s.substring(3,s.length());
				int x = Integer.parseInt(s1);
				controll.shiftTimeAndRun(x);
				
			}
			
			if(s.charAt(0)=='t')
			{
				int z = 2;
				int zd = 1;
				if(s.charAt(5)=='a')
				{
					z = 1 ;
					zd = 2;
				}
				
				if(s.charAt(7)=='r')
				{
					int k = s.indexOf(',');
					String s1 = s.substring(13,k);
					String s2 = s.substring(k+1 , s.length());
					
					int x = Integer.parseInt(s1);
					int y = Integer.parseInt(s2);
					
					controll.addRadarStatement(x, y, z, zd);
				}
				
				if(s.charAt(8)=='t')
				{
					int k = s.indexOf(',');
					String s1 = s.substring(14,k);
					String s2 = s.substring(k+1,s.length());
					
					int x = Integer.parseInt(s1);
					int y = Integer.parseInt(s2);
					
					controll.addAttackStatement(x, y, z, zd);
				}
				
				if(s.charAt(8)=='i')
				{
					String s1 = s.substring(16);
					int x = Integer.parseInt(s1);
					
					controll.addAircraftStatement(x, z, zd);
				}
			}
		}
		
		
		

	}
	// for recognize build instructions
	public static String buildReader(String s,Player p)
	{
		// if instruction equals "Anti aircraft"
		if(s.charAt(0)=='a')
		{
			s= input.nextLine();
			while(!(s.equals("done"))&&!(s.equals("mine")))
			{		
						int y = Integer.parseInt(s);
			
						p.addAntiAircraft(y);
						s = input.nextLine();
			}
		}
		
		// if instruction equals "Mine"
		if(s.charAt(0)=='m')
		{
			s=input.nextLine();
			while(!(s.equals("done")))
			{
				int num = s.indexOf(',');
				String s1 = s.substring(0,num);
				String s2 = s.substring(num+1,s.length());
			
				int x = Integer.parseInt(s1);
				int y = Integer.parseInt(s2);
			
				p.addMine(x, y);
				s = input.nextLine(); 
			}
		}
		
		// for ship instruction 
		if(s.charAt(0)!='m'&&s.charAt(0)!='a')
		{
			int shipCounter = 0;
			while(!(s.equals("done"))&&!(s.equals("mine"))&&!(s.equals("anti aircraft")))
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

