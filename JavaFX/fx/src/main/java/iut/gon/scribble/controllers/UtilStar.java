package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.modele.Etoile;
import javafx.scene.input.MouseEvent;

public class OutilEtoile extends Outil {

    public OutilEtoile(Controleur controleur) {
        super(controleur);
    }

    @Override
    public void onMousePress(MouseEvent event) {
        estEnTrainDeDessiner = true;
        draw(controleur.getPrevX(), controleur.getPrevY());
    }

    @Override
    public void onMouseDrag(MouseEvent event) {
        controleur.getDessinController().trace(controleur.getPrevX(), controleur.getPrevY(), event.getX(), event.getY());

        controleur.getFigureCourante().addPoint(event.getX(), event.getY());

        controleur.xProperty.set(event.getX());
        controleur.yProperty.set(event.getY());
    }

    @Override
    public void onMouseRelease(MouseEvent event) {
        if (estEnTrainDeDessiner) {
            estEnTrainDeDessiner = false;
        }
    }

    @Override
    public void draw(double x, double y) {
        Etoile nouvelleEtoile = new Etoile(controleur.epaisseur.get(), controleur.couleur.get().toString(),x , y);
        controleur.setFigureCourante(nouvelleEtoile);
        controleur.getDessin().addFigure(nouvelleEtoile);
    }

    @Override
    public void draw(double x, double y, int epaisseur) {
        Etoile nouvelleEtoile = new Etoile(epaisseur, controleur.couleur.get().toString(),x , y);
        controleur.setFigureCourante(nouvelleEtoile);
        controleur.getDessin().addFigure(nouvelleEtoile);
    }
}