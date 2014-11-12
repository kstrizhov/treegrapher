package tree_grapher;


import javax.swing.JFrame;

//import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyNode;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;


public class TreeGr extends JFrame
{
	public static int nodequant = 20;
	public static int nodenum = 0;	
	public static int kvetvl = 3;
	public static int x = 400;
	public static int y = 20;
	
	private static final long serialVersionUID = 1L;
	
	public Object MakeRootNode(mxGraph g, Object p)
	{
		Object root = g.insertVertex(p, null, nodenum + " (Root)", x, y, 50, 20);
		nodenum++;
		y+=60;
		return root;
	}
	
//	public Object getParent(Object v)
//	{
//		return ;
//	}
	
	public void MakeChildren(mxGraph g, Object p, Object r)
	{
		if(nodenum < nodequant-1)
		{
			Object[] v;
			v = new Object[kvetvl];
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j <= kvetvl-1; j++)
				{
					v[j] = g.insertVertex(p, null, nodenum, x, y, 15, 15);
					g.insertEdge(p, null, "", r, v[j]);
					nodenum++;
					x+=20;
				}
				r = v[i];
				x-=20;
				y+=40;
//				MakeChildren(g, p, r);
			}
//			for(int j = 0; j <= kvetvl-1; j++)
//			{
//				v[j] = g.insertVertex(p, null, nodenum, x, y, 15, 15);
//				g.insertEdge(p, null, "", r, v[j]);
//				nodenum++;
//				x+=20;
//			}
//			r = v[0];
//			x-=20;
//			y+=40;
			MakeChildren(g, p, r);
		}
		
	}
	
//	public void MakeChildren(mxGraph g, Object p, Object r)
//	{
//		Object[] v;
//		v = new Object[kvetvl];
//		for(int j = 0; j <= kvetvl-1; j++)
//		{
//			v[j] = g.insertVertex(p, null, nodenum, x, y, 15, 15);
//			g.insertEdge(p, null, "", r, v[j]);
//			nodenum++;
//			x+=20;
//		}
//	}

	public  TreeGr()
	{
		
		final mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();
		try
		{
			Object root = MakeRootNode(graph, parent);			
			MakeChildren(graph, parent, root);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
				
	}
	
	public static void main(String[] args)
	{
		TreeGr frame = new TreeGr();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}