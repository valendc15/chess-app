package org.austral.game.checkers;

import org.austral.game.commons.*;

public class CanEatAgainValidator implements Validator {


    public boolean validate(Movement movement, Board board, Player player){
        LastMovementWasEatValidator lastMovementWasEatValidator= new LastMovementWasEatValidator();
        if (lastMovementWasEatValidator.validate(movement, board, player)) {
            if(CheckersCanEatCheck.isPossibleToEatCheck(movement, board)){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
}
