package org.austral.game.commons;

public class DiagonalPathGenerator implements PathGenerator {

    private int diagonalLimit;
    private boolean allowForward;
    private boolean allowBackward;

    public DiagonalPathGenerator(int diagonalLimit, boolean allowForward, boolean allowBackward) {
        this.diagonalLimit = diagonalLimit;
        this.allowForward = allowForward;
        this.allowBackward = allowBackward;
    }

    public PathResult generatePath(Movement movement) {
        int fromX = movement.getFrom().getPositionX();
        int fromY = movement.getFrom().getPositionY();
        int toX = movement.getTo().getPositionX();
        int toY = movement.getTo().getPositionY();

        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if (deltaX != deltaY) {
            return new PathResult(null); // Not a valid diagonal movement
        }

        if (diagonalLimit == 0 || deltaX <= diagonalLimit) {
            boolean isForward = (toY > fromY);
            boolean isBackward = (toY < fromY);

            if ((isForward && allowForward) || (isBackward && allowBackward)) {
                return generateDiagonalPath(fromX, fromY, toX, toY);
            }
        }

        return new PathResult(null); // Exceeds diagonal limit or not allowed direction
    }

    private PathResult generateDiagonalPath(int fromX, int fromY, int toX, int toY) {
        int deltaX = (toX > fromX) ? 1 : -1;
        int deltaY = (toY > fromY) ? 1 : -1;
        int pathLength = Math.abs(toX - fromX);
        Position[] path = new Position[pathLength + 1]; // Include the "to" coordinate
        int pathIndex = 0;

        for (int i = 0; i <= pathLength; i++) {
            path[pathIndex] = new Position(fromX + i * deltaX, fromY + i * deltaY);
            pathIndex++;
        }

        return new PathResult(path);
    }
}