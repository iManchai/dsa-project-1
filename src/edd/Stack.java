/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 * Clase Pila
 * @author manch
 */
public class Stack<T> {
    /**
     * Atributos de la clase pila.
     * top: Nodo de la cima de la pila
     * size: Tamaño de la pila
     */
    private Nodo<T> top;
    private int size;
    
    /**
     * Constructor de la clase pila.
     */
    public Stack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Getter del nodo cima.
     * @return Nodo cima
     */
    public Nodo getTop() {
        return top;
    }

    /**
     * Getter del tamaño de la pila.
     * @return Integer del tamaño de la pila
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Función primitiva de si es vacía la lista.
     * @return Boolean
     */
    public boolean isEmpty() {
        return this.top == null;
    }
    
    /**
     * Método push de la pila.
     * @param item Tipo de objeto de la pila.
     */
    public void push(T item) {
        Nodo oldTop = this.top;
        this.top = new Nodo(item);
        this.top.setNext(oldTop);
        this.size++;
    }
    
    /**
     * Método que elimina el nodo cima y obtienes su valor.
     * @return El valor del nodo cima
     */
    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        T item = this.top.getValue();
        this.top = this.top.getNext();
        this.size--;
        return item;
    }
    
    /**
     * Método obtener valor cima sin eliminar.
     * @return Valor del nodo cima.
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.top.getValue();
    }
}
