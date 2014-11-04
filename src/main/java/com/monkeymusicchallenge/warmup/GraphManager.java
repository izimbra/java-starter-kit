package com.monkeymusicchallenge.warmup;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GraphManager {

    public static final int WALL   = -1;
    public static final int EMPTY  = 0;  
    public static final int MUSIC  = 1;
    public static final int MONKEY = 2;
    public static final int USER   = 3;

	// -1: wall, 0: empty, 1: music (song, album, playlist), 2: monkey, 3: user
	private static int[][] layout = null;

    /**
     * Create initial graph with unit edges
     * from JSON game layout
     * @param jsonLayout
     */
	public static Graph createGraph(JSONArray jsonLayout) {
        if (layout == null) {
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
		}
        return null;
	}

    /**
     * Create edge-weighted graph with distances between game objects
     */
    public static EdgeWeightedGraph createObjectGraph(JSONArray jsonLayout) {
        Graph g1 = createGraph(jsonLayout);
        EdgeWeightedGraph g2;

        // find O - game objects in g1
        List<Integer> objects = Arrays.asList(new Integer[] { MUSIC, MONKEY, USER });
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

        // create edges between objects
        // add edges to g2
        return g2;
    }
    
	// -1: wall, 0: empty, 1: music, 2: monkey, 3: user
	public static int itemToInt(String str) {
		int value = 0;
		switch (str) {
			case "wall":     value = WALL;   break;
			case "empty":    value = EMPTY;  break;
			case "album":    value = MUSIC;  break;
			case "song":     value = MUSIC;  break;
			case "playlist": value = MUSIC;  break;
			case "monkey":   value = MONKEY; break;
			case "user":     value = USER;   break;
		}
		return value;
	}
	

}
