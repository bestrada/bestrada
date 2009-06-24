package Agents.bestrada;

import BotEnvironment.SearchBot.Node;
import BotEnvironment.SearchBot.SBConstants;
import BotEnvironment.SearchBot.WumpusAgent;

public abstract class EnhancedWumpusAgent extends WumpusAgent
{
   protected void setSurroundingBeliefs(int type, int belief)
   {
      this.setSurroundingBeliefs(super.getBotLocation(), type, belief);
   }
   
   protected void setSurroundingBeliefs(Node node, int type, int belief)
   {
      this.setBelief(node, SBConstants.NORTH, type, belief);
      this.setBelief(node, SBConstants.SOUTH, type, belief);
      this.setBelief(node, SBConstants.EAST, type, belief);
      this.setBelief(node, SBConstants.WEST, type, belief);
   }

   @Override
   public void setBelief(int type, int belief)
   {
      /* don't let myself set something more dangerous than before */
      super.setBelief(type, belief);
   }
   
   @Override
   public void setBelief(Node node, int type, int belief)
   {
      if (YES == belief)
      {
         if (SAFE_HERE == type || GOLD_HERE == type || WALL_HERE == type)
         {
            super.setBelief(node, type, YES);
            super.setBelief(node, WUMPUS_HERE, NO);
            super.setBelief(node, PIT_HERE, NO);
            super.setBelief(node, MINION_HERE, NO);
         }
         else
         {
            super.setBelief(node, type, YES);
            super.setBelief(node, SAFE_HERE, NO);
            super.setBelief(node, GOLD_HERE, NO);
            super.setBelief(WALL_HERE, NO);
         }
      }
      
      /* never let myself set something less definate than before */
      int current = super.getBelief(node, type);
      if (MAYBE == current || UNKNOWN == current)
      {
         super.setBelief(node, type, belief);
      }
   }
   
   protected class NodeInfo
   {
      private Node here;
      private int direction;
      
      public NodeInfo(Node n, int direction)
      {
         this.here = n;
         this.direction = direction;
      }
      
      public int getDirection()
      {
         return this.direction;
      }

      public Node getHere()
      {
         return here;
      }
   }
   
   protected class ParentNode
   {
      private Node node;
      private int directionTo;
      
      /**
       * @param n the Node that refers to the parent
       * @param dir the direction that a child must turn to arrive at the parent
       */
      public ParentNode(Node n, int dir)
      {
         this.node = n;
         this.directionTo = dir;
      }

      public int getDirectionTo()
      {
         return directionTo;
      }

      public Node getNode()
      {
         return node;
      }
   }
   
   public static int getOppositeDirection(int direction)
   {
      int result = -1;
      switch (direction)
      {
         case SBConstants.WEST: result = SBConstants.EAST; break;
         case SBConstants.EAST: result = SBConstants.WEST; break;
         case SBConstants.NORTH: result = SBConstants.SOUTH; break;
         case SBConstants.SOUTH: result = SBConstants.NORTH; break;
      }
      return result;
   }
}
