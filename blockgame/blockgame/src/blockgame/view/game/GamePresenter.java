package blockgame.view.game;

import blockgame.model.Game;
import blockgame.model.Piece;
import blockgame.model.Point;
import blockgame.view.about.AboutAlert;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;
import blockgame.view.settings.SettingsPresenter;
import blockgame.view.settings.SettingsView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class GamePresenter {
    private Game model;
    private GameView view;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Media droppingsound;
    private int search1;

    public GamePresenter(Game model, GameView view) {
        this.model = model;
        this.view = view;
        updateView();
        addEventHandlers();
        Path soundPath = Paths.get("blockgame" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "sounds" + FILE_SEPARATOR + "drop.mp3");
        droppingsound = new Media(new File(soundPath.toString()).toURI().toString());
    }


    private void updateView() {
        view.setBoardsize(model.getBoard().getSize());
        view.setLblUser("Logged in as: " + model.getPlayer().getUsername());
        view.setLblScore("Current score: " + model.getScoreboard().getScore());
        view.setLblHighscores("Highscore: " + model.getPlayer().getHighscore());
        view.setCapacity(model.getPlayablePieces().getCapacity());

        for (Piece piece : model.getPlayablePieces().getPieces()) {
            view.getHbox2().getChildren().addAll(new ImageView(piece.getURL()));
        }

        for (int i = 0; i < model.getBoard().getSize(); i++) {
            for (int j = 0; j < model.getBoard().getSize(); j++) {
                if (model.getBoard().getGrid()[i][j].isUsed()) {
                    view.getBoard().getChildren().get(i * model.getBoard().getSize() + j).getStyleClass().add("game-grid-cell-active");
                }
            }
        }
    }

    private void addEventHandlers() {
        /*view.getBoard().setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int k = 0; k < view.getHbox2().getChildren().size(); k++) {
                    int finalK = k;
                    view.getHbox2().getChildren().get(finalK).setOnMouseReleased(e -> {
                        for (int i = 0; i < model.getBoard().getSize(); i++) {
                            for (int j = 0; j < model.getBoard().getSize(); j++) {
                                int search = (i * model.getBoard().getSize() + j);
                                int finalJ = j;
                                int finalI = i;
                                search1 = search;
                                view.getBoard().getChildren().get(search).setOnMouseMoved(event1 -> {
                                    if (search != search1) {
                                        if (model.getBoard().isPossible(model.getPlayablePieces().getPieces().get(finalK))) {
                                            for (Point p : model.getPlayablePieces().getPieces().get(finalK).getTiles()) {
                                                int r = p.getX() + p.getX();
                                                int c = p.getX() + p.getY();
                                                view.getBoard().getChildren().get(r * model.getBoard().getSize() + c).getStyleClass().add("game-grid-cell-active");
                                            }
                                        }
                                        view.getBoard().getChildren().get(search1).getStyleClass().remove("game-grid-cell-active");
                                        search1 = search;
                                    }

                                });
                                //model.play(model.getPlayablePieces().getPieces().get(0), new Point(finalI, finalJ));
                            }
                        }
                    });
                }
            }
        });*/

        //1) voeg OnDragDetected EventHandler toe aan de source (imageviews met wormen):
        EventHandler<MouseEvent> dragDetected = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView source = (ImageView) event.getSource();
                //Het image wordt in het DragBoard gestopt tijdens de transfer
                Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(source.getImage());
                dragboard.setContent(content);
                event.consume();
            }
        };
        for (
                int i = 0; i < view.getHbox2().

                getChildren().

                size();

                i++) {
            view.getHbox2().getChildren().get(i).setOnDragDetected(dragDetected);
        }

        //2) voeg OnDragOver EventHandler toe aan het target (imageview met vogelnest)
        //   dit zorgt ervoor dat het target de worm kan accepteren.
        GridPane target = view.getBoard();
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        //3) voeg OnDragDropped EventHandler toe aan target
        //   deze wordt uitgevoerd zodra de drop gebeurt.
        //   We spelen een geluidje en geven door dat de drop gelukt is
        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    new MediaPlayer(droppingsound).play();
                    success = true;
                }
                for(int i = 0; i < view.getPieces().size(); i++) {
                    final int buttonInd = i;
                    view.getHbox2().getChildren().get(i).setOnMouseClicked(e -> {
                        System.out.println("image pressed " + buttonInd);
                    });
                }


                Node node = event.getPickResult().getIntersectedNode();
                if (node != target && db.hasImage()) {
                    Integer cIndex = GridPane.getColumnIndex(node);
                    Integer rIndex = GridPane.getRowIndex(node);
                    int x = cIndex == null ? 0 : cIndex;
                    int y = rIndex == null ? 0 : rIndex;
                    ImageView image = new ImageView(db.getImage());
                    model.play(model.getPlayablePieces().getPieces().get(0), new Point(x, y));
                    //System.out.println(model.getPlayablePieces().getPieces().get(0).getValue());
                    updateView();
                    //updateView();
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
        //4) voeg OnDragDone EventHandlers toe aan sources
        //   we verwijderen de image van de imageview
        EventHandler<DragEvent> dragDone = new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                ImageView source = (ImageView) event.getSource();
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setImage(null);
                }
                event.consume();
            }
        };
        for (
                int i = 0; i < view.getHbox2().

                getChildren().

                size();

                i++) {
            view.getHbox2().getChildren().get(i).setOnDragDetected(dragDetected);
        }


        view.getMv().

                getLblAbout().

                setOnMouseClicked(mouseEvent ->

                {
                    AboutAlert aboutAlert = new AboutAlert();
                    aboutAlert.showAndWait();
                });
        view.getMv().

                getLblHighscores().

                setOnMouseClicked(mouseEvent ->

                {
                    HighscoresView highscoresView = new HighscoresView();
                    HighscoresPresenter highscoresPresenter = new HighscoresPresenter(model, highscoresView);
                    view.getScene().setRoot(highscoresView);
                    highscoresView.getScene().getWindow().sizeToScene();
                });
        view.getMv().

                getLblSettings().

                setOnMouseClicked(mouseEvent ->

                {
                    SettingsView settingsView = new SettingsView();
                    SettingsPresenter settingsPresenter = new SettingsPresenter(model, settingsView);
                    view.getScene().setRoot(settingsView);
                    settingsView.getScene().getWindow().sizeToScene();
                });
    }
}
