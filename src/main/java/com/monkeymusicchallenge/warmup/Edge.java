package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

/**
 * W
 * Integer-weighted edge
 * with information about its two ends,
 * and the path between them
 * in the original {@link Graph} object
 */
public class Edge {
    private int v;
    private int w;
    private int weight;
    private LinkedList<Integer> path;

    public Edge(int v, int w, int weight, LinkedList<Integer> path) {
        this.v = v;
        this.w = w;
        this.weight = weight;
        this.path = path;
    }

    public int getV() { return v; }
    public int getW() { return w; }

}
