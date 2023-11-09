package org.austral.game.commons;

import org.austral.game.commons.Board;

public class HasPieceValidator implements Validator {
    public boolean validate(Movement movement, Board board, Player player) {
        return board.getSquares(movement.getFrom()).hasPiece();
    }
}
