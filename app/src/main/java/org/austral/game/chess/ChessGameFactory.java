package org.austral.game.chess;

import org.austral.game.commons.*;

import java.util.ArrayList;
import java.util.List;

public class ChessGameFactory implements GameCreator{

    CompositeOrValidator whitePawnMovements = ChessMovementFactory.createWhitePawnMovements();
    CompositeOrValidator blackPawnMovements = ChessMovementFactory.createBlackPawnMovements();
    CompositeOrValidator rookMovements = ChessMovementFactory.createRookMovements();
    CompositeOrValidator knightMovements = ChessMovementFactory.createKnightMovements();
    CompositeOrValidator bishopMovements = ChessMovementFactory.createBishopMovements();
    CompositeOrValidator queenMovements = ChessMovementFactory.createQueenMovements();
    CompositeOrValidator kingMovements = ChessMovementFactory.createKingMovements();
    public Game createGame(){
        Board board = new Board(8, 8);
        List<Piece> allPieces = new ArrayList<>();
        initializeWhitePieces(allPieces);
        initializeBlackPieces(allPieces);
        CompositeAndValidator movementRules = createMovementRules();
        return new Game(board,allPieces.toArray(new Piece[0]), movementRules,new WinningRules[]{new CheckMate(),new ThreeTimesCheck()},Color.WHITE);
    }

    private CompositeAndValidator createMovementRules() {
        HasPieceValidator hasPieceValidator = new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator = new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator = new IsPlayersPieceValidator();
        NoChekValidator noChekValidator = new NoChekValidator();
        return new CompositeAndValidator(hasPieceValidator, insideBoundsValidator, isPlayersPieceValidator,noChekValidator);
    }

    private void initializeWhitePieces(List<Piece> allPieces) {
        for (int i = 0; i < 8; i++) {
            int id = i+1;
            allPieces.add(new Piece(Color.WHITE, new Position(i, 1), whitePawnMovements, "pawn", id));
        }
        allPieces.add(new Piece(Color.WHITE, new Position(0, 0), rookMovements, "rook", 9));
        allPieces.add(new  Piece(Color.WHITE, new Position(7, 0), rookMovements, "rook", 10));
        allPieces.add(new  Piece(Color.WHITE, new Position(1, 0), knightMovements, "knight", 11));
        allPieces.add(new Piece(Color.WHITE, new Position(6, 0), knightMovements, "knight", 12));
        allPieces.add(new Piece(Color.WHITE, new Position(2, 0), bishopMovements, "bishop", 13));
        allPieces.add(new Piece(Color.WHITE, new Position(5, 0), bishopMovements, "bishop", 14));
        allPieces.add(new Piece(Color.WHITE, new Position(3, 0), queenMovements, "queen", 15));
        allPieces.add(new Piece(Color.WHITE, new Position(4, 0), kingMovements, "king", true,16));
    }

    private void initializeBlackPieces(List<Piece> allPieces){
        for (int i = 0; i < 8; i++) {
            int id = i+17;
            allPieces.add(new Piece(Color.BLACK, new Position(i, 6), blackPawnMovements, "pawn", id));
        }
        allPieces.add(new Piece(Color.BLACK, new Position(0, 7), rookMovements, "rook", 25));
        allPieces.add(new Piece(Color.BLACK, new Position(7, 7), rookMovements, "rook", 26));
        allPieces.add(new Piece(Color.BLACK, new Position(1, 7), knightMovements, "knight", 27));
        allPieces.add(new Piece(Color.BLACK, new Position(6, 7), knightMovements, "knight", 28));
        allPieces.add(new Piece(Color.BLACK, new Position(2, 7), bishopMovements, "bishop", 29));
        allPieces.add(new Piece(Color.BLACK, new Position(5, 7), bishopMovements, "bishop", 30));
        allPieces.add(new Piece(Color.BLACK, new Position(3, 7), queenMovements, "queen", 31));
        allPieces.add(new Piece(Color.BLACK, new Position(4, 7), kingMovements, "king", true,32));
    }
}
