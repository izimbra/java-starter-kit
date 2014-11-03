package com.mokeymusicchallenge.warmup.test;

import com.monkeymusicchallenge.warmup.Graph;
import com.monkeymusicchallenge.warmup.TypedNode;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void whichNode() {
        TypedNode n1, n2, n3;
        n1 = new TypedNode(1,2,3);
        n2 = new TypedNode(2,3,4);
        n3 = new TypedNode(3,4,5);

        g.addNode(0, n1);
        g.addNode(1, n2);
        g.addNode(2, n3);

        assertEquals(2, g.whichNode(n3));
        assertEquals(1, g.whichNode(n2));
        assertEquals(0, g.whichNode(n1));
    }

}