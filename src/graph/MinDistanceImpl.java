package graph;
import java.util.HashMap;

public class MinDistanceImpl implements MinDistance{
	//hash map storing all the minimal distances found
	private HashMap<Vertex, Integer> minDistance = new HashMap<Vertex, Integer>();
	
	//setter for when we find a new minimal distance
	public void setMin(Vertex v, int k){
		minDistance.put(v, Integer.valueOf(k));
	}
	
	//getter for the minimal distance
	public int getMin(Vertex v){
		return minDistance.get(v).intValue();
	}
}
