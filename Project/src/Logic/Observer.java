/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Observable;

/**
 *
 * @author salma
 */
public interface Observer {
    public void update(Score score,String label);
}
