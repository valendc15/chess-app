package org.austral.game.checkers2;

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
            CheckersEatingValidator checkersEatingValidator = new CheckersEatingValidator();
            QueenEatingValidator queenEatingValidator = new QueenEatingValidator();
            if (checkersEatingValidator.validate(movement, board, player)) {
                board = board.removePiece(movement.getMiddlePositionIfDiagonalByTwo());
            }
            if (queenEatingValidator.validate(movement, board, player)) {
                board = board.removePiece(queenEatingValidator.getMiddlePosition(movement));
            }
            board = board.movePiece(movement);
        }
        board = PromotionManager.applyPromotion(board,CheckersMovementFactory.createQueenPieceMovements());
        return board;
    }

    private Board manageNonChainMovement(Player player, Board board, Movement movement) {
        if (isValidMove(player, board, movement)) {
            boolean checkersEatingValidator= new CheckersEatingValidator().validate(movement, board, player);
            boolean queenEatingValidator= new QueenEatingValidator().validate(movement, board, player);
            board = board.movePiece(movement);
            if (checkersEatingValidator) {
                board = board.removePiece(movement.getMiddlePositionIfDiagonalByTwo());
            }
            if (queenEatingValidator) {
                board = board.removePiece(new QueenEatingValidator().getMiddlePosition(movement));
            }
        }
        board = PromotionManager.applyPromotion(board,CheckersMovementFactory.createQueenPieceMovements());
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