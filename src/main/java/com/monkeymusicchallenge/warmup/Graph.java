package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;


/**
 * Created by izimbra on 30/10/14.
 * Rip-off of Sedgewick & Wayne's (2011, p.526) Graph class
 * Uses adjacency lists to represent
 */
public class Graph {
    private final int nrOfVertices; // no. vertices
    private int nrOfEdges; // no. edges
    private TypedNode[] nodes; // nodes of the graph
    private LinkedList<Integer>[] adj;

    public Graph(int nov) {
        nrOfVertices = nov;
        nodes = new TypedNode[nov];

        adj = (LinkedList<Integer>[]) new LinkedList[nov];

        for (int v = 0; v < nrOfVertices; v++)
            adj[v] = new LinkedList<Integer>();
    }

    // Add or update i-th node of the graph
    public void addNode(int i, TypedNode n) { nodes[i] = n; }

    // Adds an edge between two nodes
    public void addEdge(int v, int w) {
        adj[v].add(w);
        nrOfEdges++;
   }
    
    public TypedNode getNode(int i) { return nodes[i]; }
    
    public TypedNode[] getNodes() { return nodes; }

    public int whichNode(TypedNode n) {
        int v = 0;
        while (!nodes[v].equals(n))
            v++;
        return v;
    }

    // No. vertices in the graph
    public int nrOfVertices() { return nrOfVertices; }

    // Adjacency list of i-th node
    public LinkedList<Integer> adj(int i) { return adj[i]; }

}
