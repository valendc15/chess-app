package edu.austral.dissis.chess

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage
import org.austral.game.checkers2.CheckersGameFactory
import org.austral.game.chess.ChessGameFactory
import org.austral.game.chess.DunsanysChessGameFactory
import org.austral.game.commons.CustomGameEngine
import org.austral.game.connection.ServerController


fun main() {
    launch(ChessGameApplication::class.java)
}

class ChessGameApplication : Application() {
    private val gameEngine = CustomGameEngine(CheckersGameFactory())
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val server = NettyServerBuilder.createDefault()
    private val serverController : ServerController = ServerController(gameEngine, root, server)


    companion object {
        const val GameTitle = "Chess"
    }


    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }


}