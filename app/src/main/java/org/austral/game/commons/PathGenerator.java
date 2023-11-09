package org.austral.game.commons;

import org.austral.game.commons.Movement;

public interface PathGenerator {

    public PathResult generatePath(Movement movement);
}
