// Fichier Angle.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP2-1 ACD1
// Date : 27/02/2019



/** Définition d'un type angle par la valeur de son angle. */
public class Angle{

  /** Valeur de l'angle en radians. */
  private double rad;

  /** Constructeur principal qui construit un angle en radians. */
  Angle(double x){
    normer(x);
  }

  /** Constructeur qui construit un angle en degrés. */
  Angle(int a){
    this((double)a);
  }

  /** Constructeur par recopie d'un autre angle. */
  Angle(Angle A){
    this(A.rad);
  }

  /** Méthode de normalisation pour convertir un réel en un radian. */
  private void normer(double x){
    while (x <= -Math.PI){
      x += Math.PI;
    }
    while (x > Math.PI){
      x -= Math.PI;
    }
    rad = x;
  }

  /** Méthode retournant la valeur en radian de l'angle. */
  public double enRadians(){
    return (rad);
  }

  /** Méthode retournant la valeur en degrés de l'angle. */
  public int enDegres(){
    return (int)(((180 * rad) / Math.PI) + 0.5);
  }

  /** Méthode retournant la somme en radians de deux angles. */
  public double somme(Angle A){
    return (this.rad + A.rad);
  }

  /** Méthode retournant l'inverse d'un angle en radians. */
  public double inverse(){
    return (-this.rad);
  }

  /** Mutateur permettant d'ajouter une valeur d'angle à un angle. */
  public void ajouter(Angle A){
     this.rad += A.rad;
  }

  /** Mutateur permettant de remplacer la valeur de l'angle en radians par son opposé. */
  public void opposer(){
    if (rad < 0){
      rad += (Math.PI / 2);
    }
    else{
      rad -= (Math.PI / 2);
    }
  }

  /** Méthode toString pour le type angle. */
  public String toString(){
    return ("<"+rad+">");
  }
}
