package tree_grapher;


import java.awt.Point;

import javax.swing.JFrame;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyNode;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;



public class TreeGr extends JFrame
{	
	public static int numchildren = 3; //number of children
	public static int kgen = 3;			//number of generations
	public static Node flag;
	
	private static final long serialVersionUID = 1L;
	
	public Node MakeRootNode(mxGraph g, Object p)
	{
		Node root = new Node((mxCell) g.insertVertex(p, null,
				" (Root)", 30, 400, 50, 20));
		root.root = true;
		return root;
	}
	
//	public Object getParent(Object v)
//	{
//		return ;
//	}
	
/*	public void MakeChildren(mxGraph g, Object p, Node r)
	{
		if(nodenum < nodequant-1)
		{
			Object[] v;
			v = new Object[kvetvl];
			mxCell[] c = new mxCell[3];
			for(int i = 0; i < 3; i++)
			{
				c[i] = (mxCell) g.addCell(p);
				for(int j = 0; j <= kvetvl-1; j++)
				{
					v[j] = g.insertVertex(p, null, nodenum, x, y, 15, 15);
					System.out.println(v[j].getClass());
					g.insertEdge(p, null, "", r, v[j]);
					nodenum++;
					//x+=20;
				}
				r = v[i];
				//x-=20;
				//y+=40;
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
		
	}*/
	
/*	public void MakeChildren(mxGraph g, Object p, Node r, int nc, int kg)
	{
		Node[] v = new Node[nc];
		for(int i = 0; i < nc; i++)
		{
			v[i] = new Node((mxCell) g.insertVertex(p, null, i, 30, 30, 10, 10));
			v[i].ancestor = r;
			r.Child[i] = v[i];
			g.insertEdge(p, null, "", v[i].ancestor.mc, v[i].mc);
		}
		kgen--;
		r.cmade = true;
		if(kgen == 0)
		{
			if (flag.root)
				System.out.println("position: root");
			if (!flag.root)
			{
				flag = flag.ancestor;
				kgen++;
				flag.ancestor.obrab++;
			}
		}
		if(flag.obrab == 0 && flag.cmade)
		{
			flag = flag.Child[flag.obrab];
			
		}
		
	}*/
	
	public void MakeChildren(mxGraph g, Object p, Node r, int nc, int kg)
	{
		Node[] v = new Node[nc];
		for(int i = 0; i < nc; i++)
		{
			v[i] = new Node((mxCell) g.insertVertex(p, null, i, 30, 30, 10, 10));
			v[i].ancestor = r;
			r.Child[i] = v[i];
			g.insertEdge(p, null, "", v[i].ancestor.mc, v[i].mc);
			if(kg>0)
				MakeChildren(g, p, v[i], nc, kg-1);
		}		
	}

	public  TreeGr()
	{
		
		final mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();
		try
		{
			Node root = MakeRootNode(graph, parent);
			flag = root;
			MakeChildren(graph, parent, root, numchildren, kgen);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		
	    mxCompactTreeLayout layout = new mxCompactTreeLayout(graph);
	    layout.execute(graph.getDefaultParent());
				
	}
	
	public static void main(String[] args)
	{
		TreeGr frame = new TreeGr();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}