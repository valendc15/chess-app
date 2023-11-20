package org.austral.game.chess;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.PathGenerator;
import org.austral.game.commons.PathResult;
import org.austral.game.commons.Player;
import org.austral.game.commons.Validator;

public class MovementValidatorWithCollision implements Validator {
    private PathGenerator pathGenerator;


    public MovementValidatorWithCollision(PathGenerator pathGenerator){
        this.pathGenerator = pathGenerator;
    }

    public boolean validate(Movement movement, Board board, Player player){
        PathResult pathResult= pathGenerator.generatePath(movement);
        if(pathExistsAndIsValid(movement, board)){
            return board.checkIfPathIsEmpty(pathResult.getPath());
        }
        else
            return false;
    }

    private boolean pathExistsAndIsValid(Movement movement, Board board){
        PathResult pathResult= pathGenerator.generatePath(movement);
        return pathResult.pathExists() && board.isValidPath(pathResult.getPath());
    }

}
