package iut.gon.gribouille;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class Dialogues {
    public static boolean confirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous sauvegarder avant de quitter ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation de fermeture");
        alert.setHeaderText(null);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }
}