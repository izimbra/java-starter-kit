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
	public void xyToGraphIndex() {
		assertEquals("last row, last col should be equal 35", GraphBuilder.xyToGraphIndex(5, 5), 35);
		assertEquals("first row, first col should be equal 0", GraphBuilder.xyToGraphIndex(0, 0), 0);		
	}
	
	@Test
	public void populateGraph() {
		Graph g = builder.populateGraph(layout);
		assertEquals("graph size should be equal 36", g.nrOfVertices(), 36);
	}
}
