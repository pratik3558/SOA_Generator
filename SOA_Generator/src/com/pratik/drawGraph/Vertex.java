package com.pratik.drawGraph;

public class Vertex {
	
	public String name;
	public String address;
	public String type;
	
	public Vertex(String name, String address, String type)
	{
		this.name = name;
		this.address = address;
		this.type = type;
	}
	
	public String toString() 
	{ 
        return "V" + name;
    }
	
	public String getName()
    {
    	return name;
    }
	
	public String getAddress()
	{
		return address;
	}
	
	public String getType()
	{
		return type;
	}


}
