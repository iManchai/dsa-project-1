/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 *
 * Clase grafo dirigido no pesado utilizando lista de adyacencia como representacion.
 * @author manch
 */
public class Graph {
    /**
     * Los 3 atributos principales de la clase grafo:
     * @field adjList: Lista de adyacencia, es una lista de listas.
     * @field vertices: Lista de los vertices del grafo. Usada para el deep first search.
     * @field listOfUsers: Lista de usuarios, para acceder de mánera sencilla a todos los usuarios que conforman el grafo.
     */
    private LinkedList<LinkedList<Vertex>> adjList;
    private LinkedList<Vertex> vertices;
    private LinkedList<String> listOfUsers;
    
    /**
     * Constructor del grafo
     */
    public Graph() {
        adjList = new LinkedList();
        vertices = new LinkedList();
        listOfUsers = new LinkedList();
    }

    /**
     * Getter para la lista de adyacencia
     * @return Lista enlazada con la lista enlazada de los vertices
     */
    public LinkedList<LinkedList<Vertex>> getAdjList() {
        return this.adjList;
    }

    /**
     * Getter para la lista de usuarios
     * @return Lista de strings de los nombres de usuarios.
     */
    public LinkedList<String> getListOfUsers() {
        return this.listOfUsers;
    }
    
    /**
     * Añadir nuevo vertice al grafo.
     * @param newUser String del nuevo usuario a agregar
     */
    public void addVertex(String newUser) {
           LinkedList<Vertex> currentList = new LinkedList<>();
           Vertex newVertex = new Vertex(newUser);
            
           currentList.add(newVertex);
           this.listOfUsers.add(newVertex.getElement());
           this.adjList.add(currentList);
           this.vertices.add(newVertex);
    }
    
    /**
     * Añadir conexiones entre los vertices del grafo.
     * @param srcName Nombre del usuario inicial
     * @param dstName Nombre del usuario destino
     */
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
    
    /**
     * Eliminar vertice del grafo y de sus listas de adyacencia.
     * @param username Nombre de usuario a eliminar.
     */
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
    
    /**
     * Eliminar conexion entre dos vertices del grafo.
     * @param src Nombre usuario inicial
     * @param dst Nombre usuario destino
     */
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
    
    /**
     * Obtener lista de adyacencia de cierto usuario (a partir de su vertice)
     * @param v Vertice del usuario
     * @return Lista de adyacencia de ese usuario.
     */
    public LinkedList<Vertex> getAdjListFromVertex(Vertex v) {
        for (Node<LinkedList<Vertex>> nodeList = this.adjList.getHead(); nodeList != null; nodeList = nodeList.getNext()) {
            LinkedList<Vertex> innerList = nodeList.getValue();
            if (innerList.getHead().getValue().equals(v)) {
                return innerList;
            }
        }
        return null;
    }
    
    /**
     * Algoritmo de Depth First Search
     * @param v Vertice de partida
     * @param stackForDFS Stack para el algoritmo de Kosaraju
     */
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
    
    /**
     * Crear una copia del grafo
     * @return Copia del grafo original.
     */
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
    
    /**
     * Algoritmo de revertir el grafo.
     * @return Grafo revertido
     */
    public Graph reversed() {
        Graph reversedGraph = this.copyGraph();
        
        for (Node<LinkedList<Vertex>> nodeList = reversedGraph.adjList.getHead(); nodeList != null; nodeList = nodeList.getNext()) {
            LinkedList<Vertex> list = nodeList.getValue();
            Vertex src = list.getHead().getValue();
            
            for (Node<Vertex> nodeVertex = list.getHead().getNext(); nodeVertex != null; nodeVertex = nodeVertex.getNext()) {
                Vertex dst = nodeVertex.getValue();
                reversedGraph.removeEdge(src.getElement(), dst.getElement());
                reversedGraph.addEdge(dst.getElement(), src.getElement());
            }
        }
        
        return reversedGraph;
    }
    
    /**
     * Algoritmo de Depth First Search que inserta los componentes fuertemente conectados a una lista
     * @param v Vertice inicial
     * @param listSCC  Lista vacio para los componentes fuertemente conectados
     */
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
    /**
     * Algoritmo de Kosaraju para obtener los componentes fuertemente conectados en el grafo.
     * @return Lista enlazada con los componentes fuertemente enlazados.
     */
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
