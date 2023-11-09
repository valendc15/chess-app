package org.austral.game.checkers;

import org.austral.game.commons.Validator;
import org.austral.game.commons.Board;
import org.austral.game.commons.*;


public class NoCollisionValidator implements Validator{

    public boolean validate(Movement movement, Board board, Player player) {
        if(board.getPiece(movement.getTo()).getSuccesValue().isPresent()){
            if(board.getPiece(movement.getTo()).getSuccesValue().get().getColor().equals(player.getColor())){
                return false;
            }
            else{
                return true;
            }
        }
        return false;
    }
}
