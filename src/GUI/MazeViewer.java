package GUI;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import graph.Dijkstra;
import graph.Graph;
import graph.ShortestPathsImpl;
import graph.Vertex;
import maze.Maze;
import maze.MazeBox;
import model.BoxModel;
import model.MazeModel;

/*
 * this class is responsible for showing the maze
 */
public class MazeViewer extends JPanel implements MouseListener{
	
	private Maze maze;                  //this attribute stores the maze that is being shown
	private MazeApp mazeApp;
	private MazeModel mazeModel;        
	private BoxModel[][] boxes;
	private BoxEdit edit;
	private BoxEditor boxEditor;
	
	private MazeBox selected;          //this attribute stores the selected mazeBox (to be eventually changed)
	private MazeBox start;             //this attribute stores the start/Departure of the maze (that way the viewer doesn't have to look in the entire maze for the start each time)
	private MazeBox end;               //this attribute stores the end/Arrival of the maze (that way the viewer doesn't have to look in the entire maze for the end each time)
	private ArrayList<Vertex> path;    //this attribute stores the shortest path from start to end (so that it can be coloured easily)
	private boolean paintMode;         //this attribute tells the mazeViewer wether or not it's in paintMode (paintMode means that if you click on a box it will change its type)
	
	//constructor for the mazeViewer
	public MazeViewer(MazeApp mazeApp, Maze maze) {
		this.mazeApp = mazeApp;
		this.maze = maze;
		this.addMouseListener(this);
		this.paintMode = false;
	}
	
	//painting the maze using  a MazeModel
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.mazeModel = new MazeModel(this);
		this.boxes = this.mazeModel.getBoxes();
		this.mazeModel.drawMaze(g);
	}

	//this method manages the each click on the maze
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i=0; i<this.maze.getHEIGHT(); i++) {
			for (int j=0; j<this.maze.getWIDTH(); j++) {
				
				if (boxes[i][j].contains(e.getPoint())) {
					//the clicked box is selected
					this.selected = this.maze.getBox(i, j);
					
					//left click
					if (e.getModifiers() == MouseEvent.BUTTON1_MASK && e.getClickCount() == 1 && this.paintMode == true) {
						this.maze.setBox(i, j, this.boxEditor.getMode());
						this.resetPath();
					}
					
					//right click
					if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
						this.edit = new BoxEdit(this);
						edit.show(this, e.getX(), e.getY());
					}
					
					//repainting and exiting the for loops
					repaint();
					i = this.maze.getHEIGHT();
					j = this.maze.getWIDTH();
				}
			}
		}
	}
	
	/*
	 * this method calls the Dijkstra algorithm to find the shortest path and store it in the attribute path
	 * if there is no path from start to end or either the start or the end are missing then it shows a MazeSolvingError
	 * returns 1 if the maze is solved, 0 otherwise
	 * (the return has no purpose it's only used to get out of the method
	 */
	public int solveMaze() {
		
		//checks if there is a start and an end
		if (start==null || end== null) {
			path = null;
			MazeSolvingError error = new MazeSolvingError();
			this.repaint();
			return 0;
		}
		
		//calls dijkstra
		MazeBox departure = maze.getBox(this.start.getLine(), this.start.getColumn());
		MazeBox arrival = maze.getBox(this.end.getLine(), this.end.getColumn());
		ShortestPathsImpl shortestPaths = (ShortestPathsImpl) Dijkstra.dijkstra((Graph)this.maze, (Vertex)departure, (Vertex)arrival);
		this.path = shortestPaths.getPath((Vertex) arrival);
		
		//checks if the path is a solution i.e : is a path from start to end
		if(!shortestPaths.isSolution(path, start, end)) {
			MazeSolvingError error = new MazeSolvingError();
			path = null;
			return 0;
		}
		
		//removes the start and the end from the path (so as to not colour them later)
		this.path.remove(0);
        this.path.remove(this.path.size()-1);
		this.repaint();
		return 1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//resets the maze with Empty boxes
	public void resetMaze() {
		this.maze = new Maze (this.maze.getHEIGHT(), this.maze.getWIDTH());
		repaint();
	}
	
	//setter for BoxEditor
	public void setEditor() {
		this.boxEditor = this.mazeApp.getEditor();
	}
	
	//getter for the selected MazeBox
	public MazeBox getSelected() {
		return this.selected;
	}
	
	//getter for the maze
	public Maze getMaze() {
		return this.maze;
	}
	
	/*
	 * setter for the start
	 * written in such a way that if you try to have two starting boxes the new one will become the new start and the other will turn Empty
	 * (to avoid having 2 starts)
	 */
	public void setStart(MazeBox start) {
		if (this.start == null) this.start = start;
		else {
			if(maze.getBox(this.start.getLine(), this.start.getColumn()).getBoxSymbol() == "D") maze.setBox(this.start.getLine(), this.start.getColumn(), 'E');
			this.start = start;
		}
		maze.setBox(this.start.getLine(), this.start.getColumn(), 'D');
		this.start = maze.getBox(this.start.getLine(), this.start.getColumn());
	}
	
	/*
	 * setter for the end
	 * written in such a way that if you try to have two ending boxes the new one will become the new end and the other will turn Empty
	 * (to avoid having 2 ends)
	 */
	public void setEnd(MazeBox end) {
		if (this.end == null) this.end = end;
		else {
			if(maze.getBox(this.end.getLine(), this.end.getColumn()).getBoxSymbol() == "A") maze.setBox(this.end.getLine(), this.end.getColumn(), 'E');
			this.end = end;
		}
		maze.setBox(this.end.getLine(), this.end.getColumn(), 'A');
		this.end = maze.getBox(this.end.getLine(), this.end.getColumn());
	}
	
	//resets the path to Empty (used to avoid drawing the path even when the maze changes)
	public void resetPath() {
		this.path = null;
	}
	
	//getter for the start
	public MazeBox getStart() {
		return this.start;
	}
	
	//getter for the end
	public MazeBox getEnd() {
		return this.end;
	}
	
	//turns on the paintMode (clicking a box changes it)
	public void paintModeON() {
		this.paintMode = true;
	}
	
	//turns off the paintMode (clicking a box doesn't change it)
	public void paintModeOFF() {
		this.paintMode = false;
	}
	
	//setter for the maze that is being viewed
	public void setMaze(Maze maze) {
		this.maze = maze;
		repaint();
	}
	
	/*
	 * given 2 integers i and j (which represent the line and column of a certain mazeBox)
	 * checks wether that mazeBox is in the shortest path
	 * used to know which boxes should be coloured when drawing the path 
	 */
	public boolean solution(int i, int j) {
		if(this.path != null) return (this.path.contains((Vertex) this.getMaze().getBox(i, j)));
		return false;
	}
	
	/*
	 * given 2 integers i and j (which represent the line and column of a certain mazeBox)
	 * checks wether that mazeBox is the one selected
	 * selected mazeBoxes are drawn with a thicker outline
	 * used to know which mazeBox should be drawn with a thicker outline
	 */
	public boolean isSelected(int i, int j) {
		if (this.selected != null && this.selected.getLine() == i && this.selected.getColumn() == j) return true;
		return false;
	}
}
