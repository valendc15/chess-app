import org.austral.game.commons.Player;

public class FinishedGame {

    private Player winner;

    public FinishedGame(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }
}
