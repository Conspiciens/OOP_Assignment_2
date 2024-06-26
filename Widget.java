import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; 
import java.util.List;
import java.util.ArrayList;

class AdminView extends JFrame implements ActionListener {
    private JFrame mainWindow; 
    private Container container; 
    private List<JTextField> textFields = new ArrayList<JTextField>(); 
    private JTextArea textArea = new JTextArea(); 

    private static AdminView ptr;  

    int studentCount = 0; 

    GroupList groupManagement = new GroupList(); 
    Group rootGroup = new Group("Root", "1");

    public static synchronized AdminView getInstance(){
        if (ptr == null){
            ptr = new AdminView();
        }

        return ptr; 
    }

    private AdminView() {}

    public void render () {
        this.groupManagement.addGroup(this.rootGroup);

        /* Create Main Window */
        this.mainWindow = new JFrame(); 
        this.mainWindow.setBounds(0, 0, 1000, 1000);

        /* Exit on Close */
        this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.container = this.mainWindow.getContentPane();
        this.container.setLayout(null);

        /* Buttons on the Right Top Corner */
        JButton addUserBtn = new JButton(); 
        addUserBtn.setBounds(800, 10, 200, 100);
        addUserBtn.setText("Add User");
        addUserBtn.addActionListener(this);
        this.container.add(addUserBtn);

        JButton addGroupBtn = new JButton();  
        addGroupBtn.setBounds(800, 120, 200, 100); 
        addGroupBtn.setText("Add Group");
        addGroupBtn.addActionListener(this);
        this.container.add(addGroupBtn); 

        JTextField userTextField = new JTextField(); 
        userTextField.setBounds(600, 10, 200, 100);
        userTextField.setText("User ID");
        this.textFields.add(userTextField);
        this.container.add(userTextField);

        JTextField groupTextField = new JTextField(); 
        groupTextField.setBounds(600, 120, 200, 100); 
        groupTextField.setText("Group ID"); 
        this.textFields.add(groupTextField);  
        this.container.add(groupTextField);
         
        JButton openUserView = new JButton(); 
        openUserView.setBounds(600, 250, 200, 100);
        openUserView.setText("Open User View"); 
        openUserView.addActionListener(this);
        this.container.add(openUserView);

        /* Text Area on the Left Side */
        this.textArea.setBounds(0, 0, 500, 1000);
        this.container.add(this.textArea);


        /* Buttons on the Bottom Right Corner */
        JButton showGroupTotal = new JButton(); 
        showGroupTotal.setBounds(800, 740, 200, 100);
        showGroupTotal.setText("Show Group Total");
        showGroupTotal.addActionListener(this);
        this.container.add(showGroupTotal);

        JButton showUserTotal = new JButton(); 
        showUserTotal.setBounds(600, 740,200, 100);
        showUserTotal.setText("Show User Total");
        showUserTotal.addActionListener(this);
        this.container.add(showUserTotal);
        
        JButton showMessagesTotal = new JButton(); 
        showMessagesTotal.setBounds(600, 850, 200, 100); 
        showMessagesTotal.setText("Show Messages Total");
        showMessagesTotal.addActionListener(this);
        this.container.add(showMessagesTotal);
        
        JButton showPosPerMessages = new JButton(); 
        showPosPerMessages.setBounds(800, 850, 200, 100); 
        showPosPerMessages.setText("Show Positive Percentage Messages");
        showPosPerMessages.addActionListener(this);
        this.container.add(showPosPerMessages);

        this.setDefaultText();
        String tabSpace = "";
        for (Group group: this.groupManagement.getAllGroups()){
            this.textArea.append(tabSpace + "" + group.getGroupName() + "\n"); 
            tabSpace += " ";
            for (User user: group.getUsers()){
                this.textArea.append(tabSpace + "- " + user.getName() + "\n");
            }
            tabSpace += " "; 
        }

        this.mainWindow.setVisible(true);
    }

