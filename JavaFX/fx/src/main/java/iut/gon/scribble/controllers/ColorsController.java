package iut.gon.gribouille.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CouleursController {
    private Controleur controleur;
    private Rectangle rectanglePrecedent = null;
    @FXML private ColorPicker ColorPicker;

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
        ColorPicker.valueProperty().addListener((observable, oldcolor, newcolor) -> {
            if (ColorPicker != null && newcolor != null) {
                controleur.couleur.setValue(newcolor);
            }
        });
    }

    @FXML
    public void onPaletteClic(MouseEvent event) {
        if (event.getTarget() instanceof Rectangle) {
            Rectangle rectClique = (Rectangle) event.getTarget();

            Color nouvelleCouleur = (Color) rectClique.getFill();

            controleur.couleur.set(nouvelleCouleur);

            mettreEnSurbrillance(rectClique);
        }
    }
    private void mettreEnSurbrillance(Rectangle nouveauRect) {
        if (rectanglePrecedent != null) {
            rectanglePrecedent.setArcWidth(5);
            rectanglePrecedent.setArcHeight(5);
            rectanglePrecedent.setStrokeWidth(1);
        }

        nouveauRect.setArcWidth(10);
        nouveauRect.setArcHeight(10);
        nouveauRect.setStrokeWidth(5);

        rectanglePrecedent = nouveauRect;
    }
}
