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

    public Dijkstra(Graph g, int s) {
        // initialise distance and path arrays
        distTo = new int[g.V()];
        pathTo = (LinkedList<Integer>[]) new LinkedList[g.V()];

        // initialise all distances to infinity, except for soure
        for (int i : distTo)
            distTo[i] = Integer.MAX_VALUE; //XXX risky, is actually a value
        distTo[s] = 0;


    }

}
