package com.monkeymusicchallenge.warmup;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
     * @param g1
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
        for (TypedNode source : g2.getNodes()) {
            // find source node indices in g1 and g2
            int sG1 = g1.whichNode(source);
            int sG2 = g2.whichNode(source);
            // run Dijkstra
            d = new Dijkstra(g1, sG1);
            // in g2, add edge to other nodes if distance is not dummy value
            for (int tG2 = 0; tG2 < g2.nrOfVertices(); tG2++) {
                // find target node indices in g1 in g2
                int tG1 = g1.whichNode(g2.getNode(tG2));
                if (tG2 != sG2 && // no self-loop
                    d.getDistTo(tG1) < Integer.MAX_VALUE) { // dist must be real
                    Edge e = new Edge(
                            sG2, // use node
                            tG2, // indices from g2
                            d.getDistTo(tG1),  // use distances
                            d.getPathTo(tG1)); // and paths from g1
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
	
	/**
	 * Finds and returns the minimum spanning tree in g2
	 * @param g2
	 */
	public static EdgeWeightedGraph minimumSpanningTree(EdgeWeightedGraph g2) {
		int nrOfVertices = g2.nrOfVertices();
		EdgeWeightedGraph mst = new EdgeWeightedGraph(nrOfVertices);
		
		// Add all minimum edges (Kruskal-style)
		Edge[] edges = getSortedEdges(g2);

		// Adds a new disjoint set detached from other sets for all nodes.
		DisjointSet djSet = new DisjointSet();
		for(TypedNode n : g2.getNodes()) { 
			djSet.makeSet(n);    
		}
		
		// Go through all sorted edges and merge-find the nodes in the disjointed sets.
		int i = 0;
		while(i < edges.length) { 
			Edge minEdge = edges[i];
			int v = minEdge.getV();
			int w = minEdge.getW();
			TypedNode vNode = g2.getNode(v);
			TypedNode wNode = g2.getNode(w);
			// if disjointed sets, add nodes and edge to MST and merge the sets.
			if(djSet.findSet(vNode) != djSet.findSet(wNode)) {
				if(mst.getNode(v) == null) { mst.addNode(v, vNode); }
				if(mst.getNode(w) == null) { mst.addNode(w, wNode); }
				mst.addEdge(minEdge);
				djSet.union(vNode, wNode);
			} 
			i++;
		}
		return mst;
	}
	
	/**
	 * get all edges from EdgeWeightedGraph and sort them increasingly by weight.
	 * @param g
	 */
	public static Edge[] getSortedEdges(EdgeWeightedGraph g) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i=0; i < g.nrOfVertices(); i++) {
			for(Edge e : g.adjEdges(i)) {
				edges.add(e);
			}
		}
		// sort the edges in increasing order.
		Collections.sort(edges, new Comparator<Edge>() {
			public int compare(Edge e1, Edge e2) {
		        return Integer.valueOf(e1.getWeight()).compareTo(e2.getWeight());
		    }
		}); 
		return edges.toArray(new Edge[edges.size()]);
	}

}
