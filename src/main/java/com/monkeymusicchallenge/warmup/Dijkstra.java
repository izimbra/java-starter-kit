package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

/**
 * Implementation of Dijkstra's shortest path algorithm for graph with unit edges.
 *
 */

public class Dijkstra {
    private Graph g;

    // path lengths from source
    private int[] distTo;
    // actual paths from source
    private LinkedList<Integer>[] pathTo;
    // indexed priority queue of candidate nodes
    // priority is the distance from source
    private IndexMinPQ<Integer> pq;

    public Dijkstra(Graph g, int s) {
        int V = g.nrOfVertices();

        // initialise distance and path arrays, and PQ
        distTo = new int[V];
        pathTo = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++)
            pathTo[v] = new LinkedList<Integer>();
        pq = new IndexMinPQ<Integer>(V);

        // initialise all distances to infinity, except for source
        for (int i = 0; i < V; i++)
            distTo[i] = Integer.MAX_VALUE; //XXX risky, is actually a value
        distTo[s] = 0;
        pathTo[s].add(s);

        // add source to PQ
        pq.insert(s, 0);

        while(!pq.isEmpty())
            relaxEdges(g, pq.delMin());
    }

    private void relaxEdges(Graph g, int v) {
        for (int w : g.adj(v)) {
            if (distTo[w] > distTo[v] + 1) {
                distTo[w] = distTo[v] + 1; // unit-weight edges
                // s-w path = s-v path + w
                pathTo[w].addAll(pathTo[v]);
                pathTo[w].add(w);
                // update
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    // Getters

    public LinkedList<Integer> getPathTo(int v) {
        return pathTo[v];
    }

    public int[] getDistances() {
        return distTo;
    }

    public int getDistTo(int v) {
        return distTo[v];
    }

}
