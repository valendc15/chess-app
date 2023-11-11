package org.austral.game.checkers2;

import org.austral.game.commons.*;

import java.util.List;

public class ForcedToEatValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player){
        List< Piece> pieces = board.getPieces(player.getColor());
        CannotEatValidator cannotEatValidator = new CannotEatValidator();
        for(Piece piece : pieces){
            GetResult<Position, Boolean> position = board.findPiece(piece);
            if(position.getSuccesValue().isPresent()){
                if(!cannotEatValidator.validate(new Movement(position.getSuccesValue().get(), position.getSuccesValue().get()), board, player)){
                    return false;
                }
            }
        }
        return true;
    }
}
