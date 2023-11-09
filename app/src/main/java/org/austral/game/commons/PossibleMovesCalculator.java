package org.austral.game.commons;

import java.util.ArrayList;
import java.util.List;

public class PossibleMovesCalculator {
    public Movement[] calculatePossibleMoves(Board board, Position from) {
        GetResult<Piece, Boolean> pieceResult = board.getSquares(from).getPiece();

        if (!pieceResult.getSuccesValue().isPresent()) {
            return new Movement[0];
        }

        Piece piece = pieceResult.getSuccesValue().get();
        List<Movement> possibleMovesList = new ArrayList<>();

        Square[] squares = board.getSquares();
        for (Square square : squares) {
            Movement move = new Movement(from, square.getPosition());
            if (isLegalMove(piece, move, board)) {
                possibleMovesList.add(move);
            }
        }

        return possibleMovesList.toArray(new Movement[0]);
    }

    private boolean isLegalMove(Piece piece, Movement move, Board board) {
        Player player = new Player(piece.getColor());
        return piece.isLegalMove(move, board, player);
    }
}
