package org.austral.game.checkers2;

import org.austral.game.commons.*;


public class CheckersEatingValidator implements Validator {

    @Override
    public boolean validate(Movement movement, Board board, Player player) {
        if (diagonalAndPositionOccupied(movement, board)) {
            return areColorsDifferent(movement, board);
        }
        return false;
    }

    private boolean diagonalAndPositionOccupied(Movement movement, Board board){
        return isDiagonalByTwoOpValid(movement, board) && isMiddlePositionOccupied(movement, board);
    }

    private boolean isDiagonalByTwoOpValid(Movement movement, Board board) {
        GetResult<Position,Boolean> middlePositionResult = movement.getMiddlePositionIfDiagonalByTwoOp();
        return !middlePositionResult.getErrorValue();
    }

    private boolean isMiddlePositionOccupied(Movement movement, Board board) {
        GetResult<Position, Boolean> middlePositionResult = movement.getMiddlePositionIfDiagonalByTwoOp();
        if (middlePositionResult.getSuccesValue().isPresent()) {
            Position middlePosition = middlePositionResult.getSuccesValue().get();
            GetResult<Piece, Boolean> middlePieceResult = board.getPiece(middlePosition);
            return !middlePieceResult.getErrorValue();
        }
        return false;
    }

    private boolean areColorsDifferent(Movement movement, Board board) {
        GetResult<Piece, Boolean> possibleEnemyPieceResult = board.getPiece(movement.getMiddlePositionIfDiagonalByTwoOp().getSuccesValue().get());
        GetResult<Piece, Boolean> myPieceResult = board.getPiece(movement.getFrom());

        if (piecesArePresent(possibleEnemyPieceResult, myPieceResult)) {
            Piece possibleEnemyPiece = possibleEnemyPieceResult.getSuccesValue().get();
            Piece myPiece = myPieceResult.getSuccesValue().get();
            return possibleEnemyPiece.getColor() != myPiece.getColor();
        }
        return false;
    }

    private boolean piecesArePresent(GetResult<Piece, Boolean> possibleEnemyPieceResult, GetResult<Piece, Boolean> myPieceResult){
        return possibleEnemyPieceResult.getSuccesValue().isPresent() && myPieceResult.getSuccesValue().isPresent();
    }

}
