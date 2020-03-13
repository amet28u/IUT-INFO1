public class Test{

  public static void main(String[] args){
    RegisterWindow r = RegisterWindow.getInstance("monregistre");
    System.out.println(r);

    RegisterWindow f = RegisterWindow.getInstance("ReGiStRe 1");
    System.out.println(f);

    RegisterWindow j = RegisterWindow.getInstance("Me LlAmO aNtOnY");
    System.out.println(j);
  }
}
