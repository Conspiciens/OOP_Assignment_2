import java.util.ArrayList;
import java.util.List;

public class User {
    private String ID; 
    private String name;   
    private List<String> tweets = new ArrayList<String>();

    public User(String ID, String name){
        this.ID = ID; 
        this.name = name; 
    }

    public String getID(){
        return this.ID; 
    }

    public String getName() {
        return this.name; 
    }

    public void setTweets(List<String> tweets) {
        this.tweets = tweets;
    }

    public List<String> getTweets() {
        return this.tweets;
    }


    public int accept(Visitor visitor){
        return visitor.accept(this);
    }
}