package Controllers;

import java.util.concurrent.TimeUnit;

import Graphics.GameScene.SingleMachinceScene;
import game.Game;

public class SingleMachineController extends Game{
	public static void main(String args[]) {
		SingleMachinceScene s = new SingleMachinceScene();
		SingleMachineController c = new SingleMachineController(10, 10, s);
		s.setController(c);
	}
	private SingleMachinceScene scene;
	public SingleMachineController(int width, int height, SingleMachinceScene scene) {
		super(width, height);
		this.scene = scene;
	}
	
	public void start() {
		Thread t = new Thread()  {
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				shiftTimeAndRun();
				scene.repaintAll();
				if(gameFinished()) {
					//do sth and congrats the winner
				}
			}
		};
		t.start();
	}
}
