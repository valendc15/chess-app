package org.austral.game.commons;

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

        if (!movement.isVerticalMovement()) {
            return new PathResult(null);
        }

        int pathLength = Math.abs(toY - fromY);

        if (withinLimitAndAllowedDirection(pathLength, toY, fromY)) {
            Position[] path = generateVerticalPath(fromX, fromY, toY);
            return new PathResult(path);
        }

        return new PathResult(null);
    }

    private boolean withinLimitAndAllowedDirection(int pathlenght, int toY, int fromY) {
        return isWithinVerticalLimit(pathlenght) && isAllowedDirection(toY, fromY);
    }

    private boolean isWithinVerticalLimit(int pathLength) {
        return verticalLimit == 0 || pathLength <= verticalLimit;
    }

    private boolean isAllowedDirection(int toY, int fromY) {
        return (toY > fromY && allowForward) || (toY < fromY && allowBackward);
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
