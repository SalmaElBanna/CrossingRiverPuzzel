/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Crossers;

/**
 *
 * @author salma
 */
public class Child extends Person{
    private Mother mother;

    public Child(double weight) {
        super(weight);
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }
    
}
