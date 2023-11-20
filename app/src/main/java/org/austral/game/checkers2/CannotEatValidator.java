package org.austral.game.checkers2;

import org.austral.game.commons.*;

import java.nio.file.Path;

public class CannotEatValidator implements Validator {

    @Override
    public boolean validate(Movement movement, Board board, Player player) {
        Piece playerPiece = board.getPiece(movement.getFrom()).getSuccesValue().get();
        if (pawnAndLastMovementWasEat(movement, board, player)) {
            return cannotEatFoward(movement, board, player) && cannotEatBackward(movement, board, player);
        }
        if (playerPiece.getPieceName().equals("queen")){
            return cannotEatQueen(movement, board, player);
        }
        if (playerPiece.getColor() == Color.WHITE) {
            return cannotEatFoward(movement, board, player);
        } else {
            return cannotEatBackward(movement, board, player);
        }
    }

    private boolean pawnAndLastMovementWasEat(Movement movement, Board board, Player player){
        Piece playerPiece = board.getPiece(movement.getFrom()).getSuccesValue().get();
        Validator lastMovementWasEat = new LastMovementWasEatValidator();
        return playerPiece.getPieceName().equals("pawn") && lastMovementWasEat.validate(movement, board, player);
    }

    public boolean cannotEatFoward (Movement movement, Board board, Player player) {
        Position fowardLeft = new Position(movement.getFrom().getPositionX() + 2, movement.getFrom().getPositionY() + 2);
        Position fowardRight = new Position(movement.getFrom().getPositionX() - 2, movement.getFrom().getPositionY() + 2);
        CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
        if(validPositionAndDoesntHavePiece(fowardRight, board)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), fowardRight), board, player)){
                return false;
            }
        }
        if(validPositionAndDoesntHavePiece(fowardLeft, board)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), fowardLeft), board, player)){
                return false;
            }
        }
        return true;
    }

    private boolean validPositionAndDoesntHavePiece(Position position, Board board){
        return board.isValidPosition(position) && !board.hasPiece(position);
    }

    public boolean cannotEatBackward(Movement movement, Board board, Player player) {
        Position backwardLeft = new Position(movement.getFrom().getPositionX() + 2, movement.getFrom().getPositionY() - 2);
        Position backwardRight = new Position(movement.getFrom().getPositionX() - 2, movement.getFrom().getPositionY() - 2);
        CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
        if(validPositionAndDoesntHavePiece(backwardRight, board)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), backwardRight), board, player)){
                return false;
            }
        }
        if(validPositionAndDoesntHavePiece(backwardLeft, board)){
            if(checkersEatingValidator.validate(new Movement(movement.getFrom(), backwardLeft), board, player)){
                return false;
            }
        }
        return true;
    }


    private boolean cannotEatQueen(Movement movement, Board board, Player player){
        Square[] squares = board.getSquares();
        for (Square square : squares) {
            Movement move = new Movement(movement.getFrom(), square.getPosition());
                if (new QueenEatingValidator().validate(move, board, player)) {
                    return false;
                }
            }
        return true;
        }
}