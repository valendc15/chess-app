package org.austral.game.commons;

import java.util.Optional;

public class Square {

    private Position position;

    private Piece piece;

    public Square(Position position, Piece piece){
        this.position=position;
        this.piece=piece;
    }

    public Position getPosition() {
        return position;
    }

    public GetResult<Piece, Boolean> getPiece() {
        if (piece != null) {
            return new GetResult<>(Optional.of(piece), false);
        }
        return new GetResult<>(Optional.empty(), true);
    }

    public boolean hasPiece(){
        return piece != null;
    }

    public Square setPiece(Piece piece){
        return new Square(position, piece);
    }
}
