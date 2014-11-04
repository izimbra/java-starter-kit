package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

/**
 * Undirected graph with edges of arbitrary weight.
 */
public class EdgeWeightedGraph extends Graph {

    private LinkedList<Edge>[] adjE; // adjacency lists

    public EdgeWeightedGraph(int nov) {
        super(nov);
        //adjE = (LinkedList<Edge>[]) new LinkedList[nov];
        for (int v = 0; v < nov; v++) {
            adjE[v] = new LinkedList<Edge>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.getV();
        //int w = e.getW();  //NECESSARY?
        adjE[v].add(e);
    }

    public LinkedList<Edge> adjEdges(int v) {
        return adjE[v];
    }

    // Find some node of the specified type
    public TypedNode findType(int t) {
        for (TypedNode n : this.getNodes())
            if (n.getType() == t) return n;
        return null;
    }
}


