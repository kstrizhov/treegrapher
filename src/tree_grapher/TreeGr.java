package tree_grapher;


import java.util.ArrayList;

import javax.swing.JFrame;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;



public class TreeGr extends JFrame
{	
	public static int numchildren = 3; //number of children
	public static int kgen = 1;		//number of generations
	public static int nodenum = 0;
	
	private static final long serialVersionUID = 1L;
	
	
	public mxCell MakeRootNode(mxGraph g)
	{		
		Node node = new Node();
		
		node.num = nodenum;
		nodenum++;
		node.ancestor = null;
		node.label = "Root";
		
		return (mxCell) g.insertVertex(g.getDefaultParent(), null, node, 30, 400, 50, 50);
	}

	public class Node
	{		
		Node ancestor;
		
		ArrayList<Node> children = new ArrayList<Node>();
		
		public int num;
		
		public String label = "qqq"; 
		
		@Override
		public String toString() {
			return label;
		}
		
		public void addChild(mxCell parent, Node child) {
			Node x = new Node();
			x = (Node) parent.getValue();
			x.children.add(child);
			parent.setValue(x);			
		}
				
	};

	public void MakeChildren(mxGraph graph, mxCell parent, int kg)
	{
		for (int i = 0; i < numchildren; i++)
		{
			Node node = new Node();
			node.ancestor = (Node) parent.getValue();			
			node.num = nodenum;
			nodenum++;
			node.label = Integer.toString(node.num);
			mxCell child = (mxCell) graph.insertVertex(graph.getDefaultParent(), null,
					node, 30, 30, 50, 50,"shadow=true");
			((Node) parent.getValue()).addChild(parent, (Node) child.getValue());
			String s = Integer.toString(((Node) parent.getValue()).num);
			System.out.println(s);
			graph.insertEdge(graph.getDefaultParent(), null, null, parent, child);
			if (kg>0)
				MakeChildren(graph, child, kg-1);
		}	
	}

	public  TreeGr()
	{
		
		final mxGraph graph = new mxGraph();
		
		graph.getModel().beginUpdate();
		try
		{
			mxCell root = MakeRootNode(graph);
			MakeChildren(graph, root, kgen);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setConnectable(false);	
		getContentPane().add(graphComponent);
		
	    mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
	    layout.setLevelDistance(30);
	    layout.setNodeDistance(5);
	    layout.setEdgeRouting(false);
	    
//	    Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
//		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.TopToBottom);
		
	    layout.execute(graph.getDefaultParent());
	    
	    graph.setCellsEditable(false);
	    graph.setCellsSelectable(false);
		graph.setCellsMovable(false);
		graph.setCellsLocked(false);
		graph.setSplitEnabled(false);
		graph.setCellsDisconnectable(true);
		
				
	}
	
	public static void main(String[] args)
	{
		TreeGr frame = new TreeGr();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);

	}
}