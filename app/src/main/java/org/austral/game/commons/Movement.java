package org.austral.game.commons;

import java.util.Optional;

public class Movement {
    private Position from;
    private Position to;

    public Movement(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public int getMovementX() {
        return to.getPositionX() - from.getPositionX();
    }

    public int getMovementY() {
        return to.getPositionY() - from.getPositionY();
    }

    public boolean isVerticalMovement() {
        return getMovementX() == 0 && getMovementY() != 0;
    }

    public boolean isHorizontalMovement() {
        return getMovementX() != 0 && getMovementY() == 0;
    }

    public boolean isDiagonalMovement() {
        return Math.abs(getMovementX()) == Math.abs(getMovementY());
    }

    public boolean isDiagonalMovementByTwo() {
        return isDiagonalMovement() && Math.abs(getMovementX()) == 2;
    }

    public Position getMiddlePositionIfDiagonalByTwo() {
        if (isDiagonalMovementByTwo()) {
            int middleX = (from.getPositionX() + to.getPositionX()) / 2;
            int middleY = (from.getPositionY() + to.getPositionY()) / 2;
            return new Position(middleX, middleY);
        } else {
            return null;
        }
    }

    public GetResult<Position, Boolean> getMiddlePositionIfDiagonalByTwoOp() {
        if (isDiagonalMovementByTwo()) {
            int middleX = (from.getPositionX() + to.getPositionX()) / 2;
            int middleY = (from.getPositionY() + to.getPositionY()) / 2;
            return new GetResult<Position, Boolean>(Optional.of(new Position(middleX,middleY)), false);
        } else {
            return new GetResult<Position, Boolean>(Optional.empty(), true);
        }
    }
}

