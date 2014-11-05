package com.monkeymusicchallenge.warmup.test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.monkeymusicchallenge.warmup.Edge;
import com.monkeymusicchallenge.warmup.EdgeWeightedGraph;
import com.monkeymusicchallenge.warmup.TypedNode;

public class EdgeWeightedGraphTest {
	
	private EdgeWeightedGraph g; 
	
	/*
	 *  Graph nodes     Graph Edges: nodeNr(x,y,weight)
	 *  1  0  0         0(0,0):[1(0,1,5)]           1(0,1):[3(1,0,10), 8(2,2,8)]  2(0,2):[4(1,1,4),5(1,2,1)]
	 *  0 -1  2         3(1,0):[0(0,0,3),6(2,0,7)]  4(1,1):[2(0,2,8)]             5(1,2):[6(2,0,8)]
	 *  3  1  1         6(2,0):[7(2,1,2)]           7(2,1):[2(0,2,9)]             8(2,2):[7(2,1,3)]
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		g = new EdgeWeightedGraph(9);
		// row 1
		TypedNode n0 = new TypedNode(0,0,1);
		TypedNode n1 = new TypedNode(0,1,0);
		TypedNode n2 = new TypedNode(0,2,0);
		// row 2
		TypedNode n3 = new TypedNode(1,0,0);
		TypedNode n4 = new TypedNode(1,1,-1);
		TypedNode n5 = new TypedNode(1,2,2);
		// row 3
		TypedNode n6 = new TypedNode(2,0,3);
		TypedNode n7 = new TypedNode(2,1,1);
		TypedNode n8 = new TypedNode(2,2,1);
		
		TypedNode[] nodes = {n0, n1, n2, n3, n4, n5, n6, n7, n8};
		for(int i=0; i<9; i++)
			g.addNode(i, nodes[i]);
		//Edges
		Edge e0_1 = new Edge(0,1,5, new LinkedList<Integer>());
		Edge e1_3 = new Edge(1,3,10,new LinkedList<Integer>());
		Edge e1_8 = new Edge(1,8,8, new LinkedList<Integer>());
		Edge e2_4 = new Edge(2,4,4, new LinkedList<Integer>());
		Edge e2_5 = new Edge(2,5,1, new LinkedList<Integer>());
		
		Edge e3_0 = new Edge(3,0,3, new LinkedList<Integer>());
		Edge e3_6 = new Edge(3,6,7, new LinkedList<Integer>());
		Edge e4_2 = new Edge(4,2,8, new LinkedList<Integer>());
		Edge e5_6 = new Edge(5,6,8, new LinkedList<Integer>());
		
		Edge e6_7 = new Edge(6,7,2, new LinkedList<Integer>());
		Edge e7_2 = new Edge(7,2,9, new LinkedList<Integer>());
		Edge e8_7 = new Edge(8,7,3, new LinkedList<Integer>());
		Edge[] edges = {e0_1, e1_3, e1_8, e2_4, e2_5, e3_0, e3_6, e4_2, e5_6, e6_7, e7_2, e8_7};
		for(Edge e : edges) {
			g.addEdge(e);
		}
	}

	@After
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
