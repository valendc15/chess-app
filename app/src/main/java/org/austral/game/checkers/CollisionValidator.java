package org.austral.game.checkers;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;
import org.austral.game.commons.Validator;

public class CollisionValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player) {
        if(board.getSquares(movement.getTo()).hasPiece()){
            return false;
        }
        return true;
    }
}
