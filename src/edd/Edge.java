/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 *
 * @author manch
 */
public class Edge {
    private Vertex startAt;
    private Vertex endAt;
    
    public Edge(Vertex startAt, Vertex endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Vertex getStartAt() {
        return startAt;
    }

    public Vertex getEndAt() {
        return endAt;
    }
    
    
}
