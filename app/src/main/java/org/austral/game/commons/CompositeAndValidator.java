package org.austral.game.commons;

import org.austral.game.commons.Board;

public class CompositeAndValidator implements Validator {

    Validator[] validators;

    public CompositeAndValidator(Validator... validators){
        this.validators = validators;
    }
    public boolean validate(Movement movement, Board board, Player player) {
        for (Validator validator : validators) {
            if(!validator.validate(movement, board, player)){
                return false;
            }
        }
        return true;
    }
}
