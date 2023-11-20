package org.austral.game.chess;

import org.austral.game.commons.*;

public class MovementManager {

    private Validator movementRules;

    public MovementManager(Validator validator) {
        this.movementRules=validator;
    }

    public Board manageMovement(Player player, Board board, Movement movement) {
        if (CastlingManager.isCastling(movement, board, player)) {
            return CastlingManager.applyCastling(board, movement, player);
        }
        if (isValidMove(player, board, movement)) {
            board= board.movePiece(movement);
            return PromotionManager.applyPromotion(board, ChessMovementFactory.createQueenMovements());
        } else {
            return board;
        }
    }

    private boolean isValidMove(Player player, Board board, Movement movement) {
        return movementRules.validate(movement, board, player) && validateMoveWithPieceRules(player, board, movement);
    }

    private static boolean validateMoveWithPieceRules(Player player, Board board, Movement movement) {
        return board.getPiece(movement.getFrom()).getSuccesValue().get().isLegalMove(movement, board, player);
    }

    private static Board movePiece(Board board, Movement movement) {
        return board.movePiece(movement);
    }

    public Validator getMovementRules() {
        return movementRules;
    }
}
