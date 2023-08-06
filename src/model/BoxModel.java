package model;

import java.awt.Dimension;
import java.awt.Polygon;

/*
 * this class is responsible for drawing the hexagons and placing them in the correct spots
 */
public class BoxModel extends Polygon{
	
	private int x;                 //used to position the box correctly
	private int y;                 //used to position the box correctly
	private int a;                 //the width of a single mazeBox
	private int b;                 //the height of a single mazeBox
	
	private Dimension dimension;   //Dimension of a single mazeBox
	
	private MazeModel mazeModel;
	
	/*
	 * constructor for the box model/hexagon
	 * given the parameters i, j which represent the row and column of the mazeBox being drawn
	 * this method finds the appropriate coordinates (x, y) that will be used to draw the rest of the polygon
	 */
	public BoxModel(MazeModel mazeModel, int i,int j) {
		this.mazeModel = mazeModel;
		
		this.dimension = this.mazeModel.getDimension();
		this.a = this.dimension.width;
		this.b = this.dimension.height;
		
		if(i%2 == 0) {
			this.x = (int) (j * a);
			this.y = (int) (2 * i * b/3);
		}
		else {
			this.x = (int) (j * a + a/2); //the odd lines are shifted to the right by half an hexagon
			this.y = (int) (2 * i * b/3);
		}
		this.createHexagon();
	}
	
	/*
	 * Creates an irregular hexagon that has total width a and total height b
	 * to make the hexagon we draw a rectangle with height (b/3) and width (a) then place the remaining two point above the rectangle 
	 * by (b/3) and bellow it by (b/3)
	 */
	public void createHexagon() {
		
		//top left point
		this.addPoint(x, y + b/3);
		//bottom left point
		this.addPoint(x, y + 2 * b/3);
		//bottom point
        this.addPoint(x + a/2, y + b);
        //bottom right point
        this.addPoint(x + a, y + 2 * b/3);
        //top right point
        this.addPoint(x + a, y + b/3);
        //top point
        this.addPoint(x + a/2, y);
	}

}
