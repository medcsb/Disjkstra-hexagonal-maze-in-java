package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * this class is a frame that displays an error message
 */
public class MazeCreationError extends JFrame implements ActionListener{
	private JButton close;
	private JPanel panel;
	
	public MazeCreationError() {
		super("ERROR");
		
		panel = new JPanel();
		close = new JButton("Close");
		close.addActionListener(this);
		panel.add(new JLabel("Both inputs must be positive Integers and not null"));
		panel.add(close);
		
		add(panel);
		setPreferredSize(new Dimension(300, 100));
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	//closes the frame when "close" is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == close) {
			dispose();
		}
	}

}
