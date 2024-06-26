import java.util.ArrayList;
import java.util.List;

public class Follow implements Subject {

    private ArrayList<Observer> allObservers = new ArrayList<>();
    private List<User> usersFollowing = new ArrayList<User>();

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
            eachObserver.update(this.usersFollowing, new ArrayList<String>());
        }
    }

    public List<User> getUsersFollowing() {
        return this.usersFollowing; 
    }

    public void followerUser(User userToFollow) {
        this.usersFollowing.add(userToFollow);
        notifyObservers();
    }
}
