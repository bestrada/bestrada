package Agents;

import BotEnvironment.SearchBot.Node;

public class UniformCostSearchAgent extends AbstractSearchAgent
{ 
   @Override
   protected String getSearchMethodName()
   {
      return "Uniform Cost";
   }
   
   @Override
   protected int heuristic(Node n)
   {
      return 0;
   }

   @Override
   protected int pathCost(NodeInfo ni)
   {
      NodeInfo pni = _nodeInfo.get(ni.parent);
      return pni.pathCost + getTravelCost(pni.node, ni.node) + ni.node.getCost();      
   }
   
   
}
