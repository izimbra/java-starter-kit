package com.mokeymusicchallenge.warmup.test;

import org.junit.Before;
import org.junit.Test;

import com.monkeymusicchallenge.warmup.Dijkstra;
import com.monkeymusicchallenge.warmup.Graph;

import static org.junit.Assert.*;

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
        g.addEdge(1,0);
        g.addEdge(1,2);
        g.addEdge(2,1);
    }

    @Test
    public void source0() {
        d = new Dijkstra(g, 0);

        assertEquals(0, d.getDistTo(0));
        assertArrayEquals(new Integer[] {0}, d.getPathTo(0).toArray());
        assertEquals(1, d.getDistTo(1));
        assertArrayEquals(new Integer[] {0,1}, d.getPathTo(1).toArray());
        assertEquals(2, d.getDistTo(2));
        assertArrayEquals(new Integer[] {0,1,2}, d.getPathTo(2).toArray());
    }

    @Test
    public void source1() {
        d = new Dijkstra(g, 1);

        assertEquals(1, d.getDistTo(0));
        assertArrayEquals(new Integer[]{1, 0}, d.getPathTo(0).toArray());
        assertEquals(0, d.getDistTo(1));
        assertArrayEquals(new Integer[] {1}, d.getPathTo(1).toArray());
        assertEquals(1, d.getDistTo(2));
        assertArrayEquals(new Integer[] {1,2}, d.getPathTo(2).toArray());
    }

    @Test
    public void source2() {
        d = new Dijkstra(g, 2);

        assertEquals(2, d.getDistTo(0));
        assertArrayEquals(new Integer[] {2,1,0}, d.getPathTo(0).toArray());
        assertEquals(1, d.getDistTo(1));
        assertArrayEquals(new Integer[] {2,1}, d.getPathTo(1).toArray());
        assertEquals(0, d.getDistTo(2));
        assertArrayEquals(new Integer[] {2}, d.getPathTo(2).toArray());
    }

    @Test
    public void shortcut0to2() {
        // Add extra 0-2 edge
        g.addEdge(0,2);

        d = new Dijkstra(g, 0);

        assertEquals(0, d.getDistTo(0));
        assertArrayEquals(new Integer[] {0}, d.getPathTo(0).toArray());
        assertEquals(1, d.getDistTo(1));
        assertArrayEquals(new Integer[] {0,1}, d.getPathTo(1).toArray());
        assertEquals(1, d.getDistTo(2));
        assertArrayEquals(new Integer[] {0,2}, d.getPathTo(2).toArray());
    }


}