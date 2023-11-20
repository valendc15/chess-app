package org.austral.game.commons;

public class LPathGenerator implements PathGenerator {

    private final int xIncrement1;
    private final int yIncrement1;
    private final int xIncrement2;
    private final int yIncrement2;

    public LPathGenerator(int xIncrement1, int yIncrement1, int xIncrement2, int yIncrement2) {
        this.xIncrement1 = xIncrement1;
        this.yIncrement1 = yIncrement1;
        this.xIncrement2 = xIncrement2;
        this.yIncrement2 = yIncrement2;
    }

    public PathResult generatePath(Movement movement) {
        int fromX = movement.getFrom().getPositionX();
        int fromY = movement.getFrom().getPositionY();
        int toX = movement.getTo().getPositionX();
        int toY = movement.getTo().getPositionY();

        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if (isLPath(deltaX, deltaY)) {
            Position[] path = createLPath(fromX, fromY, toX, toY);
            return new PathResult(path);
        }

        return new PathResult(null);
    }

    private boolean isLPath(int deltaX, int deltaY) {
        return (deltaX == xIncrement1 && deltaY == yIncrement1) ||
                (deltaX == xIncrement2 && deltaY == yIncrement2);
    }

    private Position[] createLPath(int fromX, int fromY, int toX, int toY) {
        Position[] path = new Position[2];
        path[0] = new Position(fromX, fromY);
        path[1] = new Position(toX, toY);
        return path;
    }
}
