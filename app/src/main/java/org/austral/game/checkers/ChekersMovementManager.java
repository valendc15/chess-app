package org.austral.game.checkers;

import org.austral.game.commons.*;

public class ChekersMovementManager {

    /* if in chain and movementTo==MovementFrom || movementFromGetPiece == lastmovementToGetPiece && isValidatedByAllValidators return true
    else return false
    */

    private Validator movementRules;

    private boolean chain;

    public ChekersMovementManager(Validator validator, boolean chain) {
        this.movementRules=validator;
        this.chain=chain;
    }

    private boolean isValidMove(Player player, Board board, Movement movement) {
        return movementRules.validate(movement, board, player) && validateMoveWithPieceRules(player, board, movement);
    }

    private static boolean validateMoveWithPieceRules(Player player, Board board, Movement movement) {
        return board.getPiece(movement.getFrom()).getSuccesValue().get().isLegalMove(movement, board, player);
    }

    public Board manageMovement(Player player, Board board, Movement movement) {
        if (chain) {
            return manageChainMovement(player, board, movement);
        } else {
            return manageNonChainMovement(player, board, movement);
        }
    }

    private Board manageChainMovement(Player player, Board board, Movement movement) {
        if (validateSamePieceasBefore(movement, board) && isValidMove(player, board, movement)) {
            board = board.movePiece(movement);
            if (movement.isDiagonalMovementByTwo()) {
                board = board.removePiece(movement.getMiddlePositionIfDiagonalByTwo());
            }
        }
        return board;
    }

    private Board manageNonChainMovement(Player player, Board board, Movement movement) {
        if (isValidMove(player, board, movement)) {
            board = board.movePiece(movement);
            if (movement.isDiagonalMovementByTwo()) {
                board = board.removePiece(movement.getMiddlePositionIfDiagonalByTwo());
            }
        }
        return board;
    }


    public boolean validateSamePieceasBefore(Movement movement, Board board){
        Movement lastMovement = board.getMovementHistory().getLastMovement();
        GetResult<Piece, Boolean> possiblePiece=board.getPiece(movement.getFrom());
        GetResult<Piece, Boolean> lastPiece=board.getPiece(lastMovement.getTo());
        if (possiblePiece.getSuccesValue().isPresent() && lastPiece.getSuccesValue().isPresent()){
            return possiblePiece.getSuccesValue().get().equals(lastPiece.getSuccesValue().get());
        }
        return false;
    }


    public ChekersMovementManager withChain() {
        return new ChekersMovementManager(movementRules, true);
    }

    public Validator getMovementRules() {
        return movementRules;
    }

    public boolean isChain() {
        return chain;
    }


}
