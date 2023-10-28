/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 *
 * @author manch
 */
public class Stack<T> {
    private Node<T> top;
    private int size;
    
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public Node getTop() {
        return top;
    }

    public int getSize() {
        return size;
    }
    
    public boolean isEmpty() {
        return this.top == null;
    }
    
    public void push(T item) {
        Node oldTop = this.top;
        this.top = new Node(item);
        this.top.setNext(oldTop);
        this.size++;
    }
    
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        T item = this.top.getValue();
        this.top = this.top.getNext();
        this.size--;
        return item;
    }
    
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.top.getValue();
    }
}
