package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import maze.Maze;
import maze.MazeBox;
/*
 * this class is the ones responsible for the popup menu when right-clicking on a mazeBox
 * When you right-click on a mazeBox it gives you the option to either set it to a wall, empty, start or end box
 * (start and end meaning Departure and Arrival)
 */
public class BoxEdit extends JPopupMenu implements ActionListener{
	
	private MazeBox edited; //this mazeBox represents the mazeBox that is selected and is going to be edited
	private MazeViewer view;
	private JMenuItem start = new JMenuItem("set as Start");
	private JMenuItem end = new JMenuItem("set as End");
	private JMenuItem empty = new JMenuItem("set as Empty");
	private JMenuItem wall = new JMenuItem("set as Wall");
	
	//constructor for the popup menu
	public BoxEdit(MazeViewer view) {
		this.view = view;
		this.edited = this.view.getSelected();
		this.add(start);
		this.add(end);
		this.add(empty);
		this.add(wall);
		this.setVisible(true);
		this.start.addActionListener(this);
		this.end.addActionListener(this);
		this.empty.addActionListener(this);
		this.wall.addActionListener(this);
		this.start.setActionCommand("start");
		this.end.setActionCommand("end");
		this.empty.setActionCommand("empty");
		this.wall.setActionCommand("wall");
	}

	/*
	 * this method manages the clicked option and changes the selected box's type to the selected option
	 * (it resets the shortest path at the end to avoid having it drawn again when we change a box)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		try {
			switch(command) {
				case "start" :
					this.view.setStart(this.edited);
					this.view.repaint();
					break;
				case "end" :
					this.view.setEnd(this.edited);
					this.view.repaint();
					break;
				case "empty" :
					this.view.getMaze().setBox(this.edited.getLine(), this.edited.getColumn(), 'E');
					this.view.repaint();
					break;
				case "wall" :
					this.view.getMaze().setBox(this.edited.getLine(), this.edited.getColumn(), 'W');
					this.view.repaint();
					break;
			}
		}
		finally {
			this.view.resetPath();
		}
		
	}

}
