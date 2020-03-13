public class Connexion{
  private String user;
  private String type;

  public Connexion(String user, String type){
    this.user = user;
    this.type = type;
  }
  public void getDescription(){
    System.out.println("Je suis une Connexion: "+user+" "+type);
  }
}
