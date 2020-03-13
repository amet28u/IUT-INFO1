public class MethodeRecurs{

  /*public int sommePremiersCarres(int n) throws IllegalArgumentException {
    if (n < 0){
      throw new IllegalArgumentException("Warning: n must be >= 0 !");
    }
    if (n == 0){
      return 0;
    }
    return (n*n) + sommePremiersCarres(n-1);
  }*/

  /*public int sommePositifs(int[] t, int i) throws IllegalArgumentException {
    if (i < 0 || i > t.length)
      throw new IllegalArgumentException("Warning: n must be >= 0  and < t.length !");
    if (i == t.length)
      return 0;
    if (t[i] < 0)
      return sommePositifs(t, i+1);
    return t[i] + sommePositifs(t, i+1);
  }*/

  /*public boolean palindrome(String word, int i) throws IllegalArgumentException {
    if (word.length() == 0 || i < 0 || i > word.length()/2){
      throw new IllegalArgumentException("Warning: not empty String, i > 0, i too tall !");
    }
    if (i == word.length()/2){
      return true;
    }
    if (word.charAt(i) == word.charAt(word.length()-1-i)){
      return true && palindrome(word, i+1);
    }
    return false;
  }*/

  public void inverseTab(int[] tab, int i){
    if (i<tab.length/2){
      int tmp = tab[i];
      tab[i] = tab[tab.length-1-i];
      tab[tab.length-1-i] = tmp;
      inverseTab(tab, i+1);
    }
  }

  public static void main(String[] args){

    MethodeRecurs M = new MethodeRecurs();

    /*int x = M.sommePremiersCarres(2);
    System.out.println(x);*/

    /*int[] t = {-1, 2};
    int n = M.sommePositifs(t, 0);
    System.out.println(n);*/

    /*System.out.println(M.palindrome("madam", 0));*/

    int[] tab = {1, 2, 3, 4};
    for (int i = 0; i < tab.length; i++){
      System.out.println(tab[i]);
    }
    M.inverseTab(tab, 0);
    for (int i = 0; i < tab.length; i++){
      System.out.println(tab[i]);
    }

  }
}
