package com.monkeymusicchallenge.warmup;

import org.json.JSONArray;

public class LayoutManager {

	// -1: wall, 0: empty, 1: music (song, album, playlist), 2: monkey, 3: user
	private static int[][] layout = null;
	private static Node[] adjacencyArray = null;
	
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
			createAdjacencyArray();
		}
	}

	private static void createAdjacencyArray() {
		// create adjacency Array
		adjacencyArray = new Node[rows*cols];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				Node node = new Node(i, j, layout[i][j]);
				adjacencyArray[xyToInd(i,j)] = node;
			}
		}		
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
    public static int xyToInd(int x, int y) {
    	return (x*cols)+y;
    }
}
