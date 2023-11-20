package org.austral.game.checkers2;

import org.austral.game.checkers.CheckersCanEatCheck;
import org.austral.game.commons.*;

public class CanEatAgainValidator implements Validator {

    Validator movementRules;

    public CanEatAgainValidator(Validator movementRules){
        this.movementRules = movementRules;
    }

    public boolean validate(Movement movement, Board board, Player player){
        LastMovementWasEatValidator lastMovementWasEatValidator = new LastMovementWasEatValidator();

        if (lastMovementWasEatValidator.validate(movement, board, player)) {
            return checkForPossibleMove(movement, board, player);
        } else {
            return false;
        }
    }

    public boolean checkForPossibleMove(Movement movement, Board board, Player player){
        PossibleMovesCalculator possibleMovesCalculator = new PossibleMovesCalculator();
        Movement[] possibleMovements = possibleMovesCalculator.calculatePossibleMoves(board, movement.getTo());

        for (Movement possibleMovement : possibleMovements) {
            if (movementRules.validate(possibleMovement, board, player)) {
                return true;
            }
        }

        return false;
    }
}
