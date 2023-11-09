package org.austral.game.commons;

import org.austral.game.commons.Color;
import org.austral.game.commons.Movement;

public class Player {
    private Color color;
    private boolean isTurn;

    public Player(Color color) {
        this.color = color;
        isTurn = false;
    }

    public Color getColor() {
        return color;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    //To be implemented
    public Movement getMove(){
        return null;
    }


}
