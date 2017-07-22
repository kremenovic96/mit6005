/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    /*
     * testing strategy:
     * input empty list of tweets, so returned Map must be empty
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:25:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T11:09:00Z");  
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "itsjustme", "i'm typinggg @milko this for ttttesting @ranko asa", d2);
    private static final Tweet tweet4 = new Tweet(4,"meagain", "@ranko", d1);
    private static final Tweet tweet5 = new Tweet(5,"meagain1", "tagging someone at the end @ranko", d1);
    private static final Tweet tweet6 = new Tweet(6,"meagain2", "email not username bitdiddle@mit.edu", d3);
    private static final Tweet tweet7 = new Tweet(7,"meagain3", "lower and uppercase @RANKO @ranko", d4);
    private static final Tweet tweet8 = new Tweet(8,"meagain4", "here is @goran and @ranko", d4);

    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
        followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2));
      
    }
    /*
     * testing strategy
     * one mentioned user in tweet
     * two mentioned users in tweet
     * one user mentioned twice in tweet
     * 
     */
    @Test
    public void testGuessFollowsGraph(){
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet5));
        assertFalse("expected non-empty graph", followsGraph.isEmpty());
        followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet3));
     //   System.out.println(followsGraph);
      //  System.out.println(followsGraph.values());
        assertEquals(1,followsGraph.size());
        assertTrue(followsGraph.values().contains(Extract.getMentionedUsers(Arrays.asList(tweet3))));
        followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet5, tweet6));
        assertTrue(followsGraph.values().contains(Extract.getMentionedUsers(Arrays.asList(tweet5, tweet6))));
        assertEquals(2, followsGraph.size());
        followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet7));
        assertEquals(1, followsGraph.values().size());
    }
    /*
     * testing strategy
     * empty input
     * input with one tweet
     * input with multiple tweets
     */
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
        followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet5));
        influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("expected one item", 1, influencers.size());
        followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
        influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("expected empty list", influencers.isEmpty());
        followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet5, tweet8));
        influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("ranko", influencers.get(0));
        assertEquals("goran", influencers.get(1));
        

        
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
