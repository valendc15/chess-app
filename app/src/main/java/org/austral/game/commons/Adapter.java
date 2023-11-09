package org.austral.game.commons;

import edu.austral.dissis.chess.gui.BoardSize;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.PlayerColor;


import java.util.ArrayList;
import java.util.List;

public class Adapter {

    public static BoardSize getBoardSize(Board board) {
        return new BoardSize(board.getWidth(), board.getHeight());
    }

    public static PlayerColor getCurrentTurn(TurnManager turnHandler){
        return turnHandler.getCurrentPlayer().getColor()== Color.WHITE? PlayerColor.WHITE:PlayerColor.BLACK;
    }

    public static PlayerColor convertPlayerColor(Color color) {
        return color == Color.WHITE ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    public static Movement convertMove(Move move){
        return new Movement(new Position(move.getFrom().getColumn()-1,move.getFrom().getRow()-1)
                ,new Position(move.getTo().getColumn()-1,move.getTo().getRow()-1));
    }


    public static edu.austral.dissis.chess.gui.Position convertPosition(Position position){
        return new edu.austral.dissis.chess.gui.Position(position.getPositionY()+1, position.getPositionX()+1);
    }

   public static List<ChessPiece> getAllPieces(Board board){
        Square[] squares=board.getSquares();
        List<ChessPiece> chessPieces = new ArrayList<>();
        for (int i = 0; i < squares.length; i++) {
            if (squares[i].getPiece().getSuccesValue().isPresent()){
                chessPieces.add(new ChessPiece(String.valueOf(squares[i].getPiece().getSuccesValue().get().getId()),convertPlayerColor(squares[i].getPiece().getSuccesValue().get().getColor()),Adapter.convertPosition(squares[i].getPosition()),squares[i].getPiece().getSuccesValue().get().getPieceName()));
            }
        }

        return chessPieces;
   }



}
