package iut.gon.gribouille.controleurs;

import javafx.scene.input.MouseEvent;

public abstract class Outil {
    protected Controleur controleur;
    protected Boolean estEnTrainDeDessiner = false;

    public Outil(Controleur controleur) {
        this.controleur = controleur;
    }

    public abstract void onMousePress(MouseEvent event);
    public abstract void onMouseDrag(MouseEvent event);
    public abstract void onMouseRelease(MouseEvent event);
    public abstract void draw(double x, double y);
    public abstract void draw(double x, double y, int epaisseur);
}