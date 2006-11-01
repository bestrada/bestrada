package Agents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import BotEnvironment.SearchBot.BotSearch;
import BotEnvironment.SearchBot.Node;
import BotEnvironment.SearchBot.SBConstants;

public class DepthFirstSearchAgent extends BotSearch
{
   private Map<Node, Integer> _guide;
   private Map<Node, Node> _heirarchy;
   private Stack<Node> _fringe;
   private boolean _firstStep;
   
   public DepthFirstSearchAgent()
   {
      _guide = new HashMap<Node, Integer>();
      _heirarchy = new HashMap<Node, Node>();
      _fringe = new Stack<Node>();
      _firstStep = false;
   }
   

   @Override
   public void movementStep()
   {
      int direction = _guide.get(getBotLocation()).intValue();
      turnTo(direction);
      moveForward();
   }
   
   @Override
   protected void reset()
   {
      super.reset();
      
      this._fringe.clear();
      this._guide.clear();
      this._heirarchy.clear();
      this._firstStep = false;
   }


   @Override
   public void searchStep()
   {
      if (!_firstStep)
      {
         _firstStep = true;
         _fringe.add(getStartingLocation());
         _heirarchy.put(getStartingLocation(), null);
      }
      if (_fringe.isEmpty()) return;
      Node node = _fringe.pop();
      moveSearchLocation(node);
      
      if (this.getGoalFound())
      {
         /* this is the goal, so create a breadcrumb... */
         for (Node n = node; null != n; )
         {
            Node parent = _heirarchy.get(n);
            if (null == parent) break;
            
            int move = getMove(parent, n);
            _guide.put(parent, new Integer(move));
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
            if (null != n && !_heirarchy.containsKey(n) && !n.getIsWall())
            {
               _heirarchy.put(n, node);
               _fringe.add(n);
            }
         }
      }
   }
   
   private int getMove(Node from, Node to)
   {
      int result = -1;
      
      if (to == from.getNorth()) result = SBConstants.NORTH;
      else if (to == from.getEast()) result = SBConstants.EAST;
      else if (to == from.getSouth()) result = SBConstants.SOUTH;
      else if (to == from.getWest()) result = SBConstants.WEST;
      
      return result;
   }
}
