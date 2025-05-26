package src.proj;

import java.util.*;

public class Graph<T>{
    private final boolean directed;
    private final List<T> nodes;
    private final Map<T, Integer> nodeIndexMap;
    private int[][] adjMatrix; 

    public Graph(boolean directed)
    {
        this.directed = directed;
        this.nodes = new ArrayList<>();
        this.nodeIndexMap = new HashMap<>();
        this.adjMatrix = new int[0][0];
    }

    //Adds node to graph
    public void addNode(T node)
    {
        if (nodeIndexMap.containsKey(node)) 
            return;

        nodeIndexMap.put(node, nodes.size());
        nodes.add(node); 
        resizeMatrix();
    }

    //Update size of graph as it grows
    private void resizeMatrix()
    {
        int updatedSize = nodes.size();
        int[][] newMatrix = new int[updatedSize][updatedSize];

        for(int i = 0; i < updatedSize - 1; i++)
        {
            System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, updatedSize - 1);
        }

        adjMatrix = newMatrix;
    }

    public void addEdge(T from, T to)
    {
        addEdge(from, to, 1);
    }
    
    //Adds an edge to the graph
    public void addEdge(T from, T to, int weight)
    {
        addNode(from);
        addNode(to);

        int i = nodeIndexMap.get(from);
        int j = nodeIndexMap.get(to);
        adjMatrix[i][j] = weight;

        if (!directed) {
            adjMatrix[j][i] = weight;
        }
    }

    //Deletes an existed edge to the graph
    public void removeEdge(T from, T to)
    {
        if ((!nodeIndexMap.containsKey(from)) || (!nodeIndexMap.containsKey(to)))
            return;

        int i = nodeIndexMap.get(from);
        int j = nodeIndexMap.get(to);
        adjMatrix[i][j] = 0;

         if (!directed) {
            adjMatrix[j][i] = 0;
        }
    }

    //Prints out graph matrix
    public void displayMAtrix()
    {
        System.out.print("   ");
        for (T node : nodes) {
            System.out.print(node + " ");
        }
        System.out.println();

        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.print(nodes.get(i) + ": ");
            for (int j = 0; j < adjMatrix[i].length; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }           
    }

    //Return the existence of an edge or not
    public boolean edgeExist(T from, T to)
    {
        if ((!nodeIndexMap.containsKey(from)) || (!nodeIndexMap.containsKey(to)))
            return false;

        int i = nodeIndexMap.get(from);
        int j = nodeIndexMap.get(to);

        return adjMatrix[i][j] != 0;
    
    }

    //Get the weight of an edge 
    public int edgeWeight(T from, T to)
    {
        if ((!nodeIndexMap.containsKey(from)) || (!nodeIndexMap.containsKey(to)))
             throw new IllegalArgumentException("There is no connection ");

        int i = nodeIndexMap.get(from);
        int j = nodeIndexMap.get(to);

        return adjMatrix[i][j];
    
    }

    //Count how many an edge a nodes has
    public int edgeCounter(T node)
    {
        if ((!nodeIndexMap.containsKey(node)))
             throw new IllegalArgumentException("There is no connection ");

        int nodesLength = nodes.size();
        int counter = 0;

        for(int i = 0; i<nodesLength; i++)
        {
           if(node.equals(nodes.get(i)))
                continue;
            
           if(edgeExist(node, nodes.get(i))) 
                counter += 1;
        }
        return counter;
    }

        public boolean isDirected()
    {
        return directed;
    }

    //Returns graph matrix 
    public int[][] getAdjMatrix()
    {
        return adjMatrix;
    }

    //Returns list
    public List<T> getNodes()
    {
        return nodes;
    } 

    //Returns map
    public Map<T, Integer> getNodeIndexMap()
    {
        return nodeIndexMap;
    }
}

