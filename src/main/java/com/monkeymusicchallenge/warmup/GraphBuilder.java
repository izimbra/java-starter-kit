package com.monkeymusicchallenge.warmup;

public class GraphBuilder {
    
	private Graph graph;
	private static int rows = 0, cols = 0;

	public GraphBuilder(int r, int c) {
		rows = r; 
		cols = c;
		this.graph = new Graph(rows*cols);
	}

	// creates a graph reflecting the int symbols in layout
	public Graph populateGraph(int[][] layout) {
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				graph.addNode(xyToGraphIndex(i,j), new TypedNode(i, j, layout[i][j]));
			}
		}
		for(TypedNode node : graph.getNodes()) {
			connectToNeighbours(node);
		}
		return graph;
	}

	// adds edges between nodes and upper, lower, right, left node if they exist
	private void connectToNeighbours(TypedNode node) {
		int x = node.getX();
		int y = node.getY();
		int gindex = xyToGraphIndex(x, y);
		TypedNode[] neighbours = getNeighbours(x, y);
		int neighbourIndex;
		for (TypedNode n : neighbours) {
			if(n != null && n.getType() != -1) { // outside matrix or wall
				neighbourIndex = xyToGraphIndex(n.getX(), n.getY());
				graph.addEdge(gindex, neighbourIndex);
			}
		}
	}
	
	private TypedNode[] getNeighbours(int x, int y) {
		TypedNode[] neighbours = new TypedNode[4];
		try {
			neighbours[0] = graph.getNode(xyToGraphIndex(x,   y-1)); // left
			neighbours[1] = graph.getNode(xyToGraphIndex(x-1, y));   // upper
			neighbours[2] = graph.getNode(xyToGraphIndex(x,   y+1)); // right
			neighbours[3] = graph.getNode(xyToGraphIndex(x+1, y));   // lower
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index out of bounds x: " + x + " y: " + y);
		}
		return neighbours;
	}

    // Converts node coordinates to graph index
    public static int xyToGraphIndex(int x, int y) {
    	if(x >= 0 && x < rows && y >= 0 && y < cols) {
    		return (x*cols)+y;
    	} else {
    		return -1;
    	}
    }
}
