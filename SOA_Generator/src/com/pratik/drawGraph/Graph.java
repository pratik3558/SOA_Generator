package com.pratik.drawGraph;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.pratik.drawGraph.Edge;
import com.pratik.drawGraph.Vertex;
import com.pratik.visualization.JungGraph;


public class Graph {
	
	public  Map<String, Vertex > vertexMap ;
	 Set<Entry<String, Vertex>> set1 ;
		
		public  edu.uci.ics.jung.graph.Graph<Vertex, Edge> graph ;
		public edu.uci.ics.jung.graph.Graph<Vertex, Edge> bpelGraph ;
		
		
		public Graph() {
			super();
			 vertexMap = new HashMap<String, Vertex >(); //Maps int to Vertex
			 set1 = vertexMap.entrySet();
			 graph = JungGraph.createGraphObject();
			 bpelGraph = JungGraph.createGraphObject();
				
		}

		public Vertex getVertex(String vName,String address, String type)
		{
			Vertex v = (Vertex) vertexMap.get(vName);
			if( v == null )
			{
				v = createVertex(vName,address,type);
			
			}
			return v;
		}
		
		public  Vertex createVertex(String vName,String address, String type)
		{
			Vertex v = new Vertex(vName,address,type);
			vertexMap.put( vName, v); 
			return v;
		}
		
		public Vertex getVertexFromName(String name)
		{
			Vertex v = (Vertex) vertexMap.get(name);
			return v;
		}
		
		
		public void addEdge(Vertex sour, Vertex des, String type)
		{
			//Vertex u = getVertex(sour);                      //source vertex
			//Vertex v = getVertex(des);                      //destination vertex
			Edge ed = new Edge(des,type);
			// Also add edge to Jung Graph
			graph.addEdge(ed,sour,des);
		}
		
		public void addEdgeOnlyToBPELGraph(Vertex sour, Vertex des, String type)
		{
			Edge ed = new Edge(des,type);
			bpelGraph.addEdge(ed,sour,des);
		}

}
