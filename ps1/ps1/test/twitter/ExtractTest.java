/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:25:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T11:09:00Z");


    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    //me
    private static final Tweet tweet3 = new Tweet(3, "itsjustme", "i'm typinggg @milko this for ttttesting @ranko asa", d2);
    private static final Tweet tweet4 = new Tweet(4,"meagain", "@ranko", d1);
    private static final Tweet tweet5 = new Tweet(5,"meagain1", "tagging someone at the end @ranko", d1);
    private static final Tweet tweet6 = new Tweet(6,"meagain2", "email not username bitdiddle@mit.edu", d3);
    private static final Tweet tweet7 = new Tweet(7,"meagain3", "lower and uppercase @RANKO @ranko", d4);

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    /*
     * testing strategy:
     * timespan between:
     * two tweets
     * three tweets
     * four tweets
     */
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
        timespan = Extract.getTimespan(Arrays.asList(tweet6, tweet7));
        assertEquals("expected start", d4, timespan.getStart());
        assertEquals("expected end", d3, timespan.getEnd());
        
        
    }
    @Test
    public void testGetTimespanThreeTweets(){
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));
        assertEquals("expected start",d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
        
    }
    @Test
    public void testGetTimesFourTweets(){
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet7));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d4, timespan.getEnd());
    }
    
    /*
     * Testing strategy
     * Partition the input as follows:
     * tagged user:
     * at the start
     * at the end
     * in the middle
     * multiple tags
     * email(not username) in the text
     * upper and lowercase usernames are equal
     */
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
        // byme bellow
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
    //    System.out.println(mentionedUsers+"testing");
        assertFalse("expected non empty set", mentionedUsers.isEmpty());
        
        //assertEquals(2, mentionedUsers.size());
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet5));
        assertEquals(1, mentionedUsers.size());
      //  System.out.println(mentionedUsers+"testing");

        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        assertEquals(2, mentionedUsers.size());
//        System.out.println(mentionedUsers+"testing");

        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2));
        assertTrue("should be empty set", mentionedUsers.isEmpty());
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6));
        assertTrue("Should be empty set", mentionedUsers.isEmpty());
        mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet7));
        assertEquals(1, mentionedUsers.size());
        
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

    
}
