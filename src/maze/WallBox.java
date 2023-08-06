package maze;
/*
 * Wall boxes are maze boxes that you can't go through i.e distance between it and its neighbours = infinity
 */
public class WallBox extends MazeBox{
	
	//Uses the constructor for mazeBox 
	public WallBox(Maze maze, int line, int column){
		super(maze, line, column);
	}
	
	//symbol = "W"
	public final String getBoxSymbol(){
		return "W";
	}
}
