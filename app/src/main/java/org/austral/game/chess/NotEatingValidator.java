package org.austral.game.chess;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;
import org.austral.game.commons.Validator;

public class NotEatingValidator implements Validator {
    public boolean validate(Movement movement, Board board, Player player) {
        return !board.getSquares(movement.getTo()).hasPiece();
    }


}
