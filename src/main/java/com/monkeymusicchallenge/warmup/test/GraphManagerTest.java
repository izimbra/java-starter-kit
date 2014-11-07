package com.monkeymusicchallenge.warmup.test;

import com.monkeymusicchallenge.warmup.*;

import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class GraphManagerTest {
	private JSONArray jsonLayout;
    private Graph g1;
    private EdgeWeightedGraph g2;
    private EdgeWeightedGraph mst;

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
        mst = GraphManager.minimumSpanningTree(g2);
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
        // but rather nPk formula which gives 42
        // in this case - http://mathworld.wolfram.com/Permutation.html
    }

    @Test
    public void distsFromMonkeyInG2() {
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
        assertEquals (6, g2.adjEdges(monkey).size());
    }
    
    @Test
    public void minimumSpanningTree() {
    	LinkedList<Edge> list0 = mst.adjEdges(0);
    	Edge e0_1 = list0.get(0);
    	Edge e0_3 = list0.get(1);
    	assertEquals(0, e0_1.getV()); assertEquals(1, e0_1.getW());
    	assertEquals(0, e0_3.getV()); assertEquals(3, e0_3.getW());
   
    	LinkedList<Edge> list1 = mst.adjEdges(1);
    	assertEquals(0, list1.size());

       	LinkedList<Edge> list2 = mst.adjEdges(2);
    	Edge e2_3 = list2.get(0);
    	assertEquals(2, e2_3.getV()); assertEquals(3, e2_3.getW());
    	
    	LinkedList<Edge> list3 = mst.adjEdges(3);
    	Edge e3_6 = list3.get(0);
    	Edge e3_5 = list3.get(1);
    	assertEquals(3, e3_6.getV()); assertEquals(6, e3_6.getW());
    	assertEquals(3, e3_5.getV()); assertEquals(5, e3_5.getW());
    	
       	LinkedList<Edge> list4 = mst.adjEdges(4);
    	Edge e4_5 = list4.get(0);
    	assertEquals(4, e4_5.getV()); assertEquals(5, e4_5.getW());
    	
    	LinkedList<Edge> list5 = mst.adjEdges(5);
    	assertEquals(0, list5.size());
    	
    	LinkedList<Edge> list6 = mst.adjEdges(6);
    	assertEquals(0, list6.size()); 	
    }
    
    @Test
    public void getSortedEdges() {
    	Edge[] edges = GraphManager.getSortedEdges(g2);
        assertEquals(42, edges.length);
        boolean sorted = true;
        Edge min = edges[0];
        for(Edge e : edges) {
        	if(min.getWeight() > e.getWeight()) {
        		sorted = false;
        	}
        	min = e;
        }
        assertTrue(sorted);
    }



}
