/**
 * 
 */
package com.pratik.Tree;

import java.io.File;
import java.util.List;

import com.pratik.FileOperations.FileOperation;
import com.pratik.clouds.heroku.AbstractCloud;
import com.pratik.createNode.createNode;
import com.pratik.drawGraph.Graph;
import com.pratik.drawGraph.Edge;
import com.pratik.drawGraph.Vertex;
import com.pratik.graphpersistence.AdjacencyList;
import com.sugi.util.ExecuteCommand;



/**
 * @author Pratik
 *
 */
public class TreeUtils {

	
	public static int noOfChild(List list)
	{
		//List<Edge> list = AdjacencyList.getAdjacent(vertex);
		return list.size();
	}
	
	
	public static void createPhysicalNodes(String vertex,Boolean important, AbstractCloud cloud)
	{
		Graph graph=cloud.getGraph();
		AdjacencyList adjacencyList=cloud.getAdjacencyList();
		Vertex vert = graph.getVertexFromName(vertex);
		List<Edge> list = adjacencyList.getAdjacent(vert);
		FileOperation.checkAndCreateFolder(Settings.cloud);
		
		cloud.checkAndCreateCloudOutputFolder(Settings.cloudOutput);
		if(!important)
		{
			
		}
		else
		{
			
			if (vert.type != "ROOTDATASYNC")
			{
				System.out.println("I am creating DATAWS");
				
				// If the node is important node. Check for the edge count -  two cases are there
				  // 1. One edge or Two edges
				int noOfEdges = list.size();
				if(list.size()==1)
				{
					String name1 = list.get(0).target.name;
					cloud.createSingleDataNode(name1);
					ExecuteCommand.executeCmdToCreateWarFile(dirName, WSName);
				}
				
					
				
			}
			
			
			
		}
		
	}
}
