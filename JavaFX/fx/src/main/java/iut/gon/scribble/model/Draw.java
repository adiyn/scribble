package iut.gon.gribouille.modele;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 Stocke un dessin constitué de figures
 */
public class Dessin {
  /**
   * fichier : il me manquait ça, j'utilise File pour l'instant,
   * peut-être que je changerait pour SimpleObjectProperty
   */
  private File fichier;
  /**
   Indique si le Dessin a été modifié depuis la dernière sauvegarde
   */
  private final SimpleBooleanProperty estModifie = new SimpleBooleanProperty(false);

  /**
   Le nom du fichier. Ou "Sans Nom", si non sauvegardé.
   */
  private final SimpleStringProperty nomDuFichier = new SimpleStringProperty("Sans Nom");
  /**
   La liste des figures
   */
  private List<Figure> figures;

  /**
   Crée un dessin vide sans nom
   */
  public Dessin() {
    figures = FXCollections.observableArrayList();
  }

  /**
   Retourne La liste des figures
   */
  public List<Figure> getFigures() {
    return figures;
  }

  /**
   Modifie La liste des figures
   */
  public void setFigures(List<Figure> figures) {
    this.figures = figures;
  }

  /**
   Ajoute une nouvelle figure
   */
  public void addFigure(Figure figure) {
    figures.add(figure);
    estModifie.set(true);
  }

  /**
   Teste si le dessin a été modifié depuis la dernière sauvegarde
   */
  public boolean getEstModifie() {
    return estModifie.get();
  }

  /**
   Modifie l'état de modification du dessin
   */
  public void setEstModifie(boolean valeur) {
    estModifie.set(valeur);
  }

  /**
   Teste si le dessin a été modifié depuis la dernière sauvegarde
   */
  public BooleanExpression estModifieProperty() {
    return estModifie;
  }

  /**
   Retourne le nom du fichier
   */
  public String getNomDuFichier() {
    return nomDuFichier.get();
  }

  /**
   Retourne le nom du fichier
   */
  public void setNomDuFichier(String nomDuFichier) {
    this.nomDuFichier.set(nomDuFichier);
  }

  /**
   Le nom du fichier. Ou "Sans Nom", si non sauvegardé.
   */
  public StringExpression nomDuFichierProperty() {
    return nomDuFichier;
  }

  /** Sauvegarde le dessin dans le fichier spécifié.
   *  Change le nom du dessin si succès.
   */
  public void sauveSous(String nom) {
    try {
      File nouveauFichier = new File(nom);
      PrintWriter out = new PrintWriter(nouveauFichier);
      figures.forEach(f -> f.sauve(out));
      out.close();

      this.fichier = nouveauFichier;
      this.nomDuFichier.set(nouveauFichier.getName());

      estModifie.set(false);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /** Charge le dessin depuis le fichier spécifié.
   *  Change le nom du dessin si succès.
   * @throws IllegalArgumentException si le fichier est illisible
   */
  public void charge(String nom) {
    File nouveauFichier = new File(nom);
    if (nouveauFichier.exists() && nouveauFichier.canRead()) {
      figures.clear();
      try {
        Scanner scanner = new Scanner(nouveauFichier);
        scanner.useLocale(java.util.Locale.US);
        while (scanner.hasNextLine()) {
          String ligne = scanner.nextLine();
          if (! ligne.isBlank())
            figures.add(Figure.charge(ligne));
        }
        this.fichier = nouveauFichier;
        this.nomDuFichier.set(nouveauFichier.getName()); // >:(
        estModifie.set(false);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Ce fichier est introuvable !");
      }
    } else {
      throw new IllegalArgumentException("Ce fichier est illisible !");
    }
  }

} // public class Dessin
