/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   above each method
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    /*
     * test strat.
     * load a text from file and use it to make a poem
     */
    @Test
    public void testPoem(){
        try{
        GraphPoet a = new GraphPoet(new File("test/poet/starwars"));
        GraphPoet b = new GraphPoet(new File("test/poet/mugar-omni-theater.txt"));
        b = new GraphPoet(new File("test/poet/saa"));
        b = new GraphPoet(new File("test/poet/mine"));
        b = new GraphPoet(new File("test/poet/bee"));
        String inp = "Seek to explore new and exciting synergies!";
        String res = a.poem(inp);
        assertEquals("Seek to explore strange new life and exciting synergies!", res);
        a = new GraphPoet(new File("test/poet/mugar-omni-theater.txt"));
        inp = "Test the system.";
        res = a.poem(inp);
        assertEquals("Test of the system.", res);
        }
        catch(IOException a){
            System.out.println("cant create file in test file");
        }
    }

    @Test
    public void testPoemNumbers() throws IOException{
        GraphPoet testPoet = new GraphPoet(new File("test/poet/nums"));
        String poem = testPoet.poem("1 3");
        assertEquals("output poem should be 1 2 3", "1 2 3", poem);
    }
}
