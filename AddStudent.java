import java.util.ArrayList;

import javax.swing.JTextArea;

public class AddStudent implements Command {

   private Group rootGroup; 
   private GroupList groupManagement; 
   private User user; 
   private JTextArea textArea; 

   private String groupSelected; 

   public AddStudent(Group rootGroup, GroupList groupManagement, 
      User user, String groupSelected, JTextArea textArea){
      this.rootGroup = rootGroup; 
      this.groupManagement = groupManagement;
      this.user = user; 
      this.groupSelected = groupSelected;
      this.textArea = textArea;  
   }

      
   public void setDefaultText(){
       this.textArea.setText("Tree View \n");
   }

   @Override 
   public void execute(){
      /* Check if a group is selected otherwise use root */
      if (this.rootGroup.getGroupName().equals("Root")){
         this.rootGroup.addUser(this.user);
      } else {
         for (Group group : this.groupManagement.getAllGroups()){
            if (group.getGroupName().equals(this.groupSelected)){
               group.addUser(this.user);
            }
         }
      }


      String tabSpace = " ";
      this.setDefaultText(); 
      /* Init the Group to add the new User to the TextArea */
      for (Group group: this.groupManagement.getAllGroups()){
         this.textArea.append(tabSpace + "" + group.getGroupName() + "\n"); 
         tabSpace += " "; 
         for (User singleUser : group.getUsers()){
            this.textArea.append(tabSpace + "- " + singleUser.getName() + "\n");
         }
         tabSpace += " ";
      }
   } 
}
