package graph;

import java.util.ArrayList;

/*
 * Dijktra's algorithm
 */
public class Dijkstra {
	
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex)
	{
		return dijkstra(graph, startVertex, endVertex, new ProcessedVertexesImpl(), new MinDistanceImpl(), new ShortestPathsImpl());
	}

	private static final ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance minDistance, ShortestPaths shortestPaths) {
		
		ArrayList<Vertex> allVertexes = graph.getVertexes();
		processedVertexes.addVertex(startVertex);
		int n = allVertexes.size();
		
		for (Vertex v : allVertexes)
		{
			minDistance.setMin(v, Integer.MAX_VALUE);
		}
		
		minDistance.setMin(startVertex, 0);
		Vertex pivot = startVertex;
		int distanceToPivot = 0;
		
		for (int i=0; i<n; i++)
		{
			ArrayList<Vertex> successors = graph.getSuccessors(pivot);
			for (Vertex v : successors)
			{
				if (!processedVertexes.isIn(v))
				{
					int newDistance = distanceToPivot + graph.getWeight(pivot, v);
					if (newDistance < minDistance.getMin(v))
					{
						minDistance.setMin(v, newDistance);
						shortestPaths.previous(v, pivot);
					}
				}
			}
			
			int distanceToNewPivot = Integer.MAX_VALUE;
			processedVertexes.addVertex(pivot);
			
			for (Vertex v : allVertexes)
			{
				if (!processedVertexes.isIn(v))
				{
					int distanceTov = minDistance.getMin(v);
					if (distanceTov < distanceToNewPivot)
					{
						distanceToNewPivot = distanceTov;
						pivot = v;
					}
				}
			}
			
			distanceToPivot = distanceToNewPivot;
		}
		
	return shortestPaths;
	}
}
