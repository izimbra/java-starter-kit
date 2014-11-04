package com.mokeymusicchallenge.warmup.test;

import java.util.Arrays;
import org.json.JSONArray;

import com.monkeymusicchallenge.warmup.Graph;
import com.monkeymusicchallenge.warmup.GraphManager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphManagerTest {

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
	public void createLayout() 
	{
	    Graph g = GraphManager.createGraph(jsonLayout);
	    assertEquals(g.nrOfVertices(), 36);
	}

}
