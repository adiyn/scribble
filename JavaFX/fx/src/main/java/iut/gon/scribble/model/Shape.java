package iut.gon.gribouille.modele;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 Stocke une figure
 */
public abstract class Figure {
  protected int epaisseur;
  protected String couleur;
  protected List<Point> points = new ArrayList<>();

  /**
   Crée une figure avec une épaisseur et une couleur
   */
  public Figure(int epaisseur, String couleur) {
    this.epaisseur = epaisseur;
    this.couleur = couleur;
  }

  /**
   Retourne l'épaisseur du tracé de la figure
   */
  public int getEpaisseur() {
    return epaisseur;
  }

  /**
   Retourne la couleur du tracé de la figure

   @return un int représentant la couleur de la figure
   */
  public String getCouleur() {
    return couleur;
  }

  /**
   Retourne les points de la figure dans l'ordre du tracé
   */
  public List<Point> getPoints() {
    return points;
  }

  /**
   Ajoute un nouveau point à la fin du tracé
   */
  public void addPoint(Point nouveau) {
    points.add(nouveau);
  }

  /**
   Ajoute un nouveau point à la fin du tracé
   */
  public void addPoint(double x, double y) {
    addPoint(new Point(x, y));
  }

  /**
   Crée une nouvelle figure qui continue la figure courante avec une nouvelle couleur
   */
  public abstract Figure changeCouleur(String nouvelleCouleur);

  /**
   Crée une nouvelle figure qui continue la figure courante avec une nouvelle couleur
   */
  public abstract Figure changeEpaisseur(int nouvelleEpaisseur);

  /** Crée une figure à partir d'une figure sérialisée.
   * @param scan un Scanner lisant la ligne décrivant la figure
   */
  Figure(Scanner scan) {
    epaisseur = scan.nextInt();
    couleur = scan.next();
    points = new ArrayList<>();
    int nb = scan.nextInt();
    while (nb > 0) {
      double x = scan.nextDouble();
      double y = scan.nextDouble();
      points.add(new Point(x, y));
      --nb;
    }
  }

  /** Crée une figure à partir d'une figure sérialisée.
   * @param line la ligne décrivant la figure
   */
  public static Figure charge(String line) {
    Scanner scan = new Scanner(line);
    scan.useLocale(java.util.Locale.US);
    switch (scan.next()) {
      case "T" : return new Trace(scan);
      case "E" : return new Etoile(scan);
      default: throw new IllegalArgumentException("Type de figure inconnu");
    }
  }

  /** Sauvegarde la figure sous forme d'une ligne de texte.
   * @param out le PrintWriter où la ligne doit être ajoutée
   */
  public void sauve(PrintWriter out) {
    out.printf("%d %s %d ",epaisseur, couleur, points.size());
    points.forEach(p->out.printf("%.1f %.1f ",p.getX(), p.getY()));
  }

} // public class Figure
