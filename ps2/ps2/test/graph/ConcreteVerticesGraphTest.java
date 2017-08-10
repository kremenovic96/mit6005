/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph(new ArrayList<Vertex>());
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //
    // TODO tests for operations of Vertex
    @Test
    public void testVertexgetLabel(){
        Vertex v = new Vertex("first");
        assertEquals("first", v.getLabel());
        v.changeLabel("changed");
        assertEquals("changed", v.getLabel());
    }
    @Test
    public void testGetSources(){
        Vertex v = new Vertex("third");
        v.addSource("second", 1);
        v.addSource("first", 54);
        v.addSource("zero", 99);
        Map<String, Integer> tst = v.getSources();
        assertTrue(tst.keySet().contains("second"));
        assertTrue(tst.keySet().contains("first"));
        assertTrue(tst.keySet().contains("zero"));
        assertEquals(3, tst.size());
        assertEquals(1, tst.get("second").intValue());
        assertEquals(54, tst.get("first").intValue());
        assertEquals(99, tst.get("zero").intValue());       
    }
    @Test
    public void testGetTargets(){
        Vertex v = new Vertex("zero");
        v.addTarget("first", 1);
        v.addTarget("second", 2);
        v.addTarget("third", 32);
        Map<String, Integer> tst = v.getTargets();
        assertTrue(tst.keySet().contains("second"));
        assertTrue(tst.keySet().contains("first"));
        assertTrue(tst.keySet().contains("third"));
        assertEquals(3, tst.size());
        assertEquals(1, tst.get("first").intValue());
        assertEquals(2, tst.get("second").intValue());
        assertEquals(32, tst.get("third").intValue());
    }
    
}
