package org.austral.game.commons;

public interface Game{

    public Game move(Movement movement);

    public boolean isOver();

    public Board getBoard();

    public TurnManager getTurnManager();

    public Player getCurrentPlayer();

}
