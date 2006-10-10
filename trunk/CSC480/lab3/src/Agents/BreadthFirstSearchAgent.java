package Agents;

import java.util.HashMap;
import java.util.Map;

import BotEnvironment.SearchBot.BotSearch;
import BotEnvironment.SearchBot.Node;

public class BreadthFirstSearchAgent extends BotSearch
{
   private enum Direction { NORTH, SOUTH, EAST, WEST }
   private Map<Node, Direction> _guide;
   
   public BreadthFirstSearchAgent()
   {
      _guide = new HashMap<Node, Direction>();
   }
   
   public void step()
   {
      Node here = this.getBotLocation();
   }

   @Override
   public void movementStep()
   {
      Direction d = _guide.get(this.getBotLocation());
      switch (d)
      {
         case NORTH: this.moveNorth(); break;
         case SOUTH: this.moveSouth(); break;
         case EAST:  this.moveEast();  break;
         case WEST:  this.moveWest();  break;
      }
   }

   @Override
   public void searchStep()
   {
      // TODO Auto-generated method stub
      
   }
   
   protected void moveSouth()
   {
      
   }
   
   protected void moveNorth()
   {
      
   }
   
   protected void moveEast()
   {
      
   }
   
   protected void moveWest()
   {
      
   }
}
