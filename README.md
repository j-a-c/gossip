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
    - structure strategies
        - single hub
        - multi hub
        - equally connected
	- tree
	- single coordinator election
	- even token distribution (multi election)
    - Python graph display
    - Protocol singleton?

graph.py
    - After graphs output has been saved, graph.py can be used to generate an
      animated GIF of the graph's changing topology

Driver.java
    - driver for the Tests

Node.java
    - a single node in the network
NodeSystem
    - the complete system of nodes
SystemTime
    - returns the current time of the system
    - this is used because the System.currentTimeMillis() does not provide
      enough granularity

(Each node has a StructureStrategy)
StructureStrategy
    - interface for strategies the nodes can use to structure themselves.
CloseStrategy
    - a strategy that attempts to connect nodes to their closest neighbors
EmptyStrategy
    - a strategy that does nothing

(One GossipProtocol is used by the NodeSystem)
GossipProtocol
    - interface for gossip protocols
PushPullProtocol
    - implementation of the push/pull protocol
