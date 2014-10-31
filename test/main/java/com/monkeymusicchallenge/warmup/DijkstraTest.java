package com.monkeymusicchallenge.warmup;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

public class DijkstraTest {
    private Graph g;
    private Dijkstra d;

    /** Create the simple graph
     *
     *       2
     *       ^
     *       |
     * 0 - > 1
     */
    @Before
    public void setUp() {
        g = new Graph(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
    }

    @Test
    public void source0() {
        d = new Dijkstra(g, 0);
        assertEquals(d.getDistTo()[0],0);
    }



}