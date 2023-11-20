package org.austral.game.commons;

import java.util.Optional;

public class PromotionManager {
    public static Board applyPromotion(Board board, CompositeOrValidator newMovements) {
        board = applyPromotionForColor(board, Color.BLACK, 0, newMovements);
        board = applyPromotionForColor(board, Color.WHITE, board.getHeight()-1, newMovements);
        return board;
    }

    private static Board applyPromotionForColor(Board board, Color color, int row, CompositeOrValidator newMovements) {
        for (int i = 0; i < board.getWidth(); i++) {
            Optional<Square> squareOptional = Optional.ofNullable(board.getSquares(new Position(i, row)));

            if (squareOptional.isEmpty()) {
                continue;
            }

            Optional<Piece> pieceOptional = squareOptional.get().getPiece().getSuccesValue();

            if (isPresentAndIsCandidate(pieceOptional, color)) {
                board = board.removePiece(new Position(i, row));
                Piece newQueen = new Piece(color, new Position(i, row), newMovements, "queen", pieceOptional.get().getId());
                return board.placePiece(newQueen, new Position(i, row));
            }
        }
        return board;
    }

    private static boolean isPromotionCandidate(Piece piece, Color color) {
        return piece.getPieceName().equals("pawn") && piece.getColor() == color;
    }

    private static boolean isPresentAndIsCandidate(Optional<Piece> piece, Color color) {
        return piece.isPresent() && isPromotionCandidate(piece.get(), color);
    }

}

