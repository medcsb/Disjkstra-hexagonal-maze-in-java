package maze;

/*
 * Departure box is the beginning of the maze
 */
public class DepartureBox extends MazeBox{
	
	//uses the constructor of mazeBox
	public DepartureBox(Maze maze, int line, int column){
		super(maze, line, column);
	}
	
	//symbol = "D"
	public final String getBoxSymbol(){
		return "D";
	}
}
