package org.austral.game.commons;

import org.austral.game.commons.Board;

public class CompositeOrValidator implements Validator {

    Validator[] validators;

    public CompositeOrValidator(Validator... validators){
        this.validators = validators;
    }
    public boolean validate(Movement movement, Board board, Player player) {
        for (Validator validator : validators) {
            if(validator.validate(movement, board, player)){
                return true;
            }
        }
        return false;
    }
}
