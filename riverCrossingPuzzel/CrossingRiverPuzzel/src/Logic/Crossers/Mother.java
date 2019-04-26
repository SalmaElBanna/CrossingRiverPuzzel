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
public class Mother extends Person{
   
    private Child son;
    private Child daughter;
    
    public Mother(double weight) {
        super(weight);
    }

    public void setChild(Child son,Child daughter) {
        this.son=son;
        this.daughter=daughter;
    }
    
    
}
