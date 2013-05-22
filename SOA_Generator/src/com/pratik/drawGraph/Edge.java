package com.pratik.drawGraph;

import com.pratik.drawGraph.Vertex;

public class Edge {
	
	public Vertex target;
	public String type;

	public Edge() {
		target = null;
		type = null;
	}

	public Edge(Vertex d, String invocation) {
		target = d;
		type = invocation;
	}
	
	public String toString() { // Always good for debugging
        return "E";
	}

}
