package org.austral.game.commons;

import org.austral.game.commons.Board;
import org.austral.game.commons.Movement;
import org.austral.game.commons.PathGenerator;
import org.austral.game.commons.PathResult;
import org.austral.game.commons.Player;
import org.austral.game.commons.Validator;

public class MovementValidatorWithoutCollision implements Validator {

        private PathGenerator pathGenerator;

        public MovementValidatorWithoutCollision(PathGenerator pathGenerator) {
            this.pathGenerator = pathGenerator;
        }

        public boolean validate(Movement movement, Board board, Player player) {
            PathResult pathResult = pathGenerator.generatePath(movement);
            return pathResult.pathExists() && board.isValidPath(pathResult.getPath());
        }
}
