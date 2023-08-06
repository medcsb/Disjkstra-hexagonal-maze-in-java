package graph;

import java.util.HashSet;

public class ProcessedVertexesImpl implements ProcessedVertexes {
	//hashset storing all the processed vertexes
    private HashSet<Vertex> processedVertexes = new HashSet<Vertex>();

    //checks if the given vertex is processed
	public boolean isIn(Vertex v) {
		return this.processedVertexes.contains(v);
	}

	//adds the given vertex to the processed vertexes
	public void addVertex(Vertex v) {
		this.processedVertexes.add(v);
	}
}
