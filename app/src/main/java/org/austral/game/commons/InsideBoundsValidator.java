package org.austral.game.commons;

import org.austral.game.commons.Board;

public class InsideBoundsValidator implements Validator {
    public boolean validate(Movement movement, Board board, Player player){
        return board.isValidPosition(movement.getFrom()) && board.isValidPosition(movement.getTo());
    }
}
