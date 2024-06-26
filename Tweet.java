import java.util.ArrayList;
import java.util.List;

public class Tweet implements Subject {
    private ArrayList<Observer> allObservers = new ArrayList<>();
    private List<String> tweets = new ArrayList<String>();
    private List<User> users = new ArrayList<User>();
    
    @Override
    public void addObserver(Observer observer) {
        allObservers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        allObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer eachObserver : allObservers) {
            eachObserver.update(this.users, this.tweets);
        }
    }

    public List<String> getTweets(){
        return this.tweets;
    }

    public void postTweet(User currentUser, String tweet) {
        this.users.add(currentUser);
        this.tweets.add(tweet);
        notifyObservers();
    }
}