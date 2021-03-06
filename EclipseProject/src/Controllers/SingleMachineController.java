package Controllers;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import Graphics.GameScene.Main;
import Graphics.GameScene.SingleMachinceScene;
import game.Game;

public class SingleMachineController extends Game{
	static URL url2 = Main.class.getResource("Win.wav");
	final static AudioClip clip =  Applet.newAudioClip(url2);
	public static void main(String args[]) {
		SingleMachineController c = new SingleMachineController(10, 10);
		c.players[0].setName("hdh");
		SingleMachinceScene s = new SingleMachinceScene(c);
		c.setScene(s);
		s.setVisible(true);
		c.start();
	}
	private SingleMachinceScene scene;
	public SingleMachineController(int width, int height) {
		super(width, height);
	}
	
	public void setScene(SingleMachinceScene scene) {
		this.scene = scene;
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
					clip.play();
					JOptionPane.showMessageDialog(null, SingleMachineController.this.checkWinner().getName()+" wins!");
					scene.dispose();
					
				}
				}
			}
		};
		t.start();
	}
	
	
}
