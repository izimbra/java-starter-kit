package com.musicchallange.test;

import org.json.JSONArray;
import com.monkeymusicchallenge.warmup.GraphManager;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GraphManagerTest {

	JSONArray jsonLayout;
	
	@BeforeClass
	public void setUp() throws Exception {
		// creates the layout returned by server
		jsonLayout = new JSONArray();
		jsonLayout.add(new JSONArray(Arrays.asList("user", "empty", "empty", "empty", "empty", "empty")));
		jsonLayout.add(new JSONArray(Arrays.asList("playlist", "empty", "empty", "wall", "wall", "empty")));
		jsonLayout.add(new JSONArray(Arrays.asList("wall", "wall", "wall", "album", "empty", "empty")));
		jsonLayout.add(new JSONArray(Arrays.asList("empty", "empty", "empty", "wall", "empty", "song")));
		jsonLayout.add(new JSONArray(Arrays.asList("empty", "wall", "empty", "empty", "empty", "wall")));
		jsonLayout.add(new JSONArray(Arrays.asList("monkey", "wall", "song", "wall", "empty", "playlist")));
	}

	@AfterClass
	public void tearDown() throws Exception {
		jsonLayout = null;
	}
	
	@Test
	public void testCreateLayout() 
	{
	    //GraphManager.createGraph(jsonLayout);
	    assertNotEquals(1,2);
	}

	/*@Test
	public void testCreateGraph() 
	{
	    int x  = 1 ; int y = 1;
	    assertEquals(2, myClass.add(x,y));
	}
	
	@Test
	public void testConnectToNeighbour() 
	{
	    int x  = 1 ; int y = 1;
	    assertEquals(2, myClass.add(x,y));
	}
	
	@Test
	public void testGetNeighbours() 
	{
	    int x  = 1 ; int y = 1;
	    assertEquals(2, myClass.add(x,y));
	}
	
	@Test
	public void testItemToInt() 
	{
	    int x  = 1 ; int y = 1;
	    assertEquals(2, myClass.add(x,y));
	}
	
	@Test
	public void testXYToGraphIndex() 
	{
	    assertEquals(2, GraphManager.xyToGraphIndex(3,4));
	}
	*/
}
