package Graphics.GameScene;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import BoardObjects.AntiAircraft;
import BoardObjects.Ship;
import game.Player;

public class RevealedGameTablePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 631723198551042002L;
	
	private Player player;
	public static int SIZE = 501;
	public RevealedGameTablePanel(Player player) {
		this.player = player;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<player.getWidth();i++)
			for(int j=0;j<player.getHeight();j++) {
				if(player.getBoard()[i][j] == null) 
					g.setColor(Color.GRAY);
				else if(player.getBoard()[i][j].getClass().equals(Ship.class)) {
					Ship s = (Ship) player.getBoard()[i][j];
					if(s.isPartNonDamaged(i, j))
						g.setColor(Color.yellow);
					else
						g.setColor(Color.red);
				}
				else if(player.getBoard()[i][j].getClass().equals(AntiAircraft.class)) 
					g.setColor(Color.blue);
				else
					g.setColor(Color.black);
				g.fillRect(i*(SIZE/player.getWidth()), j*(SIZE/player.getHeight()), 
						SIZE/player.getWidth(), SIZE/player.getHeight());
				
				g.setColor(Color.black);
				g.drawRect(i*(SIZE/player.getWidth()), j*(SIZE/player.getHeight()), 
						SIZE/player.getWidth(), SIZE/player.getHeight());
				
			}
	}
}
