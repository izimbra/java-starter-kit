package com.monkeymusicchallenge.warmup.test;

import java.util.LinkedList;

import com.monkeymusicchallenge.warmup.Graph;
import com.monkeymusicchallenge.warmup.TypedNode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    
    /*
    *       2
    *      ^ |
    *   -> | v
    * 0 <-  1
    */ 
    @Test
    public void addEdges() {
    	g.addEdge(1,0);
    	g.addEdge(2,1);
    	
    	LinkedList<Integer> list0 = g.adj(0);
    	assertEquals(1, (int) list0.get(0));
    	
    	LinkedList<Integer> list1 = g.adj(1);
    	assertEquals(0, (int) list1.get(0));
    	assertEquals(2, (int) list1.get(1));
    	
    	LinkedList<Integer> list2 = g.adj(2);
    	assertEquals(1, (int) list2.get(0));
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
    
    @Test
    public void getNodes() {
    	assertEquals(3, g.getNodes().length);
    	assertNull(g.getNode(0)); 
    }
    
    @Test
    public void getNode() {
    	TypedNode n1, n2, n3;
    	n1 = new TypedNode(0,0,0);
    	n2 = new TypedNode(0,1,0);
    	n3 = new TypedNode(0,2,3);
    	
        g.addNode(0, n1); // empty
        g.addNode(1, n2); // empty
        g.addNode(2, n3); // user
        
    	assertEquals(n1, g.getNode(0));
    	assertEquals(n3, g.getNode(2));
    }
    

}