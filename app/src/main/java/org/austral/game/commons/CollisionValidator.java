package org.austral.game.commons;

public class CollisionValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player) {
        if(board.getSquares(movement.getTo()).hasPiece()){
            return false;
        }
        return true;
    }
}
