package Agents;

import java.util.Random;


/**
 * MyRandomAgent add's a randomization factor to the simple right-turn obstacle 
 * avoidance response of MyAgent.
 *  
 * @author bestrada
 */
public class MyRandomAgent extends MyAgent
{
   private Random generator = new Random();
   
   @Override
   public void step()
   {
      /* my random agent will randomly turn in mid stride even if there are no 
       * obstacles that forces it to turn. this will happen, on average, once 
       * out of every ten steps. */
      boolean doTurn = 0 == (generator.nextInt() % 10);
      if (doTurn) turnRight();
      
      super.step();
   }

   @Override
   public void turnRight()
   {
      /* on average, half the time my random agent turns right, it will really 
       * turn right. the other half of the time it will turn left */
      boolean random = 0 == (generator.nextInt() % 2);
      if (random) super.turnRight();
      else super.turnLeft();
   }
   
}
