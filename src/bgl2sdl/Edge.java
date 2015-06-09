package bgl2sdl;

import java.util.Vector;



public class Edge{
	public Integer startingNode;
	public Integer endingNode;
	
public Edge(){}
	
	public Edge(Integer sn, Integer en)
	{
		startingNode=sn;
		endingNode=en;
	}

	@Override
	public String toString()
	{
		return new String("S: " + startingNode + " E: " + endingNode);
	}
	
	public boolean belongsToEdge(Integer point)
	{
		if(point.equals(startingNode) || point.equals(endingNode))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	

	public  Edge findEdge(Integer node, Vector<Edge> edgesToSearch)
	{
		for(int i=0; i<edgesToSearch.size();i++)
		{
			if(node.equals(edgesToSearch.get(i).startingNode) ||
					node.equals(edgesToSearch.get(i).endingNode))
			{
				return edgesToSearch.get(i);
			}
		}
		return null;

	}

	
	

	public static void main(String[] args)
	{
		AttributeChecker cenas = new AttributeChecker();
		Vector<Integer> nodestest= new Vector<Integer>();
		nodestest.add(1);
		nodestest.add(2);
		nodestest.add(3);
		nodestest.add(4);
		nodestest.add(5);
		nodestest.add(6);
		
		Vector<Edge>edgestest= new Vector<Edge>();
		
		edgestest.add(new Edge(1,2));
		edgestest.add(new Edge(2,3));
		edgestest.add(new Edge(3,4));
		edgestest.add(new Edge(3,5));
		edgestest.add(new Edge(5,6));
		
		cenas.checkConnectivity(nodestest, edgestest);
		
	}
	


}
