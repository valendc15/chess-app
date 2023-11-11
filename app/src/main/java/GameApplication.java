import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.austral.game.chess.ChessGameFactory;
import org.austral.game.commons.CustomGameEngine;
import org.austral.game.connection.ClientListenerImp;
import org.austral.game.connection.ServerConnectionListenerImp;
import org.austral.game.connection.ServerController;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import javafx.application.Application;

public class GameApplication extends Application {

    GameEngine gameEngine = new CustomGameEngine(new ChessGameFactory());
    ImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());
    GameView root = new GameView(imageResolver);
    NettyServerBuilder server = NettyServerBuilder.Companion.createDefault();
    ServerController serverController = new ServerController(gameEngine, root, server);

    public static final String GameTitle = "Chess";
    public static void main(String[] args) {
        launch(ChessGameApplication.class, args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(GameTitle);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}