import java.util.ArrayList;
import java.util.Random;

/**
 * @author Joshua A. Campbell
 *
 * Represents the system containing the nodes.
 */
public class NodeSystem
{
    // The minimum number of nodes in the network
    private int MIN_NUM_NODES = 10;
    // The max number of nodes in the network
    private int MAX_NUM_NODES = 100;

    // The minimum size of a node's routing table
    private int ROUTING_TABLE_MIN_SIZE = 5;
    // The maximum size of a node's routing table
    private int ROUTING_TABLE_MAX_SIZE = 50;

    // Random number generator for this system
    public Random rand;

    // All nodes in the System
    private ArrayList<Node> nodes; 

    public NodeSystem()
    {
        // Initialize random number generator
        rand = new Random();
        rand.setSeed(100L);

        nodes = new ArrayList<Node>();
    }

    /**
     * Initializes the state of this node system. 
     * This includes generating and connecting the nodes in the system.
     */
    public void initialize()
    {
        // Determine the number of nodes in the network
        int numNodes = rand.nextInt(MAX_NUM_NODES+1);
        while (numNodes < MIN_NUM_NODES)
            numNodes = rand.nextInt(MAX_NUM_NODES+1);

        System.out.println("Number of nodes: " + numNodes);

        // Create the nodes
        for(int i = 0; i < numNodes; i++)
        {
            Node node = new Node();
            
            // TODO
            // Determine this node's peer table size
            node.setPeerTableSize(10);
            nodes.add(node);
        }

        // Randomly connect nodes
        for (Node node : nodes)
        {
            for(int index = 0; index < node.getPeerTableSize(); index++)
            {
                // Find a random node not in this node's peer table
                int nextNeighbor = rand.nextInt(nodes.size());

                while(node.knowsPeer(nextNeighbor) || nextNeighbor == node.getID())
                {
                    nextNeighbor = rand.nextInt(nodes.size());
                }

                node.setPeer(index, nextNeighbor);
            }
        }
    }


    /**
     * Infect a random node with this strategy
     */
    public void infect(StructureStrategy strategy)
    {
        int infectedPeer = rand.nextInt(nodes.size()); 
        System.out.println("Infecting peer: " + infectedPeer);

        nodes.get(infectedPeer).setStrategy(strategy, System.currentTimeMillis());
    }

    // TODO
    public void nextState()
    {
    
    }

    // TODO
    public void getCurrentStateSnapshot()
    {
    
    }
}
