from images2gif import writeGif
from PIL import Image
import matplotlib.pyplot as plt
import networkx as nx
import os
import re

SEPARATOR = '.'
GRAPHDATA = 'output.txt'
IMAGESIZE = (300,300)


"""
    Try to cast to an int
"""
def tryint(s):
    try:
        return int(s)
    except:
        return s

"""
    Turn a string into a list of string and number chunks.
    "z23a" -> ["z", 23, "a"]
"""
def alphanum_key(s):
    return [ tryint(c) for c in re.split('([0-9]+)', s) ]

"""
    Sort the given list in the way that humans expect.
"""
def humanSort(l):
    l.sort(key=alphanum_key)



# Create a new directed graph
graph = nx.DiGraph()

# Number to distinguish output images from different iterations
graphNumber = 0

# Edges in the graph
edges = []

# Read output file
for line in open(GRAPHDATA, 'r').read().splitlines():
    # Generate a graph
    if line == SEPARATOR:
        # Add edges to the graph
        graph.add_weighted_edges_from(edges)
        # Draw graph
        nx.draw(graph)
        # Save graph
        fileName = 'graph' + str(graphNumber) + '.png'
        plt.savefig(fileName)
        # Reset canvas
        graph.clear()
        plt.clf()
        # Update output png number
        graphNumber += 1
        # Clear the edges
        edges = []
    # Add to the collection of edges
    else:
        numbers = line.split()
        start = numbers[0]
        for end in numbers[1:]:
            # Large weight = tighter spring
            edges.append( (start,end, abs(int(start)-int(end))/45.0) )

# Find files to write to png
# Sort in human order - 1, 2, 3,..
fileNames = [fn for fn in os.listdir('.') if fn.endswith('.png')]
humanSort(fileNames)

# Open images
images = [Image.open(fn) for fn in fileNames]
for im in images:
    im.thumbnail(IMAGESIZE, Image.ANTIALIAS)

# Write output
filename = "graph_transitions.gif"
writeGif(filename, images, duration=0.2)

# Clean up temporary PNGs
for fn in fileNames:
    os.remove(fn)
