package tree_grapher;

import com.mxgraph.model.mxCell;

public class Node
{
	
	mxCell mc;
	
	Node(mxCell mc)
	{
		this.mc = mc;
	}
	
	boolean root;
	boolean cmade;
	int obrab;
	Node ancestor;
	Node[] Child = new Node[1024];
	
	public Node getAncestor()
	{
		if(root) return null;
		return ancestor;
	}
	
	public void setAncestor(Node n, Node a)
	{
		n.ancestor = a;
	}
	
	public void setChildren(Node n, Node[] C)
	{	
		n.Child =  C;
	}
	
	public void setChild()
	{
		
	}
	
//	public mxCell getChild()
//	{
//		return children;
//	}
	
}
