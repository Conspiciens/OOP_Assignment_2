import java.util.ArrayList;
import java.util.List;

public interface UserGroup {
    public void display(); 
}

class GroupList implements UserGroup {
   private List<Group> Groups = new ArrayList<Group>();

   public GroupList(){
   }

   public void addGroup(Group group) { 
    if (Groups.contains(group))
        return;
    this.Groups.add(group);
   }

   public List<Group> getAllGroups(){
        return this.Groups; 
   }

   public void display(){
        for (Group group : Groups){
            group.display();
        }
   }


}

class Group implements UserGroup {
    private String name; 
    private String ID; 
    private TimeDecorator creation; 
    private List<User> Users = new ArrayList<User>(); 

    public Group(String name, String ID, long creationTime){
        this.name = name; 
        this.ID = ID; 
        this.creation = new TimeDecorator(creationTime);
    }

    public void addUser(User user){
        if (Users.contains(user))
            return; 
        Users.add(user);
    }

    public void setCreationTime(long creationTime){
        creation.setCreationTime(creationTime);
    }

    public void printCreationTime() {
        System.out.print(name + ": ");
        creation.getCreationTime();
    }

    public List<User> getUsers() {
        return this.Users; 
    }

    public String getGroupName() {
        return this.name; 
    }

    public String getGroupID() {
        return this.ID; 
    }

    public void display() {
        System.out.println(ID); 
        System.out.println(name); 
        for (User user : Users){
            System.out.println(user); 
        }
    }

    public int accept(Visitor visitor) {
        return visitor.accept(this);
    }
}
