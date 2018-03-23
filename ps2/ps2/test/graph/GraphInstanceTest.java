/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    @Test
    public void testAdd(){
        Graph<String> a = emptyInstance();
        a.add("first elem");
        assertEquals("must be only one elem in a graph", 1, a.vertices().size());
        a.add("second elem");
        assertEquals("must be two elements", 2, a.vertices().size());
        a.add("third elem");
        assertEquals("must be three elements", 3, a.vertices().size());
        String s = "four";
        assertTrue(a.add(s));
        assertFalse(a.add(s));
    }
    /*
     * testing strategy:
     * remove from middle
     *  from end
     *  from start
     *  from middle
     */
    @Test
    public void testRemove(){
        Graph<String> a = emptyInstance();
        String aa = "first elem";
        String b = "second elem";
        String c = "third elem";
        String d = "fourth";
        a.add(aa);
        a.add(b);
        a.add(c);
        a.add(d);
        assertFalse(a.remove("not there"));
        assertTrue(a.remove(aa));
        assertFalse(a.vertices().contains(aa));
        assertEquals(3, a.vertices().size());
        assertTrue(a.remove(c));
        assertTrue(a.remove(d));
        assertTrue(a.remove(b));
        assertTrue(a.vertices().isEmpty());
        
    }
    /*
     * testing strat
     * connect two vertices
     * change weight between these two
     * connect other way round
     * 
     */
    @Test
    public void testset(){
        Graph<String> a = emptyInstance();
        String aa = "first elem";
        String b = "second elem";
        String c = "third elem";
        String d = "fourth";
        a.add(aa);
        a.add(b);
        a.add(c);
        a.add(d);
        assertEquals(0, a.set(aa, b, 2));
        assertEquals(0, a.set(c, d, 1));
        
        assertEquals(1, a.set(c, d, 5));
        assertEquals(2, a.set(aa, b, 6));
        assertEquals(0, a.set(b, aa, 31));
        assertEquals(0, a.set(b, c, 1));
        assertEquals(31, a.set(b, aa, 31));
        assertEquals(1, a.set(b, c, 1));
        String x = "new el";
        String y = "new el";
        a.set(x, y, 55);//there is no such vertices so they must be auto-added
        assertEquals(55, a.set(x, y, 66));
        
    }
    /*
     * test strat:
     * added vertices individually
     * added vertices by using set() method
     * 
     * 
     * 
     * 
     */
   /* @Test
    public void testVertices(){
        Graph<String> a = emptyInstance();
        String aa = "first elem";
        String b = "second elem";
        String c = "third elem";
        String d = "fourth";
        a.add(aa);
        a.add(b);
        a.add(c);
        a.add(d);
        assertTrue(a.vertices().containsAll(Arrays.asList(aa,b,c,d)));
        String x = "fifth";
        String y = "six";
        a.set(x, y, 1);
        assertTrue(a.vertices().contains(x));
        assertTrue(a.vertices().contains(y));
    }*/
    /*
     * testing strat
     * src of one vertices is one 
     * src of other three vertices is same
     */
    @Test
    public void testSources(){
        Graph<String> a = emptyInstance();
        String aa = "first elem";
        String b = "second elem";
        String c = "third elem";
        String d = "fourth";
     //   a.set("xy", "yx", 44);
      /*  Map<String, Integer> tst = emptyInstance().sources("xy");
        assertEquals(1, tst.size());*/

        a.set(aa, b, 1);
        a.set(c, b, 2);
        a.set(aa, b, 6);
        a.set(aa, c, 5);
        Map<String, Integer> tst = a.sources(c);
        assertEquals(1, tst.size());
        tst = a.sources(b);
        assertEquals(2, tst.size());
        assertTrue(tst.values().containsAll(Arrays.asList(2, 6)));
    }
    /*
     * testing strat
     * four vertices one point to one 
     * three point to one
     */
    @Test
    public void testTargets(){
        Graph<String> a = emptyInstance();
        String aa = "first elem";
        String b = "second elem";
        String c = "third elem";
        String d = "fourth";
        a.set(aa, b, 1);
        a.set(c, b, 2);
        a.set(aa, b, 6);
        a.set(aa, c, 5);
        //System.out.println(a);
        Map<String, Integer> tst = a.targets(c);
        assertEquals(1, tst.size());
        tst = a.targets(b);
        assertEquals(0, tst.size());
        a.set(c, "hello", 99);
        tst = a.targets(c);
        assertEquals(2, tst.size());
        assertTrue(tst.keySet().containsAll(Arrays.asList(b, "hello")));
    }
    
    
}
