import javax.swing.JTextArea;

public class AddGroup implements Command {
    private Group rootGroup; 
    private GroupList groupManagement; 
    private JTextArea textArea; 
    
    public AddGroup(Group rootGroup, GroupList groupManagement, 
        User user, JTextArea textArea){
            
        this.rootGroup = rootGroup; 
        this.groupManagement = groupManagement; 
        this.textArea = textArea; 
    }

    public void setDefaultText() {
        this.textArea.setText("Tree View");
    }

    @Override 
    public void execute() {
        this.groupManagement.addGroup(rootGroup);
        this.setDefaultText();

        String tabSpace = " "; 
        for (Group group : this.groupManagement.getAllGroups()){
            this.textArea.append(tabSpace + group.getGroupName() + "\n");
            for (User user : group.getUsers()){
                this.textArea.append(tabSpace + "- " + user.getName() + "\n");
            }
            tabSpace += " "; 
        }
    } 
}
