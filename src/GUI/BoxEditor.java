package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/*
 * this class is responsible for the buttons at the bottom of the application
 * these buttons allow the user to change a mazeBox by clicking it
 */
public class BoxEditor extends JPanel implements ActionListener{
	
	private MazeViewer view;
	private JButton eButton;   //when clicked each new box we click on becomes Empty
	private JButton wButton;   //when clicked each new box we click on becomes a Wall
	private JButton reset;     //when clicked resets every box to an Empty box (resets the maze to an empty maze)
	private JButton exitMode;  //when clicked exits paintMode which means clicking on a box won't change it anymore
	private char mode;         //char that stores the type of mazeBox we want to paint
	
	/*
	 * constructor for the panel containing the 4 buttons
	 * PS : the departure and arrival types can be set by right-clicking and choosing them in the popup menu
	 */
	public BoxEditor(MazeViewer view) {
		this.view = view;
		this.eButton = new JButton("Empty");
		this.wButton = new JButton("Wall");
		this.reset = new JButton("reset");
		this.exitMode = new JButton("Exit Paint Mode");
		this.setLayout(new GridLayout(1, 6));
		this.add(this.eButton);
		this.add(this.wButton);
		this.add(this.reset);
		this.add(this.exitMode);
		this.eButton.addActionListener(this);
		this.wButton.addActionListener(this);
		this.reset.addActionListener(this);
		this.exitMode.addActionListener(this);
		this.setVisible(true);
	}

	/*
	 * this method manages the clicked buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.eButton) {
			this.view.setEditor();
			this.view.paintModeON();
			this.mode = 'E';
		}
		if (e.getSource() == this.wButton) {
			this.view.setEditor();
			this.view.paintModeON();
			this.mode = 'W';
		}
		if (e.getSource() == this.reset) {
			this.view.setEditor();
			this.view.resetMaze();
			this.view.paintModeOFF();
		}
		if (e.getSource() == this.exitMode) {
			this.view.setEditor();
			this.view.paintModeOFF();
		}
	}
	
	//getter for the mode
	public char getMode() {
		return this.mode;
	}
}
