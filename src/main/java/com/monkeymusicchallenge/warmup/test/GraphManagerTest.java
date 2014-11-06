package com.monkeymusicchallenge.warmup.test;

import com.monkeymusicchallenge.warmup.*;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraphManagerTest {
	private JSONArray jsonLayout;
    private Graph g1;
    private EdgeWeightedGraph g2;

	
	@Before
	public void setUp() throws Exception {
		// creates the layout returned by server
		jsonLayout = new JSONArray();
		jsonLayout.put(new JSONArray(Arrays.asList("user"    , "empty", "empty", "empty", "empty", "empty")));
		jsonLayout.put(new JSONArray(Arrays.asList("playlist", "empty", "empty", "wall" , "wall" , "empty")));
		jsonLayout.put(new JSONArray(Arrays.asList("wall"    , "wall" , "wall" , "album", "empty", "empty")));
		jsonLayout.put(new JSONArray(Arrays.asList("empty"   , "empty", "empty", "wall" , "empty", "song")));
		jsonLayout.put(new JSONArray(Arrays.asList("empty"   , "wall" , "empty", "empty", "empty", "wall")));
		jsonLayout.put(new JSONArray(Arrays.asList("monkey"  , "wall" , "song" , "wall" , "empty", "playlist")));

        // create the graphs
        g1 = GraphManager.createGraph(jsonLayout);
        g2 = GraphManager.createObjectGraph(g1);
    }

	@After
	public void tearDown() throws Exception {
		jsonLayout = null;
	}
	
	@Test
	public void noVerticesInG1() {
        assertNotNull(g1);
        assertEquals(36, g1.nrOfVertices());
    }

    @Test
	public void nodeTypesInG1()
	{
        assertEquals(Types.USER,   g1.getNode(0).getType());
	    assertEquals(Types.WALL,   g1.getNode(14).getType());
	    assertEquals(Types.MUSIC,  g1.getNode(15).getType());
	    assertEquals(Types.EMPTY,  g1.getNode(20).getType());
	    assertEquals(Types.WALL,   g1.getNode(21).getType());
	    assertEquals(Types.MONKEY, g1.getNode(30).getType());
	    assertEquals(Types.MUSIC,  g1.getNode(35).getType());
	}
    
    @Test
    public void noEdgesInG1() {
    	// should be a complete graph except walls
        assertEquals(56, g1.nrOfEdges());
    }
    

    @Test
    public void noVerticesInG2() {
        assertNotNull(g2);
        assertEquals(7, g2.nrOfVertices());
    }

    @Test
    public void nodeTypesInG2() {
        // monkey
        TypedNode monkey = g2.findType(Types.MONKEY);
        assertEquals(5, monkey.getX());
        assertEquals(0, monkey.getY());
        // user
        TypedNode user = g2.findType(Types.USER);
        assertEquals(0, user.getX());
        assertEquals(0, user.getY());
    }

    @Test
    public void noEdgesInG2() {
    	// should be a complete graph!
        assertEquals(42, g2.nrOfEdges());
        // not n*n (we don't want self-loop edges)
        // but rather nPk formula which gives
        // in this case - http://mathworld.wolfram.com/Permutation.html
    }

    @Test
    public void distFromMonkeyInG2() {
        // find monkey node
        int v = g2.whichNode(g2.findType(Types.MONKEY));
        // find and sort distances from monkey
        LinkedList<Integer> dists = new LinkedList<Integer>();
        for (Edge e : g2.adjEdges(v))
            dists.add(e.getWeight());
        Collections.sort(dists);
        assertArrayEquals(new Integer[]{6,9,9,10,17,18}, dists.toArray(new Integer[6]));
    }

    @Test
    public void edgesFromMonkeyInG2() {
    	int monkey = g2.whichNode(g2.findType(Types.MONKEY));
        //System.out.println(monkey);
        ListIterator<Edge> it = g2.adjEdges(4).listIterator();
        while(it.hasNext())
        	System.out.println(it.next().getV());
    
        //System.out.println(g2.adjEdges(0));
        assertEquals (6, g2.adjEdges(monkey).size());
    }


    @Test
    public void minimumSpanningTree() {
    	
    }



}
