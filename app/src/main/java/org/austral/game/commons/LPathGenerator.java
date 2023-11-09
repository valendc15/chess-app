package org.austral.game.commons;

public class LPathGenerator implements PathGenerator {

    public PathResult generatePath(Movement movement) {
        int fromX = movement.getFrom().getPositionX();
        int fromY = movement.getFrom().getPositionY();
        int toX = movement.getTo().getPositionX();
        int toY = movement.getTo().getPositionY();

        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            Position[] path = new Position[2];
            path[0] = new Position(fromX, fromY);
            path[1] = new Position(toX, toY);
            return new PathResult(path);
        }

        return new PathResult(null);
    }
}
