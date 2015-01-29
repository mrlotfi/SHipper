package Graphics.GameScene;

import game.Player;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controllers.NetworkController;
import Network.NetworkTransmitter;

@SuppressWarnings("serial")
public class NetworkedScene extends BaseGameScene{
	private NetworkTransmitter trs;
	private GameTablePanel panelEnemy;
	private RevealedGameTablePanel myPanel;
	private NetworkController controller;
	private int owner;
	public NetworkedScene(final NetworkController controller, int owner,NetworkTransmitter trs) {
		this.trs  =  trs;
		this.controller = controller;
		this.owner = owner;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				JOptionPane.showMessageDialog(null, "go to hell");
				NetworkedScene.this.trs.sendString("e");
				NetworkedScene.this.trs.stop();
			}
		});
		
		initializeEnemyPanel();
		initializeMyPanel();
		
		initializeOthers();
		
		repaint();
	}
	
	private void initializeMyPanel() {
		myPanel = new RevealedGameTablePanel(controller.players[owner]);
		myPanel.setBounds(20+RevealedGameTablePanel.SIZE+60,20,RevealedGameTablePanel.SIZE, RevealedGameTablePanel.SIZE);
		getContentPane().add(myPanel);
	}
	
	private void initializeEnemyPanel() {
		final Player player = controller.players[1-owner];
		panelEnemy = new GameTablePanel(player);
		panelEnemy.setBounds(20, 20, GameTablePanel.SIZE, GameTablePanel.SIZE);
		panelEnemy.setVisible(true);
		
		panelEnemy.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				int x = arg0.getX() / (GameTablePanel.SIZE/player.getWidth());
				int y = arg0.getY() / (GameTablePanel.SIZE/player.getHeight());
				panelEnemy.setActiveCell(x, y);
				repaintAll();
			}

		});
		panelEnemy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch(arg0.getButton()) {
				case MouseEvent.BUTTON1:
					if(NetworkedScene.this.trs.sendString("a,"+panelEnemy.getActiveX()+
							","+panelEnemy.getActiveY()+","+NetworkedScene.this.owner+","
							+(1-NetworkedScene.this.owner)) ) 
						controller.addAttackStatement(panelEnemy.getActiveX(), panelEnemy.getActiveY(),
								NetworkedScene.this.owner, (1-NetworkedScene.this.owner));
					else {
						JOptionPane.showMessageDialog(null, "I think network is dc'd or opponent has left the game.sorry");
						System.exit(1);
					}
					break;
				case MouseEvent.BUTTON2:
					if(NetworkedScene.this.trs.sendString("A,"+panelEnemy.getActiveY()+
							","+NetworkedScene.this.owner+","
							+(1-NetworkedScene.this.owner)))
						controller.addAircraftStatement(panelEnemy.getActiveY(), NetworkedScene.this.owner, 
								1-NetworkedScene.this.owner);
					else {
						JOptionPane.showMessageDialog(null, "I think network is dc'd or opponent has left the game.sorry");
						System.exit(1);
					}
					break;
				case MouseEvent.BUTTON3:
					if (NetworkedScene.this.trs.sendString("r,"+panelEnemy.getActiveX()+","+
							panelEnemy.getActiveY()+","+NetworkedScene.this.owner+","
							+(1-NetworkedScene.this.owner)) ) 
						controller.addRadarStatement(panelEnemy.getActiveX(),panelEnemy.getActiveY()
								,NetworkedScene.this.owner,(1-NetworkedScene.this.owner));
					else {
						JOptionPane.showMessageDialog(null, "I think network is dc'd or opponent has left the game.sorry");
						System.exit(1);
					}
					break;
				}
			}
		});
		getContentPane().add(panelEnemy);
	}
	
	private void initializeOthers() {
		JLabel playerOneName = new JLabel(controller.players[1-owner].getName()+" 's MAP");
		playerOneName.setFont(playerOneName.getFont().deriveFont(14f));
		playerOneName.setBounds(20,20+GameTablePanel.SIZE +5,134,30);
		add(playerOneName);
		
		JLabel playerTwoName = new JLabel(controller.players[owner].getName()+" 's MAP");
		playerTwoName.setFont(playerTwoName.getFont().deriveFont(14f));
		playerTwoName.setBounds(20 + GameTablePanel.SIZE+60,20+GameTablePanel.SIZE +5,134,30);
		add(playerTwoName);
		
		
		JButton chatBtn = new JButton("Chat");
		chatBtn.setBounds(20+2*GameTablePanel.SIZE+80, 20+GameTablePanel.SIZE+60,200,60);
		chatBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {controller.showChatFrame();}
		});
		add(chatBtn);
	}
	
	public void repaintAll() {
		// TODO Auto-generated method stub
		myPanel.repaint();
		panelEnemy.repaint();
	}
	
	
	
}
