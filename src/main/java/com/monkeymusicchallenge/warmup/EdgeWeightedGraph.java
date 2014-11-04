package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

/**
 * Undirected graph with edges of arbitrary weight.
 */
public class EdgeWeightedGraph extends Graph {

    private LinkedList<Edge>[] adj; // adjacency lists

    public EdgeWeightedGraph(int nov) {
        super(nov);
    }

    public void addEdge(Edge e) {
        int v = e.getV();
        int w = e.getW();
        adj[v].add(e);
    }
}


