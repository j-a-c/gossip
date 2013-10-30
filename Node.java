import java.lang.StringBuilder;

/**
 * @author Joshua A. Campbell
 *
 * Represents a node in the network. A node is uniquely identified by its
 * indentifier (myId).
 */
public class Node
{

    // Current identifier to be allocated 
    private static int id = 0;

    // This node's identifier
    private int myId;
    // This node's known peers
    private Node[] peerTable;

    // The current structure strategy this node is using
    private StructureStrategy strategy;
    // The last time this node was updated with a strategy
    private long updateTime;

    public Node(StructureStrategy strategy, long enterTime)
    {
        this.strategy = strategy;
        updateTime = enterTime;
        myId = id++;
    }

    /**
     * Returns true if this node knows the peer with the give id.
     */
    public boolean knowsPeer(int peerID)
    {
        for (Node peer :  peerTable)
            if (peer != null && peer.getID() == peerID)
                return true;
        return false;
    }

    /**
     * Causes the node to gossip with the peer at the current index in the
     * peerTable.
     */
    public void gossip(int index)
    {
        Node partner = peerTable[index];

        // The first step of gossiping is sharing the most recent protocol
        if (this.updateTime >= partner.getUpdateTime())
            partner.setStrategy(this.strategy, SystemTime.getTime());
        else this.setStrategy(partner.getStrategy(), SystemTime.getTime());
        // Then do the most recent protocol
        strategy.doStrategy(this, partner);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Node: ");
        builder.append(myId);
        for (Node peer: peerTable)
        {
            builder.append(" ");
            builder.append(peer.getID());
        }

        return builder.toString();
    }

    /*
     * Getter & Setters
     */

    public int getID()
    {
        return this.myId;
    }

    public int getPeerTableSize()
    {
        return peerTable.length;
    }

    public long getUpdateTime()
    {
        return this.updateTime;
    }

    public void setPeerTableSize(int size)
    {
        peerTable = new Node[size]; 
    }

    public void setPeer(int index, Node peer)
    {
        if (index >= peerTable.length || index < 0)
            throw new RuntimeException("Invalid peer index!");

        peerTable[index] = peer;
    }

    public StructureStrategy getStrategy()
    {
        return this.strategy;
    }

    public void setStrategy(StructureStrategy strategy, long updateTime)
    {
        this.strategy = strategy;
        this.updateTime = updateTime;
    }
}
