package org.austral.game.checkers;

import org.austral.game.commons.Validator;
import org.austral.game.commons.Board;
import org.austral.game.commons.*;

public class CheckersEatingChainMovementValidator implements Validator{

    PathGenerator pathGenerator;

    public CheckersEatingChainMovementValidator(DiagonalPathGenerator diagonalPathGenerator){
        this.pathGenerator = diagonalPathGenerator;
    }
    public boolean validate(Movement movement, Board board, Player player){
        PathResult pathResult= pathGenerator.generatePath(movement);
        if(pathResult.pathExists() && board.isValidPath(pathResult.getPath()) && HasEatenBeforeCheck.validate(movement, board, pathGenerator)){
            return true;
        }
        else
            return false;
    }
}
