package graph;

import java.util.ArrayList;

public interface ShortestPaths {
	
	public ArrayList<Vertex> getPath(Vertex vertex); //returns the list of the shortest path to the given vertex

	public void previous(Vertex vertex, Vertex previous); //sets a previous Vertex to one of its successors
}
