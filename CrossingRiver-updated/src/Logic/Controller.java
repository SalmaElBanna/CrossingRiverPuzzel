/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Logic.Commands.Command;

/**
 *
 * @author salma
 */
public class Controller {
    
    private Command command;
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void performCommand() {
		command.execute();
	}
}
