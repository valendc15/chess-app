package org.austral.game.commons;

public class PathResult {

    private Position[] path;

    public PathResult(Position[] path) {
        this.path = path;
    }

    //Cambiar a un check si el path es vacio, no devolver null
    public Position[] getPath() {
        if (path == null)
            throw new IllegalStateException("Path does not exist: You should call pathExists before calling getPath");
        return path;
    }

    public boolean pathExists() {
        return path != null;
    }
}
