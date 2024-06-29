import java.util.ArrayList;
import java.util.List;

public class User {
    private String ID; 
    private String name;   
    private TimeDecorator creationTime; 
    private long lastUpdateTime;  
    private List<String> tweets = new ArrayList<String>();

    public User(String ID, String name, long creationTime){
        this.ID = ID; 
        this.name = name; 
        this.creationTime = new TimeDecorator(creationTime); 
    }

    public String getID(){
        return this.ID; 
    }

    public String getName() {
        return this.name; 
    }

    public void setUpdateTime(long updateTime){
        this.lastUpdateTime = updateTime;
    }

    public void setCreationTime(long creationTime){
        this.creationTime.setCreationTime(creationTime);
    }

    public void displayCreationTime() {
        System.out.print(this.name + ": "); 
        this.creationTime.displayCreationTime();
    }

    public void setTweets(List<String> tweets) {
        this.tweets = tweets;
    }

    public List<String> getTweets() {
        return this.tweets;
    }

    public long getUpdateTime(){
        return this.lastUpdateTime;
    }

    public int accept(Visitor visitor){
        return visitor.accept(this);
    }
}