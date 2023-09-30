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
    private NodeUser startAt;
    private NodeUser endAt;
    
    public Edge(NodeUser startAt, NodeUser endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public NodeUser getStartAt() {
        return startAt;
    }

    public NodeUser getEndAt() {
        return endAt;
    }
    
    
}
