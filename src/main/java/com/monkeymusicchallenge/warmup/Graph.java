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

    // Converts node coordinates to index
    public int xyToInd(int x, int y) {

    }

    public void addEdge(int v, int w) {

    }

    /**
     * Helper class
     * Graph vertex with 2D coordinates
     */
    private static class Node {
        private int x, y;
        private int kind;

        public Node (int x, int y, int kind) {

        }
    }
}