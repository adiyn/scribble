package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.modele.Trace;
import javafx.scene.input.MouseEvent;

public class OutilCrayon extends Outil {

    public OutilCrayon(Controleur controleur) {
        super(controleur);
    }

    @Override
    public void onMousePress(MouseEvent event) {
        estEnTrainDeDessiner = true;
        draw(controleur.getPrevX(), controleur.getPrevY());
    }

    @Override
    public void onMouseDrag(MouseEvent event) {
        controleur.getDessinController().setEpaisseur(controleur.getFigureCourante().getEpaisseur());
        controleur.getDessinController().setCouleur(controleur.getFigureCourante().getCouleur());
        controleur.getDessinController().trace(controleur.getPrevX(), controleur.getPrevY(), event.getX(), event.getY());
        controleur.getFigureCourante().addPoint(event.getX(), event.getY());

        controleur.setPrevX(event.getX());
        controleur.setPrevY(event.getY());

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
        Trace nouvelleTrace = new Trace(controleur.epaisseur.get(), controleur.couleur.get().toString(), x, y);
        controleur.setFigureCourante(nouvelleTrace);
        controleur.getDessin().addFigure(nouvelleTrace);
    }

    @Override
    public void draw(double x, double y, int epaisseur) {
        Trace nouvelleTrace = new Trace(epaisseur, controleur.couleur.get().toString(), x, y);
        controleur.setFigureCourante(nouvelleTrace);
        controleur.getDessin().addFigure(nouvelleTrace);
    }
}