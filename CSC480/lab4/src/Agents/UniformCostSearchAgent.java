package Agents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import BotEnvironment.SearchBot.BotSearch;
import BotEnvironment.SearchBot.Node;
import BotEnvironment.SearchBot.SBConstants;

public class UniformCostSearchAgent extends BotSearch
{
   public static final int MOVEMENT_COST = 20;
   public static final int TURN_COST = 10;
   
   /**
    * pretty much a struct for extra node information
    * @author bestrada
    */
   class NodeInfo
   {
      public int facing;
      public int cost;
      public int nextMove;
      
      public Node parent;
   }
   
   private Map<Node, NodeInfo> _nodeInfo;
   private TreeSet<Node> _fringe;
   private boolean _firstStep;
   
   public UniformCostSearchAgent()
   {
      _firstStep = false;
      
      _nodeInfo = new HashMap<Node, NodeInfo>();
      _fringe = new TreeSet<Node>(
            new Comparator<Node>()
            {
               public int compare(Node n1, Node n2)
               {
                  return _nodeInfo.get(n1).cost - _nodeInfo.get(n2).cost;
               }
            }
      );
   }
   
   @Override
   protected void reset()
   {
      super.reset();
      
      _nodeInfo.clear();
      _firstStep = false;
   }

   @Override
   public void movementStep()
   {
      int direction = _nodeInfo.get(super.getBotLocation()).nextMove;
      super.turnTo(direction);
      super.moveForward();
   }

   @Override
   public void searchStep()
   {
      if (!_firstStep)
      {
         _firstStep = true;
         _fringe.add(super.getStartingLocation());
         
         NodeInfo ni = new NodeInfo();
         ni.cost = 0;
         ni.facing = SBConstants.EAST;
         ni.parent = null;
         
         _nodeInfo.put(super.getStartingLocation(), ni);
      }
      if (_fringe.isEmpty()) return;
      
      Node node = _fringe.first();
      _fringe.remove(node);
      moveSearchLocation(node);
      
      if (super.getGoalFound())
      {
         /* this is the goal, so create a breadcrumb... */
         for (Node n = node; null != n; )
         {
            Node parent = _nodeInfo.get(n).parent;
            if (null == parent) break;
            
            int move = getMove(parent, n);
            _nodeInfo.get(parent).nextMove = move;
            //_guide.put(parent, new Integer(move));
            n = parent;
         }
      }
      else
      {
         List<Node> children = new ArrayList<Node>(4);
         children.add(getNorthOfSearchLocation());
         children.add(getEastOfSearchLocation());
         children.add(getSouthOfSearchLocation());
         children.add(getWestOfSearchLocation());
         
         for(Node n : children)
         {
            if (null != n && !_nodeInfo.containsKey(n) && !n.getIsWall())
            {
               NodeInfo pni = _nodeInfo.get(node);
               NodeInfo ni = new NodeInfo();
               ni.parent = node;
               ni.cost = pni.cost + getTravelCost(node, n) + n.getCost();
               ni.facing = this.getMove(node, n);
               _nodeInfo.put(n, ni);
               _fringe.add(n);
            }
         }         
      }
   }
   
   protected int getTravelCost(Node from, Node to)
   {
      /* it will always cost at least this much */
      int cost = UniformCostSearchAgent.MOVEMENT_COST;
      
      NodeInfo fni = _nodeInfo.get(from);
      
      switch (fni.facing)
      {
         case SBConstants.NORTH:
            switch (this.getMove(from, to))
            {
               case SBConstants.EAST: case SBConstants.WEST:
                  cost += UniformCostSearchAgent.TURN_COST;
                  break;
               case SBConstants.SOUTH:
                  cost += (UniformCostSearchAgent.TURN_COST * 2);
                  break;
            }
         case SBConstants.SOUTH:
            switch (this.getMove(from, to))
            {
               case SBConstants.EAST: case SBConstants.WEST:
                  cost += UniformCostSearchAgent.TURN_COST;
                  break;
               case SBConstants.NORTH:
                  cost += (UniformCostSearchAgent.TURN_COST * 2);
                  break;
            }
         case SBConstants.EAST:
            switch (this.getMove(from, to))
            {
               case SBConstants.SOUTH: case SBConstants.NORTH:
                  cost += UniformCostSearchAgent.TURN_COST;
                  break;
               case SBConstants.WEST:
                  cost += (UniformCostSearchAgent.TURN_COST * 2);
                  break;
            }
         case SBConstants.WEST:
            switch (this.getMove(from, to))
            {
               case SBConstants.SOUTH: case SBConstants.NORTH:
                  cost += UniformCostSearchAgent.TURN_COST;
                  break;
               case SBConstants.EAST:
                  cost += (UniformCostSearchAgent.TURN_COST * 2);
                  break;
            }            
      }
      return cost;
   }
   
   protected int getMove(Node from, Node to)
   {
      int result = -1;
      
      if (to == from.getNorth()) result = SBConstants.NORTH;
      else if (to == from.getEast()) result = SBConstants.EAST;
      else if (to == from.getSouth()) result = SBConstants.SOUTH;
      else if (to == from.getWest()) result = SBConstants.WEST;
      
      return result;      
   }
}
