package Agents;

import BotEnvironment.SearchBot.Node;

public class bestrada_UniformCostSearchAgent extends bestrada_AbstractSearchAgent
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
