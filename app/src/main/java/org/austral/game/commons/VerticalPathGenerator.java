package org.austral.game.commons;

import org.austral.game.commons.Movement;
import org.austral.game.commons.PathGenerator;
import org.austral.game.commons.PathResult;
import org.austral.game.commons.Position;

public class VerticalPathGenerator implements PathGenerator {

    int verticalLimit;
    boolean allowForward;
    boolean allowBackward;

    public VerticalPathGenerator(int verticalLimit, boolean allowForward, boolean allowBackward) {
        this.verticalLimit = verticalLimit;
        this.allowForward = allowForward;
        this.allowBackward = allowBackward;
    }

    public PathResult generatePath(Movement movement) {
        int fromX = movement.getFrom().getPositionX();
        int fromY = movement.getFrom().getPositionY();
        int toX = movement.getTo().getPositionX();
        int toY = movement.getTo().getPositionY();

        if (movement.isVerticalMovement()) {
            int pathLength = Math.abs(toY - fromY);

            if (verticalLimit == 0 || pathLength <= verticalLimit) {
                if (toY > fromY && allowForward) {
                    Position[] path = generateVerticalPath(fromX, fromY, toY);
                    return new PathResult(path);
                } else if (toY < fromY && allowBackward) {
                    Position[] path = generateVerticalPath(fromX, fromY, toY);
                    return new PathResult(path);
                }
            }
        }

        return new PathResult(null);
    }

    private Position[] generateVerticalPath(int x, int fromY, int toY) {
        int pathLength = Math.abs(toY - fromY);
        Position[] path = new Position[pathLength + 1];
        int pathIndex = 0;
        int direction = (toY > fromY) ? 1 : -1;

        for (int i = fromY; i != toY + direction; i += direction) {
            path[pathIndex] = new Position(x, i);
            pathIndex++;
        }

        return path;
    }
}
