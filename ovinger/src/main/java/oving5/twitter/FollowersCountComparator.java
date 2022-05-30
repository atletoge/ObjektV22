package oving5.twitter;

import java.util.Comparator;

public class FollowersCountComparator implements Comparator<TwitterAccount> {

    @Override
    public int compare(TwitterAccount o1, TwitterAccount o2) {
        return o2.getFollowerAmount() - o1.getFollowerAmount();
    }
    
}
