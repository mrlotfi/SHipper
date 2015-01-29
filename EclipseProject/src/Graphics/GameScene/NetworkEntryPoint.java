package Graphics.GameScene;

import game.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Controllers.GameStartActions;
import Graphics.BuildScene.BuildSceneFrame;
import Network.NetworkTransmitter;

@SuppressWarnings("serial")
public class NetworkEntryPoint extends JFrame {
	private boolean Done;
	private NetworkTransmitter trs;
	private Player myPlayer;
	private JButton btnDone;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JTextField textField;
	public NetworkEntryPoint(NetworkTransmitter t) {
		trs = t;
		Done = false;
		
		setBounds(100, 100, 225, 262);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblRows = new JLabel("r");
		lblRows.setBounds(47, 8, 23, 14);
		getContentPane().add(lblRows);
		
		initializeDoneButton();
		
	    spinner = new JSpinner();
		spinner.setBounds(65, 5, 45, 28);
		getContentPane().add(spinner);
		
		JLabel lblCols = new JLabel("c");
		lblCols.setBounds(115, 8, 18, 14);
		getContentPane().add(lblCols);
		
		spinner_1 = new JSpinner();
		spinner_1.setBounds(132, 5, 45, 28);
		getContentPane().add(spinner_1);
		
		textField = new JTextField();
		textField.setBounds(37, 85, 128, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblYourName = new JLabel("Your Name");
		lblYourName.setBounds(37, 60, 128, 23);
		getContentPane().add(lblYourName);
		
		initializeBuildMapButton();
		
	}
	
	private void initializeDoneButton() {
		btnDone = new JButton("Done");
		btnDone.setEnabled(false);
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Done) {
					trs.sendObject(myPlayer);
					JOptionPane.showMessageDialog(null, "Now wait till he builds his map. the game will be started when "
							+ " he has done building his map");
					GameStartActions.waitAfterHosting(trs, myPlayer);
					dispose();
				}
			}
		});
		btnDone.setBounds(37, 150, 128, 23);
		getContentPane().add(btnDone);
	}
	
	private void initializeBuildMapButton() {
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
				btnDone.setEnabled(true);
			}
		});
		getContentPane().add(btnMakeYourMap);
	}
}
