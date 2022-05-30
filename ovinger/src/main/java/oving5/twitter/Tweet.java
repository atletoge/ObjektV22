package oving5.twitter;

public class Tweet {

    private String tekst = "test";
    private int retweetCount;
    private TwitterAccount owner;
    private Tweet originalTweet = null;

    public Tweet(TwitterAccount twitterAccount, String tekst) {
        this.tekst = tekst;
        this.owner = twitterAccount;
    }

    public Tweet(TwitterAccount twitterAccount, Tweet tweet) {
        if (tweet.getOwner().equals(twitterAccount)) {
            throw new IllegalArgumentException("Du kan ikke retweete din egen tweet");
        }
        this.tekst = tweet.getText();
        this.owner = twitterAccount;
        this.originalTweet = tweet;
        if(tweet.getOriginalTweet() != null){
            this.originalTweet = tweet.getOriginalTweet();
        }
        tweet.retweetCounter();

    }

    public void retweetCounter() {
        this.retweetCount ++;
    }

    public String getText() {
        return this.tekst;
    }

    public TwitterAccount getOwner() {
        return this.owner;
    }

    public Tweet getOriginalTweet(){
        return this.originalTweet;
    }

    public int getRetweetCount() {
        return this.retweetCount;
    }
}
