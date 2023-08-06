package maze;

public class MazeReadingException extends Exception {

	public MazeReadingException (String fileName, int lineNo, String errorMsg)
	{
		super("Error detected while reading the maze " + fileName + "(" + lineNo + ")" + errorMsg);
	}

}
