package iut.gon.gribouille.modele;

/**
 Stocke un point
 */
public class Point {
  private double x;
  private double y;

  /**
   Crée un point à partir de son abscisse et son ordonnée
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   Récupère l'abscisse du point
   */
  public double getX() {
    return x;
  }

  /**
   Modifie l'abscisse du point
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   Récupère l'ordonnée du point
   */
  public double getY() {
    return y;
  }

  /**
   Modifie l'ordonnée du point
   */
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return String.format("(%.1f, %.1f)", x, y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Point point = (Point) o;

    if (Double.compare(point.x, x) != 0) return false;
    return Double.compare(point.y, y) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
} // public class Point
