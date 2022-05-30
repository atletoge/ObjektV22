package oving5.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TwitterAccount {
    
    private String brukernavn;
    private List<TwitterAccount> followers = new ArrayList();
    private List<TwitterAccount> following = new ArrayList();
    private List<Tweet> tweets = new ArrayList();
    private int antallTweets;
    private int antallRetweets;


    public TwitterAccount(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public int getFollowerAmount() {
        return this.followers.size();
    }

    @Override
    public String toString() {
        return brukernavn+" er ditt brukernavn";
    }

    public String getUserName() {
        return this.brukernavn;
    }

    public void follow(TwitterAccount account) {
        if (this.equals(account)){
            throw new IllegalArgumentException("Du kan ikke følge deg selv");
        }
        this.following.add(account);
        account.followers.add(this);
    }
    
    public void unfollow(TwitterAccount account) {
        if(this.isFollowing(account)){
            this.following.remove(account);
            account.followers.remove(this);
        }
        else {
            throw new IllegalArgumentException("Du må følge personen for å unfollowe");
        }
    }

    public boolean isFollowing(TwitterAccount account) {
        return this.following.contains(account);
    }

    public boolean isFollowedBy(TwitterAccount account) {
        return account.isFollowing(this);
    }

    public void tweet(String tekst) {
        Tweet nyTweet = new Tweet(this, tekst);
        this.tweets.add(0,nyTweet);
        this.antallTweets ++;
    }

    public void retweet(Tweet tweet) {
        Tweet retweet = new Tweet(this, tweet);
        this.tweets.add(0,retweet);
        retweet.getOriginalTweet().getOwner().retweetCounter();
        this.antallTweets ++;
    }

    public Tweet getTweet(int i){
        if(i < this.tweets.size()+1) {
            return this.tweets.get(i-1);
        }
        else {
            throw new IllegalArgumentException("Ugyldig tall i");
        }
    }

    public int getTweetCount(){
        return this.antallTweets;
    }

    public int getRetweetCount() {
        return this.antallRetweets;
    }

// Opprettet for innkapsling
    public void retweetCounter() {
        this.antallRetweets ++;
    }

    public List<TwitterAccount> getFollowers(Comparator<TwitterAccount> comparator) {
        if (comparator == null) {
            return this.followers;
        } List<TwitterAccount> sortedAccs = new ArrayList<>(followers);
        Collections.sort(sortedAccs, comparator);
        return sortedAccs;
    }
}
