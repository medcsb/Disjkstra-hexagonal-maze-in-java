package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * this class is a frame that displays an error message
 */
public class MazeSolvingError extends JFrame implements ActionListener{

	private JButton close;
	private JPanel panel;
	private String errorMessage;
	
	//constructor
	public MazeSolvingError() {
		super("ERROR");
		
		errorMessage = "Maze unsolvable or missing start or end";
		panel = new JPanel();
		close = new JButton("close");
		close.addActionListener(this);
		panel.add(new JLabel(errorMessage));
		panel.add(close);
		
		add(panel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(300, 100));
		pack();
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
