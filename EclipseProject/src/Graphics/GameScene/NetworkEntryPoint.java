package Graphics.GameScene;

import game.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Controllers.NetworkController;
import Graphics.BuildScene.BuildSceneFrame;
import Network.NetworkTransmitter;

public class NetworkEntryPoint extends JFrame {
	public static void main(String args[]) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		NetworkEntryPoint n = new NetworkEntryPoint(null);
		n.setVisible(true);
	}
	
	
	private boolean Done;
	private NetworkTransmitter trs;
	Player myPlayer;
	public NetworkEntryPoint(NetworkTransmitter t) {
		trs = t;
		
		Done = false;
		
		
		setBounds(100, 100, 225, 262);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblRows = new JLabel("r");
		lblRows.setBounds(47, 8, 23, 14);
		getContentPane().add(lblRows);
		
		final JButton btnNewButton = new JButton("Done");
		btnNewButton.setEnabled(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Done) {
					trs.sendObject(myPlayer);
					JOptionPane.showMessageDialog(null, "Now wait till he builds his map. the game will be started when "
							+ " he has done building his map");
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
								e.printStackTrace();
							}
						}
					};
					t.start();
					dispose();
				}
			}
		});
		btnNewButton.setBounds(37, 150, 128, 23);
		getContentPane().add(btnNewButton);
		
		final JSpinner spinner = new JSpinner();
		spinner.setBounds(65, 5, 45, 28);
		getContentPane().add(spinner);
		
		JLabel lblCols = new JLabel("c");
		lblCols.setBounds(115, 8, 18, 14);
		getContentPane().add(lblCols);
		
		final JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(132, 5, 45, 28);
		getContentPane().add(spinner_1);
		
		final JTextField textField = new JTextField();
		textField.setBounds(37, 85, 128, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblYourName = new JLabel("Your Name");
		lblYourName.setBounds(37, 60, 128, 23);
		getContentPane().add(lblYourName);
		
		JButton btnMakeYourMap = new JButton("Make your map");
		btnMakeYourMap.setBounds(37, 120, 128, 23);
		btnMakeYourMap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rows = Math.abs(Integer.parseInt(spinner.getValue().toString()));
				int cols = Math.abs(Integer.parseInt(spinner_1.getValue().toString()));
				if(rows<10 || rows>20)
					rows = 10;
				if(cols<10 || cols>20)
					cols = 10;
				spinner.setEnabled(false);
				spinner_1.setEnabled(false);
				myPlayer = new Player(rows,cols,0);
				myPlayer.setName(textField.getText());
				BuildSceneFrame frame = new BuildSceneFrame(myPlayer, rows, cols);
				Done = true;
				frame.setVisible(true);
				btnNewButton.setEnabled(true);
			}
		});
		getContentPane().add(btnMakeYourMap);
		
		
	}
}
