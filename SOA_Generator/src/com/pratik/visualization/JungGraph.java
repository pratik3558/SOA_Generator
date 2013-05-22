package com.pratik.visualization;

import com.pratik.drawGraph.Edge;
import com.pratik.drawGraph.Vertex;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;


public class JungGraph  {
	
	/**
	 * Create Directed Sparse Graph object
	 * @return graph object
	 */
	public static Graph<Vertex,Edge> createGraphObject()
	{
		Graph<Vertex,Edge> g = new DirectedSparseMultigraph<Vertex, Edge>();
		return g;
	}

}