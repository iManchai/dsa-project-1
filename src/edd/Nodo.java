/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd;

/**
 * Clase Nodo
 * @author manch
 */
public class Nodo<T> {
    /**
     * Atributos de la clase nodo
     * @field value: Información del nodo
     * @field next: Apuntador al nodo siguiente
     */
    private T value;
    private Nodo next;
    
    /**
     * Constructor del nodo
     * @param newValue Valor que contendrá el nodo
     */
    public Nodo(T newValue) {
        this.value = newValue;
        this.next = null;
    }

    /**
     * Getter del valor del nodo
     * @return Valor del nodo
     */
    public T getValue() {
        return value;
    }

    /**
     * Setter del valor del nodo
     * @param value Nuevo valor asignado
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Getter del nodo siguiente
     * @return Nodo
     */
    public Nodo getNext() {
        return next;
    }

    /**
     * Setter del nodo siguiente
     * @param next Nuevo nodo
     */
    public void setNext(Nodo next) {
        this.next = next;
    }
    
    
}
