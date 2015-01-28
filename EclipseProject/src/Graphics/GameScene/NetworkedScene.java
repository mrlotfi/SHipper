package Graphics.GameScene;

import game.Player;

import java.awt.JobAttributes;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import sun.nio.ch.Net;
import Controllers.NetworkController;
import Controllers.SingleMachineController;
import Network.NetworkTransmitter;

@SuppressWarnings("serial")
public class NetworkedScene extends JFrame {

	
	
	private NetworkTransmitter trs;
	private GameTablePanel panelEnemy;
	private RevealedGameTablePanel myPanel;
	private NetworkController controller;
	private int owner;
	private JTextArea log;
	private JLabel time;
	public NetworkedScene(final NetworkController controller, int owner,NetworkTransmitter trs) {
		this.trs  =  trs;
		
		setSize(1400,700);
		setLayout(null);
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
		
		final Player player = controller.players[1-owner];
		panelEnemy = new GameTablePanel(player);
		panelEnemy.setBounds(20, 20, GameTablePanel.SIZE, GameTablePanel.SIZE);
		panelEnemy.setVisible(true);
		
		panelEnemy.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				int x = arg0.getX() / (GameTablePanel.SIZE/player.getWidth());
				int y= arg0.getY() / (GameTablePanel.SIZE/player.getHeight());
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
		
		
		myPanel = new RevealedGameTablePanel(controller.players[owner]);
		myPanel.setBounds(20+RevealedGameTablePanel.SIZE+60,20,RevealedGameTablePanel.SIZE, RevealedGameTablePanel.SIZE);
		getContentPane().add(myPanel);
		
		
		JLabel messageLogLabel = new JLabel("Message Log");
		messageLogLabel.setFont(messageLogLabel.getFont().deriveFont(14f));
		messageLogLabel.setBounds(20 + 2*GameTablePanel.SIZE+80, 15, 134, 30);
		
		getContentPane().add(messageLogLabel);
		
		JLabel playerOneName = new JLabel(controller.players[1-owner].getName()+" 's MAP");
		playerOneName.setFont(playerOneName.getFont().deriveFont(14f));
		playerOneName.setBounds(20,20+GameTablePanel.SIZE +5,134,30);
		add(playerOneName);
		
		JLabel playerTwoName = new JLabel(controller.players[owner].getName()+" 's MAP");
		playerTwoName.setFont(playerTwoName.getFont().deriveFont(14f));
		playerTwoName.setBounds(20 + GameTablePanel.SIZE+60,20+GameTablePanel.SIZE +5,134,30);
		add(playerTwoName);
		
		String guide = "Guide: \nGrey: Unknown     Black: Exploded mine    Yellow: Revealed ship \ns"
				+ "Red: Exploded ship     Blue:Exploded antiaircraft     Green: Reavealed empty cell";
		JTextArea guideLabel = new JTextArea(guide,10,10);
		guideLabel.setBounds(20,20+GameTablePanel.SIZE +40 , 600,80);
		guideLabel.setFocusable(false);
		add(guideLabel);
		
		time = new JLabel("Time: 00:00");
		time.setBounds(700, 550, 200, 80);
		time.setFont(time.getFont().deriveFont(28f));
		getContentPane().add(time);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20+2*GameTablePanel.SIZE+80,50,200,450);
		getContentPane().add(scrollPane);
		
		log = new JTextArea();
		log.setEditable(false);
		log.setFocusable(false);
		DefaultCaret caret = (DefaultCaret)log.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(log);
		
		JButton chatBtn = new JButton("Chat");
		chatBtn.setBounds(20+2*GameTablePanel.SIZE+80, 20+GameTablePanel.SIZE+60,200,60);
		chatBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.showChatFrame();
			}
		});
		add(chatBtn);
		
		repaint();
	}
	
	public void appendText(String text) {
		log.append(text);
	}
	
	public void setTime(int t) {
		int sec = t % 60;
		int min = t/60;
		time.setText("Time: "+String.format("%02d",min)+":"+String.format("%02d",sec));
	}
	
	public void repaintAll() {
		// TODO Auto-generated method stub
		myPanel.repaint();
		panelEnemy.repaint();
	}
	
	
	
}
