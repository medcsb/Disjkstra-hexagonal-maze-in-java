package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import GUI.MazeViewer;

/*
 * this class is responsible for shaping and colouring the maze
 */
public class MazeModel {
	
	private MazeViewer view;
	private BoxModel[][] boxes;     //a matrix containing Hexagons which represent each a mazeBox
	private Dimension boxDimension; //dimension of a single mazeBox
	
	/*
	 * Constructor
	 * PS : this.getHeight() returns the height of the maze where as view.getheight() returns the height of the panel "MazeViewer"
	 * the box dimension is made in such a way that all the boxes will fit in the MazeViewer perfectly even if it is resized
	 */
	public MazeModel(MazeViewer view) {
		this.view = view;
		boxDimension = new Dimension((int) (2 * this.view.getWidth() / (2 * this.getWidth() + 1)), (int) (3 * this.view.getHeight() / (2 * this.getHeight() + 1)));
		boxes = new BoxModel[this.getHeight()][this.getWidth()];
		for (int i=0; i<this.getHeight(); i++) {
			for (int j=0; j<this.getWidth(); j++) {
				this.boxes[i][j] = new BoxModel(this, i, j);
			}
		}
	}

	//draws the entire maze
	public void drawMaze(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (int i=0; i<view.getMaze().getHEIGHT(); i++) {
			for (int j=0; j<view.getMaze().getWIDTH(); j++) {
				g2d = this.getPaintSettings(g2d, i, j);
			}
		}
	}
	
	/*
	 * draws a single hexagon and gives it the correct colour
	 * i, j represent the line and column of the hexagon being drawn
	 * when the hexagon is selected it is drawn with a thicker outline
	 * the hexagon is coloured according to its type and wether or not it's in the solution path
	 */
	public Graphics2D getPaintSettings(Graphics2D g2d, int i, int j) {
		String type = view.getMaze().getBox(i, j).getBoxSymbol();
		
		if(this.view.isSelected(i, j)) {
			g2d.setStroke(new BasicStroke(4));
		}
		else {
			g2d.setStroke(new BasicStroke(2));
		}
		try {
			switch (type) {
				case ("E"):
					g2d.setColor(Color.black);
					break;
				case ("D"):
					g2d.setColor(Color.blue);
					break;
				case ("A"):
					g2d.setColor(Color.red);
					break;
				case ("W"):
					g2d.setColor(Color.gray);
					break;
			}
		}finally {
			if (this.view.solution(i, j)) {
				g2d.setColor(Color.green);
			}
		}
		if (type != "E" || this.view.solution(i, j)) g2d.fillPolygon(this.boxes[i][j]);
		g2d.setColor(Color.black);
		g2d.draw(this.boxes[i][j]);
		return g2d;
	}
	
	//getter for the dimension of a hexagon
	public Dimension getDimension() {
		return this.boxDimension;
	}
	
	//getter for the height of the maze i.e : how many rows
	public int getHeight() {
		return this.view.getMaze().getHEIGHT();
	}
	
	//getter for the width of the maze i.e : how many columns
	public int getWidth() {
		return this.view.getMaze().getWIDTH();
	}
	
	//getter for the boxes i.e : hexagons
	public BoxModel[][] getBoxes(){
		return this.boxes;
	}
}
