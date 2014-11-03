package com.monkeymusicchallenge.warmup;

public class GraphBuilder {
    
	private Graph graph;
	private static int rows = 0, cols = 0;

	GraphBuilder(int r, int c) {
		rows = r; cols = c;
		this.graph = new Graph(rows*cols);
	}

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
		neighbours[0] = graph.getNode(xyToGraphIndex(x,   y-1)); // left
		neighbours[1] = graph.getNode(xyToGraphIndex(x-1, y));   // upper
		neighbours[2] = graph.getNode(xyToGraphIndex(x,   y+1)); // right
		neighbours[3] = graph.getNode(xyToGraphIndex(x+1, y));   // lower
		return neighbours;
	}

    // Converts node coordinates to index
    public static int xyToGraphIndex(int x, int y) {
    	return (y*cols)+x;
    }
}
