package Graphics.GameScene;

import game.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;



public class GameTablePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6263574880824994116L;
	public static int SIZE = 501;
	private Player player;
	private int activeX,activeY;
	public GameTablePanel(Player player) {
		this.player = player;
		activeX = activeY = 0;
		
	}
	
	public int getActiveX() {
		return activeX;
	}
	
	public int getActiveY() {
		return activeY;
	}
	public void setActiveCell(int x, int y) {
		if(x>=0 && y>=0 &&
				x<player.getWidth() && 
				y <player.getHeight()) {
			this.activeX = x;
			this.activeY = y;
		}
	}
	
	private static Color getColor(char c) {
		switch(c) {
		case 'n':
			return Color.gray;
		case 'm':
			return Color.black;
		case 's':
			return Color.yellow;
		case 'a':
		return Color.blue;
		case 'd':
		return Color.red;
		case 'p':
		return Color.green;//push
		}
		return null;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<player.getWidth();i++)
			for(int j=0;j<player.getHeight();j++) {
				g.setColor(getColor(player.getKnownCell(i, j)));
				g.fillRect(i*(SIZE/player.getWidth()), j*(SIZE/player.getHeight()), SIZE/player.getWidth(), SIZE/player.getHeight());
				g.setColor(Color.black);
				g.drawRect(i*(SIZE/player.getWidth()), j*(SIZE/player.getHeight()), SIZE/player.getWidth(), SIZE/player.getHeight());
				
			}

		g.setColor(Color.red);
		g.drawRect(activeX*(SIZE/player.getWidth()), activeY*(SIZE/player.getHeight()), SIZE/player.getWidth(), SIZE/player.getHeight());
	}
}
