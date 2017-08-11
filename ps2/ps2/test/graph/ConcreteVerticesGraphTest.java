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
        @Test
        public void testtoString(){
            Graph<String> a = emptyInstance();
            a.add("hello");
            a.add("you");
            a.add("there?");
            a.set("hello", "you", 2);
            a.set("you", "meeee", 36);
            /*a.set("haha", "hehe", 1);
            a.set("hehe", "monkey", 32);
            a.set("hello", "monkey", 16);*/
        //    System.out.print(a);
            String graf = a.toString();
            assertTrue(graf.contains("4 vertices"));
            assertTrue(graf.contains("hello"));
            assertTrue(graf.contains("you"));
            assertTrue(graf.contains("there"));
            assertTrue(graf.contains("hello-2-you"));
            assertTrue(graf.contains("you-36-meeee"));
        }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //strategy above each test
    // TODO tests for operations of Vertex
    
    //tests name of vertex
    @Test
    public void testVertexgetLabel(){
        Vertex v = new Vertex("first");
        assertEquals("first", v.getLabel());
        v.changeLabel("changed");
        assertEquals("changed", v.getLabel());
    }
    
    //
    @Test
    public void testSources(){
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
    public void testTargets(){
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
    
    @Test
    public void testAdd(){
        Graph<String> graf = emptyInstance();
        graf.add("first");
        assertTrue(graf.vertices().contains("first"));
        graf.add("second");
        assertEquals("there is two vertices", 2, graf.vertices().size());
        
    }
    
    @Test
    public void testRemove(){
        //napisati za vertex graf remove metodu testtt
        Graph<String> graf = emptyInstance();
        graf.add("first");
        assertTrue(graf.remove("first"));
        assertTrue(graf.vertices().isEmpty());
        graf.add("second");
        graf.add("first");
        graf.remove("first");
        assertEquals(1, graf.vertices().size());
        assertTrue(graf.vertices().contains("second"));        
    }
    
    @Test
    public void testset(){
        Graph<String> graf = emptyInstance();
        graf.add("first");
        graf.add("second");
        assertEquals(0, graf.set("first", "second", 5));
        assertEquals(5, graf.set("first", "second", 31));
        assertEquals(0, graf.set("hello", "you", 55));
        assertEquals(0, graf.set("second", "first", 55));
        assertEquals(55, graf.set("second", "first", 32));
        
    }
}
