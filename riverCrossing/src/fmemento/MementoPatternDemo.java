/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmemento;

import Logic.*;
import java.util.ArrayList;

/**
 *
 * @author Salma
 */
public class MementoPatternDemo {
   public static void main(String[] args) {
   
      Originator originator = new Originator();
      CareTaker careTaker = new CareTaker();
      ArrayList<ICrosser> l1 = new ArrayList<>();
      ArrayList<ICrosser> l2 = new ArrayList<>();
      Fox f = null;
      Farmer farmer = null;
      Carrots c = null;
      Cabbage cab = null;
      l1.add(f);
      l1.add(farmer);
      l2.add(c);
      l2.add(cab);
      Game g = new Game(5,false,l2,l1);
      originator.setState(g);
      careTaker.add(originator.saveStateToMemento());    
      System.out.print("Current State: " + originator.getState().position);		
   }
}
