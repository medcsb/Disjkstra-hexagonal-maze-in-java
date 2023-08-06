package graph;

public interface MinDistance {
	
	public void setMin(Vertex v, int value); //stocks the minimal distance between the start vertex and the given vertex

	public int getMin(Vertex v);//returns the minimal distance between the start vertex and a given vertex
}
