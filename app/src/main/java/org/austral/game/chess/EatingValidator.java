package org.austral.game.chess;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;
import org.austral.game.commons.Validator;

public class EatingValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player) {
        if(board.getSquares(movement.getTo()).hasPiece() && board.getPiecefromPosition(movement.getFrom()).getColor()!=board.getPiecefromPosition(movement.getTo()).getColor()){
            return true;
        }
        return false;
    }

}
