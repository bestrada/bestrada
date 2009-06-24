/****
 * CSC480 - Assignment 2 - Search Algorithm Bots
 * Bryan Estrada
 * 
 * agents that do Uniform Cost, Greedy Best, and A* Search Methods to find the 
 * goal in the Bot Environment.
 */

Relavent Files:
===============
I am also submitting the bot environment source in this archive for your 
convenience. If you use the entire source from the archive, then the ant build 
tasks described below will work. But by your request, I am including the java 
source code for my agents with the naming conventions you specified.The agents  
in the Agents package are pertinent to this  assignment. They include:

 - bestrada_AbstractSearchAgent.java:
     does most of the work, but needs to be subclassed to be able to make 
     calculations about the environment. This is an abstract class, so you 
     cannot instantiate it. Do not try to use it with the Bot Environment.
     
 - bestrada_UniformCostSearchAgent.java:
     Extends AbstractSearchAgent. Returns 0 for the heuristic and a computation 
     of the sum of total path cost for the path cost.
     
 - bestrada_GreedyBestSearchAgent.java:
     Extends AbstractSearchAgent. Returns 0 for the path cost and uses the 
     SBFunctions class to give the heuristic (i.e. the distance to goal).
     
 - bestrada_AStarSearchAgent.java:
     Extends AbstractSearchAgent. Returns a computed sum of the total path cost 
     for the path cost and uses the SBFunctions class to approximate a 
     heuristic (i.e. the distance to the goal).
     
To build:
=========
%> ant build

To run:
=======
(you need to be on a machine that has the Swing libraries and a GUI environment 
 to run it in)
%> ant run

Other:
======
(cleans the build)
%> ant clean
