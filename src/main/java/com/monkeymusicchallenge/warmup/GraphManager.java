package com.monkeymusicchallenge.warmup;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GraphManager {

    /**
     * Create initial graph with unit edges from JSON game layout
     * @param jsonLayout
     */
	public static Graph createGraph(JSONArray jsonLayout) {
        int[][] layout = null;

        if (jsonLayout.length() != 0) {
			// calculate the matrix values (wall, empty, music and user)
			int rows = jsonLayout.length();
			int cols = jsonLayout.getJSONArray(0).length();

            GraphBuilder builder = new GraphBuilder(rows, cols);

			// create layout matrix
			layout = new int[rows][cols];
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < cols; ++j) {
					String item = jsonLayout.getJSONArray(i).optString(j);
					layout[i][j] = itemToInt(item);
				}
			}
            return builder.populateGraph(layout);
		} else {
            return null;
		}
	}

    /**
     * Create edge-weighted graph with distances between game objects
     */
    public static EdgeWeightedGraph createObjectGraph(Graph g1) {
        EdgeWeightedGraph g2;

        // find O - game objects in g1
        List<Integer> objects = Arrays.asList(new Integer[] { Types.MUSIC, Types.MONKEY, Types.USER });
        LinkedList<TypedNode> ns = new LinkedList<TypedNode>();
        for (TypedNode n : g1.getNodes())
            if (objects.contains(n.getType()))
                ns.add(n);

        // set nodes of g2 to be O, new indices
        g2 = new EdgeWeightedGraph(ns.size());
        int v = 0;
        for (TypedNode n : ns)
            g2.addNode(v++, n);

        // run Dijkstra on all pairs in O
        // and create edges between objects
        Dijkstra d;
        for (TypedNode n : g2.getNodes()) {
            // find source node in g1
            int s = g2.whichNode(n);
            //run Dijkstra
            d = new Dijkstra(g2, s);
            // add edge to other nodes if distance is not dummy value
            for (int t = 0; t < g2.nrOfVertices(); t++) {
                if (t != s && d.getDistTo(t) < Integer.MAX_VALUE) {
                    Edge e = new Edge(s, t, d.getDistTo(t), d.getPathTo(t));
                    g2.addEdge(e);
                }
            }
        }
        return g2;
    }
    
	// -1: wall, 0: empty, 1: music, 2: monkey, 3: user
	public static int itemToInt(String str) {
		int value = 0;
		switch (str) {
			case "wall":     value = Types.WALL;   break;
			case "empty":    value = Types.EMPTY;  break;
			case "album":    value = Types.MUSIC;  break;
			case "song":     value = Types.MUSIC;  break;
			case "playlist": value = Types.MUSIC;  break;
			case "monkey":   value = Types.MONKEY; break;
			case "user":     value = Types.USER;   break;
		}
		return value;
	}
	
	public static EdgeWeightedGraph minSpanningTree(Graph g2) {
		return null;
	}
	

}
