package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShortestPathsImpl implements ShortestPaths {
	//hashMap storing the vertex alongside its previous vertex
	public HashMap<Vertex, Vertex> previous = new HashMap<>();

    //assigns the previous vertex to the given vertex as its "previous"
    public void previous(Vertex current, Vertex previous) {
        this.previous.put(current, previous);
    }
    
    //returns the previous vertex to the given vertex
    public Vertex getPrevious(Vertex current){
    	return this.previous.get(current);
    }

    // Method to retrieve the shortest path as a list of vertices
    public ArrayList<Vertex> getPath(Vertex vertex) {
        ArrayList<Vertex> path = new ArrayList<Vertex>();
        while (vertex != null) {
            path.add(vertex);
            vertex = this.getPrevious(vertex);
        }
        return path;
    }
    
    // Checks wether or not a solution is possible i.e there exists a path from start to end
    public boolean isSolution(ArrayList<Vertex> path, Vertex start, Vertex end) {
    	if (path.contains(start) && path.contains(end)) return true;
    	return false;
    }
}
