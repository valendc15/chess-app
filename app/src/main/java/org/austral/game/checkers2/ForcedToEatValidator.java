package org.austral.game.checkers2;

import org.austral.game.commons.*;

import java.util.List;

public class ForcedToEatValidator implements Validator {

    public boolean validate(Movement movement, Board board, Player player) {
        List<Piece> pieces = board.getPieces(player.getColor());
        CannotEatValidator cannotEatValidator = new CannotEatValidator();

        for (Piece piece : pieces) {
            if (!isPieceValid(board, piece)) continue;

            Movement pieceMovement = createMovementFromPiecePosition(board, piece);
            if (!cannotEatValidator.validate(pieceMovement, board, player)) {
                return false;
            }
        }

        return true;
    }

    private boolean isPieceValid(Board board, Piece piece) {
        GetResult<Position, Boolean> position = board.findPiece(piece);
        return position.getSuccesValue().isPresent();
    }

    private Movement createMovementFromPiecePosition(Board board, Piece piece) {
        GetResult<Position, Boolean> position = board.findPiece(piece);
        return new Movement(position.getSuccesValue().get(), position.getSuccesValue().get());
    }
}
