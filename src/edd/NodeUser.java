/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 *
 * @author manch
 */
public class NodeUser {
    private User element;
    private NodeUser next;
    
    public NodeUser(User user) {
        this.element = user;
        this.next = null;
    }

    public User getElement() {
        return element;
    }

    public void setElement(User element) {
        this.element = element;
    }

    public NodeUser getNext() {
        return next;
    }

    public void setNext(NodeUser next) {
        this.next = next;
    }
}
