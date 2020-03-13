public class Test{
  public static void main(String[] args){
    SGBD sgbd = new SGBD();
    SGBD sgbd1 = new SGBD();
    Connexion con, con1;
    sgbd = new SGBDOracle();
    con = sgbd.creerConnexion("admin","pass");
    con.getDescription();
    sgbd1 = new SGBDSql();
    con1 = sgbd1.creerConnexion("root","sql");
    con1.getDescription();
  }
}
