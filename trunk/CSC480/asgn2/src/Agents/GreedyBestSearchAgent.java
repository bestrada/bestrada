package Agents;

import BotEnvironment.SearchBot.Node;
import BotEnvironment.SearchBot.SBFunctions;

public class GreedyBestSearchAgent extends AbstractSearchAgent
{
   protected String getSearchMethodName()
   {
      return "Greedy Best First";
   }
   
   @Override
   protected int heuristic(Node n)
   {
      return SBFunctions.getDistanceToGoal(n);
   }

   @Override
   protected int pathCost(NodeInfo ni)
   {
      return 0;
   }
   
   
}
