package blockgame.view.game;

import blockgame.model.Game;
import blockgame.model.Piece;
import blockgame.model.Point;
import blockgame.view.gameover.GameOverPresenter;
import blockgame.view.gameover.GameOverView;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Maxim Derboven
 * 26/02/2021
 */
public class GamePresenter {

    /**
     * Attributen
     */
    private Game model;
    private GameView view;
    private final Media droppingsound;
    private int selectedblock = 0;
    private Point lastLocation = null;
    static final String ACTIVE_CELL_CSS = "game-grid-cell-active";
    private final Media removeSound;

    /**
     * Constructor voor Het Game scherm
     * Sounds, (window)Eventhandlers & updaten van de view.
     */
    public GamePresenter(Game model, GameView view) {
        this.model = model;
        this.view = view;
        this.droppingsound = new Media(new File(Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "drop.mp3").toString()).toURI().toString());
        this.removeSound = new Media(new File(Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "delete.mp3").toString()).toURI().toString());
        addEventHandlers();
        updateView();
        addWindowEventHandlers();
    }

    /**
     * Het scherm updaten bij wijzigingen en start
     */
    private void updateView() {
        /* Dynamisch score updaten */
        view.setLblUser("Logged in as: " + model.getPlayer().getUsername());
        view.setLblScore("Current score: " + model.getScoreboard().getScore());
        view.setLblHighscores("Highscore: " + model.getPlayer().getHighscore());
        /* Zolang het mogelijk is om een blok te plaatsen, update doen */
        if (model.isPossible()) {
            /* Alle foto's weg halen */
            view.getBlocksBox().getChildren().clear();
            /* Terug alle fotos toevoegen die er over zijn */
            /* Kon in principe ook enkel de gebruikte op null zetten */
            for (Piece piece : model.getPlayablePieces().getPieces()) {
                view.getBlocksBox().getChildren().addAll(new ImageView(piece.getURL()));
            }

            /* Voor elk vakje op het bord updaten. */
            boolean played = false;
            for (int x = 0; x < model.getBoard().getSize(); x++) {
                for (int y = 0; y < model.getBoard().getSize(); y++) {
                    /* Bij vakjes dat verwijderd zijn : */
                    if (view.getGridBoard().getChildren().get(y * model.getBoard().getSize() + x).getStyleClass().contains(ACTIVE_CELL_CSS) && !model.getBoard().getGrid()[x][y].isUsed() && !played && model.isMusic()) {
                        new MediaPlayer(removeSound).play();
                        played = true;
                        /* Animatie rij verwijderen hier ? */
                    }

                    /* ALle vakjes updaten zowel de geplaatste als de verwijderde (stijlen), dus voort gemak gewoon alle vakjes loopen */
                    view.getGridBoard().getChildren().get(y * model.getBoard().getSize() + x).getStyleClass().remove(ACTIVE_CELL_CSS);
                    if (model.getBoard().getGrid()[x][y].isUsed()) {
                        view.getGridBoard().getChildren().get(y * model.getBoard().getSize() + x).getStyleClass().add(ACTIVE_CELL_CSS);
                    }
                }
            }
            /* Event handelers terug toevoegen ander is het niet meer mogelijk om nieuwe blok foto's te slepen (aangezien oude verwijderd worden)*/
            addEventHandlers();
        } else {
            /* Als het niet meer mogelijk is te spelen : Game over scherm laten zien */
            GameOverView gov = new GameOverView();
            view.getScene().setRoot(gov);
            new GameOverPresenter(model, gov);
            return;
        }
        /* Als drag and drop uit staat : een border glow rond de foto's zetten zodat de gebruiker weet welke geselecteerd is. */
        if (!model.getBoard().isDraganddrop()) view.getBlocksBox().getChildren().get(0).setEffect(view.getBorderGlow());
    }

    /* Voorbeeld locatie updaten adhv stand muis */
    private void updateLastLocation(Point location) {
        /* De geselecteerde blok terug op 0 zetten wanneer gespeeld */
        if (selectedblock == view.getBlocksBox().getChildren().size()) selectedblock = 0;
        /* Als de huidige locatie leeg is : verwijder de highlight & zet locatie op null (buiten spelbord) & returnen*/
        if (location == null) {
            removeLastLocation();
            lastLocation = null;
            return;
        }
        /* Als lastlocation niet null is = highlight verwijderen om hieronder een nieuwe aan te maken */
        if (lastLocation != null) removeLastLocation();

        /* Op locatie kijken of het mogelijk is een blok te plaatsen. Zo ja highlight de vakjes door de Points van de blok te loopen tegenover het spelbord */
        if (model.getBoard().isFree(model.getPlayablePieces().getPieces().get(selectedblock), location)) {
            for (Point point : model.getPlayablePieces().getPieces().get(selectedblock).getTiles()) {
                Node SecondCell = view.getGridBoard().getChildren().get((location.getY() + point.getY()) * model.getBoard().getSize() + (location.getX() + point.getX()));
                SecondCell.getStyleClass().add(ACTIVE_CELL_CSS);
            }
        }
        /* Location updaten */
        lastLocation = location;
    }

    /* Highlight verwijderen wordt hierboven aangeroepen na de verandering van muis */
    private void removeLastLocation() {
        if (model.getBoard().isFree(model.getPlayablePieces().getPieces().get(selectedblock), lastLocation)) {
            for (Point point : model.getPlayablePieces().getPieces().get(selectedblock).getTiles()) {
                view.getGridBoard().getChildren().get((lastLocation.getY() + point.getY()) * model.getBoard().getSize() + (lastLocation.getX() + point.getX())).getStyleClass().remove(ACTIVE_CELL_CSS);
            }
        }
    }

    /* Window Alert toevoegen tegen het verlies van score (confirmation box) */
    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Sure you want to exit ?");
                alert.setContentText("Are you sure you want to exit? Your score is not saved.");
                ButtonType btnExit = new ButtonType("Exit");
                ButtonType btnMenu = new ButtonType("to Menu");
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/images/logo.png").toString()));
                alert.getButtonTypes().setAll(btnExit, btnMenu, btnCancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get().getText().equalsIgnoreCase("Exit")) {
                    Platform.exit();
                } else if (result.get().getText().equalsIgnoreCase("to Menu")) {
                    Game newmodel = new Game();
                    MainMenuView mv = new MainMenuView();
                    view.getScene().setRoot(mv);
                    new MainMenuPresenter(newmodel, mv);
                    event.consume();
                } else {
                    event.consume();
                }
            }
        });
    }

    /* Even handelers */
    private void addEventHandlers() {

        /*Voeg de grid toe als target */
        GridPane target = view.getGridBoard();
        /* Check welke uit te voeren aan de hand van welke modus aan staat (click of drag and drop) */
        if (!model.getBoard().isDraganddrop()) {
            /*add click event zodat we weten welke blok de gebruiker wilt gaan gebruiken. (sleep event zal ook eebn blok aanduiden) */
            for (int j = 0; j < view.getBlocksBox().getChildren().size(); j++) {
                int finalJ = j;

                /* Voor het clicken van de blokken foto's een hand cursor zetten */
                view.getBlocksBox().getChildren().get(j).setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        view.getBlocksBox().getChildren().get(finalJ).setCursor(Cursor.HAND);
                    }
                });

                /* Op het klikken van de muis op de fotos : de highlight van de vorige blok die evt nog staat verwijderen, de block als selected block markeren & glowen */
                view.getBlocksBox().getChildren().get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (selectedblock != finalJ) {
                            if (lastLocation != null) {
                                updateLastLocation(null);
                            }
                            //glow effect toevoegen & verwijderen bij select:
                            for (int i = 0; i < model.getPlayablePieces().getPieces().size(); i++) {
                                view.getBlocksBox().getChildren().get(i).setEffect(null);
                            }
                            view.getBlocksBox().getChildren().get(finalJ).setEffect(view.getBorderGlow());

                            selectedblock = finalJ;
                        }
                    }
                });
            }
            /* Wanneer er op de target geklicked wordt kijken op welke node (= welk vakje) & daar proberen te playen */
            target.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    Node node = event.getPickResult().getIntersectedNode();
                    if (node != target) {
                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int y = cIndex == null ? 0 : cIndex;
                        int x = rIndex == null ? 0 : rIndex;
                        if (model.getBoard().isFree(model.getPlayablePieces().getPieces().get(selectedblock), new Point(x, y)) && model.isMusic()) {
                            new MediaPlayer(droppingsound).play();
                            model.play(model.getPlayablePieces().getPieces().get(selectedblock), new Point(x, y));
                            updateView();
                        }
                    }
                    event.consume();
                }
            });

            /* Voor de highlight aan elk vakje een on entered hangen zodat de location dynamisch geupdate blijft */
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

            /**/
            for (int j = 0; j < view.getBlocksBox().getChildren().size(); j++) {
                int finalJ = j;

                /* hand maken on mouse entered voor drag (CLOSED HAND AANGEZIEN DRAG)*/
                view.getBlocksBox().getChildren().get(j).setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        view.getBlocksBox().getChildren().get(finalJ).setCursor(Cursor.CLOSED_HAND);
                    }
                });

                /* zet de geselecteerde image op de dragboard en zet hem als geselecteerd blok. */
                view.getBlocksBox().getChildren().get(j).setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedblock = finalJ;
                        ImageView source = (ImageView) event.getSource();
                        //Het image wordt in het DragBoard gestopt tijdens de transfer
                        Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putImage(source.getImage());
                        dragboard.setContent(content);
                    }
                });
            }

            /*Wanneer de drag in actie is (dus wanneer er wordt gesleept) de image laten mee gaan */
            target.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    if (event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                }
            });

            /* Voor de highlight aan elk vakje een on drag entered hangen zodat de location dynamisch geupdate blijft (van awt lib) */
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

            /* Wanneer de muis wordt losgelaten maw de drop : zet doen */
            target.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasImage()) {
                        success = true;
                        updateLastLocation(null);
                    }

                    Node node = event.getPickResult().getIntersectedNode();
                    if (node != target && db.hasImage()) {
                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int y = cIndex == null ? 0 : cIndex;
                        int x = rIndex == null ? 0 : rIndex;
                        if (model.getBoard().isFree(model.getPlayablePieces().getPieces().get(selectedblock), new Point(x, y)) && model.isMusic()) {
                            new MediaPlayer(droppingsound).play();
                        }
                        model.play(model.getPlayablePieces().getPieces().get(selectedblock), new Point(x, y));
                        success = true;
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }
            });

            /* Na de drop het dragevent cancelen */
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
