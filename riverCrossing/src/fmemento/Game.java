/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmemento;

import Logic.ICrosser;
import java.util.ArrayList;

/**
 *
 * @author Salma
 */
public class Game {
    
    public int score;
    public boolean position;
    public ArrayList<ICrosser> leftbank = new ArrayList<>();
    public ArrayList<ICrosser> righttbank = new ArrayList<>();

    public Game(int score, boolean position,ArrayList<ICrosser> leftbank,ArrayList<ICrosser> rightbank) {
        this.score = score;
        this.position = position;
        this.leftbank = leftbank;
        this.righttbank = rightbank;
    }   
}
