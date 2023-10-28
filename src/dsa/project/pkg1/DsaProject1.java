/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dsa.project.pkg1;
import edd.*;

/**
 *
 * @author manch
 */
public class DsaProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph grafo = new Graph();
        
        grafo.addVertex("Elias");
        grafo.addVertex("Jose");
        grafo.addVertex("Gustavo");
        grafo.addVertex("Juan");
        
        grafo.addEdge("Elias", "Jose");
        grafo.addEdge("Elias", "Gustavo");
        grafo.addEdge("Gustavo", "Juan");
        
        System.out.println(grafo.getAdjList().getHead().getValue());
        System.out.println(grafo.getAdjListFromVertex(grafo.getAdjList().getHead().getValue().getHead().getValue()));
        
        Graph copiaGrafo = grafo.copyGraph();
        grafo.getAdjList().print();
        copiaGrafo.getAdjList().print();
    }
    
}
