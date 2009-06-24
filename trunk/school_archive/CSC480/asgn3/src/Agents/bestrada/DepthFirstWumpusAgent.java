package Agents.bestrada;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import BotEnvironment.SearchBot.Node;
import BotEnvironment.SearchBot.SBConstants;

public class DepthFirstWumpusAgent extends EnhancedWumpusAgent
{
   private Stack<Move> _moves;
   private Map<Node, EnhancedNode> _enhanced;
   private Map<Node, Boolean> _beenPushed;
   
   private boolean _hasTakenFirstStep;
   private boolean _justHitWall;
   private Move _lastMoveTaken;
   
   public DepthFirstWumpusAgent()
   {
      super();
      super.setDeveloperName("Bryan Estrada");
      
      _hasTakenFirstStep = false;
      _justHitWall = false;
      _lastMoveTaken = null;
      
      _moves = new Stack<Move>();
      _enhanced = new HashMap<Node, EnhancedNode>();
      _beenPushed = new HashMap<Node, Boolean>();
   }
   
   private void pre()
   {
      if (nearMinion() || nearWumpus())
      {
         super.fireArrow(SBConstants.NORTH);
         super.fireArrow(SBConstants.SOUTH);
         super.fireArrow(SBConstants.EAST);
         super.fireArrow(SBConstants.WEST);
      }
      if (!_hasTakenFirstStep)
      {
         _hasTakenFirstStep = true;
         _moves.push(new Move(super.getBotLocation(), SBConstants.SOUTH));
         _moves.push(new Move(super.getBotLocation(), SBConstants.WEST));
         _moves.push(new Move(super.getBotLocation(), SBConstants.NORTH));
         _moves.push(new Move(super.getBotLocation(), SBConstants.EAST));
      }
      else
      {
         if (nearPit())
         {
            if (_lastMoveTaken.getDirection() != SBConstants.NORTH)
               super.setBelief(SBConstants.SOUTH, PIT_HERE, MAYBE);
            if (_lastMoveTaken.getDirection() != SBConstants.EAST)
               super.setBelief(SBConstants.WEST, PIT_HERE, MAYBE);
            if (_lastMoveTaken.getDirection() != SBConstants.SOUTH)
               super.setBelief(SBConstants.NORTH, PIT_HERE, MAYBE);
            if (_lastMoveTaken.getDirection() != SBConstants.WEST)
               super.setBelief(SBConstants.EAST, PIT_HERE, MAYBE);            
         }
         else
         {
            super.setSurroundingBeliefs(SAFE_HERE, YES);
            if (!_justHitWall /*&& !_beenPushed.containsKey(super.getBotLocation())*/)
            {
               /* TODO: I should add these in random order so that my agent 
                * doesn't always favor going north east */ 
               int start = (int) Math.floor(Math.random() * 4);
               //int start = _lastMoveTaken.getDirection();
               for (int i = start + 1; true; i++)
               {
                  int direction = i % 4;
                  if (super.getBelief(direction, WALL_HERE) != YES && _lastMoveTaken.getDirection() != EnhancedWumpusAgent.getOppositeDirection(direction))
                  {
                     _moves.push(new Move(super.getBotLocation(), direction));
                  }
                  if (direction == start) break;
               }
               /*
               if (_lastMoveTaken.getDirection() != SBConstants.NORTH && super.getBelief(SBConstants.SOUTH, WALL_HERE) != YES)
                  _moves.push(new Move(super.getBotLocation(), SBConstants.SOUTH));
               if (_lastMoveTaken.getDirection() != SBConstants.EAST && super.getBelief(SBConstants.WEST, WALL_HERE) != YES)
                  _moves.push(new Move(super.getBotLocation(), SBConstants.WEST));
               if (_lastMoveTaken.getDirection() != SBConstants.SOUTH && super.getBelief(SBConstants.NORTH, WALL_HERE) != YES)
                  _moves.push(new Move(super.getBotLocation(), SBConstants.NORTH));
               if (_lastMoveTaken.getDirection() != SBConstants.WEST && super.getBelief(SBConstants.EAST, WALL_HERE) != YES)
                  _moves.push(new Move(super.getBotLocation(), SBConstants.EAST));
               */
               //_beenPushed.put(super.getBotLocation(), true);
            }
         }
      }
   }
   
   @Override
   public void step()
   {
      pre();
      
      _lastMoveTaken = _moves.pop();
      while (super.getBotLocation() != _lastMoveTaken.getFrom())
      {
         EnhancedNode en = _enhanced.get(super.getBotLocation());
         super.turnTo(en.getDirectionToParent());
         super.moveForward();
      }
      
      super.turnTo(_lastMoveTaken.getDirection());
      int result = super.moveForward();
      
      post(_lastMoveTaken, result);
   }
   
   private void post(Move move, int result)
   {
      switch(result)
      {
         case HIT_WALL: 
            super.setBelief(move.getDirection(), WALL_HERE, YES);
            _justHitWall = true;
            break;
            
         case SAFE: 
            super.setBelief(SAFE_HERE, YES);
            
         default: 
            _enhanced.put(super.getBotLocation(), new EnhancedNode(move.getFrom(), super.getOppositeDirection(move.getDirection())));
            _justHitWall = false;
      }

   }
   
   public void reset()
   {
      super.reset();
      _moves.clear();
      _beenPushed.clear();
      _enhanced.clear();
      
      _lastMoveTaken = null;
      _hasTakenFirstStep = _justHitWall = false;
   }
   
   /****
    * Class Move describes a move that a depth first agent must take. It 
    * contains a node that represents where the move should begin and a 
    * direction that describes where an agent should step towards.
    */
   class Move
   {
      private Node _from;
      private int _direction;
      
      public Move(Node from, int direction)
      {
         _from = from;
         _direction = direction;
      }
      
      public Node getFrom() { return _from; }
      public int getDirection() { return _direction; }
   }
   
   /****
    * Class EnhancedNode contains additional information used to keep track of 
    * node information, parent nodes, and how to get to them. 
    */
   class EnhancedNode
   {
      private Node _parentNode;
      private int _directionToParent;
      
      public EnhancedNode(Node parent, int directionToParent)
      {
         _parentNode = parent;
         _directionToParent = directionToParent;
      }

      public int getDirectionToParent() { return _directionToParent; }
      public Node getParentNode() { return _parentNode; }
   }
}
