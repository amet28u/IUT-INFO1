public class RegisterWindow{

  private volatile static RegisterWindow instance;
  private static String nomregistre;

  private RegisterWindow(String nom){
    nomregistre = nom;
  }

  public static RegisterWindow getInstance(String nom){
    if (instance == null){
      instance = new RegisterWindow(nom);
    }
    return instance;
  }

  public String toString(){
    return "Instance de "+RegisterWindow.nomregistre;
  }
}
