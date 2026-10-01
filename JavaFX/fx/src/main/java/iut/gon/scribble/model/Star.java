package iut.gon.gribouille.modele;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 Stocke une Figure "Etoile"
 */
public class Etoile extends Figure {
  /**
   Le point central de l'étoile
   */
  private Point centre;

  /**
   Crée une étoile avec une épaisseur, une couleur et un centre
   */
  public Etoile(int epaisseur, String couleur, double xc, double yc) {
    super(epaisseur, couleur);
    centre = new Point(xc, yc);
  }

  /**
   Retourne le point central de l'étoile
   */
  public Point getCentre() {
    return centre;
  }

  public Figure changeCouleur(String nouvelleCouleur) {
    return new Etoile(epaisseur, nouvelleCouleur, centre.getX(), centre.getY());
  }

  public Figure changeEpaisseur(int nouvelleEpaisseur) {
    return new Etoile(nouvelleEpaisseur, couleur, centre.getX(), centre.getY());
  }

  /** Crée une étoile à partir d'une ligne de texte.
   * @param scan le Scanner lisant la sérialisation de la figure
   */
  Etoile(Scanner scan) {
    super(scan);
    double x = scan.nextDouble();
    double y = scan.nextDouble();
    centre = new Point(x, y);
  }

  @Override
  public void sauve(PrintWriter out) {
    out.print("E ");
    super.sauve(out);
    out.printf("%.1f %.1f\n",centre.getX(), centre.getY());
  }

}
