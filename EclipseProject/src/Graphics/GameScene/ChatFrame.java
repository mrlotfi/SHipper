package Graphics.GameScene;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import Network.NetworkTransmitter;

public class ChatFrame extends JFrame {
	
	public static void main(String args[]) {
		ChatFrame f = new ChatFrame(null, "kosdfsf");
		f.setVisible(true);
	}

	private NetworkTransmitter trs;
	private String myName;
	private JTextArea textArea_2;
	public ChatFrame(NetworkTransmitter trans, String name) {
		
		
		this.trs = trans;
		myName = name;
		setBounds(100, 100, 429, 340);
		getContentPane().setLayout(null);

		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 12, 302, 79);
		getContentPane().add(scrollPane);
		
		final JTextArea textArea = new JTextArea("");
		
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 105, 393, 186);
		getContentPane().add(scrollPane_1);
		
		
		textArea_2 = new JTextArea("");
		textArea_2.setEditable(false);
		scrollPane_1.setViewportView(textArea_2);
		DefaultCaret caret = (DefaultCaret)textArea_2.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane_1.setViewportView(textArea_2);
		
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBounds(320, 12, 83, 79);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				trs.sendString("c"+myName+": " + textArea.getText());
				textArea_2.append(myName+": "+textArea.getText());
				textArea.setText("");
			}
		});
		getContentPane().add(btnNewButton);
	}
	
	public void appendRecievedMessage(String text) {
		textArea_2.append(text);
	}
}
