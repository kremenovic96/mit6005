/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
       // throw new RuntimeException("not implemented");        
        Instant a = startTime(tweets);
        Instant b = endTime(tweets);
        Timespan c = new Timespan(a, b);
        return c;
        
    }
    public static Instant startTime(List<Tweet> tweets){
        if(tweets.isEmpty())
            return Instant.now();
        
        Instant startTime = Instant.MAX;
        for(Tweet tweet : tweets)
            if(tweet.getTimestamp().isBefore(startTime))
                startTime = tweet.getTimestamp();
        
        return startTime;
    }
    
    
    public static Instant endTime(List<Tweet> tweets){
        if(tweets.isEmpty())
            return Instant.now();
        Instant endTime = Instant.MIN;
        for(Tweet tweet : tweets)
            if(tweet.getTimestamp().isAfter(endTime))
                endTime = tweet.getTimestamp();
        return endTime;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
        Set<String> names = new HashSet<>();
        for (Tweet tweet : tweets){
          //  System.out.println("SSSSS");
            String text = tweet.getText().toLowerCase();
           // System.out.println(text);
            
            for (int i = 1; i < text.length();i++){
                String tmp = "";
                //System.out.println(i);
                if((text.charAt(i-1)==' ' && text.charAt(i)=='@') || (i-1==0 && text.charAt(0)=='@')){
                    if(i-1 != 0) i++;
                
                    //System.out.println("ENTERED");
                    while(i != text.length()){
                        if (text.charAt(i) == ' ') break;//-1 rijesi al hvali zadnj sllovo
                        tmp = tmp+text.charAt(i);
                        i++;
                       // System.out.println(i);
                       // System.out.println(tmp);
                    }
                } 
                //tmp = tmp+'a';
               // System.out.println(tmp.length()+"len of tmp");
               // System.out.println("left while");
                if (tmp.length() != 0){
                    names.add(tmp);
                   // System.out.println(tmp);
                    tmp = "";
                }
                
            }
                             
        }
        return names;
        
    }

}
