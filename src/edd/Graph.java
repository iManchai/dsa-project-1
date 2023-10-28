/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 *
 * @author manch
 */
public class Graph {
    private LinkedList<LinkedList<Vertex>> adjList;
    private LinkedList<Vertex> vertices;
    private LinkedList<String> listOfUsers;
    
    public Graph() {
        adjList = new LinkedList();
        vertices = new LinkedList();
        listOfUsers = new LinkedList();
    }

    public LinkedList<LinkedList<Vertex>> getAdjList() {
        return this.adjList;
    }

    public LinkedList<String> getListOfUsers() {
        return this.listOfUsers;
    }
    
    public void addVertex(String newUser) {
           LinkedList<Vertex> currentList = new LinkedList<>();
           Vertex newVertex = new Vertex(newUser);
            
           currentList.add(newVertex);
           this.listOfUsers.add(newVertex.getElement());
           this.adjList.add(currentList);
           this.vertices.add(newVertex);
    }
    
    public void addEdge(String srcName, String dstName) {

        LinkedList<Vertex> srcList = null;
        Vertex dstVertex = null;

        // Find source list
        for (Node<LinkedList<Vertex>> Nodelist = this.adjList.getHead(); Nodelist != null; Nodelist = Nodelist.getNext()) {
            if (Nodelist.getValue().getHead().getValue().getElement().equals(srcName)) {
                 srcList = Nodelist.getValue();
                break;
            }
        }

        // Find dest vertex
        for (Node<LinkedList<Vertex>> Nodelist = this.adjList.getHead(); Nodelist != null; Nodelist = Nodelist.getNext()) { 
            if (Nodelist.getValue().getHead().getValue().getElement().equals(dstName)) {
                dstVertex = Nodelist.getValue().getHead().getValue();
                break;
            }
        }

        // Add edge 
        if (srcList != null && dstVertex != null) {
            srcList.add(dstVertex);
        }

    }
    
