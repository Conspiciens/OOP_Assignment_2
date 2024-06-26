import java.util.List;

public class ListView implements Observer {

    private UserView userView;

    public ListView(UserView userView) {
        this.userView = userView;
    }

    @Override
    public void update(List<User> listToRender, List<String> messages) {
        int finalIndex = listToRender.size() - 1;

        if (messages.isEmpty()) {
            /* Append the name of the User to the Text Area */
            this.userView.getTextAreas().get(0)
                .append(listToRender.get(finalIndex).getName() + "\n");

            /* Append all the Users tweets that the user follows */
            for (User eachUser : listToRender) {
                for (String eachTweet : eachUser.getTweets()) {        
                    this.userView.getTextAreas().get(1).append(eachUser.getName() + ": " + eachTweet + "\n");
                } 
            }
        } else {
            /* Append Tweets that User follows */
            this.userView.getTextAreas().get(1).append(
                listToRender.get(finalIndex).getName() + ": " + messages.get(messages.size() - 1) + "\n");
        }

    }
}
