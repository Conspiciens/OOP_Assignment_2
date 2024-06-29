import java.util.ArrayList; 
import java.util.List; 

public class CheckValid implements Command {

    private List<String> users = new ArrayList<String>();
    private List<String> groups = new ArrayList<String>(); 
    private GroupList groupManagement; 


    public CheckValid(GroupList groupManagement) {
       this.groupManagement = groupManagement; 
    }

    @Override 
    public void execute(){ 
        for (Group group : this.groupManagement.getAllGroups()){
            if (this.groups.contains(group.getGroupID()) 
                || group.getGroupID().contains(" ")) {
                System.out.println("Group: " + group.getGroupID() + " is not Valid");
            } else {
                System.out.println("Group: " + group.getGroupID() + " is Valid");
                groups.add(group.getGroupID());
            }
            for (User user : group.getUsers()){
                if (this.users.contains(user.getID()) 
                    || user.getID().contains(" ")) {
                    System.out.println("User: " + user.getID() + " is not Valid"); 
                } else {
                    System.out.println("User: " + user.getID() + " is Valid");
                    users.add(user.getID());
                }
            }
        }
    } 
}
