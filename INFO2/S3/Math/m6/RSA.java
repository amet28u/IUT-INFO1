import java.math.BigInteger;

public class RSA{
  public String chiffrement(String message, ClePublique c){
    char[] parts = message.toCharArray(); // String en caractère
    for (int i = 0; i < parts.length; i++){
      System.out.print(parts[i]+",");
    }
    System.out.println("parts --- chaque char en une valeur du tableau");

    int[] intMessage = new int[parts.length]; // caractère en entier
    for (int i = 0; i < parts.length; i++){
        intMessage[i] = (int)parts[i];
    }
    for (int i = 0; i < parts.length; i++){
      System.out.print(intMessage[i]+",");
    }
    System.out.println("intMessage --- transformation char en int via cast");

    // Mettre chaque entier dans un format max qu'on applique à tout les entiers
    // pour pouvoir chiffrer le nombre global et non chaque entiers, car cela
    // met des memes valeurs chiffree pour chaque meme valeur non chiffree
    //groupe de 3 avec ajout de 0 inutile
    int size1 = (parts.length)*6;
    int[] tabbloc3 = new int[size1];
    int j = 0;
    for (int i = 0; i < parts.length; i++){
      int tmp1, tmp2, tmp3, tmp4, tmp5;
      tabbloc3[j] = (int)((intMessage[i])/100000); //10^5
      tmp1 = (int)((intMessage[i])/100000);
      tabbloc3[j+1] = (int)((intMessage[i]-(tmp1*100000))/10000); //10^4
      tmp2 = (int)((intMessage[i]-(tmp1*100000))/10000);
      tabbloc3[j+2] = (int)((intMessage[i]-(tmp1*100000+tmp2*10000))/1000); //10^3
      tmp3 = (int)((intMessage[i]-(tmp1*100000+tmp2*10000))/1000);
      tabbloc3[j+3] = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000))/100); //10^2
      tmp4 = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000))/100);
      tabbloc3[j+4] = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000+tmp4*100))/10); //10^1
      tmp5 = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000+tmp4*100))/10);
      tabbloc3[j+5] = (int)(intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000+tmp4*100+tmp5*10));
      j+=6;
      /*tabbloc3[j] = (int)((intMessage[i])/100); //chiffre 10^2
      tmp1 = (int)((intMessage[i])/100);
      tabbloc3[j+1] = (int)((intMessage[i]-(tmp1*100))/10); //chiffre 10^1
      tmp2 = (int)((intMessage[i]-(tmp1*100))/10);
      tabbloc3[j+2] = (int)(intMessage[i]-(tmp1*100+tmp2*10)); //chiffre 10^0
      j+=3;*/
    }
    for (int i = 0; i < size1; i++){
      System.out.print(tabbloc3[i]+",");
    }
    System.out.println("tabbloc3 --- split des chiffres par groupe de 6");

    int size2;
    if (size1%8 == 0){
      size2 = (size1/8);
    }
    else{
      size2 = (size1/8)+1;
    }
    j = 0;
    BigInteger[] tabbloc4 = new BigInteger[size2];
    for (int i = 0; i < tabbloc4.length; i++){
      if (j <= (size1-8)){
        tabbloc4[i] = tabbloc3[j]*10000000+tabbloc3[j+1]*1000000+tabbloc3[j+2]*100000+tabbloc3[j+3]*10000+tabbloc3[j+4]*1000+tabbloc3[j+5]*100+tabbloc3[j+6]*10+tabbloc3[j+7];
      }
      else{
        if (size1%8 == 1){
          tabbloc4[i] = tabbloc3[j];
        }
        else{
          if (size1%8 == 2){
            tabbloc4[i] = tabbloc3[j]*10+tabbloc3[j+1];
          }
          else{
            if (size1%8 == 3){
              tabbloc4[i] = tabbloc3[j]*100+tabbloc3[j+1]*10+tabbloc3[j+2];
            }
            else{
              if (size1%8 == 4){
                tabbloc4[i] = tabbloc3[j]*1000+tabbloc3[j+1]*100+tabbloc3[j+2]*10+tabbloc3[j+3];
              }
              else{
                if (size1%8 == 5){
                  tabbloc4[i] = tabbloc3[j]*10000+tabbloc3[j+1]*1000+tabbloc3[j+2]*100+tabbloc3[j+3]*10+tabbloc3[j+4];
                }
                else{
                  if (size1%8 == 6){
                    tabbloc4[i] = tabbloc3[j]*100000+tabbloc3[j+1]*10000+tabbloc3[j+2]*1000+tabbloc3[j+3]*100+tabbloc3[j+4]*10+tabbloc3[j+5];
                  }
                  else{
                    if (size1%8 == 7){
                      tabbloc4[i] = tabbloc3[j]*1000000+tabbloc3[j+1]*100000+tabbloc3[j+2]*10000+tabbloc3[j+3]*1000+tabbloc3[j+4]*100+tabbloc3[j+5]*10+tabbloc3[j+6];
                    }
                  }
                }
              }
            }
          }
        }
      }
      j+=8;
    }


    for (int i = 0; i < tabbloc4.length; i++){
      System.out.print(tabbloc4[i]+",");
    }
    System.out.println("tabbloc4 --- regroupement par 8");


    BigInteger[] chiffree = new BigInteger[size2]; // chiffrement des entiers
    for (int i = 0; i < size2; i++){
      chiffree[i] = (BigInteger)(Math.pow(tabbloc4[i], c.getE())%c.getN());
    }
    for (int i = 0; i < size2; i++){
      System.out.print(chiffree[i]+",");
    }
    System.out.println("chiffree --- chiffrement des groupes");

    char[] messageChiffree = new char[size2]; // entier en caractère
    for (int i = 0; i < size2; i++){
      messageChiffree[i] = (char)chiffree[i];
    }
    for (int i = 0; i < size2; i++){
      System.out.print(messageChiffree[i]+",");
    }
    System.out.println("messageChiffree --- transformation int en char via unicode");

    String retour = ""; // caractère en String
    for (int i = 0; i < size2; i++){
      retour = retour + messageChiffree[i];
    }
    return retour;
  }




  public String dechiffrement(String message, ClePrivee c){
    char[] parts = message.toCharArray(); // String en caractère
    for (int i = 0; i < parts.length; i++){
      System.out.print(parts[i]+",");
    }
    System.out.println("parts --- chaque char en une valeur du tableau");

    int[] intMessage = new int[parts.length]; // caractère en entier
    for (int i = 0; i < parts.length; i++){
        intMessage[i] = (int)parts[i];
    }
    for (int i = 0; i < parts.length; i++){
      System.out.print(intMessage[i]+",");
    }
    System.out.println("intMessage --- transformation char en int via cast");

    // Mettre chaque entier dans un format max qu'on applique à tout les entiers
    // pour pouvoir chiffrer le nombre global et non chaque entiers, car cela
    // met des memes valeurs chiffree pour chaque meme valeur non chiffree
    //groupe de 3 avec ajout de 0 inutile
    int size1 = (parts.length)*6;
    int[] tabbloc3 = new int[size1];
    int j = 0;
    for (int i = 0; i < parts.length; i++){
      int tmp1, tmp2, tmp3, tmp4, tmp5;
      tabbloc3[j] = (int)((intMessage[i])/100000); //10^5
      tmp1 = (int)((intMessage[i])/100000);
      tabbloc3[j+1] = (int)((intMessage[i]-(tmp1*100000))/10000); //10^4
      tmp2 = (int)((intMessage[i]-(tmp1*100000))/10000);
      tabbloc3[j+2] = (int)((intMessage[i]-(tmp1*100000+tmp2*10000))/1000); //10^3
      tmp3 = (int)((intMessage[i]-(tmp1*100000+tmp2*10000))/1000);
      tabbloc3[j+3] = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000))/100); //10^2
      tmp4 = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000))/100);
      tabbloc3[j+4] = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000+tmp4*100))/10); //10^1
      tmp5 = (int)((intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000+tmp4*100))/10);
      tabbloc3[j+5] = (int)(intMessage[i]-(tmp1*100000+tmp2*10000+tmp3*1000+tmp4*100+tmp5*10));
      j+=6;
      /*tabbloc3[j] = (int)((intMessage[i])/100); //chiffre 10^2
      tmp1 = (int)((intMessage[i])/100);
      tabbloc3[j+1] = (int)((intMessage[i]-(tmp1*100))/10); //chiffre 10^1
      tmp2 = (int)((intMessage[i]-(tmp1*100))/10);
      tabbloc3[j+2] = (int)(intMessage[i]-(tmp1*100+tmp2*10)); //chiffre 10^0
      j+=3;*/
    }
    for (int i = 0; i < size1; i++){
      System.out.print(tabbloc3[i]+",");
    }
    System.out.println("tabbloc3 --- split des chiffres par groupe de 6");

    int size2;
    if (size1%8 == 0){
      size2 = (size1/8);
    }
    else{
      size2 = (size1/8)+1;
    }
    j = 0;
    int[] tabbloc4 = new int[size2];
    for (int i = 0; i < tabbloc4.length; i++){
      if (j <= (size1-8)){
        tabbloc4[i] = tabbloc3[j]*10000000+tabbloc3[j+1]*1000000+tabbloc3[j+2]*100000+tabbloc3[j+3]*10000+tabbloc3[j+4]*1000+tabbloc3[j+5]*100+tabbloc3[j+6]*10+tabbloc3[j+7];
      }
      else{
        if (size1%8 == 1){
          tabbloc4[i] = tabbloc3[j];
        }
        else{
          if (size1%8 == 2){
            tabbloc4[i] = tabbloc3[j]*10+tabbloc3[j+1];
          }
          else{
            if (size1%8 == 3){
              tabbloc4[i] = tabbloc3[j]*100+tabbloc3[j+1]*10+tabbloc3[j+2];
            }
            else{
              if (size1%8 == 4){
                tabbloc4[i] = tabbloc3[j]*1000+tabbloc3[j+1]*100+tabbloc3[j+2]*10+tabbloc3[j+3];
              }
              else{
                if (size1%8 == 5){
                  tabbloc4[i] = tabbloc3[j]*10000+tabbloc3[j+1]*1000+tabbloc3[j+2]*100+tabbloc3[j+3]*10+tabbloc3[j+4];
                }
                else{
                  if (size1%8 == 6){
                    tabbloc4[i] = tabbloc3[j]*100000+tabbloc3[j+1]*10000+tabbloc3[j+2]*1000+tabbloc3[j+3]*100+tabbloc3[j+4]*10+tabbloc3[j+5];
                  }
                  else{
                    if (size1%8 == 7){
                      tabbloc4[i] = tabbloc3[j]*1000000+tabbloc3[j+1]*100000+tabbloc3[j+2]*10000+tabbloc3[j+3]*1000+tabbloc3[j+4]*100+tabbloc3[j+5]*10+tabbloc3[j+6];
                    }
                  }
                }
              }
            }
          }
        }
      }
      j+=8;
    }


    for (int i = 0; i < tabbloc4.length; i++){
      System.out.print(tabbloc4[i]+",");
    }
    System.out.println("tabbloc4 --- regroupement par 8");

    int[] dechiffree = new int[size2]; // dechiffrement des entiers
    for (int i = 0; i < size2; i++){
      dechiffree[i] = (int)(Math.pow(tabbloc4[i], c.getD())%c.getN());
    }
    for (int i = 0; i < size2; i++){
      System.out.print(dechiffree[i]+",");
    }
    System.out.println("dechiffree --- dechiffrement des groupes");

    char[] messagedeChiffree = new char[size2]; // entier en caractère
    for (int i = 0; i < size2; i++){
      messagedeChiffree[i] = (char)dechiffree[i];
    }
    for (int i = 0; i < size2; i++){
      System.out.print(messagedeChiffree[i]+",");
    }
    System.out.println("messagedeChiffree --- transformation int en char via unicode");

    String retour = ""; // caractère en String
    for (int i = 0; i < size2; i++){
      retour = retour + messagedeChiffree[i];
    }
    return retour;
  }

  //public void genererCle()
}
