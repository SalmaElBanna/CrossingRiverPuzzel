/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmemento;

/**
 *
 * @author Salma
 */
public class Memento {
    
    private Game state;

    public Memento(Game state) {
        this.state = state;
    }

    public Game getState() {
        return state;
    }
    
}