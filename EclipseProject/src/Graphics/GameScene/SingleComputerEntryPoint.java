package Graphics.GameScene;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import Controllers.SingleMachineController;
import Graphics.BuildScene.BuildSceneFrame;

@SuppressWarnings("serial")
public class SingleComputerEntryPoint extends JFrame {
	private boolean firstCompleted = false ,secondCompleted = false;
	private JButton btnDone = null;
	private SingleMachineController controller;
	private int rows,cols;
	private JSpinner spinner,spinner_1;
	private JTextField textField, textField_1;
	public SingleComputerEntryPoint() {
		setTitle("Build a new game");
		setBounds(100, 100, 293, 300);
		getContentPane().setLayout(null);
		
		initializeConstantElements();
		
		spinner = new JSpinner();
		spinner.setBounds(81, 5, 52, 24);
		getContentPane().add(spinner);
		spinner_1 = new JSpinner();
		spinner_1.setBounds(180, 5, 52, 24);
		getContentPane().add(spinner_1);
		
		JTextField textField = new JTextField();
		textField.setBounds(44, 58, 188, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(44, 114, 188, 28);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		initializeDynamicElements();
	}
	
	private void initializeConstantElements() {
		JLabel lblNumOfRows = new JLabel("rows");
		lblNumOfRows.setBounds(44, 8, 29, 14);
		getContentPane().add(lblNumOfRows);
		JLabel lblNewLabel = new JLabel("cols");
		lblNewLabel.setBounds(143, 8, 39, 14);
		getContentPane().add(lblNewLabel);
		JLabel lblPlayerName = new JLabel("Player 1 name:");
		lblPlayerName.setBounds(44, 33, 94, 14);
		getContentPane().add(lblPlayerName);
		JLabel lblPlayerName_1 = new JLabel("Player 2 name:");
		lblPlayerName_1.setBounds(44, 89, 94, 14);
		getContentPane().add(lblPlayerName_1);
	}
	
	private void initializeDynamicElements() {
		final JButton btnPlayerBuild = new JButton("Player 1 build map");
		btnPlayerBuild.setBounds(44, 145, 188, 23);
		btnPlayerBuild.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rows = Math.abs(Integer.parseInt(spinner.getValue().toString()));
				int cols = Math.abs(Integer.parseInt(spinner_1.getValue().toString()));
				if(rows < 10 || rows >20)
					rows = 10;
				if(cols <10 || cols>20)
					cols = 10;
				spinner.setEnabled(false);
				spinner_1.setEnabled(false);
				if(!secondCompleted) 
					controller = new SingleMachineController(rows,cols);
				BuildSceneFrame frame1 = new BuildSceneFrame(controller.players[0], rows, cols);
				JOptionPane.showMessageDialog(null,"Player 1"+
						" build your map "+"player 2"+
						" please wait and don't watch his map!");
				frame1.setVisible(true);
				btnPlayerBuild.setEnabled(false);
				firstCompleted = true;
				if(secondCompleted && firstCompleted)
					btnDone.setEnabled(true);
			}
			
		});
		getContentPane().add(btnPlayerBuild);
		
		final JButton btnPlayerBuild_1 = new JButton("Player 2 build map");
		btnPlayerBuild_1.setBounds(44, 179, 188, 23);
		btnPlayerBuild_1.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rows = Math.abs(Integer.parseInt(spinner.getValue().toString()));
				cols = Math.abs(Integer.parseInt(spinner_1.getValue().toString()));
				if(rows < 10 || rows >20)
					rows = 10;
				if(cols <10 || cols>20)
					cols = 10;
				spinner.setEnabled(false);
				spinner_1.setEnabled(false);
				if(!firstCompleted) 
					controller = new SingleMachineController(rows,cols);
				BuildSceneFrame frame2 = new BuildSceneFrame(controller.players[1], rows, cols);
				JOptionPane.showMessageDialog(null,"Player 2"+
						" build your map "+"player 1"+
						" please wait and don't watch his map!");
				frame2.setVisible(true);
				btnPlayerBuild_1.setEnabled(false);
				secondCompleted = true;
				if(secondCompleted && firstCompleted)
					btnDone.setEnabled(true);
			}
			
		});
		getContentPane().add(btnPlayerBuild_1);
		
		btnDone = new JButton("Done");
		btnDone.setBounds(44, 210, 188, 23);
		btnDone.setEnabled(false);
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnDone.isEnabled()) {
					controller.players[0].setName(textField.getText());
					controller.players[1].setName(textField_1.getText());
					SingleMachinceScene scene = new SingleMachinceScene(controller);
					controller.setScene(scene);
					scene.setVisible(true);
					controller.start();
					dispose();
				}
			}
		});
		getContentPane().add(btnDone);
	}
}
