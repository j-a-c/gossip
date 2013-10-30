/**
 * @author Joshua A. Campbell
 *
 * Interface for self-structuring strategies. 
 * Uses strategy method so that Nodes can acquire different strategies.
 */
public interface StructureStrategy
{

    public void doStrategy(Node first, Node second);

}
