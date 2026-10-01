package iut.gon.gribouille.controleurs;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import iut.gon.gribouille.modele.Dessin;
import iut.gon.gribouille.modele.Etoile;
import iut.gon.gribouille.modele.Figure;
import iut.gon.gribouille.modele.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import java.util.List;

public class DessinController {
    private Controleur controleur;

    @FXML Canvas dessin;
    @FXML private Pane conteneurPane;

    public Canvas getCanvas() {
        return dessin;
    }

    public void efface() {
        dessin.getGraphicsContext2D().clearRect(0,0,dessin.getWidth(), dessin.getHeight());
    }

    public void trace(double x1, double y1, double x2, double y2) {
        dessin.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }

    public void redessiner(Dessin cahier) {
        if (cahier == null) return; // si il n'y a rien à redessiner on passe

        efface();
        // on recupere 
        GraphicsContext canva = dessin.getGraphicsContext2D();

        for (Figure fig : cahier.getFigures()) {
            canva.setStroke(Color.web(fig.getCouleur()));
            canva.setLineWidth(fig.getEpaisseur());

            if (fig instanceof Etoile) {
                Etoile etoile = (Etoile) fig;
                Point centre = etoile.getCentre();
                for (Point p : etoile.getPoints()) {
                    canva.strokeLine(centre.getX(), centre.getY(), p.getX(), p.getY());
                }
            } else {
                List<Point> pts = fig.getPoints();
                for (int i = 0; i < pts.size() - 1; i++) {
                    Point p1 = pts.get(i);
                    Point p2 = pts.get(i + 1);
                    canva.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                }
            }
        }
    }

    @FXML
    public void initialize() {
        dessin.widthProperty().bind(conteneurPane.widthProperty());
        dessin.heightProperty().bind(conteneurPane.heightProperty());

        dessin.widthProperty().addListener((observable, oldVal, newVal) -> {
            if (controleur != null) redessiner(controleur.getDessin());
        });

        dessin.heightProperty().addListener((observable, oldVal, newVal) -> {
            if (controleur != null) redessiner(controleur.getDessin());
        });
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void onMouseMove(MouseEvent event) {
        controleur.onMouseMove(event);
    }

    public void onMousePress(MouseEvent event) {
        controleur.onMousePress(event);
    }

    public void onMouseDrag(MouseEvent event) {
        controleur.onMouseDrag(event);
    }

    public void onMouseRelease(MouseEvent event) {
        controleur.onMouseRelease(event);
    }

    public void setEpaisseur(int w) {
        dessin.getGraphicsContext2D().setLineWidth(w);
    }

    public void setCouleur(String c) {
        Canvas dessin = controleur.getDessinController().dessin;
        dessin.getGraphicsContext2D().setStroke(Color.valueOf(c));
    }
}
