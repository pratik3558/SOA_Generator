package com.pratik.graphpersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pratik.drawGraph.Edge;
import com.pratik.drawGraph.Vertex;

public class AdjacencyList {
	
	private  Map<Vertex, List<Edge>> adjacencies;

	   public AdjacencyList() {
		super();
		// TODO Auto-generated constructor stub
		   adjacencies = new HashMap<Vertex, List<Edge>>();
		
	}

	public  void addEdge(Vertex source, Vertex target, String type){
	       List<Edge> list;
	       if(!adjacencies.containsKey(source)){
	           list = new ArrayList<Edge>();
	           adjacencies.put(source, list);
	       }else{
	           list = adjacencies.get(source);
	       }
	       list.add(new Edge(target,type));
	   }

	   public  List<Edge> getAdjacent(Vertex source){
	       return adjacencies.get(source);
	   }

}
