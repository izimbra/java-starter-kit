package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

/**
 * Created by izimbra on 30/10/14.
 * Rip-off of Sedgewick & Wayne's (2011, p.526) Graph class
 * Uses adjacency lists to represent
 */
public class Graph {
    private final int V; // no. vertices
    private int E; // no. edges
    public LinkedList<Integer>[] adj; // adjacency lists to represent connected nodes

    public Graph(int V) {
        this.V = V;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (LinkedList<Integer> a : adj) {
            a = new LinkedList<Integer>();
        }
    }

    public void addEdge(int v, int w) {

    }

}
