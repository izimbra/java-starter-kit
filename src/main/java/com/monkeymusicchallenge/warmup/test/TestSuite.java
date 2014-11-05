package com.monkeymusicchallenge.warmup.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   GraphManagerTest.class,
   GraphTest.class,
   EdgeWeightedGraphTest.class,
   GraphBuilderTest.class,
   DijkstraTest.class
})
public class TestSuite { }  	