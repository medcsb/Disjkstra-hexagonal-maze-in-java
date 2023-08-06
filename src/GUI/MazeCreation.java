package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.AncestorListener;

import maze.Maze;

/*
 * this class is a frame that pops up when creating a new maze, it asks the user for input and creates a corresponding maze
 */
public class MazeCreation extends JFrame implements ActionListener{
	private MazeApp mazeApp;
	
	private JTextField heightField;
	private JTextField widthField;
	private final JLabel labelHeight;
	private final JLabel labelWidth;
	
	private JPanel panel;
	private JButton button;
	
	//constructor for the MazeCreation frame
	public MazeCreation(MazeApp mazeApp) {
		this.mazeApp = mazeApp;
	
		labelHeight = new JLabel("set Height :");
		labelWidth = new JLabel("set Width :");
		panel = new JPanel();
		button = new JButton("Create Maze");
		button.addActionListener(this);
		heightField = new JTextField(20);
		widthField = new JTextField(20);
		
		heightField.setMaximumSize(this.heightField.getPreferredSize());
		widthField.setMaximumSize(this.widthField.getPreferredSize());
		panel.add(labelHeight);
		panel.add(heightField);
		panel.add(labelWidth);
		panel.add(widthField);
		panel.add(button);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		setTitle("Create Maze");
		setPreferredSize(new Dimension(250, 150));
		pack();
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setVisible(true);
		add(panel);
	}
	
	/*
	 * creates a new maze with the inputs of the user
	 * shows an error message if the inputs are invalid
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.button) {
			try {
				this.mazeApp.viewMaze(new Maze(Integer.parseInt(this.heightField.getText()), Integer.parseInt(this.widthField.getText())));
				this.dispose();
			}
			catch(NumberFormatException | NegativeArraySizeException ex) {
				MazeCreationError errorMessage = new MazeCreationError();
			}
		}
	}
}
