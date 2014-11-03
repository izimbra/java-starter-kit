package com.mokeymusicchallenge.warmup.test;

import org.junit.Before;
import org.junit.Test;

import com.monkeymusicchallenge.warmup.Graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraphTest {

    private Graph g;

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
    public void noOfVertices() {
        assertEquals(g.nrOfVertices(), 3);
    }

    @Test
    public void adjListsNotEmpty() {
        for (int i = 0; i < g.nrOfVertices(); i++)
            assertNotNull(g.adj(i));
    }

}