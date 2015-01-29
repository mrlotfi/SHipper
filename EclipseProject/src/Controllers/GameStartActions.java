package Controllers;

import game.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Graphics.BuildScene.BuildSceneFrame;
import Graphics.GameScene.NetworkEntryPoint;
import Graphics.GameScene.NetworkedScene;
import Network.NetworkTransmitter;

public class GameStartActions {
	public static void startHosting() {
		JOptionPane.showMessageDialog(null, "I'm waiting for some one to connect. I will Inform you in time");
		Thread t = new Thread() {
			NetworkTransmitter trs;
			public void run() {
				trs = new NetworkTransmitter(true);
				if(trs.waitForConnection()) {
					NetworkEntryPoint entryPoint = new NetworkEntryPoint(trs);
					entryPoint.setVisible(true);
				}
			}
		};
		t.start();
	}
	
	public static void startJoiningHost() {
		String s = JOptionPane.showInputDialog(null,
				"Please enter address of computer you want to connect");
		final NetworkTransmitter trs = new NetworkTransmitter(false);
		if(trs.tryToConnect(s)) {
			trs.setOPIP(s);
			JOptionPane.showMessageDialog(null, "now wait till he makes the game size and his map. "
					+ "I will inform you in time");
			
			final JFrame frame = new JFrame();
			frame.setBounds(200,200,299,141);
			frame.setLayout(null);
			frame.setVisible(false);
			final JTextField field = new JTextField("Enter Your name here");
			field.setBounds(10, 11, 263, 30);
			frame.getContentPane().add(field);
			final JButton btn = new JButton("Start!");
			btn.setBounds(10, 42, 263, 30);
			frame.getContentPane().add(btn);
			
			
			Thread t = new Thread() {
				Player hisPlayer;
				public void run() {
					hisPlayer = null;
					try {
						hisPlayer = (Player) trs.recieveObject();
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "I coudln't recieve correct information. try later");
						trs.stop();
						return;
					}
					final Player myPlayer = new Player(hisPlayer.getWidth(), hisPlayer.getHeight(), 1);
					BuildSceneFrame f = new BuildSceneFrame(myPlayer, myPlayer.getWidth(), myPlayer.getHeight());
					f.setVisible(true);
					frame.setVisible(true);
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
				}
			};
			t.start();
		}
		else {
			JOptionPane.showMessageDialog(null, "No computer is waiting with such address");
		}
	}
	
	public static void waitAfterHosting(final NetworkTransmitter trs, final Player myPlayer) {
		Thread t = new Thread() {
			public void run() {
				try {
					NetworkController controller = (NetworkController) trs.recieveObject();
					NetworkedScene s = new NetworkedScene(controller, 0,trs);
					controller.setTransmitter(trs);
					controller.setScene(s);
					s.setVisible(true);
					trs.setNetworkController(controller);
					trs.startListening();
					controller.setChatFrame(myPlayer.getName());
					controller.start();
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Sth gone wrong. Try later");
					trs.stop();
				}
			}
		};
		t.start();
	}
}
