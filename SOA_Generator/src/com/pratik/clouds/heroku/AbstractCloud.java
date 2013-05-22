package com.pratik.clouds.heroku;
import com.pratik.Internalnodes.IntegerStack;
import com.pratik.drawGraph.Graph;
import com.pratik.graphpersistence.AdjacencyList;

public abstract class AbstractCloud {
	protected Graph graph;
	protected AdjacencyList adjacencyList;
	protected IntegerStack integerStack;
	
	public IntegerStack getIntegerStack() {
		return integerStack;
	}
	public void setIntegerStack(IntegerStack integerStack) {
		this.integerStack = integerStack;
	}
	public AbstractCloud() {
		super();
		graph=new Graph();
		adjacencyList = new AdjacencyList();
		integerStack = new IntegerStack();
	}
	public AdjacencyList getAdjacencyList() {
		return adjacencyList;
	}
	public void setAdjacencyList(AdjacencyList adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	public Graph getGraph() {
		return graph;
	}
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	protected abstract String cloudAddress();
	public abstract String createAddress(String number);
	public abstract String createCloudFoundryAddressForDataWS(String vertex);
	public abstract String cloudName();
	
	// I will not be using the function createAndDeploySingleDataNode()
	public abstract void createAndDeploySingleDataNode();
	
	public abstract void createSingleDataNode(String number);
	/*
	 * checks and create the clouds output folder to store the nodes
	 * for example output/clouds/heroku will store the nodes created to deploy on heroku
	 */
	public abstract void checkAndCreateCloudOutputFolder(String cloudOutputFolder);

}
