package Controllers;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import Graphics.GameScene.ChatFrame;
import Graphics.GameScene.NetworkedScene;
import Network.NetworkTransmitter;
import game.Game;

public class NetworkController extends Game {
	private NetworkedScene scene;
	private transient NetworkTransmitter trs;
	private transient ChatFrame chatFrame;
	public NetworkController(int width, int height) {
		super(width, height);
		this.trs = trs;
	}
	
	public void setTransmitter(NetworkTransmitter trs) {
		this.trs = trs;
	}
	
	public void setScene(NetworkedScene scene) {
		this.scene = scene;
	}
	
	public void setChatFrame(String name) {
		this.chatFrame = new ChatFrame(trs, name);
	}
	
	public void showChatFrame() {
		chatFrame.setVisible(true);
	}
	
	public void start() {
		Thread t = new Thread()  {
			public void run() {
				while(true) {
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				scene.appendText(shiftTimeAndRun());
				scene.setTime(getCurrentTime());
				scene.repaintAll();
				if(gameFinished()) {
					JOptionPane.showMessageDialog(null,checkWinner().getName()+" wins!");
					scene.dispose();
					this.stop();
				}
				}
			}
		};
		t.start();
	}
	
	public void parseInput(String s) {
		if(s.charAt(0) == 'a') {
			String[] temp = s.split(",");
			addAttackStatement(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), 
					Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
		}
		else if(s.charAt(0) == 'A') {
			String[] temp = s.split(",");
			addAircraftStatement(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), 
					Integer.parseInt(temp[3]));
		}
		else if(s.charAt(0) == 'r') {
			String[] temp = s.split(",");
			addRadarStatement(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), 
					Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
		}
		else if(s.charAt(0) == 'e') {
			JOptionPane.showMessageDialog(null, "Sorry. Your fu***** opponent had a rage quit.You are absolute winner ;)");
			scene.dispose();
			trs.stop();
		}
		else if(s.charAt(0) == 'c') {
			chatFrame.appendRecievedMessage(s.substring(1));
			showChatFrame();
		}
		
	}
}
