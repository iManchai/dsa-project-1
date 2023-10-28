/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 *
 * @author manch
 */
public class Vertex {
    private String username;
    private boolean visited;
    
    public Vertex(String username) {
        this.username = username;
        this.visited = false;
    }

    public String getElement() {
        return this.username;
    }

    public void setElement(String username) {
        this.username = username;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    

}
