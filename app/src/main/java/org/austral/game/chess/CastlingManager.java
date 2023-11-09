package org.austral.game.chess;

import java.util.Optional;
import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.Player;
import org.austral.game.commons.Piece;
import org.austral.game.commons.HorizontalPathGenerator;

public class CastlingManager {
    public static Board applyCastling(Board board, Movement movement, Player player) {
        if (isCastling(movement, board, player)) {
            Optional<MovementValidatorWithCollision> movementValidatorOptional = Optional.of(new MovementValidatorWithCollision(new HorizontalPathGenerator(0)));
            if (movementValidatorOptional.isPresent() && !movementValidatorOptional.get().validate(movement, board, player)) {
                return board;
            }

            Optional<Piece> kingOptional= board.getPiece(movement.getFrom()).getSuccesValue();
            Optional<Piece> rookOptional= board.getPiece(movement.getTo()).getSuccesValue();

            if (kingOptional.isPresent() && rookOptional.isPresent()) {
                Piece king = kingOptional.get();
                Piece rook = rookOptional.get();

                Board castlingResult = board
                        .removePiece(movement.getFrom())
                        .removePiece(movement.getTo())
                        .placePiece(king, movement.getTo())
                        .placePiece(rook, movement.getFrom());

                if (!board.isCheck(king.getColor())) {
                    return castlingResult;
                }
            }
        }
        return board;
    }

    public static boolean isCastling(Movement movement, Board board, Player player) {
        Optional<Piece> fromPieceOptional = board.getPiece(movement.getFrom()).getSuccesValue();
        Optional<Piece> toPieceOptional = board.getPiece(movement.getTo()).getSuccesValue();

        if (fromPieceOptional.isPresent() && toPieceOptional.isPresent()) {
            Piece fromPiece = fromPieceOptional.get();
            Piece toPiece = toPieceOptional.get();

            if (fromPiece.getPieceName().equals("king") && toPiece.getPieceName().equals("rook")
                    && fromPiece.isFirstMove() && toPiece.isFirstMove()
                    && fromPiece.getColor() == player.getColor() && toPiece.getColor() == player.getColor()) {
                return true;
            }

            if (fromPiece.getPieceName().equals("rook") && toPiece.getPieceName().equals("king")
                    && fromPiece.isFirstMove() && toPiece.isFirstMove()
                    && fromPiece.getColor() == player.getColor() && toPiece.getColor() == player.getColor()) {
                return true;
            }
        }
        return false;
    }
}
