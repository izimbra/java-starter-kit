package com.monkeymusicchallenge.warmup.test;

import java.util.Arrays;
import org.json.JSONArray;

import com.monkeymusicchallenge.warmup.Graph;
import com.monkeymusicchallenge.warmup.GraphManager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphManagerTest {
	static final int WALL   = -1;
	static final int EMPTY  = 0;  
	static final int MUSIC  = 1;
	static final int MONKEY = 2;
	static final int USER   = 3;

	private JSONArray jsonLayout;
	
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
	}

	@After
	public void tearDown() throws Exception {
		jsonLayout = null;
	}
	
	@Test
	public void createGraph() 
	{
	    Graph g = GraphManager.createGraph(jsonLayout);
	    assertEquals(g.nrOfVertices(), 36);
	    assertEquals(USER,   g.getNode(0).getType());
	    assertEquals(WALL,   g.getNode(14).getType());
	    assertEquals(MUSIC,  g.getNode(15).getType());
	    assertEquals(EMPTY,  g.getNode(20).getType());
	    assertEquals(WALL,   g.getNode(21).getType());
	    assertEquals(MONKEY, g.getNode(30).getType());
	    assertEquals(MUSIC,  g.getNode(35).getType());
	}

}
