package org.austral.game.chess;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;
import org.austral.game.commons.Validator;

public class NoChekValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player) {
        Board board1=board;
        board1=board1.movePiece(movement);
        if(CheckValidator.isCheck(board1,player.getColor())){
            return false;
        }
        return true;
    }

}
