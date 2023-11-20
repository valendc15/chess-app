package org.austral.game.chess;

import org.austral.game.commons.*;

import java.util.Optional;

public class CastlingManager {

    public static Board applyCastling(Board board, Movement movement, Player player) {
        if (isCastling(movement, board, player)) {
            if (!validateCastling(movement, board, player)) {
                return board;
            }

            Board castlingResult = performCastling(board, movement);
            if (!CheckValidator.isCheck(castlingResult, player.getColor())) {
                return castlingResult;
            }
        }
        return board;
    }

    private static boolean validateCastling(Movement movement, Board board, Player player) {
        MovementValidatorWithCollision movementValidator = new MovementValidatorWithCollision(new HorizontalPathGenerator(0));
        return movementValidator.validate(movement, board, player);
    }

    private static Board performCastling(Board board, Movement movement) {
        Optional<Piece> kingOptional = board.getPiece(movement.getFrom()).getSuccesValue();
        Optional<Piece> rookOptional = board.getPiece(movement.getTo()).getSuccesValue();

        if (kingAndRookArePresent(kingOptional, rookOptional)) {

            int direction = (movement.getFrom().getPositionX() < movement.getTo().getPositionX()) ? 1 : -1;

            Position kingNewPos = new Position(movement.getFrom().getPositionX() + 2 * direction, movement.getFrom().getPositionY());
            Position rookNewPos = new Position(movement.getFrom().getPositionX() + direction, movement.getFrom().getPositionY());

            Board castlingResult = board.movePiece(new Movement(movement.getFrom(), kingNewPos));
            castlingResult = castlingResult.movePiece(new Movement(movement.getTo(), rookNewPos));

            return castlingResult;
        }
        return board;
    }

    private static boolean kingAndRookArePresent(Optional<Piece> kingOptional, Optional<Piece> rookOptional) {
        return kingOptional.isPresent() && rookOptional.isPresent();
    }


    public static boolean isCastling(Movement movement, Board board, Player player) {
        Optional<Piece> fromPieceOptional = board.getPiece(movement.getFrom()).getSuccesValue();
        Optional<Piece> toPieceOptional = board.getPiece(movement.getTo()).getSuccesValue();

        if (kingAndRookArePresent(fromPieceOptional, toPieceOptional)) {
            Piece fromPiece = fromPieceOptional.get();
            Piece toPiece = toPieceOptional.get();

            return (castlingConditions(fromPiece, toPiece, player));
        }
        return false;
    }

    private static boolean castlingConditions(Piece fromPiece, Piece toPiece, Player player) {
        return (fromPiece.getPieceName().equals("king") && toPiece.getPieceName().equals("rook"))
                && fromPiece.isFirstMove() && toPiece.isFirstMove()
                && fromPiece.getColor() == player.getColor() && toPiece.getColor() == player.getColor();
    }
}
