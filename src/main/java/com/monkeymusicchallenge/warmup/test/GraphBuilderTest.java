package com.monkeymusicchallenge.warmup.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.monkeymusicchallenge.warmup.Graph;
import com.monkeymusicchallenge.warmup.GraphBuilder;

public class GraphBuilderTest {
	static final int WALL   = -1;
	static final int EMPTY  = 0;  
	static final int MUSIC  = 1;
	static final int MONKEY = 2;
	static final int USER   = 3;


	int[][] layout = {
			{ 3, 0, 0, 0, 0, 0 }, 
			{ 1, 0, 0,-1,-1, 0 }, 
			{-1,-1,-1, 1, 0, 0 }, 
			{ 0, 0, 0,-1, 0, 1 }, 
			{ 0,-1, 0, 0, 0,-1 }, 
			{ 2,-1, 1,-1, 0, 1 }};
	GraphBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		builder = new GraphBuilder(6, 6);
	}

	@After
	public void tearDown() throws Exception {
		builder = null;
	}
	
	@Test
	public void populateGraph() {
		Graph g = builder.populateGraph(layout);
		assertEquals("graph size should be equal 36", g.nrOfVertices(), 36);
        assertEquals(USER,   g.getNode(0).getType());
	    assertEquals(WALL,   g.getNode(14).getType());
	    assertEquals(MUSIC,  g.getNode(15).getType());
	    assertEquals(EMPTY,  g.getNode(20).getType());
	    assertEquals(WALL,   g.getNode(21).getType());
	    assertEquals(MONKEY, g.getNode(30).getType());
	    assertEquals(MUSIC,  g.getNode(35).getType());
	}
}
