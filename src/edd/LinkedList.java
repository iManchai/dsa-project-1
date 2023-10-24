/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 *
 * @author manch
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty() {
        return this.head == null;
    }
    
    public void add(T info) {
        if (this.isEmpty()) {
            Node newNode = new Node(info);
            this.head = newNode;
            this.tail = newNode;
            this.size++;
        } else {
            Node newNode = new Node(info);
            Node currentTail = this.tail;
            currentTail.setNext(newNode);
            this.tail = newNode;
            this.size++;
        }
    }
    
      public void print() {
    Node current = head;
  
    while(current != null) {
      System.out.print(current.getValue() + " -> ");  
      current = current.getNext();
    }
    
    System.out.println("null");
  }

    
    public T get(int index) {
    if (this.isEmpty()) {
        throw new IndexOutOfBoundsException("This list is empty");
    } else {
        int auxIndex = 0;
        Node<T> auxNode = this.head;
        while (auxIndex != index) {
            if (auxNode.getNext() == null) {
                throw new IndexOutOfBoundsException("Index not found " + index);
            } else {
                auxNode = auxNode.getNext();
                auxIndex++;
            }
        }
        return auxNode.getValue();
        }
    }
    
    public void remove(Object data) {
        Node current = this.head;
        Node previous = null;
        
        while (current != null && current.getValue() != data) {
            previous = current;
            current = current.getNext();
        }
        
        if (current == null) {
            return;
        }
        
        if (previous == null) {
            head = head.getNext();
        } else {
            previous.setNext(current.getNext());
        }
        this.size--;
    } 
    
}
