import java.util.List;

/**
 * @author Joshua A. Campbell
 *
 * Interface for gossip protocols to be used by the NodeSystem
 */
public interface GossipProtocol
{
    /**
     * The gossip protocol to be implemented on this list of nodes.
     * Accepts a list of nodes and simulates the protocol on the nodes.
     * The list of nodes should not be manipulated or destroyed.
     */
    public void doProtocol(List<Node> nodes);
}
