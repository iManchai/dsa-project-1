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
    private LinkedList<String> listOfUsers;
    
    public Graph() {
        adjList = new LinkedList();
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
        
        // Remove the list for that vertex in the adjacency list
        if (listToRemove != null) {
            this.adjList.remove(listToRemove);
            this.listOfUsers.remove(username);
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
    
    
}
