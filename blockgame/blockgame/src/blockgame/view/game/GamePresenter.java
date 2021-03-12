package blockgame.view.game;

import blockgame.model.Game;
import blockgame.model.Piece;
import blockgame.model.Point;
import blockgame.view.gameover.GameOverPresenter;
import blockgame.view.gameover.GameOverView;
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

/**
 * Maxim Derboven
 * 26/02/2021
 */
public class GamePresenter {
    private Game model;
    private GameView view;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Media droppingsound;
    private int selectedblock = 0;
    private Point lastLocation = null;
    static final String ACTIVE_CELL_CSS = "game-grid-cell-active";

    public GamePresenter(Game model, GameView view) {
        this.model = model;
        this.view = view;
        updateView();
        addEventHandlers();
        Path soundPath = Paths.get("blockgame" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "sounds" + FILE_SEPARATOR + "drop.mp3");
        droppingsound = new Media(new File(soundPath.toString()).toURI().toString());
    }


    private void updateView() {
        view.setLblUser("Logged in as: " + model.getPlayer().getUsername());
        view.setLblScore("Current score: " + model.getScoreboard().getScore());
        view.setLblHighscores("Highscore: " + model.getPlayer().getHighscore());
        view.setCapacity(model.getPlayablePieces().getCapacity());

        if (model.isPossible()) {
            view.getBlocksBox().getChildren().clear();
            for (Piece piece : model.getPlayablePieces().getPieces()) {
                view.getBlocksBox().getChildren().addAll(new ImageView(piece.getURL()));
            }
            for (int x = 0; x < model.getBoard().getSize(); x++) {
                for (int y = 0; y < model.getBoard().getSize(); y++) {
                    view.getGridBoard().getChildren().get(y * model.getBoard().getSize() + x).getStyleClass().remove(ACTIVE_CELL_CSS);
                    if (model.getBoard().getGrid()[x][y].isUsed()) {
                        view.getGridBoard().getChildren().get(y * model.getBoard().getSize() + x).getStyleClass().add(ACTIVE_CELL_CSS);
                    }
                }
            }
            addEventHandlers();
        } else {
            GameOverView gv = new GameOverView();
            GameOverPresenter mp = new GameOverPresenter(model, gv);
            view.getScene().setRoot(gv);
        }
    }


    private void updateLastLocation(Point location) {
        if (selectedblock == view.getBlocksBox().getChildren().size()) {
            selectedblock -= 1;
        }
        if (location == null) {
            removeLastLocation();
            lastLocation = null;
            return;
        }
        if (lastLocation != null) {
            removeLastLocation();
        }
        if (model.getBoard().isFree(model.getPlayablePieces().getPieces().get(selectedblock), location)) {
            for (Point point : model.getPlayablePieces().getPieces().get(selectedblock).getTiles()) {
                Node SecondCell = view.getGridBoard().getChildren().get((location.getY() + point.getY()) * model.getBoard().getSize() + (location.getX() + point.getX()));
                SecondCell.getStyleClass().add(ACTIVE_CELL_CSS);
            }
        }
        lastLocation = location;
    }

    private void removeLastLocation() {
        if (model.getBoard().isFree(model.getPlayablePieces().getPieces().get(selectedblock), lastLocation)) {
            for (Point point : model.getPlayablePieces().getPieces().get(selectedblock).getTiles()) {
                view.getGridBoard().getChildren().get((lastLocation.getY() + point.getY()) * model.getBoard().getSize() + (lastLocation.getX() + point.getX())).getStyleClass().remove(ACTIVE_CELL_CSS);
            }
        }
    }


    private void addEventHandlers() {

        //voeg aan alle vakjes een handler toe om deze te updaten wanneer er met een object over wordt gehovert.
        GridPane target = view.getGridBoard();
        if (!model.getBoard().isDraganddrop()) {
            //add click event zodat we weten welke blok de gebruiker wilt gaan gebruiken. (sleep event zal ook eebn blok aanduiden)
            for (int j = 0; j < view.getBlocksBox().getChildren().size(); j++) {
                int finalJ = j;
                view.getBlocksBox().getChildren().get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (selectedblock != finalJ) {
                            if (lastLocation != null) {
                                updateLastLocation(null);
                            }
                            selectedblock = finalJ;
                            System.out.println("Selected block; " + selectedblock);
                        }
                    }
                });
            }
            //klikken en zet doen
            target.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    Node node = event.getPickResult().getIntersectedNode();
                    if (node != target) {
                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int y = cIndex == null ? 0 : cIndex;
                        int x = rIndex == null ? 0 : rIndex;
                        model.play(model.getPlayablePieces().getPieces().get(selectedblock), new Point(x, y));
                        new MediaPlayer(droppingsound).play();
                    }
                    updateView();
                    event.consume();
                }
            });

            for (int x = 0; x < model.getBoard().getSize(); x++) {
                for (int y = 0; y < model.getBoard().getSize(); y++) {
                    Point locatie = new Point(x, y);
                    view.getGridBoard().getChildren().get(locatie.getY() * model.getBoard().getSize() + locatie.getX()).setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            updateLastLocation(locatie);
                        }
                    });
                }
            }
        } else {

            //zet de geselecteerde image op de dragboard en zet hem als geselecteerd blok.
            for (int j = 0; j < view.getBlocksBox().getChildren().size(); j++) {
                int finalJ = j;
                view.getBlocksBox().getChildren().get(j).setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedblock = finalJ;
                        System.out.println(selectedblock);
                        ImageView source = (ImageView) event.getSource();
                        //Het image wordt in het DragBoard gestopt tijdens de transfer
                        Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putImage(source.getImage());
                        dragboard.setContent(content);
                    }
                });
            }

            //clipboard ondrag bewaren onderweg naar target.
            target.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                }
            });

            for (int x = 0; x < model.getBoard().getSize(); x++) {
                for (int y = 0; y < model.getBoard().getSize(); y++) {
                    Point locatie = new Point(x, y);
                    view.getGridBoard().getChildren().get(locatie.getY() * model.getBoard().getSize() + locatie.getX()).setOnDragOver(new EventHandler<DragEvent>() {
                        @Override
                        public void handle(DragEvent event) {
                            updateLastLocation(locatie);
                        }
                    });
                }
            }

            // Droppen en zet doen
            target.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        new MediaPlayer(droppingsound).play();
                        success = true;
                        updateLastLocation(null);
                    }

                    Node node = event.getPickResult().getIntersectedNode();
                    if (node != target && db.hasImage()) {
                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int y = cIndex == null ? 0 : cIndex;
                        int x = rIndex == null ? 0 : rIndex;
                        model.play(model.getPlayablePieces().getPieces().get(selectedblock), new Point(x, y));
                        success = true;
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
            // Na de drop en zet
            for (int j = 0; j < view.getBlocksBox().getChildren().size(); j++) {
                view.getBlocksBox().getChildren().get(j).setOnDragDone(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent dragEvent) {
                        updateView();
                        dragEvent.consume();
                    }
                });
            }

        }

    }
}
