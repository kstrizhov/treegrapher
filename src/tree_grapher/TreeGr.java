package tree_grapher;


import java.awt.Point;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JFrame;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyNode;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;



public class TreeGr extends JFrame
{	
	public static int numchildren = 3; //number of children
	public static int kgen = 1;			//number of generations
	
	private static final long serialVersionUID = 1L;
	
	
	public mxCell MakeRootNode(mxGraph g)
	{		
		return (mxCell) g.insertVertex(g.getDefaultParent(), null, "(Root)", 30, 400, 50, 50);
	}

	class Node
	{
		@Override
		public String toString() {
			return "qqq";
		}
		
	};

	public void MakeChildren(mxGraph graph, mxCell parent, int kg)
	{
		for (int i = 0; i < numchildren; i++)
		{
			Object node = new Node();
			mxCell child = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, node, 30, 30, 50, 50);
			graph.insertEdge(graph.getDefaultParent(), null, null, parent, child);
			if (kg>0)
				MakeChildren(graph, child, kg-1);
		}	
	}

	public  TreeGr()
	{
		
		final mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		
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
		getContentPane().add(graphComponent);
		
	    mxCompactTreeLayout layout = new mxCompactTreeLayout(graph, false);
	    layout.setLevelDistance(30);
	    layout.setNodeDistance(5);
	    layout.setEdgeRouting(false);
	    
	    Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.TopToBottom);
		
	    layout.execute(graph.getDefaultParent());
	    
	    graph.setCellsEditable(false);
	    graph.setCellsSelectable(true);
		graph.setCellsMovable(true);
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