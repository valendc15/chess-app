
package org.austral.game.commons;

import java.util.ArrayList;
import java.util.List;

    public class MovementHistory {

        private List<Board> previousBoards;

        private List<Movement> previousMovements;

        public MovementHistory(List<Board> previousBoards, List<Movement> previousMovements) {
            this.previousBoards = previousBoards;
            this.previousMovements = previousMovements;
        }

        public List<Board> getPreviousBoards() {
            return previousBoards;
        }

        public List<Movement> getPreviousMovements() {
            return previousMovements;
        }

        public MovementHistory addMovement(Board board, Movement movement){
            List<Board> newPreviousBoards = new ArrayList<>(previousBoards);
            List<Movement> newPreviousMovements = new ArrayList<>(previousMovements);

            newPreviousBoards.add(board);
            newPreviousMovements.add(movement);

            return new MovementHistory(newPreviousBoards, newPreviousMovements);
        }

        public Board getLastBoard() {
            if (previousBoards.isEmpty())
                return null;
            else if (previousBoards.size() == 1)
                return previousBoards.get(0);
            else if (previousBoards.size() > 1) {
                return previousBoards.get(previousBoards.size() - 1);
            }
            return null;
        }
        public Movement getLastMovement(){
            if (previousMovements.isEmpty())
                return null;
            else if (previousMovements.size() == 1)
                return previousMovements.get(0);
            else if (previousMovements.size() > 1) {
                return previousMovements.get(previousMovements.size() - 1);
            }
            return null;
        }
    }