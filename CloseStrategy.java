/**
 * Strategy where the nodes try to connect with their closest peers.
 * In this class, the absolute value of the id of two nodes is a measure of
 * distance. The node will switch out its farthest neighbor with the closest
 * neighbor from the partner node, is that node is closer.
 */
public class CloseStrategy implements StructureStrategy
{

    @Override
    public void doStrategy(Node first, Node second)
    {
        // Calculate the first node's farthest peer and the closest one to the
        // second node
        int firstFarthestDistance = Integer.MIN_VALUE;
        int firstFarthestIndex = -1;
        int secondClosestDistance = Integer.MAX_VALUE;
        int secondClosestIndex = -1;
        for (int i = 0 ; i < first.getPeerTableSize(); i++)
        {
            Node current = first.getPeerAt(i); 
            // New farthest node for first peer?
            if ( Math.abs(first.getID() - current.getID()) > firstFarthestDistance )
            {
                firstFarthestDistance = Math.abs(first.getID() - current.getID());
                firstFarthestIndex = i;
            }

            // New closest node to second peer?
            // Make sure node isn't itself and that node doesn't already know
            // the peer we are checking
            int tempDist = Math.abs(second.getID() - current.getID());
            boolean knowsPeer = second.knowsPeer(current.getID());
            if (!knowsPeer && tempDist > 0 && tempDist < secondClosestDistance)
            {
                secondClosestDistance = tempDist;
                secondClosestIndex = i;
            }
        }

        // Calculate the second node's farthest peer and the closest one to the
        // second node
        int secondFarthestDistance = Integer.MIN_VALUE;
        int secondFarthestIndex = -1;
        int firstClosestDistance = Integer.MAX_VALUE;
        int firstClosestIndex = -1;
        for (int i = 0; i < second.getPeerTableSize(); i++)
        {
            Node current = second.getPeerAt(i);

            // New farthest node for second peer?
            if ( Math.abs(second.getID() - current.getID()) > secondFarthestDistance )
            {
                secondFarthestDistance = Math.abs(second.getID() - current.getID());
                secondFarthestIndex = i;
            }

            // New closest node to first peer?
            // Make sure node isn't itself and that the node doesn't already
            // know the peer we are checking
            int tempDist = Math.abs(first.getID() - current.getID());
            boolean knowsPeer = first.knowsPeer(current.getID());
            if (!knowsPeer && tempDist > 0 && tempDist < firstClosestDistance)
            {
                firstClosestDistance = tempDist;
                firstClosestIndex = i;
            }
        }
        
        // Save the closest nodes since we will be swapping at the next step
        Node closestToFirst = second.getPeerAt(firstClosestIndex);
        Node closestToSecond = first.getPeerAt(secondClosestIndex);

        // Swap new closest node into first node
        if (firstFarthestDistance > firstClosestDistance)
            first.setPeer(firstFarthestIndex, closestToFirst);

        // Swap new closest node into second node
        if (secondFarthestDistance > secondClosestDistance)
            second.setPeer(secondFarthestIndex, closestToSecond);

    }
}
