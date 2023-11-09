package org.austral.game.commons;

import org.austral.game.commons.Board;

public class InitialMovementValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player){
        if(board.getSquares(movement.getFrom()).getPiece().getSuccesValue().get().isFirstMove()){
            return true;
        }
        return false;
    }
}
