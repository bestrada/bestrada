/****
 * CSC480 - Assignment 3 - Wumpus Agent
 * Bryan Estrada
 * 
 * an agent that traverses the wumpus environment to try and find the gold on 
 * the map. this particular agent will avoid falling into pits, attack enemies, 
 * and randomly walk the map in a depth-first-like manner until the gold is 
 * found.
 */

Relevant Files:
===============
Agents.bestrada.EnhancedWumpusAgent.java: 
This is an abstract class that extends the WumpusAgent class. It provides some 
additional utility methods that are useful (such as setting beliefs for all 
surrounding tiles).

Agents.bestrada.DepthFirstWumpusAgent.java:
This is the concrete implementation of the WumpusAgent class. It keeps an 
internal data structure of nodes and their parents and is used to traverse a 
depth-first type search to reach the goal. The difference is, however, is that 
the agent uses a randomization technique to add nodes to the stack. This is 
done to avoid entering loops where the agent performs the same actions every 
time it reaches certain squares.

Instructions:
=============
I've included the source code and compiled class files in this submission. Make 
sure the wumpus environment jar and the class files are in the runtime of the 
java virtual machine.

Limitations:
============
As mentioned, the agent uses a brute-force type of randomization that introduces 
sever inefficiency. Previous attempts of a more structured, intelligent solution 
failed not because the agent dies, but because the agent would repeatedly 
perform the same actions in an infinite cycle of moves. This looping behavior 
occurred in seemingly unpredictable scenarios, so I combatted this with an 
unpredictable, random agent. As a result, the agent will find the gold sooner 
or later without falling into pits, but will have severely depleted life points 
most of the time.
