package com.pratik.Tree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.pratik.Internalnodes.IntegerStack;
import com.sugi.bpel.GenerateBpel;
import com.pratik.clouds.heroku.AbstractCloud;
import com.pratik.clouds.heroku.Heroku;
import com.pratik.drawGraph.Graph;
import com.pratik.drawGraph.Vertex;
import com.pratik.graphpersistence.AdjacencyList;
import com.sugi.persistence.saveTreeToFile;
import com.sugi.util.Helper;
import com.sugi.visualization.VisualizeGraph;

public class CreateTree {
	
	static AbstractCloud cloud=new Heroku();
	
	private static Node root;
	//static Graph graph = new Graph();
	static HashMap<String,List<String>> combinations = new HashMap<String,List<String>>();
	static List<String> setOne = new ArrayList<String>();
	static List<String> setTwo = new ArrayList<String>();
	static List<String> setThree = new ArrayList<String>();
	static Set<Entry<String, List<String>>> set = combinations.entrySet();
	static String path = "Output";
	static String output = path + File.separator;
	static int stackNodeCount = 0;
	
	static int noOfDataWS = 1;
	static int noOfSyncWS = 1;
	static int stackAccessCount = 1;
	
	/**
	 * No argument constructor to create a Empty tree and add elements
	 */
	public CreateTree()
	{
		root = null;
	}
	
	
	/**
	 * Class represents the Node dataStructure
	 * @author sugi
	 *
	 */
	class Node
	{
		public int id;
		public int data;
		Node leftChild;
		Node rightChild;
		
		public Node(int id, int data, Node left, Node right)
		{
			this.id = id;
			this.data = data;
			this.leftChild = left;
			this.rightChild = right;
		}
	}
	
	/**
	 * Insert into the tree
	 * @param node
	 * @param id
	 * @param data
	 */
	public void insertValuesintoTree(int id, int data)
	{
		
		Node insert = new Node(id,data,null,null);
		
		// Get address from the input address text file 
		String address = "";
		String type = "";
		
		if(root == null)
		{
			root = insert;
		}	
		else
		{
			Node current = root;
			while(true)
			{
				String writeToFile = "";
				
				if(current.leftChild == null)
				{
					current.leftChild = insert;
					break;
				}
				else if(current.rightChild == null)
				{
					current.rightChild = insert;
					current = current.leftChild;
					break;
				}
				else
					current = current.leftChild;
			}
		}
	}
	
	
	public static void processTree(Node root)
	{
		Node current = root;
		Graph graph=cloud.getGraph();
		AdjacencyList adjacencyList=cloud.getAdjacencyList();
		IntegerStack integerStack=cloud.getIntegerStack();
		String address = null;
		address = cloud.createAddress(String.valueOf(root.data));
		//address = Helper.createAddress(String.valueOf(root.data));  
		String writeToFile = "";
		String type = "ROOTSYNC";
		Vertex ver = graph.getVertex(Integer.toString(root.data),address,type);
		writeToFile = String.valueOf(current.data);
		saveTreeToFile.writeRootToText(writeToFile);
		saveTreeToFile.writeRootToText("\n");
		graph.graph.addVertex(ver);
		
		
		// Also add the root to adjacency list now
		
		
		while(true)
		{
			
			String invocation = "DEFAULT";
			if(current.leftChild != null && current.rightChild != null)
			{
				Vertex dest = null;
			    // add edge from source to left and right child
				//current becomes left child as tree grows left side 
				
				// As it is the internal node, add parent to stack to backtrack
				
				integerStack.push(current.data);
				
				
				//Add left child
				Vertex source = graph.getVertexFromName(String.valueOf(current.data));
				type = Helper.Toss();
				address = cloud.createAddress(String.valueOf(current.leftChild.data));
				dest = graph.getVertex(String.valueOf(current.leftChild.data),address,type);
				writeToFile = String.valueOf(current.data) + "->" + String.valueOf(current.leftChild.data);
				saveTreeToFile.writeTreeAsText(writeToFile);
				saveTreeToFile.writeTreeAsText("\n");
				
				graph.graph.addVertex(dest);
				graph.addEdge(source, dest,invocation);
				
				// When you add left child, add in adjacency list too
				
				//AdjacencyList.addEdge(source, dest,invocation);
				adjacencyList.addEdge(source, dest,invocation);
				
				
				//Add right child
				
				type = Helper.Toss();
				address = cloud.createAddress(String.valueOf(current.rightChild.data));
				dest = graph.getVertex(String.valueOf(current.rightChild.data),address,type);
				writeToFile = String.valueOf(current.data) + "->" + String.valueOf(current.rightChild.data);
				saveTreeToFile.writeTreeAsText(writeToFile);
				saveTreeToFile.writeTreeAsText("\n");
				
				//Graph.graph.addVertex(dest);
				graph.graph.addVertex(dest);
				graph.addEdge(source, dest,invocation);
				
				current = current.leftChild;
				
				// When you add right child, add in adjacency list too
				
				adjacencyList.addEdge(source, dest,invocation);
				
				
			}
			else if(current.leftChild != null)
			{
				// Only the left child exists
			    //add edge from parent to left child
				
				// As it is the internal node, add parent to stack to backtrack
				
				integerStack.push(current.data);
				
				Vertex source = graph.getVertexFromName(String.valueOf(current.data));
				type = Helper.Toss();
				address = cloud.createAddress(String.valueOf(current.leftChild.data));
				Vertex dest = graph.getVertex(String.valueOf(current.leftChild.data),address,type);
				writeToFile = String.valueOf(current.data) + "->" + String.valueOf(current.leftChild.data);
				saveTreeToFile.writeTreeAsText(writeToFile);
				saveTreeToFile.writeTreeAsText("\n");
				
				graph.graph.addVertex(dest);
				
				
				// default invocation to data web service is GET, change it later to random one
				graph.addEdge(source, dest,invocation);
				
				current = current.leftChild;
				
				// When you add left child, add in adjacency list too
				
				adjacencyList.addEdge(source, dest,invocation);
				
			}
			else
			{
				//internal node and hence break the loop
				
				// This is leaf, so do not want to add to adjacency list.
				
				Vertex leaf = graph.getVertexFromName(String.valueOf(current.data));
				
				
				// I am checking in this if loop because to identify single node trees
				if(leaf.type.equalsIgnoreCase("ROOTSYNC"))
				{   
					leaf.type = "ROOTDATASYNC";
					integerStack.push(current.data);
					break;
				}
				else
				{
					leaf.type = "DATA";
					break;
				}
			}
		} // End of While  - After this think about mapping logical tree to physical tree creation.
		
		// Current is still the leaf, create physical node for this leaf.  
		//stack is already populated with internal nodes
		// Get the internal node, look at the adjacency list for dependency to create code. 
		
		//Creating physical node for the leaf - last leaf
		//String dirName = "DataWS" + noOfDataWS;
		//String outdir = output + dirName;
		//CreateDataWS.createDataNode(outdir);
		
		//Now it is time to create all other nodes. Follow the algorithm . 
		// Get elements from the stack, if it is first element then use synctods class
		// if it is not, use synctosync class to create files
		
		
		while(integerStack.returnSize() != 0)
		{	
			int element = integerStack.pop();
			if(stackNodeCount == 0)
			{
				TreeUtils.createPhysicalNodes(String.valueOf(element),true,cloud);
				stackNodeCount += 1;
			}
			else
			{
				TreeUtils.createPhysicalNodes(String.valueOf(element),false,cloud);
				stackNodeCount += 1;
			}
		}
		stackNodeCount = 0; // Re-initialising it to zero again. 
	   }
	
	
	
