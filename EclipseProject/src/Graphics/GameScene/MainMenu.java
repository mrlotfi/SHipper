package Graphics.GameScene;

import game.Player;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javafx.scene.layout.Border;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Controllers.NetworkController;
import Graphics.BuildScene.BuildSceneFrame;
import Network.NetworkTransmitter;

@SuppressWarnings("serial")
public class MainMenu extends JFrame{

	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		//
		MainMenu m = new MainMenu();
		m.setVisible(true);
	}
	
	
	public MainMenu() {
		setBounds(100, 100, 300, 120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
		});
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton singleButton = new JButton("Play on this computer with your friend");
		getContentPane().add(singleButton);
		singleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SingleComputerEntryPoint entryPoint = new SingleComputerEntryPoint();
				entryPoint.setVisible(true);
			}
		});
		
		JButton hostBut = new JButton("Host a game");
		hostBut.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "I'm waiting for some one to connect. I will Inform you in time");
				Thread t = new Thread() {
					NetworkTransmitter trs;
					public void run() {
						trs = new NetworkTransmitter(true);
						if(trs.waitForConnection()) {
							NetworkEntryPoint entryPoint = new NetworkEntryPoint(trs);
							entryPoint.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "You are already hosting a game! first finish that :|");
					}
				};
				t.start();
			}
		});
		getContentPane().add(hostBut);
		
		JButton joinBut = new JButton("Join a game");
		joinBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String s = JOptionPane.showInputDialog(null,"Please enter address of computer you want to connect");
				final NetworkTransmitter trs = new NetworkTransmitter(false);
				if(trs.tryToConnect(s)) {
					trs.setOPIP(s);
					JOptionPane.showMessageDialog(null, "now wait till he makes the game size and his map. I will inform you in time");
					
					Thread t = new Thread() {
						Player hisPlayer;
						public void run() {
							hisPlayer = null;
							try {
								hisPlayer = (Player) trs.recieveObject();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
							final Player myPlayer = new Player(hisPlayer.getWidth(), hisPlayer.getHeight(), 1);
							BuildSceneFrame f = new BuildSceneFrame(myPlayer, myPlayer.getWidth(), myPlayer.getHeight());
							f.setVisible(true);
							
							final JFrame frame = new JFrame();
							frame.setBounds(200,200,170,300);
							frame.setLayout(null);
							frame.setVisible(true);
							final JTextField field = new JTextField("Enter Your name here");
							field.setBounds(20,100,100,30);
							frame.getContentPane().add(field);
							JButton btn = new JButton("Start!");
							btn.setBounds(20, 20, 100, 50);
							
							btn.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {
									NetworkController c = new NetworkController(myPlayer.getWidth(), myPlayer.getHeight());
									c.setTransmitter(trs);
									c.players[0] = hisPlayer;
									c.players[1] = myPlayer;
									myPlayer.setName(field.getText());
									trs.sendObject(c);	
									trs.setNetworkController(c);
									trs.startListening();
									
									NetworkedScene s = new NetworkedScene(c, 1,trs);
									s.setVisible(true);
									c.setScene(s);
									c.setChatFrame(myPlayer.getName());
									c.start();
									frame.dispose();
								}
							});
							
							frame.getContentPane().add(btn);
							
						}
					};
					t.start();
				}
				else {
					JOptionPane.showMessageDialog(null, "No computer is waiting with such address");
				}
			}
		});
		getContentPane().add(joinBut);
		
		
	}

}
