package org.austral.game.checkers;

import org.austral.game.commons.*;


public class CheckersNotEatingMovementValidator implements Validator {

    PathGenerator pathGenerator;

    public CheckersNotEatingMovementValidator(PathGenerator pathGenerator) {
        this.pathGenerator = pathGenerator;
    }

    public boolean validate(Movement movement, Board board, Player player){
        PathResult pathResult= pathGenerator.generatePath(movement);
        if(pathResult.pathExists() && board.isValidPath(pathResult.getPath()) && !HasEatenBeforeCheck.validate(movement, board, pathGenerator) && !CheckersCanEatCheck.checkForEating(movement, board, pathResult)){
            return true;
        }
        else
            return false;
    }
}