	public static void run()
	{
		System.out.println("Inserting values into the trees");
		CreateTree tree = new CreateTree();
		tree.insertValuesintoTree(0, 1);
		tree.insertValuesintoTree(0, 2);
		tree.insertValuesintoTree(0, 3);
		tree.insertValuesintoTree(0, 4);
		tree.insertValuesintoTree(0, 5);
		tree.insertValuesintoTree(0, 6);
		printInOrder(root);
		processTree(root);
		//VisualizeGraph.visual(Graph.graph);
	}
	
	public static void printInOrder(Node node) 
	{
		  if (node != null) 
		  {
		  printInOrder(node.leftChild);
		  System.out.println("  Traversed " + node.data);
		  printInOrder(node.rightChild);
		  }
	}
	
	public static void main(String args[])
	{
		int noOfNodes = 0;
		int loopThrough = 0;
		int nodeCount = 1;
		int j = 1;
		List<String> list = null;
		CreateTree tree = new CreateTree();	
		if(args.length == 0)
		{
			System.out.println("Provide number of Nodes");
			System.exit(0);
		}
		else
			noOfNodes = Integer.parseInt(args[0]);
		
		
		setOne.add("4");
		setOne.add("3");
		setOne.add("2");
		setOne.add("1");
		setTwo.add("5");
		setTwo.add("3");
		setTwo.add("2");
		setThree.add("6");
		setThree.add("4");
		combinations.put("1", setOne);
		combinations.put("2", setTwo);
		combinations.put("3", setThree);
		
		
		System.out.println("Number of Nodes" + noOfNodes);
		loopThrough = noOfNodes / 10;
		//System.out.println("Number of loops" + loopThrough);
		
		
		for(int i = 0; i < loopThrough; i++)
		{
			if(j > 3)
				j = 1;
			else
			{
				list = combinations.get(String.valueOf(j));
				Iterator<String> itr = list.iterator();
				while(itr.hasNext())
				{
					int forInsert = Integer.parseInt(itr.next());
					root = null;
					for(int k = 0; k < forInsert; k++)
					{
						tree.insertValuesintoTree(0, nodeCount);
						nodeCount += 1;
					}
					processTree(root);
				}
				j += 1;
			}
		}
		
	//	VisualizeGraph.visual(Graph.graph);
		//Helper.createBPELLogicalGraph();
		//GenerateBpel.init();
		//VisualizeGraph.visual(Graph.bpelGraph);
	}

}
