package Agents;

import BotEnvironment.SearchBot.Node;
import BotEnvironment.SearchBot.SBFunctions;

public class AStarSearchAgent extends AbstractSearchAgent
{
   @Override
   protected String getSearchMethodName()
   {
      return "A*";
   }
   
   @Override
   protected int heuristic(Node n)
   {
      return SBFunctions.getDistanceToGoal(n);
   }
   
   @Override
   protected int pathCost(NodeInfo ni)
   {
      NodeInfo pni = _nodeInfo.get(ni.parent);
      return pni.pathCost + getTravelCost(pni.node, ni.node) + ni.node.getCost();      
   }
}
