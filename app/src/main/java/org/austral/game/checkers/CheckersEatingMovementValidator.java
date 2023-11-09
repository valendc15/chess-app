package org.austral.game.checkers;

import org.austral.game.commons.*;

public class CheckersEatingMovementValidator implements Validator {
    PathGenerator pathGenerator;

    public CheckersEatingMovementValidator(DiagonalPathGenerator diagonalPathGenerator) {
        this.pathGenerator = diagonalPathGenerator;
    }

    public boolean validate(Movement movement, Board board, Player player){
        PathResult pathResult= pathGenerator.generatePath(movement);
        if(pathResult.pathExists() && board.isValidPath(pathResult.getPath())  && !HasEatenBeforeCheck.validate(movement, board, pathGenerator) && CheckersCanEatCheck.checkForEating(movement, board, pathResult)){
            return true;
        }
        else
            return false;
    }
}
