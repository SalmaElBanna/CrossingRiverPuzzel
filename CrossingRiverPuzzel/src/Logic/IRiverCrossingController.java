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
public interface IRiverCrossingController {
    public void newGame(ICrossingStrategy gameStrategy);
/**
* resets the game without changing the strategy
*/
public void resetGame();
/**
* @return the current strategy instructions if the user want to see
them
*/
public String[] getInstructions();

public List<ICrosser> getCrossersOnRightBank();

public List<ICrosser> getCrossersOnLeftBank();

public boolean isBoatOnTheLeftBank();

public int getNumberOfSails();

public boolean canMove(List<ICrosser> crossers, boolean
fromLeftToRightBank);

public void doMove(List<ICrosser> crossers, boolean
fromLeftToRightBank);

public boolean canUndo();

public boolean canRedo();
}
