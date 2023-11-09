package org.austral.game.checkers2;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;
import org.austral.game.commons.Validator;

public class LastMovementWasntEatValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player){
        LastMovementWasEatValidator lastMovementWasEatValidator= new LastMovementWasEatValidator();
        return !lastMovementWasEatValidator.validate(movement, board, player);
    }
}
