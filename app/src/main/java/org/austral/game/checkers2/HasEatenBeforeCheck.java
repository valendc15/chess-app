package org.austral.game.checkers2;

import org.austral.game.checkers.CheckersCanEatCheck;
import org.austral.game.commons.*;

public class HasEatenBeforeCheck implements Validator {
    public boolean validate(Movement movement, Board board, Player player) {
        MovementHistory movementHistory = board.getMovementHistory();

        if (hasPreviousMovements(movementHistory) && isLastMovementEating(movement, movementHistory, player)) {
            return true;
        }

        return false;
    }

    private boolean hasPreviousMovements(MovementHistory movementHistory) {
        return !movementHistory.getPreviousMovements().isEmpty();
    }

    private boolean isLastMovementEating(Movement movement, MovementHistory movementHistory, Player player) {
        Movement lastMovement = movementHistory.getLastMovement();
        Board lastBoard = movementHistory.getLastBoard();
        Validator checkersEatingValidator = new CheckersEatingValidator();

        return lastMovement.getTo().equals(movement.getFrom()) &&
                checkersEatingValidator.validate(lastMovement, lastBoard, player);
    }
}

