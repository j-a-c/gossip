/**
 * @author Joshua A. Campbell
 * 
 * Driver for the self-structuring gossip tests.
 */
public class Driver
{

    // Number of states to simulate
    private static int CYCLES = 10;

    public static void main(String[] args)
    {
        System.out.println("Creating system.");

        NodeSystem system = new NodeSystem();

        system.initialize();

        // Set the gossip protocol
        // TODO make a parameter
        system.setGossipProtocol(new PushPullProtocol());

        // Infect a node
        // TODO make a parameter
        system.infect(new EmptyStrategy());
        
        for(int i = 0; i < CYCLES; i++)
        {
            system.nextState();
        }
        
    }
}
