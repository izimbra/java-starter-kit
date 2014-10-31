package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;


/**
 * Created by izimbra on 30/10/14.
 * Rip-off of Sedgewick & Wayne's (2011, p.526) Graph class
 * Uses adjacency lists to represent
 */
public class Graph {
    private final int nrOfVertices; // no. vertices
    private int E; // no. edges
    private TypedNode[] nodes; // nodes of the graph
    private LinkedList<Integer> adj[];

    public Graph(int nov) {
        this.nrOfVertices = nov;
        nodes = new TypedNode[nov];
        adj = (LinkedList<Integer>[]) new LinkedList[nov];
        for (LinkedList<Integer> a : adj)
            a = new LinkedList<Integer>();
    }

    // Add or update i-th node of the graph
    public void addNode(int i, TypedNode n) {
        nodes[i] = n;
    }

    // Adds an edge between two nodes
    public void addEdge(int v, int w) {
        if (nodes[v] == null || nodes[w] == null) {
            throw new NullPointerException("Node not initialised");
        } else {
            adj[v].add(w);
            E++;
        }
    }
    
    public TypedNode getNode(int i) {
    	return nodes[i];
    }
    
    public TypedNode[] getNodes() {
    	return nodes;
    }
}
