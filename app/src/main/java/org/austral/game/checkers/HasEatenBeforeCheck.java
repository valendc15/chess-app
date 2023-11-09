package org.austral.game.checkers;

import org.austral.game.commons.*;

public class HasEatenBeforeCheck{
    public static boolean validate(Movement movement, Board board, PathGenerator pathGenerator){
        MovementHistory movementHistory=board.getMovementHistory();
        if(!movementHistory.getPreviousMovements().isEmpty()){
            Movement lastMovement=movementHistory.getLastMovement();
            Board lastBoard=board.getMovementHistory().getLastBoard();
            if(lastMovement.getTo().equals(movement.getFrom()) && CheckersCanEatCheck.checkForEating(lastMovement, lastBoard, pathGenerator.generatePath(lastMovement))){
                return true;
            }
        }
        return false;
    }
}
