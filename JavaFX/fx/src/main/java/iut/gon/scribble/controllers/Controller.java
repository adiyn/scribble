package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.Dialogues;
import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Figure;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controleur {
    public VBox couleurs;
    public MenuBar menus;
    private double prevX;
    private double prevY;
    private Dessin modeleDessin;
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
    public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<>(Color.BLACK);
    public final SimpleDoubleProperty xProperty = new SimpleDoubleProperty(0);
    public final SimpleDoubleProperty yProperty = new SimpleDoubleProperty(0);
    public final SimpleStringProperty nomOutil = new SimpleStringProperty("Crayon");

    private Outil outilCourant;

    @FXML private Figure figureCourante;
    @FXML public GridPane statut;
    @FXML private MenusController menusController;
    @FXML private DessinController dessinController;
    @FXML private CouleursController couleursController;
    @FXML private StatutController statutController;

    @FXML public void initialize() {
        menusController.setControleur(this);
        dessinController.setControleur(this);
        couleursController.setControleur(this);
        statutController.setControleur(this);

        outilCourant = new OutilCrayon(this);
    }

    public Dessin getDessin() {
        return modeleDessin;
    }

    public void setDessin(Dessin modeleDessin) {
        this.modeleDessin = modeleDessin;
    }

    public void setFigureCourante(Figure f) {
        this.figureCourante = f;
    }
    public Figure getFigureCourante() {
        return figureCourante;
    }

    public double getPrevX() {
        return prevX;
    }

    public void setPrevX(double prevX) {
        this.prevX = prevX;
    }

    public double getPrevY() {
        return prevY;
    }

    public void setPrevY(double prevY) {
        this.prevY = prevY;
    }

    public MenusController getMenusController() {
        return menusController;
    }

    public void setMenusController(MenusController menusController) {
        this.menusController = menusController;
    }

    public DessinController getDessinController() {
        return dessinController;
    }

    public void setDessinController(DessinController dessinController) {
        this.dessinController = dessinController;
    }

    public CouleursController getCouleursController() {
        return couleursController;
    }

    public void setCouleursController(CouleursController couleursController) {
        this.couleursController = couleursController;
    }

    public StatutController getStatutController() {
        return statutController;
    }

    public void setStatutController(StatutController statutController) {
        this.statutController = statutController;
    }

    public boolean peutQuitter() {
        if (!getDessin().getEstModifie()) {
            return true;
        }
        if (Dialogues.confirmation()) {
            Stage stage = (Stage) menus.getScene().getWindow();
            menusController.onSauvegarder(stage);

            return !getDessin().getEstModifie();
        }
        return true;
    }

    public void onCrayon() {
        outilCourant = new OutilCrayon(this);
        nomOutil.set("Crayon");
    }

    public void onEtoile() {
        outilCourant = new OutilEtoile(this);
        nomOutil.set("Étoile");
    }

    public void onMousePress(MouseEvent event) {
        prevX = event.getX();
        prevY = event.getY();

        outilCourant.onMousePress(event);
    }

    public void onMouseDrag(MouseEvent event) {
        outilCourant.onMouseDrag(event);
    }

    public void onMouseMove(MouseEvent event) {
        xProperty.set(event.getX());
        yProperty.set(event.getY());
    }

    public void onMouseRelease(MouseEvent event) {
        outilCourant.onMouseRelease(event);
    }

    public void onKeyPressed(String touche) {
        switch (touche.toLowerCase()) {
            case "c":
                onCrayon();
                outilCourant.draw(this.xProperty.get(), this.yProperty.get());
                break;
            case "e":
                onEtoile();
                outilCourant.draw(this.xProperty.get(), this.yProperty.get());
                break;
            case "1":
                epaisseur.set(1);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "2":
                epaisseur.set(2);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "3":
                epaisseur.set(3);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "4":
                epaisseur.set(4);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "5":
                epaisseur.set(5);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "6":
                epaisseur.set(6);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "7":
                epaisseur.set(7);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "8":
                epaisseur.set(8);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
            case "9":
                epaisseur.set(9);
                outilCourant.draw(this.xProperty.get(), this.yProperty.get(), this.epaisseur.get());
                break;
        }
    }
}