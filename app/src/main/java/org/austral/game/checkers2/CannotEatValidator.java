package org.austral.game.checkers2;

import org.austral.game.commons.*;

public class CannotEatValidator implements Validator {

    @Override
    public boolean validate(Movement movement, Board board, Player player) {
        Piece playerPiece = board.getPiece(movement.getFrom()).getSuccesValue().get();
        Validator lastMovementWasEat = new LastMovementWasEatValidator();
        if (playerPiece.getPieceName().equals("queen") || lastMovementWasEat.validate(movement, board, player)) {
            return cannotEatFoward(movement, board, player) && cannotEatBackward(movement, board, player);
        }
        if (playerPiece.getColor() == Color.WHITE) {
            return cannotEatFoward(movement, board, player);
        } else {
            return cannotEatBackward(movement, board, player);
        }
    }

    public boolean cannotEatFoward (Movement movement, Board board, Player player) {
        Position fowardLeft = new Position(movement.getFrom().getPositionX() + 2, movement.getFrom().getPositionY() + 2);
        Position fowardRight = new Position(movement.getFrom().getPositionX() - 2, movement.getFrom().getPositionY() + 2);
        CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
        if(board.isValidPosition(fowardRight) && !board.hasPiece(fowardRight)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), fowardRight), board, player)){
                return false;
            }
        }
        if(board.isValidPosition(fowardLeft) && !board.hasPiece(fowardLeft)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), fowardLeft), board, player)){
                return false;
            }
        }
        return true;
    }

    public boolean cannotEatBackward(Movement movement, Board board, Player player) {
        Position backwardLeft = new Position(movement.getFrom().getPositionX() + 2, movement.getFrom().getPositionY() - 2);
        Position backwardRight = new Position(movement.getFrom().getPositionX() - 2, movement.getFrom().getPositionY() - 2);
        CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
        if(board.isValidPosition(backwardRight) && !board.hasPiece(backwardRight)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), backwardRight), board, player)){
                return false;
            }
        }
        if(board.isValidPosition(backwardLeft) && !board.hasPiece(backwardLeft)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), backwardLeft), board, player)){
                return false;
            }
        }
        return true;
    }
}