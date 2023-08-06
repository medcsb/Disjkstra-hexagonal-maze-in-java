package graph;

import java.util.ArrayList;

public interface Graph {
	
	public ArrayList<Vertex> getVertexes(); //returns a list of all vertexes
	
	public ArrayList<Vertex> getSuccessors(Vertex vertex); //returns a list of the neighbours of a given vertex
	
	public int getWeight(Vertex start, Vertex end); //returns the weight of the edge connecting two vertexes
}
