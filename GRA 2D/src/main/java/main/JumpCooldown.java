package main;

public class JumpCooldown extends Entity {

   boolean canJump = true;
    Thread gameThread;
    long currentTime;

   public void setCanJump() {
       canJump = !canJump;
       currentTime = System.currentTimeMillis() + 500;
   }

   public boolean checkIfCanJump() {
       if(!canJump) {
           System.out.println("Siema");
           if(currentTime <= System.currentTimeMillis()) {
               canJump = !canJump;
               return true;
           }
           return false;
       }
       return true;
   }

}
