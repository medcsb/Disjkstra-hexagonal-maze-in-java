package maze;

import graph.Vertex;

public abstract class MazeBox implements Vertex{
	
	private final Maze maze;
	private final int line;   //row of the mazeBox
	private final int column; //column of the mazeBox
	
	//constructor
	public MazeBox(Maze maze, int line, int column){
		this.maze = maze;
		this.line = line;
		this.column = column;
	}
	
	//returns the row + column of the mazeBox
		public String getLabel(){
			String l = String.valueOf(getLine());
			String w = String.valueOf(getColumn());
			return "(" + l + "," + w + ")";
		}
	
	public abstract String getBoxSymbol();
	
	//getter for the row
	public final int getLine(){
		return line;
	}
	
	//getter for the column
	public final int getColumn(){
		return column;
	}
	
	// Checks wether the line and column coordinates are possible
	public boolean exists(int line, int column) {
		if (line<0 || column<0) return false;
		if (line>=this.maze.getHEIGHT() || column>=this.maze.getWIDTH()) return false;
		return true;	
	}
}
