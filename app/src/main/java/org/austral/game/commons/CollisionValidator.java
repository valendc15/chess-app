package org.austral.game.commons;

public class CollisionValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player) {
        return !board.getSquares(movement.getTo()).hasPiece();
    }
}
