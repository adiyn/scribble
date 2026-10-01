package iut.gon.gribouille.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatutController {
    private Controleur controleur;

    @FXML private Label labelX;
    @FXML private Label labelY;
    @FXML private Label labelEpaisseur;
    @FXML private Label labelCouleur;
    @FXML public Label labelOutil;

    public void setControleur(Controleur c) {
        this.controleur = c;

        labelX.textProperty().bind(controleur.xProperty.asString("%.0f"));
        labelY.textProperty().bind(controleur.yProperty.asString("%.0f"));

        labelEpaisseur.textProperty().bind(controleur.epaisseur.asString());
        labelCouleur.textProperty().bind(controleur.couleur.asString());
        labelOutil.textProperty().bind(controleur.nomOutil);
    }
}
