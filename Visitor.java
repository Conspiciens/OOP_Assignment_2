import java.util.ArrayList;

public interface Visitor {
    int accept(User user); 
    int accept(Group group);
    int acceptTweets(User user); 
    int acceptTweetsPosPer(User user);
}

class visitImpl implements Visitor {
    private int userCount = 0;
    private int groupCount = 0;
    private int totalTweets = 0;
    private int positiveTweets = 0;
    private ArrayList<String> positiveMessages = new ArrayList<String>();
    
    public visitImpl(){
        positiveMessages.add("Lets go");
        positiveMessages.add("Keep going"); 
        positiveMessages.add("Good"); 
        positiveMessages.add("positive"); 
    }
    
    @Override
    public int accept(User user){
        return ++this.userCount; 
    }

    @Override 
    public int accept(Group group){
        return ++this.groupCount; 
    }

    @Override 
    public int acceptTweets(User user){
        return this.totalTweets += user.getTweets().size();
    }

    @Override 
    public int acceptTweetsPosPer(User user){
        /* Check if the tweet contains a positive message */
        for (String tweet : user.getTweets()){ 
            for (String posMes : positiveMessages){
                if (tweet.contains(posMes)){
                    positiveTweets++;
                }
            }
        }

        return positiveTweets;
    }


}

