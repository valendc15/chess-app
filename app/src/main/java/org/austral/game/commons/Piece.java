package org.austral.game.commons;

public class Piece {

    int id;
    Color color;

    Position origin;

    Validator movementRules;

    String name;

    int moveCount;

    boolean isCheckable;

    public Piece(Color c, Position origin, Validator movementRules, String n, int id) {
        color = c;
        this.origin=origin;
        this.movementRules = movementRules;
        name = n;
        moveCount=0;
        isCheckable=false;
        this.id=id;
    }

    public Piece(Color c, Position origin, Validator movementRules, String n, boolean isCheckable, int id) {
        color = c;
        this.origin=origin;
        this.movementRules = movementRules;
        name = n;
        moveCount=0;
        this.isCheckable=isCheckable;
        this.id=id;
    }

    public Piece(Color c, Position origin, Validator movementRules, String n, int moveCount, boolean isCheckable, int id) {
        color = c;
        this.origin=origin;
        this.movementRules = movementRules;
        name = n;
        this.moveCount=moveCount;
        this.isCheckable=isCheckable;
        this.id =id;
    }

    public Position getOrigin() {
        return origin;
    }

    public Color getColor() {
        return color;
    }


    public Piece move(){
        return new Piece(color, origin, movementRules, name, moveCount+1, isCheckable, id);
    }

    public boolean isFirstMove() {
        return moveCount==1;
    }

    public String getPieceName() {
        return name;
    }

    public boolean isLegalMove(Movement movement, Board board, Player player){
        return movementRules.validate(movement, board, player);
    }

    public String toString() {
        return color.toString().charAt(0) + name.substring(0, 2);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public boolean isCheckable() {
        return isCheckable;
    }

    public int getId() {
        return id;
    }
}
