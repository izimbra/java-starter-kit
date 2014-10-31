package com.monkeymusicchallenge.warmup;

import org.json.JSONArray;

public class GraphManager {

	// -1: wall, 0: empty, 1: music (song, album, playlist), 2: monkey, 3: user
	private static int[][] layout = null;
	private static Graph graph = null;
	
	private static int rows = 0, cols = 0;
	
	public static void createLayout(JSONArray jsonLayout) {
		if (layout == null) {
			// calculate the matrix values (wall, empty, music and user)
			rows = jsonLayout.length();
			cols = jsonLayout.getJSONArray(0).length();
			// create layout matrix
			layout = new int[rows][cols];
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < cols; ++j) {
					String item = jsonLayout.getJSONArray(i).optString(j);
					layout[i][j] = itemToInt(item);
				}
			}
			createGraph();
		}
	}

	private static void createGraph() {
		graph = new Graph(rows*cols);
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				graph.addNode(xyToGraphIndex(i,j), new TypedNode(i, j, layout[i][j]));
			}
		}
		for(TypedNode node : graph.getNodes()) {
			connectToNeighbours(node);
		}
	}

	private static void connectToNeighbours(TypedNode node) {
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

	private static TypedNode[] getNeighbours(int x, int y) {
		TypedNode[] neighbours = new TypedNode[4];
		neighbours[0] = graph.getNode(xyToGraphIndex(x, y-1)); // left
		neighbours[1] = graph.getNode(xyToGraphIndex(x-1, y)); // upper
		neighbours[2] = graph.getNode(xyToGraphIndex(x, y+1)); // right
		neighbours[3] = graph.getNode(xyToGraphIndex(x+1, y)); // lower
		return neighbours;
	}

	// -1: wall, 0: empty, 1: music, 2: monkey, 3: user
	private static int itemToInt(String str) {
		int value = 0;
		switch (str) {
	    	case "wall":     value = -1; break;
	    	case "empty":    value = 0;  break;
	    	case "album":    value = 1;  break;
	    	case "song":     value = 1;  break;
	    	case "playlist": value = 1;  break;
	    	case "monkey":   value = 2;  break;
	    	case "user":     value = 3;  break;           
		}
		return value;
	}	
	
    // Converts node coordinates to index
    private static int xyToGraphIndex(int x, int y) {
    	return (y*cols)+x;
    }
}
