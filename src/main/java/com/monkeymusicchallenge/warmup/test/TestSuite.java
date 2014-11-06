package com.monkeymusicchallenge.warmup.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   GraphTest.class,
   EdgeWeightedGraphTest.class,
   GraphBuilderTest.class,
   DijkstraTest.class,
   GraphManagerTest.class
})
public class TestSuite { }  	