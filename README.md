gossip
======

Self-Structuring Networks Using Gossiping

Tests for gossip-based dissemination for self-structuring networks

A system of nodes is created with a random amount of randomly connected nodes.
One of these nodes in the "infected" with a structure strategy. Each timestep,
a node chooses one of its known peer to exchange strategies with. The goal is
to monitor how fast strategies spread, as well as how the structure of the
network changes as random nodes are infected with new strategies over time. It
is also important to monitor the long-term effectiveness of different
strategies.

TODO:
    - Output current state
    - Dynamic Node peer table size
    - Gossip protocols
        - push/pull
    - structure strategies
        - closest
        - single hub
        - multi hub
        - equally connected
    - Python graph display

Driver.java
    - driver for the Tests

Node.java
    - a single node in the network
NodeSystem
    - the complete system of nodes

(Each node has a StructureStrategy)
StructureStrategy
    - interface for strategies the nodes can use to structure themselves.
EmptyStrategy
    - a strategy that does nothing

(One GossipProtocol is used by the NodeSystem)
GossipProtocol
    - interface for gossip protocols
PushPullProtocol
    - implementation of the push/pull protocol
