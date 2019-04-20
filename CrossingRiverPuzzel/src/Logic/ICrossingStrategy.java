/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.List;

/**
 *
 * @author salma
 */
public interface ICrossingStrategy {
    public boolean isValid(List<ICrosser> rightBankCrossers,List<ICrosser> leftBankCrossers, List<ICrosser>boatRiders);

public List<ICrosser> getInitialCrossers();

public String[] getInstructions();
}
