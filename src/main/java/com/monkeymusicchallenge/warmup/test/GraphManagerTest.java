package com.monkeymusicchallenge.warmup.test;

import com.monkeymusicchallenge.warmup.EdgeWeightedGraph;
import com.monkeymusicchallenge.warmup.Graph;
import com.monkeymusicchallenge.warmup.GraphManager;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraphManagerTest {
	static final int WALL   = -1;
	static final int EMPTY  = 0;  
	static final int MUSIC  = 1;
	static final int MONKEY = 2;
	static final int USER   = 3;

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
        assertNotNull(g1);
        assertEquals(USER,   g1.getNode(0).getType());
	    assertEquals(WALL,   g1.getNode(14).getType());
	    assertEquals(MUSIC,  g1.getNode(15).getType());
	    assertEquals(EMPTY,  g1.getNode(20).getType());
	    assertEquals(WALL,   g1.getNode(21).getType());
	    assertEquals(MONKEY, g1.getNode(30).getType());
	    assertEquals(MUSIC,  g1.getNode(35).getType());
	}

    @Test
    public void noVerticesInG2() {
        assertNotNull(g2);
        assertEquals(7, g2.nrOfVertices());
    }

    @Test
    public void edgesFromMonkey() {
        int monkey = g2.whichNode(g2.findType(MONKEY));
        System.out.println(g2.adjEdges(monkey));
        assertEquals (6, g2.adjEdges(monkey).size());

    }



}
