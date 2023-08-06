package maze;

/*
 * Empty boxes are mazeBoxes which you can go through i.e the distance between it and its neighbours is 1
 */
public class EmptyBox extends MazeBox {
	
	//uses constructor of MazeBox
	public EmptyBox(Maze maze, int line, int column){
		super(maze, line, column);
	}

	//symbol = "E"
	public final String getBoxSymbol(){
		return "E";
	}
}
