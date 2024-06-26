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
            System.out.println(listToRender);
            this.userView.getTextAreas().get(0).append(listToRender.get(finalIndex).getName() + "\n");

            for (User eachUser : listToRender) {
                for (String eachTweet : eachUser.getTweets()) {        
                    this.userView.getTextAreas().get(1).append(eachUser.getName() + ": " + eachTweet + "\n");
                } 
            }
        } else {
            this.userView.getTextAreas().get(1).append(
                listToRender.get(finalIndex).getName() + ": " + messages.get(messages.size() - 1) + "\n");
        }

    }
}
