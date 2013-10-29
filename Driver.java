/**
 * @author Joshua A. Campbell
 * 
 * Driver for the self-structuring gossip tests.
 */
public class Driver
{

    public static void main(String[] args)
    {
        System.out.println("Creating system.");

        NodeSystem system = new NodeSystem();

        system.initialize();

        // TODO
        system.infect();
        
    }
}
