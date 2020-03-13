// Fichier Complexe.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP2-2 ACD1
// Date : 27/02/2019

/** Définition d'un nombre complexe par sa partie réelle et sa partie imaginaire. */
class Complexe{

  /** Ecart pour tester si le complexe est un réel pur. */
  final double EPSILON = 0.001;

  /** Partie réelle du nombre complexe. */
  double a;
  /** Partie imaginaire du nombre complexe. */
  double b;

  /** Constructeur principal qui construit un complexe avec ses parties réelle et imaginaire. */
  Complexe(double a, double b){
    this.a = a;
    this.b = b;
  }

  /** Constructeur qui construit un complexe avec un module et un argument. */
  Complexe(double module, Angle argument){
    this(module * Math.cos(argument.enRadians()), module * Math.sin(argument.enRadians()));
  }

  /** Constructeur qui construit un réel pur. */
  Complexe(double a){
    this(a, 0);
  }

  /** Constructeur qui construit un imaginaire pur. */
  Complexe(Angle A){
    this(1, Math.sin(A.enRadians()));
  }

  /** Méthode retournant un tableau comprenant les parties réelles et imaginaires. */
  public double[] composantes(){
    double[] tab_valeurs = new double[2];
    tab_valeurs[0] = a;
    tab_valeurs[1] = b;
    return tab_valeurs;
  }

  /** Méthode retournant le module d'un nombre complexe. */
  public double module(){
    return (Math.sqrt(a * a + b * b));
  }

  /** Méthode retournant l'argument d'un nombre complexe. */
  public double argument(){
    int signe;
    if (b >= 0) signe = 1;
    else signe = -1;
    return (Math.acos(a / module()) * signe);
  }

  /** Méthode de test si le complexe est un réel pur. */
  public boolean estReelPur(){
    if (b < 0 + EPSILON || b > 0 - EPSILON) return (false);
    return (true);
  }

  /** Méthode de test si le complexe est un imaginaire pur. */
  public boolean estImaginairePur(){
    if (a < 0 + EPSILON || a > 0 - EPSILON) return (false);
    return (true);
  }

  /** Méthode retournant le conjugué du complexe. */
  public Complexe conjugue(){
    Complexe z = new Complexe(this.a, -this.b);
    return (z);
  }

  /** Méthode retournant un complexe étant la somme des deux complexes. */
  public Complexe somme(Complexe c){
    Complexe y = new Complexe(this.a + c.a, this.b + c.b);
    return (y);
  }

  /** Méthode retournant un complexe étant le produit des deux complexes. */
  public Complexe produit(Complexe c){
    Complexe x = new Complexe(this.a * c.a - this.b * c.b, this.a * c.b + c.a * this.b);
    return (x);
  }

  /** Méthode retournant le produit entre un complexe et un réel. */
  public Complexe produit(double x){
    Complexe w = new Complexe(this.a * x + this.b, this.a + x * this.b);
    return (w);
  }

  /** Méthode retournant l'inverse d'un complexe. */
  public Complexe inverse(){
    Complexe v = new Complexe(a / (a * a + b * b), -b / (a * a + b * b));
    return (v);
  }

  /** Mutateur permettant d'ajouter un complexe à un autre complexe. */
  public void ajouter(Complexe c){
    this.a += c.a;
    this.b += c.b;
  }

  /** Mutateur permettant de multiplier un complexe à un autre complexe. */
  public void multiplier(Complexe c){
    this.a = this.a * c.a + this.b * c.b;
    this.b = this.a * c.b + c.a * this.b;
  }

  /** Mutateur permettant de multiplier un complexe à un réel. */
  public void multiplier(double x){
    this.a = this.a * x + this.b;
    this.b = this.a + x * this.b;
  }

  /** Mutateur permettant d'inverser un complexe. */
  public void inverser(){
    this.a = a / (a * a + b * b);
    this.b = -b / (a * a + b * b);
  }

  /** Méthode toString pour un type complexe. */
  public String toString(){
    return ("<"+a+", "+b+">");
  }
}
