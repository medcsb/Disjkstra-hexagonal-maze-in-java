package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

/*
 * this class is responsible for the panel and the buttons on the right of the application
 * the buttons in this panel allow for solving the maze, importing and saving mazes and creating a new maze with different height and/or width
 */
public class ControlPanel extends JPanel implements ActionListener{
	
	private MazeApp mazeApp;
	private MazeViewer view;
	
	private JButton solveMaze;
	private JButton load;
	private JButton save;
	private JButton createNewMaze;
	
	private JFileChooser saveFile;
	private JFileChooser loadFile;
	
	//constructor for the panel and its buttons
	public ControlPanel(MazeViewer view, MazeApp mazeApp) {
		this.view = view;
		this.mazeApp = mazeApp;
		this.setLayout(new GridLayout(4, 1));
		this.solveMaze = new JButton("Solve this Maze");
		this.save = new JButton("Save this maze");
		this.load = new JButton("Load a new Maze");
		this.createNewMaze = new JButton("Create a new Maze");
		this.add(solveMaze);
		this.add(save);
		this.add(load);
		this.add(createNewMaze);
		this.solveMaze.addActionListener(this);
		this.save.addActionListener(this);
		this.load.addActionListener(this);
		this.createNewMaze.addActionListener(this);
		this.setBackground(Color.gray);
		this.setVisible(true);
	}

	/*
	 * this method manages the clicked buttons and resulting actions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.solveMaze) {
			this.view.solveMaze();
		}
		if (e.getSource() == this.save) {
			this.saveFile = new JFileChooser();
			this.saveFile.setCurrentDirectory(new File("./data"));
			int responseSave = this.saveFile.showSaveDialog(null);
			if (responseSave == JFileChooser.APPROVE_OPTION) {
				File file = this.saveFile.getSelectedFile();
				this.view.getMaze().saveToTextFile(file.getAbsolutePath());
			}
		}
		if (e.getSource() == this.load) {
			this.loadFile = new JFileChooser();
			this.loadFile.setCurrentDirectory(new File("./data"));
			int responseLoad = this.loadFile.showOpenDialog(null);
			if (responseLoad == JFileChooser.APPROVE_OPTION) {
				File file = new File(this.loadFile.getSelectedFile().getAbsolutePath());
				this.view.getMaze().initFromTextFile(file.getAbsolutePath());
				if (this.view.getMaze().findStart() != null) this.view.setStart(this.view.getMaze().findStart());
				if (this.view.getMaze().findEnd() != null) this.view.setEnd(this.view.getMaze().findEnd());
			}
			this.view.repaint();
		}
		if (e.getSource() == this.createNewMaze) {
			MazeCreation creationMenu = new MazeCreation(this.mazeApp);
		}
	}
}
