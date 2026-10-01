package iut.gon.gribouille.controleurs;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenusController {
    private Controleur controleur;

    @FXML private ToggleGroup epaisseurGroup;
    @FXML private ToggleGroup outilGroup;

    @FXML public void initialize() {
        epaisseurGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null && controleur != null) {
                RadioMenuItem radio = (RadioMenuItem) newToggle;
                int nouvelleEpaisseur = Integer.parseInt(radio.getText());
                controleur.epaisseur.set(nouvelleEpaisseur);
            }
        });
        outilGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null && controleur != null) {
                RadioMenuItem radio = (RadioMenuItem) newToggle;
                if (radio.getText().equals("Crayon")) {
                    controleur.onCrayon();
                } else {
                    controleur.onEtoile();
                }
            }
        });
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void onSauvegarder(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder le dessin");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dessins Gribouille", "*.grib"));

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            controleur.getDessin().sauveSous(file.getAbsolutePath());
        }
    }

    public void onSauvegarder(ActionEvent event) {
        Stage stage = (Stage) controleur.menus.getScene().getWindow();

        onSauvegarder(stage);
        event.consume();
    }

    public void onCharger(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un dessin");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Dessins Gribouille", "*.grib"));

        Stage stage = (Stage) controleur.menus.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            controleur.getDessin().charge(file.getAbsolutePath());
            controleur.getDessinController().redessiner(controleur.getDessin());
        }
        event.consume();
    }

    public void onQuitter(ActionEvent event) {
        if (controleur.peutQuitter()) {
            Platform.exit();
            event.consume();
        }
    }

    public void onExporter(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportation le dessin");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image PNG", "*.png"));

        Stage stage = (Stage) controleur.menus.getScene().getWindow();
        File fichier = fileChooser.showSaveDialog(stage);

        if (fichier != null) {
            try {
                Canvas canvas = controleur.getDessinController().getCanvas();
                WritableImage snapshot = canvas.snapshot(new SnapshotParameters(), null);

                BufferedImage buffimg = SwingFXUtils.fromFXImage(snapshot, null);

                ImageIO.write(buffimg, "png", fichier);

            } catch (IOException e) {
                System.err.println("Erreur lors de l'export (espace manquant ?) :( : " + e.getMessage());
            }
        }
    }

    public void onAPropos(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText("Fait par Nils Marechal TD2 TP2 :3");
        info.show();
    }
}