package blockgame.view.settings;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:18
 */
public class SettingsPresenter {
    private Game model;
    private SettingsView view;

    public SettingsPresenter(Game model, SettingsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
        /* Waarden instellen van de file location */
        view.getTfFileLocation().setText(model.getAm().getLocation());

        /* Waarden instellen van de slider */
        view.getSlSize().setValue(model.getBoard().getSize());
        view.getBoardSizeSliderLabel().setText(String.format("Size: %dx%d", model.getBoard().getSize(), model.getBoard().getSize()));

        /* Waarden instellen van de difficulty */
        view.getChkDifficulty().setSelected(model.getPlayablePieces().isDifficulty());

        /* Waarden instellen van de Drag and drop */
        if (model.getBoard().isDraganddrop()){
            view.getRbDrag().setSelected(true);
        }else {
            view.getRbClick().setSelected(true);
        }

        /* Waarden instellen van de playable pieces */
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 5, model.getPlayablePieces().getCapacity());
        view.getSpPlayablePieces().setValueFactory(valueFactory);
    }

    private void addEventHandlers() {
        view.getImgSave().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                /* Board size */
                model.getBoard().setSize((int) view.getSlSize().getValue());
                System.out.println("Board size: " + model.getBoard().getSize());

                /* Difficulty button */
                model.getPlayablePieces().setDifficulty(view.getChkDifficulty().isSelected());
                System.out.println("Difficulty: " + model.getPlayablePieces().isDifficulty());

                /* Drag and drop */
                model.getBoard().setDraganddrop(view.getRbDrag().isSelected());
                System.out.println("Drag and drop: " + view.getRbDrag().isSelected());

                /* Playable pieces */
                model.getPlayablePieces().setCapacity(view.getSpPlayablePieces().getValueFactory().getValue());
                System.out.println("Playable pieces: " + model.getPlayablePieces().getPieces());

                MainMenuView mv = new MainMenuView();
                MainMenuPresenter mp = new MainMenuPresenter(model, mv);
                view.getScene().setRoot(mv);
            }
        });

        /* Slider label dynamisch veranderen */
        view.getSlSize().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoardSizeSliderLabel().setText(String.format("Size: %.0fx%.0f", view.getSlSize().getValue(), view.getSlSize().getValue()));
            }
        });
        view.getSlSize().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoardSizeSliderLabel().setText(String.format("Size: %.0fx%.0f", view.getSlSize().getValue(), view.getSlSize().getValue()));
            }
        });

        view.getImgSave().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setCursor(Cursor.HAND);
                view.getImgSave().setScaleX(1.1);
                view.getImgSave().setScaleY(1.1);
            }
        });

        view.getImgSave().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setScaleX(1);
                view.getImgSave().setScaleY(1);
            }
        });

    }

}
