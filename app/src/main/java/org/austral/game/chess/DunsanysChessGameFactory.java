package org.austral.game.chess;

import org.austral.game.commons.*;

import java.util.ArrayList;
import java.util.List;

public class DunsanysChessGameFactory implements GameCreator {

    public Game createGame() {
        Board board = new Board(8, 8);
        List<Piece> allPieces = new ArrayList<>();

        CompositeOrValidator whitePawnMovements = ChessMovementFactory.createWhitePawnMovements();
        CompositeOrValidator blackPawnMovements = ChessMovementFactory.createBlackPawnMovements();
        CompositeOrValidator rookMovements = ChessMovementFactory.createRookMovements();
        CompositeOrValidator knightMovements = ChessMovementFactory.createKnightMovements();
        CompositeOrValidator bishopMovements = ChessMovementFactory.createBishopMovements();
        CompositeOrValidator queenMovements = ChessMovementFactory.createQueenMovements();
        CompositeOrValidator kingMovements = ChessMovementFactory.createKingMovements();

        initializeWhitePawns(whitePawnMovements, allPieces);  // Initialize white pawns
        initializeBlackPieces(blackPawnMovements, rookMovements, knightMovements, bishopMovements, queenMovements, kingMovements, allPieces);  // Initialize black pieces

        CompositeAndValidator movementRules = createMovementRules();  // Create movement rules

        WinningRules[] winningRules = {new CheckMate(), new AllPiecesEaten(), new NoMovements()};
        return new Game(board, allPieces.toArray(new Piece[0]), movementRules, winningRules, Color.BLACK);
    }


    private void initializeWhitePawns(CompositeOrValidator whitePawnMovements, List<Piece> allPieces) {
        for (int i = 0; i < 32; i++) {
            int xPos = i % 8;
            int yPos = i / 8;
            allPieces.add(new Piece(Color.WHITE, new Position(xPos, yPos), whitePawnMovements, "pawn", i + 1));
        }
    }

    private void initializeBlackPieces(CompositeOrValidator blackPawnMovements, CompositeOrValidator rookMovements, CompositeOrValidator knightMovements, CompositeOrValidator bishopMovements, CompositeOrValidator queenMovements, CompositeOrValidator kingMovements, List<Piece> allPieces) {

        // Initialize black pawns
        for (int i = 0; i < 8; i++) {
            int id = i+33;
            allPieces.add(new Piece(Color.BLACK, new Position(i, 6), blackPawnMovements, "pawn", id));
        }
        // Initialize other black pieces
        allPieces.add(new Piece(Color.BLACK, new Position(0, 7), rookMovements, "rook", 100));
        allPieces.add(new Piece(Color.BLACK, new Position(1, 7), knightMovements, "knight", 101));
        allPieces.add(new Piece(Color.BLACK, new Position(2, 7), bishopMovements, "bishop", 102));
        allPieces.add(new Piece(Color.BLACK, new Position(3, 7), queenMovements, "queen", 103));
        allPieces.add(new Piece(Color.BLACK, new Position(4, 7), kingMovements, "king", true, 104));
        allPieces.add(new Piece(Color.BLACK, new Position(5, 7), bishopMovements, "bishop", 105));
        allPieces.add(new Piece(Color.BLACK, new Position(6, 7), knightMovements, "knight", 106));
        allPieces.add(new Piece(Color.BLACK, new Position(7, 7), rookMovements, "rook", 107));
    }

    private CompositeAndValidator createMovementRules() {
        InsideBoundsValidator insideBoundsValidator = new InsideBoundsValidator();
        HasPieceValidator hasPieceValidator = new HasPieceValidator();
        IsPlayersPieceValidator isPlayersPieceValidator = new IsPlayersPieceValidator();
        NoChekValidator noChekValidator = new NoChekValidator();

        return new CompositeAndValidator(insideBoundsValidator, hasPieceValidator, isPlayersPieceValidator, noChekValidator);
    }
}