    public void setDefaultText(){
        this.textArea.setText("Tree View \n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textFieldInfo; 
        String studentName; 
        String tabSpace = " ";

        Visitor visitor = new visitImpl();
        JFrame miniFrame = new JFrame();
        miniFrame.setBounds(0, 0, 1000, 1000);
        JLabel label = new JLabel();

        switch (e.getActionCommand()){
            case "Add User": 
                String groupForUser = this.textArea.getSelectedText();

                studentName = "stu" + String.valueOf(studentCount);  
                textFieldInfo = this.textFields.get(0).getText();

                User newUser = new User(textFieldInfo, studentName); 
                if (groupForUser == null){
                    this.rootGroup.addUser(newUser);
                } else {
                    for (Group group : this.groupManagement.getAllGroups()){
                        if (group.getGroupName().equals(groupForUser)){
                            group.addUser(newUser);
                        }
                    }
                }

                this.setDefaultText();
                for (Group singleGroup : this.groupManagement.getAllGroups()){
                    this.textArea.append(tabSpace + "" + singleGroup.getGroupName() + "\n"); 
                    tabSpace += " "; 
                    for (User singleUser : singleGroup.getUsers()){
                        this.textArea.append(tabSpace + "- " + singleUser.getName() + "\n");
                    }
                    tabSpace += " ";
                }
                
                this.studentCount++; 
                break;
            case "Add Group": 
                String groupName = "group" + studentCount; 
                textFieldInfo = this.textFields.get(0).getText(); 

                Group newGroup = new Group(groupName, textFieldInfo);
                this.groupManagement.addGroup(newGroup);

                this.setDefaultText();
                for (Group group: this.groupManagement.getAllGroups()){
                    this.textArea.append(tabSpace + "" + group.getGroupName() + "\n"); 
                    tabSpace += " ";
                    for (User singleUser : group.getUsers()){
                        this.textArea.append(tabSpace + "- " + singleUser.getName() + "\n");
                    }
                    tabSpace += " ";
                }
                break;

            case "Open User View": 
                String userText = this.textArea.getSelectedText();

                for (Group group : this.groupManagement.getAllGroups()){
                    for (User user : group.getUsers()){
                        if (user.getName().equals(userText)){
                            new UserView(user, this.groupManagement); 
                        }
                    }
                }

                break;

            case "Show Group Total": 
                int totalGroup = 0; 
                for (Group group : this.groupManagement.getAllGroups()){
                    totalGroup = visitor.accept(group);
                }

                label.setText("Total amount of Groups: " + totalGroup);
                miniFrame.add(label); 
                miniFrame.setVisible(true);
                break;
            case "Show User Total": 
                int totalUser = 0; 
                for (Group group : this.groupManagement.getAllGroups()){
                    for (User user : group.getUsers()){
                        totalUser = visitor.accept(user);
                        System.out.println(totalUser);
                    }
                }

                label.setText("Total amount of Users: " + totalUser); 
                miniFrame.add(label); 
                miniFrame.setVisible(true);
                break;

            case "Show Messages Total": 
                int totalMessages = 0; 
                for (Group group : this.groupManagement.getAllGroups()){
                    for (User user : group.getUsers()){
                        totalMessages = visitor.acceptTweets(user);
                    }
                }

                label.setText("Total amount of Messages: " + totalMessages);
                miniFrame.add(label);
                miniFrame.setVisible(true);
                break; 

            case "Show Positive Percentage Messages": 
                int totalPositiveTweets = 0; 
                int totalTweets = 0; 

                for (Group group: this.groupManagement.getAllGroups()){
                    for (User user : group.getUsers()){
                        totalPositiveTweets = visitor.acceptTweetsPosPer(user); 
                        totalTweets = visitor.acceptTweets(user); 
                    }
                }

                if (totalTweets == 0)
                    break; 
                
                double percentage = (totalPositiveTweets / totalTweets) * 100; 

                label.setText("Total Positive Messages: " + percentage + " %");
                miniFrame.add(label); 
                miniFrame.setVisible(true);
                break; 
        }
    }
}

class UserView extends JFrame implements ActionListener {
    private User user; 
    private GroupList groupManagement; 
    private Follow follow = new Follow();
    private Tweet tweet = new Tweet();

    private JFrame mainWindow = new JFrame(); 
    private Container container; 
    private List<JTextField> textFields = new ArrayList<JTextField>();
    private List<JTextArea> textAreas = new ArrayList<JTextArea>();


    public UserView(User user, GroupList groupManagement) {
        this.user = user; 
        this.groupManagement = groupManagement; 

        follow.addObserver(new ListView(this));
        tweet.addObserver(new ListView(this));

        this.mainWindow.setBounds(0, 0, 1000, 1000);

        this.mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.container = this.mainWindow.getContentPane();
        this.container.setLayout(null);

        JButton followButton = new JButton(); 
        followButton.setBounds(500, 0, 500, 100);
        followButton.setText("Follow User");
        followButton.addActionListener(this);
        container.add(followButton);

        JTextField followField = new JTextField(); 
        followField.setBounds(0, 0, 500, 100);
        followField.setText("User ID");
        this.textFields.add(followField);
        container.add(followField);

        JTextArea followingDisplay = new JTextArea(); 
        followingDisplay.setBounds(0, 100, 1000, 300);
        followingDisplay.append("List View (Current Following): \n");
        this.textAreas.add(followingDisplay);
        container.add(followingDisplay);

        JButton postButton = new JButton(); 
        postButton.setBounds(500, 400, 500, 100);
        postButton.setText("Post Button");
        postButton.addActionListener(this);
        container.add(postButton);

        JTextField tweetMessage = new JTextField();
        tweetMessage.setBounds(0, 400, 500, 100); 
        tweetMessage.setText("Input Message");
        this.textFields.add(tweetMessage);
        this.container.add(tweetMessage);

        JTextArea tweetDisplay = new JTextArea(); 
        tweetDisplay.setBounds(0, 500, 1000, 300);
        tweetDisplay.append("List View (News Feed)\n");
        this.textAreas.add(tweetDisplay); 
        this.container.add(tweetDisplay);

        this.mainWindow.setVisible(true);
    }

    public List<JTextArea> getTextAreas(){
        return this.textAreas;
    }

    @Override 
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Follow User": 
                String followID = this.textFields.get(0).getText(); 

                for (Group singleGroup : this.groupManagement.getAllGroups()){
                    for (User singleUser : singleGroup.getUsers()) {
                        System.out.println(singleUser.getName());
                        if (singleUser.getID().equals(followID)){
                            follow.followerUser(singleUser);
                        }
                    }
                }
                break;
            case "Post Button": 
                String message = this.textFields.get(1).getText();

                System.out.println(message);
                this.tweet.postTweet(this.user, message);                
                this.user.setTweets(tweet.getTweets());
                break; 
        }

    }
}