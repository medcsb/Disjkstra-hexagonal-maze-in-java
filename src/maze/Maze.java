package maze;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintWriter;

import graph.Graph;
import graph.Vertex;

public class Maze implements Graph{
	
	//width of the maze (How many columns there are)
	private int WIDTH;
	//height of the maze (How many lines there are)
	private int HEIGHT;
	//a matrix containing every mazeBox in the maze
	private MazeBox[][] mazeboxes;
	
	//constructor for the maze (initialises a maze with EmptyBoxes)
	public Maze(int height, int width) {
		this.HEIGHT = height;
		this.WIDTH = width;
		this.mazeboxes = new MazeBox[HEIGHT][WIDTH];
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				this.setBox(i, j, 'E');
			}
		}
	}
	
	//getter for the mazeBoxes
	public final MazeBox getBox(int line, int column){
		return mazeboxes[line][column];
	}
	
	//setter for the mazeBoxes
	public void setBox(int line, int column, char type){
		switch (type) {
		case 'D' :
			this.mazeboxes[line][column] = new DepartureBox(this, line, column); break;
		case 'A' :
			this.mazeboxes[line][column] = new ArrivalBox(this, line, column); break;
		case 'W' :
			this.mazeboxes[line][column] = new WallBox(this, line, column); break;
		case 'E' :
			this.mazeboxes[line][column] = new EmptyBox(this, line, column); break; 			
		default :
			return;
		}
	}
	
	//returns the list of all vertexes in the maze
	public final ArrayList<Vertex> getVertexes()
	{
		ArrayList<Vertex> Vertexes = new ArrayList<Vertex>();

		for (int line = 0 ; line < HEIGHT ; line++)
		{
			MazeBox[] thisLine = mazeboxes[line];
			for (int column = 0 ; column < WIDTH ; column++)
				Vertexes.add(thisLine[column]);
		}

		return Vertexes;
	}
	
	//returns the neighbours of a given vertex excluding wall neighbours
	public final ArrayList<Vertex> getSuccessors(Vertex vertex){
		ArrayList<Vertex> successors = new ArrayList<Vertex>();
		MazeBox mazebox = (MazeBox)vertex ;
		if (vertex == null) {
			// if vertex is null return null ArrayList
			return successors;
		}
		else {
			int line = mazebox.getLine();
			int column = mazebox.getColumn();
			
			if (line%2 == 0) {
				//adding the left neighbour
				if (mazebox.exists(line, column-1) && this.mazeboxes[line][column-1].getBoxSymbol() != "W") successors.add(mazeboxes[line][column-1]);
				//adding the right neighbour
				if (mazebox.exists(line, column+1) && this.mazeboxes[line][column+1].getBoxSymbol() != "W") successors.add(mazeboxes[line][column+1]);
				//adding the bottom left neighbour
				if (mazebox.exists(line+1, column-1) && this.mazeboxes[line+1][column-1].getBoxSymbol() != "W") successors.add(mazeboxes[line+1][column-1]);
				//adding the bottom right neighbour
				if (mazebox.exists(line+1, column) && this.mazeboxes[line+1][column].getBoxSymbol() != "W") successors.add(mazeboxes[line+1][column]);
				//adding the top left neighbour
				if (mazebox.exists(line-1, column-1) && this.mazeboxes[line-1][column-1].getBoxSymbol() != "W") successors.add(mazeboxes[line-1][column-1]);
				//adding the top right neighbour
				if (mazebox.exists(line-1, column) && this.mazeboxes[line-1][column].getBoxSymbol() != "W") successors.add(mazeboxes[line-1][column]);
			}
			else {
				//adding the left neighbour
				if (mazebox.exists(line, column-1) && this.mazeboxes[line][column-1].getBoxSymbol() != "W") successors.add(mazeboxes[line][column-1]);
				//adding the right neighbour
				if (mazebox.exists(line, column+1) && this.mazeboxes[line][column+1].getBoxSymbol() != "W") successors.add(mazeboxes[line][column+1]);
				//adding the bottom left neighbour
				if ( mazebox.exists(line+1, column) && this.mazeboxes[line+1][column].getBoxSymbol() != "W") successors.add(mazeboxes[line+1][column]);
				//adding the bottom right neighbour
				if (mazebox.exists(line+1, column+1) && this.mazeboxes[line+1][column+1].getBoxSymbol() != "W") successors.add(mazeboxes[line+1][column+1]);
				//adding the top left neighbour
				if (mazebox.exists(line-1, column) && this.mazeboxes[line-1][column].getBoxSymbol() != "W") successors.add(mazeboxes[line-1][column]);
				//adding the top right neighbour
				if (mazebox.exists(line-1, column+1) && this.mazeboxes[line-1][column+1].getBoxSymbol() != "W") successors.add(mazeboxes[line-1][column+1]);
			}
			return successors;
		}
	}
	
	// returns the Departure box for the maze if there is any (useful for checking that the maze has been assigned a start)
	public MazeBox findStart() {
		for (int i=0; i<this.HEIGHT; i++) {
			for (int j=0; j<this.WIDTH; j++) {
				if (this.mazeboxes[i][j].getBoxSymbol() == "D") {
					return this.mazeboxes[i][j];
				}
			}
		}
		return null;
	}
	
	// returns the Arrival box for the maze if there is any (useful for checking that the maze has been assigned a end)
	public MazeBox findEnd() {
		for (int i=0; i<this.HEIGHT; i++) {
			for (int j=0; j<this.WIDTH; j++) {
				if (this.mazeboxes[i][j].getBoxSymbol() == "A") {
					return this.mazeboxes[i][j];
				}
			}
		}
		return null;
	}
	
	//by default the distance between two adjacent boxes is set to 1
	public int getWeight(Vertex start, Vertex end){
		return 1;
	}

	//getter for the width
	public final int getWIDTH() {
		return WIDTH;
	}

	//getter for the height
	public final int getHEIGHT() {
		return HEIGHT;
	}
	
	//loads a maze from a text file
	public final void initFromTextFile(String fileName){
		ArrayList<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			String line1 = lines.get(0);
			this.HEIGHT = lines.size();
			this.WIDTH = line1.length();
			this.mazeboxes = new MazeBox[HEIGHT][WIDTH];
			for (int i=0; i<HEIGHT; i++) {
				if (i != 0) System.out.print("\n");
				for (int j=0; j<WIDTH; j++){
					line = lines.get(i);
					char[] typeList = line.toCharArray();
					char type = typeList[j];
					this.setBox(i, j, type);
					System.out.print(type);
				}
			}
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
	}

	//saves a maze in a text file
	public final void saveToTextFile(String fileName) {
		try {
		    PrintWriter out = new PrintWriter(fileName);
		    
		    for (int i=0; i<this.HEIGHT; i++){
		    	if (i != 0) out.write("\n");
		    	for (int j=0; j<this.WIDTH; j++) {
		    		MazeBox mazeBox = this.mazeboxes[i][j];
		    		String type = mazeBox.getBoxSymbol();
		    		out.write(type);
		    	}
		    }
		    out.close();
		} 
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
	}
}
