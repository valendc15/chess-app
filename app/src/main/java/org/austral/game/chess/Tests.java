package org.austral.game.chess;

import org.austral.game.commons.*;
import org.junit.Assert;
import org.junit.Test;

public class Tests{

    private Game createGameWithSpecificBoard(Board board){
        HasPieceValidator hasPieceValidator = new HasPieceValidator();
        InsideBoundsValidator insideBoundsValidator = new InsideBoundsValidator();
        IsPlayersPieceValidator isPlayersPieceValidator = new IsPlayersPieceValidator();
        NoChekValidator noChekValidator = new NoChekValidator();
        CompositeAndValidator movementRules = new CompositeAndValidator(hasPieceValidator, insideBoundsValidator, isPlayersPieceValidator,noChekValidator);
        return new Game(board,new Piece[]{},movementRules,new WinningRules[]{new CheckMate()},Color.WHITE);
    }
    @Test
    public void testPawnMovement(){
        Board board = new Board(8,8);
        Piece blackPawn = new Piece(Color.BLACK, new Position(2, 6), ChessMovementFactory.createBlackPawnMovements(), "Pawn",1);
        Piece whitePawn = new Piece(Color.WHITE, new Position(1, 2), ChessMovementFactory.createWhitePawnMovements(), "Pawn",2);
        board = board.placePiece(blackPawn, blackPawn.getOrigin());
        board = board.placePiece(whitePawn, whitePawn.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        Assert.assertEquals("Pawn", game.getBoard().getSquares(new Position(2, 6)).getPiece().getSuccesValue().get().getPieceName());
    }

    @Test
    public void testRookMovement(){
        Board board = new Board(8,8);
        Piece blackRook = new Piece(Color.BLACK, new Position(2, 6), ChessMovementFactory.createRookMovements(), "Rook",1);
        Piece whiteRook = new Piece(Color.WHITE, new Position(1, 2), ChessMovementFactory.createRookMovements(), "Rook",2);
        board = board.placePiece(blackRook, blackRook.getOrigin());
        board = board.placePiece(whiteRook, whiteRook.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        Assert.assertEquals("Rook", game.getBoard().getSquares(new Position(2, 6)).getPiece().getSuccesValue().get().getPieceName());
    }

    @Test
    public void testKnightMovements(){
        Board board = new Board(8,8);
        Piece blackKnight = new Piece(Color.BLACK, new Position(2, 6), ChessMovementFactory.createKnightMovements(), "Knight",1);
        Piece whiteKnight = new Piece(Color.WHITE, new Position(1, 2), ChessMovementFactory.createKnightMovements(), "Knight",2);
        board = board.placePiece(blackKnight, blackKnight.getOrigin());
        board = board.placePiece(whiteKnight, whiteKnight.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        game=game.whiteMakeMove(new Movement(new Position(1,2),new Position(2,4)));
        game=game.whiteMakeMove(new Movement(new Position(2,4),new Position(4,5)));
        game=game.whiteMakeMove(new Movement(new Position(4,5),new Position(6,6)));
        Assert.assertEquals("Knight", game.getBoard().getSquares(new Position(6, 6)).getPiece().getSuccesValue().get().getPieceName());
    }

    @Test
    public void testBishopMovements(){
        Board board = new Board(8,8);
        Piece blackBishop = new Piece(Color.BLACK, new Position(2, 6), ChessMovementFactory.createBishopMovements(), "Bishop",1);
        Piece whiteBishop = new Piece(Color.WHITE, new Position(1, 2), ChessMovementFactory.createBishopMovements(), "Bishop",2);
        board = board.placePiece(blackBishop, blackBishop.getOrigin());
        board = board.placePiece(whiteBishop, whiteBishop.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        game=game.whiteMakeMove(new Movement(new Position(1,2),new Position(2,3)));
        Assert.assertEquals("Bishop", game.getBoard().getSquares(new Position(2, 3)).getPiece().getSuccesValue().get().getPieceName());
    }

    @Test
    public void testQueenMovements(){
        Board board = new Board(8,8);
        Piece blackQueen = new Piece(Color.BLACK, new Position(2, 6), ChessMovementFactory.createQueenMovements(), "Queen",1);
        Piece whiteQueen = new Piece(Color.WHITE, new Position(1, 2), ChessMovementFactory.createQueenMovements(), "Queen",2);
        board = board.placePiece(blackQueen, blackQueen.getOrigin());
        board = board.placePiece(whiteQueen, whiteQueen.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        game=game.whiteMakeMove(new Movement(new Position(1,2),new Position(2,3)));
        game=game.whiteMakeMove(new Movement(new Position(2,3),new Position(2,5)));
        game=game.whiteMakeMove(new Movement(new Position(2,5),new Position(5,5)));
        Assert.assertEquals("Queen", game.getBoard().getSquares(new Position(5, 5)).getPiece().getSuccesValue().get().getPieceName());
    }


    @Test
    public void testKingMovements(){
        Board board = new Board(8,8);
        Piece blackKing = new Piece(Color.BLACK, new Position(2, 6), ChessMovementFactory.createKingMovements(), "King",true,1);
        Piece whiteKing = new Piece(Color.WHITE, new Position(1, 2), ChessMovementFactory.createKingMovements(), "King",true,2);
        board = board.placePiece(blackKing, blackKing.getOrigin());
        board = board.placePiece(whiteKing, whiteKing.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        game=game.whiteMakeMove(new Movement(new Position(1,2),new Position(2,3)));
        game=game.whiteMakeMove(new Movement(new Position(2,3),new Position(2,4)));
        game=game.whiteMakeMove(new Movement(new Position(2,4),new Position(3,5)));
        Assert.assertEquals("King", game.getBoard().getSquares(new Position(3, 5)).getPiece().getSuccesValue().get().getPieceName());
    }


    @Test
    public void testNoChekMate(){
        Board board = new Board(8, 8);
        Piece blackQueen = new Piece(Color.BLACK, new Position(4, 1), ChessMovementFactory.createQueenMovements(), "Queen",1);
        Piece whiteKing = new Piece(Color.WHITE, new Position(4, 0), ChessMovementFactory.createKnightMovements(), "King",true,2);
        Piece whiteQueen=new Piece(Color.WHITE,new Position(4,7),ChessMovementFactory.createQueenMovements(),"Queen",3);
        board=board.placePiece(blackQueen,blackQueen.getOrigin());
        board=board.placePiece(whiteKing,whiteKing.getOrigin());
        board=board.placePiece(whiteQueen,whiteQueen.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        Assert.assertFalse(game.isOver());
    }

    @Test
    public void TestCheckMate(){
        Board board = new Board(8, 8);
        Piece blackQueen = new Piece(Color.BLACK, new Position(4, 2), ChessMovementFactory.createQueenMovements(), "Queen",1);
        Piece blackQueen3=new Piece(Color.BLACK,new Position(7,0),ChessMovementFactory.createQueenMovements(),"Queen",2);
        Piece whiteKing = new Piece(Color.WHITE, new Position(4, 0), ChessMovementFactory.createKingMovements(), "King",true,3);
        board=board.placePiece(blackQueen,blackQueen.getOrigin());
        board=board.placePiece(blackQueen3,blackQueen3.getOrigin());
        board=board.placePiece(whiteKing,whiteKing.getOrigin());
        Game game = createGameWithSpecificBoard(board);
        Assert.assertTrue(game.isOver());
        }
}