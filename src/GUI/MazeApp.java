package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import maze.Maze;
/*
 * this class(JFrame) is the main frame containing all the panels that show and allow manipulation of the maze
 * In the main menu you can create or load a maze or read about how to use the application
 */
public class MazeApp extends JFrame implements ActionListener{
	
	private MazeViewer mazeViewer;
	private ControlPanel control;
	private BoxEditor boxEditor;
	
	private JPanel mainMenuP;
	private JButton newMazeB;
	private JButton loadMaze;
	private JButton manuel;
	
	//constructor of the frame and its contents
	public MazeApp() {
		mainMenuP = new JPanel();
		newMazeB = new JButton("Create a new Maze");
		loadMaze = new JButton("Load a new maze");
		manuel = new JButton("How to use ?");
		
		mainMenuP.setLayout(new GridLayout(3, 1));
		mainMenuP.add(newMazeB);
		mainMenuP.add(loadMaze);
		mainMenuP.add(manuel);
		add(mainMenuP);
		
		newMazeB.addActionListener(this);
		loadMaze.addActionListener(this);
		manuel.addActionListener(this);
		
		setTitle("Maze App");
		setPreferredSize(new Dimension(300, 300));
		setVisible(true);
		this.pack();
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	/*
	 * method that is called when we create a maze
	 * allows us to view the maze using the MazeViewer
	 */
	public void viewMaze(Maze maze) {
		getContentPane().removeAll();
		mazeViewer = new MazeViewer(this, maze);
		boxEditor = new BoxEditor(mazeViewer);
		control = new ControlPanel(mazeViewer, this);
		mazeViewer.setVisible(true);
		//setting the start and the end of the maze for the maze viewer to avoid looking in the entire maze for them later
		if (maze.findStart() != null) mazeViewer.setStart(maze.findStart());
		if (maze.findEnd() != null) mazeViewer.setEnd(maze.findEnd());
		
		this.add(mazeViewer, BorderLayout.CENTER);
		this.add(control, BorderLayout.EAST);
		this.add(boxEditor, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(700, 700));
		this.pack();
	}

	/*
	 * this method manages the actions of the button clicks
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newMazeB) {
			MazeCreation creationMenu = new MazeCreation(this);
		}
		if(e.getSource()==loadMaze) {
			JFileChooser loadFile = new JFileChooser();
			loadFile.setCurrentDirectory(new File("./data"));
			int responseLoad = loadFile.showOpenDialog(null);
			if (responseLoad == JFileChooser.APPROVE_OPTION) {
				File file = new File(loadFile.getSelectedFile().getAbsolutePath());
				Maze maze = new Maze(0, 0);
				maze.initFromTextFile(file.getAbsolutePath());
				viewMaze(maze);
			}
		}
		if(e.getSource()==manuel) {
			System.out.print("B");
			Manuel manuel = new Manuel();
		}
	}
	
	public BoxEditor getEditor() {
		return boxEditor;
	}
}
