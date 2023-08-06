package maze;

/*
 * Arrival box is the end of the maze
 */
public class ArrivalBox extends MazeBox{
	
	//uses constructor of mazeBox
	public ArrivalBox(Maze maze, int line, int column){
		super(maze, line, column);
	}
	
	//symbol = "A"
	public final String getBoxSymbol(){
		return "A";
	}
}
