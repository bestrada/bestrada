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
      public NodeInfo(Node n)
      {
	 this.node = n;
      }
      
      /** what direction your guy faces if he moved here */
      public int facing;
      
      /** how much it costs to get to this spot */
      public int cost;
      
      /** the "breadcrumb" if this is on the path to the goal */
      public int nextMove;
      
      /** a reference to this node's parent */
      public Node parent;
      
      /** a reference to this node itself */
      public Node node;
   }
   
   private Map<Node, NodeInfo> _nodeInfo;
   private TreeSet<NodeInfo> _fringe;
   private boolean _firstStep;
   
   public UniformCostSearchAgent()
   {
      _firstStep = false;
      
      _nodeInfo = new HashMap<Node, NodeInfo>();
      _fringe = new TreeSet<NodeInfo>(
            new Comparator<NodeInfo>()
            {
               public int compare(NodeInfo n1, NodeInfo n2)
               {
                  return n1.cost - n2.cost;
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
         
         NodeInfo ni = new NodeInfo(super.getStartingLocation());
         ni.cost = 0;
         ni.facing = SBConstants.EAST;
         ni.parent = null;
         
         _nodeInfo.put(super.getStartingLocation(), ni);
         _fringe.add(ni);
      }
      if (_fringe.isEmpty()) return;
      
      Node node = _fringe.first().node;
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
            if (null != n && !n.getIsWall())
            {
               /* get extra info for the parent */
               NodeInfo pni = _nodeInfo.get(node);
               
               /* create new node info for this one */
               NodeInfo ni = new NodeInfo(n);
               
               ni.parent = node;
               ni.cost = pni.cost + getTravelCost(node, n) + n.getCost();
               ni.facing = this.getMove(node, n);
               
               /* put it in if it doesn't exist, or override if this one costs 
                * less */
               if (!_nodeInfo.containsKey(n) || _nodeInfo.get(n).cost >= ni.cost)
               {
        	  _nodeInfo.put(n, ni);
                  _fringe.add(ni);
               }
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
            break;
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
            break;
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
            break;
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
            break;
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
      
      if (result == -1)
      {
	 super.log("Hey, couldn't find the direction!");
      }
      
      return result;      
   }
}
