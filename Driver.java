import java.util.Random;

/**
 * @author Joshua A. Campbell
 * 
 * Driver for the self-structuring gossip tests.
 */
public class Driver
{

    // Number of states to simulate
    // TODO make a parameter
    private static int CYCLES = 3;

    public static void main(String[] args)
    {
        // Random generator to be used for the simulation
        // TODO make seed a parameter
        Random seedRand = new Random();
        seedRand.setSeed(100L);

        System.out.println("Initializing system.");

        Random systemRand = new Random(seedRand.nextLong());
        NodeSystem system = new NodeSystem(systemRand);

        system.initialize();
        System.out.println("Done initializing system.");

        // TODO move into NodeSystem constructor?
        // Set the gossip protocol
        // TODO make protocol a parameter
        Random protocolRand = new Random(seedRand.nextLong());
        GossipProtocol protocol = new PushPullProtocol(protocolRand);
        system.setGossipProtocol(protocol);

        // Infect a node
        // TODO make strategy a parameter
        system.infect(new CloseStrategy());
        
        System.out.println("Starting simulation.");
        for(int i = 0; i < CYCLES; i++)
        {
            system.nextState();
        }
        
    }
}
