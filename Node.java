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
    private int[] peerTable;

    // The current structure strategy this node is using
    private StructureStrategy strategy;
    // The last time this node was updated with a strategy
    private long updateTime;

    public Node()
    {
        myId = id++;
    }

    /**
     * Returns true if this node knows the peer with the give id.
     */
    public boolean knowsPeer(int peerID)
    {
        for (int pid :  peerTable)
            if (pid == peerID)
                return true;
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Node: ");
        builder.append(myId);
        for (int pid : peerTable)
        {
            builder.append(" ");
            builder.append(pid);
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

    public void setPeerTableSize(int size)
    {
        peerTable = new int[size]; 
    }

    public void setPeer(int index, int peerID)
    {
        if (index >= peerTable.length || index < 0)
            throw new RuntimeException("Invalid peer index!");

        peerTable[index] = peerID;
    }


    public void setStrategy(StructureStrategy strategy, long updateTime)
    {
        this.strategy = strategy;
        this.updateTime = updateTime;
    }
}
