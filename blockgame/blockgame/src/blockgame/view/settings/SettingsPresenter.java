package blockgame.view.settings;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
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
        //fill view with data from model
    }

    private void addEventHandlers() {
        view.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                /* Board size */
                model.getBoard().setSize((int) view.getSlSize().getValue());
                System.out.println(model.getBoard().getSize());

                /* Difficulty button */
                model.getPlayablePieces().setDifficulty(view.getChkDifficulty().isSelected());
                System.out.println(model.getPlayablePieces().isDifficulty());

                /* Playable pieces */
                model.getPlayablePieces().setCapacity(view.getSpPlayablePieces().getValueFactory().getValue());
                System.out.println(model.getPlayablePieces().getPieces());

                model.getBoard().setDraganddrop(view.getChkMode().isSelected());
                System.out.println(view.getChkMode().isSelected());
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

        view.getImgClose().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenuView mv = new MainMenuView();
                MainMenuPresenter mp = new MainMenuPresenter(model, mv);
                view.getScene().setRoot(mv);
            }
        });

        view.getImgClose().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgClose().setCursor(Cursor.HAND);
            }
        });

        view.getBtnSave().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBtnSave().setCursor(Cursor.HAND);
            }
        });

        view.getChkMode().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (view.getChkMode().isSelected()){
                    view.getLblMode().setText("Mode (drag)");
                }else {
                    view.getLblMode().setText("Mode (click)");
                }
            }

        });

    }

}
