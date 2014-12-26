package game;


import java.util.ArrayList;

import BoardObjects.Ship;
/**
 * fromate reshte haye dastue:
 * statement,runTime(zamani ke bayad bere to khoruji un dastor),Player,Player, x, y
 * statement:
 *	 1: aircraft 2:attack 3:radar
 * Player:
 * 	0 ya 1 
 * age y nadash (zedde havaii) y ro -1 mizarim
 * aAcc , bAcc: marbut be safe dasturaye jari
 * ehtemalan bejash ye kelas bezaram tamiz tar she
 * @author M-L-N
 *
 */
public class Game {
	private int currentTime;// In dasturaye go hamash sahihe dg :/
	private int aAcc,bAcc;// iAcc holds the time of run of player i's last operation
	private ArrayList<int[]> statements;
	public Player[] players;
	public boolean gameFinished ;
	public Game(int width, int height) {
		aAcc = bAcc = currentTime = 0;
		players = new Player[2];
		players[0] = new Player(width, height, 0);
		players[1] = new Player(width, height, 1);
		statements = new ArrayList<int[]>();
		gameFinished = false ;
		
	}
	public void addAircraftStatement(int row, int playerIndex,int defenderIndex) {
		int ted;
		if(playerIndex == 0) {
			if(currentTime <= aAcc) {
				ted = aAcc;
				aAcc++;
			}
			else {
				aAcc = currentTime;
				ted = aAcc;
				aAcc+=2;
			}
		}
		else {
			if(currentTime <= bAcc) {
				ted = bAcc;
				bAcc++;
			}
			else {
				bAcc = currentTime;
				ted = bAcc;
				bAcc+=2;
			}
		}
		statements.add(new int[] {1,ted+2,playerIndex,defenderIndex, row, -1});
	}
	public void addAttackStatement(int x, int y, int attackerIndex,int defenderIndex) {
		int ted;	
		if(attackerIndex == 0)  {
			if(currentTime <= aAcc) {
				ted = aAcc;
				aAcc++;
			}
			else {
				aAcc = currentTime;
				ted = aAcc;
				aAcc++;
			}
		}
		else {
			if(currentTime <= bAcc) {
				ted = bAcc;
				bAcc++;
			}
			else {
				bAcc = currentTime;
				ted = bAcc;
				bAcc++;
			}
		}
		statements.add(new int[] {2,ted+1,attackerIndex,defenderIndex,x,y});
	}
	public void addRadarStatement(int x, int y, int subjectIndex,int objectIndex) {
		int ted;
		if(subjectIndex == 0) {
			if(currentTime <= aAcc) {
				ted = aAcc;
				aAcc++;
			}
			else {
				aAcc = currentTime;
				ted = aAcc;
				aAcc+=2;
			}
		}
		else {
			if(currentTime <= bAcc) {
				ted = bAcc;
				bAcc++;
			}
			else {
				bAcc = currentTime;
				ted = bAcc;
				bAcc+=2;
			}
		}
		statements.add(new int[] {3,ted+2,subjectIndex,objectIndex,x,y});
	}
	
	public String shiftTimeAndRun() {
		currentTime++;
		String out = "";
		for(int[] a : statements) {
			if(a[1] == currentTime) {
				if(a[0] == 1) {
					out = out + players[a[2]].aircraftAttack(players[a[3]],a[4]);
					if(checkWinner() != null) {
						out = out + "team "+checkWinner().toChar()+" wins";
						return out;
					}
				}
				else if(a[0] ==2 ) {
					out = out + players[a[2]].attack(players[a[3]], a[4], a[5]);
					if(checkWinner() != null) {
						out = out + "team "+checkWinner().toChar()+" wins";
						if(checkWinner() != null) {
							out = out + "team "+checkWinner().toChar()+" wins";
							return out;
						} 
					}
				}
				else {
					out = out + players[a[2]].radar(players[a[3]], a[4], a[5]);
				}
			}
		}
		return out;
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
		if(win0 && win1){
			gameFinished=true;
			return null;// alal osul nabayad be chenin vazi bokhorim
		}
		if(win0){
			gameFinished=true;
			return players[0];
		}
		
		if(win1){	
			gameFinished=true;
			return players[1];
		}
		return null;
	}
}
