/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author salma
 */
public class Stars extends java.util.Observable  {
   private int stars;
    private int minNumOfSails;
    private int count;
    private String label;
    public Stars(String label,Receiver receiver) {
       this.label=label; 
       count=receiver.getNumOfSails();
       if( receiver.getGameStrategy().getClass().getSimpleName()=="Story1"){
           minNumOfSails=7;
       }
       if( receiver.getGameStrategy().getClass().getSimpleName()=="Story2"){
           minNumOfSails=9;
       }
           if(count==minNumOfSails){
              increaseStars();
           }
           if(count>minNumOfSails)
               decreaseStars(count,minNumOfSails);
         
       
    }
    public void increaseStars(){
     stars=3;
     setChanged();
     notifyObservers(stars);    
    }
    public void decreaseStars(int count,int minNumOfSails){
    if((count-minNumOfSails)<3)
        stars=2;
    else 
        stars=1;
    setChanged();
    notifyObservers(stars);
    } 
}
