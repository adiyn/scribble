package iut.gon.gribouille;

import iut.gon.gribouille.controleurs.Controleur;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CadreGribouille.fxml"));
        Parent root = fxmlLoader.load();

        Controleur con = fxmlLoader.getController();

        iut.gon.gribouille.modele.Dessin modeleDessin = new iut.gon.gribouille.modele.Dessin();

        con.setDessin(modeleDessin);

        stage.setOnCloseRequest(event -> {
            if (!con.peutQuitter()) {
                event.consume();
            } else {
                Platform.exit();
            }
        });

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            con.onKeyPressed(event.getText());
        });

        StringExpression symboleModification = Bindings.when(modeleDessin.estModifieProperty())
                .then(" * ")
                .otherwise("");
        StringExpression titreDynamique = Bindings.concat(modeleDessin.nomDuFichierProperty(), symboleModification);
        stage.titleProperty().bind(titreDynamique);

        scene = new Scene(root, 650, 490);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }
}