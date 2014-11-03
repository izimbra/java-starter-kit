package com.mokeymusicchallenge.warmup.test;


import junit.framework.*;
public class TestRunner {
   public static void main(String[] a) {
      // add the test's in the suite
      //TestSuite suite = new TestSuite(DijkstraTest.class, GraphManagerTest.class, GraphTest.class );
	  TestSuite suite = new TestSuite(GraphManagerTest.class);
      TestResult result = new TestResult();
      suite.run(result);
      System.out.println("Number of test cases = " + result.runCount());
    }
}
