package org.austral.game.commons;

public class HorizontalPathGenerator implements PathGenerator {

    int horizontalLimit;

    public HorizontalPathGenerator(int horizontalLimit) {
        this.horizontalLimit = horizontalLimit;
    }

    public PathResult generatePath(Movement movement) {
        int fromX = movement.getFrom().getPositionX();
        int fromY = movement.getFrom().getPositionY();
        int toX = movement.getTo().getPositionX();
        int toY = movement.getTo().getPositionY();

        if (movement.isHorizontalMovement()) {
            int pathLength = Math.abs(toX - fromX);

            if (horizontalLimit == 0 || (toX > fromX && pathLength <= horizontalLimit) || (toX < fromX && pathLength <= horizontalLimit)) {
                Position[] path = generateHorizontalPath(fromY, fromX, toX);
                return new PathResult(path);
            }
        }

        return new PathResult(null);
    }

    private Position[] generateHorizontalPath(int y, int fromX, int toX) {
        int pathLength = Math.abs(toX - fromX);
        Position[] path = new Position[pathLength + 1]; // Include the "to" coordinate
        int pathIndex = 0;
        int direction = (toX > fromX) ? 1 : -1;

        for (int i = fromX; i != toX + direction; i += direction) {
            path[pathIndex] = new Position(i, y);
            pathIndex++;
        }

        return path;
    }
}
