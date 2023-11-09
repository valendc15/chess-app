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
            if (squareOptional.isPresent()) {
                Optional<Piece> pieceOptional = squareOptional.get().getPiece().getSuccesValue();
                if (pieceOptional.isPresent() && pieceOptional.get().getPieceName().equals("pawn") && pieceOptional.get().getColor() == color) {
                    board = board.removePiece(new Position(i, row));
                    return board.placePiece(
                            new Piece(color, new Position(i, row),
                                    newMovements, "queen", pieceOptional.get().getId()),
                            new Position(i, row)
                    );
                }
            }
        }
        return board;
    }
}

