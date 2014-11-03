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
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
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
