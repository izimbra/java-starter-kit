package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;


/**
 * Rip-off of Sedgewick & Wayne's (2011, p.526) undirected Graph class
 * Uses adjacency lists for storing edge information
 */
public class Graph {
    private final int nrOfVertices; // no. vertices
    protected int nrOfEdges;        // no. edges
    private TypedNode[] nodes;      // nodes of the graph
    private LinkedList<Integer>[] adj;

    public Graph(int nov) {         // nov = number of vertices
        nrOfVertices = nov;
        nodes = new TypedNode[nov];
        adj = (LinkedList<Integer>[]) new LinkedList[nov];
        for (int v = 0; v < nrOfVertices; v++)
            adj[v] = new LinkedList<Integer>();
    }

    // Add or update i-th node of the graph
    public void addNode(int i, TypedNode n) { 
    	nodes[i] = n; 
    }

    // Adds an edge between two nodes
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v); // TODO: Should this be done?
        nrOfEdges++;
    }
    
    public TypedNode getNode(int i) {
    	return (i >= 0 && i < nrOfVertices) ? nodes[i] : null;
    }
    
    public TypedNode[] getNodes() { return nodes; }

    // Index of a node
    public int whichNode(TypedNode n) {
        int v = 0;
        while (!nodes[v].equals(n) && v < nrOfVertices)
            v++;
        return v;
    }

    // No. vertices in the graph
    public int nrOfVertices() { return nrOfVertices; }

    public int nrOfEdges() { return nrOfEdges; }

    // Adjacency list of i-th node
    public LinkedList<Integer> adj(int i) { return adj[i]; }

}
