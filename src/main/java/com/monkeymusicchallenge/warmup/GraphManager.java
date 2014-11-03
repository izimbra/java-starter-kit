package com.monkeymusicchallenge.warmup;

import org.json.JSONArray;

public class GraphManager {

   // private static Graph graph;
   // private static WeightedEdgeGraph g;

	// -1: wall, 0: empty, 1: music (song, album, playlist), 2: monkey, 3: user
	private static int[][] layout = null;
	private static int rows = 0, cols = 0;

    /**
     * Create initial graph with unit edges
     * from JSON game layout
     * @param jsonLayout
     */
	public static Graph createGraph(JSONArray jsonLayout) {

        // Helper class with helper methods
        class Helper {
            private Graph graph;

            Helper(Graph graph) {
                this.graph = graph;
            }

            private Graph populateGraph() {
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
                neighbours[0] = graph.getNode(xyToGraphIndex(x, y-1)); // left
                neighbours[1] = graph.getNode(xyToGraphIndex(x-1, y)); // upper
                neighbours[2] = graph.getNode(xyToGraphIndex(x, y+1)); // right
                neighbours[3] = graph.getNode(xyToGraphIndex(x+1, y)); // lower
                return neighbours;
            }

            // -1: wall, 0: empty, 1: music, 2: monkey, 3: user
            private int itemToInt(String str) {
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
        }

        // method body
        if (layout == null) {
			// calculate the matrix values (wall, empty, music and user)
			rows = jsonLayout.length();
			cols = jsonLayout.getJSONArray(0).length();

            Helper h = new Helper(new Graph(rows*cols));

			// create layout matrix
			layout = new int[rows][cols];
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < cols; ++j) {
					String item = jsonLayout.getJSONArray(i).optString(j);
					layout[i][j] = h.itemToInt(item);
				}
			}
            return h.populateGraph();
		}
        return null;
	}

    /**
     * Create graph with distances between game objects
     */
    public static void createObjectGraph() {

    }

	// helper method

	
    // Converts node coordinates to index
    private static int xyToGraphIndex(int x, int y) {
    	return (y*cols)+x;
    }
}
