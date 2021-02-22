package blockgame;

import blockgame.model.Game;
import blockgame.view.loginScreen.LoginPresenter;
import blockgame.view.loginScreen.LoginView;
import blockgame.view.registerScreen.RegisterPresenter;
import blockgame.view.registerScreen.RegisterView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game model = new Game();

        RegisterView view1 = new RegisterView();
        new RegisterPresenter(model, view1);

        LoginView view2 = new LoginView();
        new LoginPresenter(model, view2);
        Stage st = new Stage();
        st.setScene(new Scene(view2));
        st.setResizable(false);
        st.setTitle("Login");
        st.show();

        primaryStage.setScene(new Scene(view1));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Register");
        primaryStage.show();
    }

    private static blockgame.model.Game game;
    //private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        game = new blockgame.model.Game(); //init
        Application.launch(args);
    }

}