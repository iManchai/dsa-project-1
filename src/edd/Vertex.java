/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 * Clase vertice.
 * @author manch
 */
public class Vertex {
    /**
     * Atributos de la clase vertice.
     * @field username: Nombre de usuario
     * @field visited: Saber si fue visitado por el algoritmo de Depth First Search.
     */
    private String username;
    private boolean visited;
    
    /**
     * Constructor de vertice.
     * @param username Nombre de usuario
     */
    public Vertex(String username) {
        this.username = username;
        this.visited = false;
    }

    /**
     * Getter del usuario.
     * @return Nombre de usuario
     */
    public String getElement() {
        return this.username;
    }

    /**
     * Setter del usuario.
     * @param username Nombre de usuario a setear
     */
    public void setElement(String username) {
        this.username = username;
    }

    /**
     * Getter de si fue visitado.
     * @return Booleano si es visitado o no
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Setter de si es visitado.
     * @param visited Si es verdadero o falso que fue visitado el vertice
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    

}
