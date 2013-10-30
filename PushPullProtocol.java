import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Joshua A. Campbell
 * 
 * Implementation the push-pull approach for gossiping.
 *
 * Each period, node P picks another node Q at random; P and Q send updates to
 * each other. This takes O(log N) to propagate a single update to all nodes,
 * where N = the number of nodes.
 */
class PushPullProtocol implements GossipProtocol
{
    // The source of randomness for this protocol.
    Random rand;

    public PushPullProtocol(Random rand)
    {
        this.rand = rand;
    }

    @Override
    public void doProtocol(List<Node> nodes)
    {
        List<Integer> perm = new ArrayList<Integer>(nodes.size());

        // Generate a random order to process the nodes in
        // TODO Autoboxing (int to Integer) might have a performance 
        // hit after a while...
        for(int i = 0; i < nodes.size(); i++)
            perm.add(i);
        Collections.shuffle(perm, rand);

        // Select a random neighbor from the list of known peers to swap
        // structure strategies with. This node might have been communicated
        // with by a previous node - I ignored this possibility because in real
        // life communication is not synchronous.
    
        // Auto-unboxing..
        for(Integer i : perm)
        {
            Node currentPeer = nodes.get(i);
            int tableSize = currentPeer.getPeerTableSize();
            int selectedNeighbor = rand.nextInt(tableSize);
            
            currentPeer.gossip(selectedNeighbor);
        }
    }
}
