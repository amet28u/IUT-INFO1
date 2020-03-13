public class SGBDOracle extends SGBD{

  public Connexion creerConnexion(String user, String type){
    Connexion c = new Connexion(user, type);
    return c;
  }
}
