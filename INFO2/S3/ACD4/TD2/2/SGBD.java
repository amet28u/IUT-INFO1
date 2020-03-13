import java.util.*;

public class SGBD{

  private static ArrayList<Connexion> list;

  public SGBD(){
    list = new ArrayList<Connexion>();
  }

  public SGBD commanderConnexion(String type){
    SGBD sgbd = new SGBD();
    if (type.equals("Oracle")){
      sgbd = new SGBDOracle();
    }
    if (type.equals("MySQL")){
      sgbd = new SGBDSql();
    }
    return sgbd;
  }

  public Connexion creerConnexion(String user, String type){
    if (type.equals("Oracle")){
      SGBDSql sql = new SGBDSql();
      SGBD.list.add(sql.creerConnexion(user, type));
      return sql.creerConnexion(user, type);
    }
    if (type.equals("MySQL")){
      SGBDOracle oracle = new SGBDOracle();
      SGBD.list.add(oracle.creerConnexion(user, type));
      return oracle.creerConnexion(user, type);
    }
    return (Connexion)null;
  }
}
