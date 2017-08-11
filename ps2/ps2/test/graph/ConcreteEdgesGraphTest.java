/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>(new HashSet<String>(), new ArrayList<Edge<String>>());
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    //create empty graph, zero edges, and with edges
    // TODO tests for ConcreteEdgesGraph.toString()
    
    @Test
    public void testtoStringWithEdges(){
        Graph<String> a = emptyInstance();
        a.add("hello");
        a.add("you");
        a.add("there?");
        a.set("hello", "you", 1);
        // System.out.println(a);
        String graf = a.toString();
        String[] lines = graf.split("\n");
        assertEquals("should be 4 lines", 4, lines.length);
        assertTrue(graf.contains("3 vertices"));
        assertTrue(graf.contains("1 edges"));
        assertTrue(graf.contains("hello"));
        assertTrue(graf.contains("you"));
        assertTrue(graf.contains("there"));
        assertTrue(graf.contains("hello-1-you"));
        a.set("centar", "laus", 3);       
      //  System.out.println(a);
        graf = a.toString();
        lines = graf.split("\n");
        assertEquals("should be 5 lines", 5, lines.length);
        assertTrue(graf.contains("2 edges"));
        assertTrue(graf.contains("5 vertices"));
        assertTrue(graf.contains("hello-1-you"));
        assertTrue(graf.contains("centar-3-laus"));

    }
    
    @Test
    public void testToStringNoEdges(){
        Graph<String> a = emptyInstance();
        a.add("hello");
        a.add("you");
        a.add("there?");
        String graf = a.toString();
        String[] lines = graf.split("\n");
        assertEquals("should be 3 lines", 3, lines.length);
       // System.out.println(a);
        assertTrue(graf.contains("0 edges"));
        assertTrue(graf.contains("3 vertices"));
    }
    
    @Test
    public void testToStringEmptyGraph(){
        Graph<String> a = emptyInstance();
        String graf = a.toString();
        String[] lines = graf.split("\n");
        assertEquals("should be 2 lines", 2, lines.length);
        assertTrue(graf.contains("0 edges"));
        assertTrue(graf.contains("0 vertices"));
    }
    

    
    
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   check getFrom(), getTo(), and getWeight of a single Edge
    
    // TODO tests for operations of Edge
    @Test
    public void testEdgegetTo(){
    Edge<String> a = new Edge<>("aa", "bb", 3);
    assertEquals("bb", a.getTo());
    a = new Edge<>("hello", "you", 1);
    assertEquals("you", a.getTo());   
    }
    
    @Test
    public void testEdgegetFrom(){
        Edge<String> a = new Edge<>("aa", "bb", 3);
        assertEquals("aa", a.getFrom());
        a = new Edge<>("hello", "you", 1);
        assertEquals("hello", a.getFrom());
    }
    
    @Test
    public void testEdgegetWeight(){
        Edge<String> a = new Edge<>("aa", "bb", 3);
        assertEquals(3, a.getWeight());
        a = new Edge<>("hello", "you", 1);
        assertEquals(1, a.getWeight());
    }
    
    

}