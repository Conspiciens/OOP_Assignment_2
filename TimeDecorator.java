public class TimeDecorator {
   private long creationTime; 
   
   public TimeDecorator(long creationTime){
        this.creationTime = creationTime;
   }

   public void setCreationTime(long creationTime){
        this.creationTime = creationTime; 
   }

   public long getCreationTime(){
        return this.creationTime; 
   }

   public void displayCreationTime() {
        System.out.println(creationTime);
   }
}