    public void removeVertex(String username) {
        LinkedList<Vertex> listToRemove = null;
        
        // Find vertex to remove
        for (Node<LinkedList<Vertex>> NodeList = this.adjList.getHead(); NodeList != null; NodeList = NodeList.getNext()) {
            if (NodeList.getValue().getHead().getValue().getElement().equals(username)) {
                listToRemove = NodeList.getValue();
                break;
            }
        }
        
        // Remove the list for that vertex in the adjacency list, and remove the node for the vertices list and the users list
        if (listToRemove != null) {
            this.adjList.remove(listToRemove);
            this.listOfUsers.remove(username);
            for (Node<Vertex> nodeVertex = this.vertices.getHead(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
                if (nodeVertex.getValue().getElement().equals(username)) {
                    this.vertices.remove(nodeVertex.getValue());
                }
            }
        }
        
        
        // Remove the vertex for the other lists in the adjacency list
        for (Node<LinkedList<Vertex>> nodeList = this.adjList.getHead(); nodeList != null; nodeList = nodeList.getNext()) {
            LinkedList<Vertex> list = nodeList.getValue();
            for (Node<Vertex> nodeVertex = list.getHead(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
                Vertex v = nodeVertex.getValue();
                if (v.getElement().equals(username)) {
                    list.remove(v);
                    break;
                }
            }
        }
    }
    
    public void removeEdge(String src, String dst) {
        
        LinkedList<Vertex> srcList = null;
        for (Node<LinkedList<Vertex>> nodeList = this.adjList.getHead(); nodeList != null; nodeList = nodeList.getNext()) {
            if (nodeList.getValue().getHead().getValue().getElement().equals(src)) {
                srcList = nodeList.getValue();
                break;
            }
        }
        
        Vertex dstVertex = null;
        if (srcList != null) {
            for (Node<Vertex> nodeVertex = srcList.getHead(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
                if (nodeVertex.getValue().getElement().equals(dst)) {
                    dstVertex = nodeVertex.getValue();
                    break;
                }
            }
        }
        
        if (srcList != null && dstVertex != null) {
            srcList.remove(dstVertex);
        }
    }
    
    public LinkedList<Vertex> getAdjListFromVertex(Vertex v) {
        for (Node<LinkedList<Vertex>> nodeList = this.adjList.getHead(); nodeList != null; nodeList = nodeList.getNext()) {
            LinkedList<Vertex> innerList = nodeList.getValue();
            if (innerList.getHead().getValue().equals(v)) {
                return innerList;
            }
        }
        return null;
    }
    
    public void dfs(Vertex v, Stack stackForDFS) {
        //Mark vertex as visited
        v.setVisited(true);
        
        LinkedList<Vertex> adj = this.getAdjListFromVertex(v);
        for (Node<Vertex> nodeVertex = adj.getHead(); nodeVertex != null; nodeVertex.getNext()) {
            Vertex vertex = nodeVertex.getValue();
            if (!vertex.isVisited()) {
                dfs(vertex, stackForDFS);
            }
        }
        
        stackForDFS.push(v);
        
    }
    
    public Graph copyGraph() {
        Graph copy = new Graph();


        // Copy the vertex that already exists
        for (Node<Vertex> nodeVertex = this.vertices.getHead(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
            copy.addVertex(nodeVertex.getValue().getElement());
        }
        
        //Copy edges
        for (Node<LinkedList<Vertex>> nodeList = this.adjList.getHead(); nodeList != null; nodeList = nodeList.getNext()) {
            String src = nodeList.getValue().getHead().getValue().getElement();
            
            for (Node<Vertex> nodeVertex = nodeList.getValue().getHead().getNext(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
                String dst = nodeVertex.getValue().getElement();
                copy.addEdge(src, dst);
            }
        } 
        return copy;
    }
    
    public Graph reversed() {
        Graph copyGraph = this.copyGraph();
        
        for (Node<LinkedList<Vertex>> nodeList = copyGraph.adjList.getHead(); nodeList != null; nodeList = nodeList.getNext()) {
            LinkedList<Vertex> list = nodeList.getValue();
            Vertex src = list.getHead().getValue();
            
            for (Node<Vertex> nodeVertex = list.getHead().getNext(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
                Vertex dst = nodeVertex.getValue();
                copyGraph.removeEdge(src.getElement(), dst.getElement());
                copyGraph.addEdge(dst.getElement(), src.getElement());
            }
        }
        
        return copyGraph;
    }
    
    public void dfsSCC(Vertex v, LinkedList listSCC) {
        v.setVisited(true);
        listSCC.add(v);
        
        LinkedList<Vertex> adj = this.getAdjListFromVertex(v);
        for (Node<Vertex> nodeVertex = adj.getHead(); nodeVertex != null; nodeVertex.getNext()) {
            Vertex vertex = nodeVertex.getValue();
            if (!vertex.isVisited()) {
                dfsSCC(vertex, listSCC);
            }
        }

    }
    
    public LinkedList<LinkedList<Vertex>> kosarajuSCC() {
        Stack<Vertex> stack = new Stack();
        
        //Mark unvisited all the vertices
        for (Node<Vertex> nodeVertex = this.vertices.getHead(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
            nodeVertex.getValue().setVisited(false);
        }
        
        // Initial DFS
        for (Node<Vertex> nodeVertex = this.vertices.getHead(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
            if (!nodeVertex.getValue().isVisited()) {
                dfs(nodeVertex.getValue(), stack);
            }
        }
        
        // Reverse the graph
        Graph reversedGraph = this.reversed();
        
        // Mark unvisited all vertices again
        for (Node<Vertex> nodeVertex = this.vertices.getHead(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
            nodeVertex.getValue().setVisited(false);
        }
        
        //List of the SCC
        LinkedList<LinkedList<Vertex>> sccList = new LinkedList();
        
        // Do the second DFS on the reverse graph from vertices on the stack.
        while (!stack.isEmpty()) {
            Vertex v = stack.pop();
            if (!v.isVisited()) {
                LinkedList<Vertex> scc = new LinkedList();
                reversedGraph.dfsSCC(v, scc);
                sccList.add(scc);
                
            }
        }
        
        return sccList;
        
    }
    
    
}
