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
    private static int CYCLES = 10;

    public static void main(String[] args)
    {
        // Random generator to be used for the simulation
        // TODO make a parameter
        Random rand = new Random();
        rand.setSeed(100L);

        System.out.println("Initializing system.");

        NodeSystem system = new NodeSystem(rand);

        system.initialize();
        System.out.println("Done initializing system.");

        // TODO move into NodeSystem constructor?
        // Set the gossip protocol
        // TODO make a parameter
        system.setGossipProtocol(new PushPullProtocol(rand));

        // Infect a node
        // TODO make a parameter
        system.infect(new EmptyStrategy());
        
        System.out.println("Starting simulation.");
        for(int i = 0; i < CYCLES; i++)
        {
            system.nextState();
        }
        
    }
}
