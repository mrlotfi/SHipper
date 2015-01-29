package Graphics.GameScene;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;



@SuppressWarnings("serial")
public abstract class BaseGameScene extends JFrame {
	private JTextArea log;
	private JLabel time;
	
	public BaseGameScene() {
		setSize(1400,700);
		setLayout(null);
		initializeElements();
	}
	
	private void initializeElements() {
		JLabel messageLogLabel = new JLabel("Message Log");
		messageLogLabel.setFont(messageLogLabel.getFont().deriveFont(14f));
		messageLogLabel.setBounds(20 + 2*GameTablePanel.SIZE+80, 15, 134, 30);
		getContentPane().add(messageLogLabel);
		
		String guide = "Guide: \nGrey: Unknown     Black: Exploded mine    Yellow: Revealed ship \ns"
				+ "Red: Exploded ship     Blue:Exploded antiaircraft     Green: Reavealed empty cell";
		JTextArea guideLabel = new JTextArea(guide,10,10);
		guideLabel.setBounds(20,20+GameTablePanel.SIZE +40 , 600,80);
		guideLabel.setFocusable(false);
		getContentPane().add(guideLabel);
		
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
	
	public abstract void repaintAll();
	
}
