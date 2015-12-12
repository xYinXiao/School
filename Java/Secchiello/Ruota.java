import java.util.Vector;

class Ruota extends Secchiello {
  private String nome = null;

  public Ruota(String nome) {
    super();
    super.add(5, 90);
    this.nome = nome;
  }

  public Vector <Integer> rovesciaVector() {
    Vector <Integer> ret = new Vector <Integer>();
    int i, j;
    boolean tro;

    ret = super.rovesciaVector();

    tro = true;
    while(tro) {
      tro = false;
      for(i = ret.size() - 1;i > 0 && !tro;i--) {
        if (ret.indexOf(ret.get(i)) != i) {
          tro = true;
          ret.set(i, dadi.get(i).lancio());
        }
      }
    }
    return ret;
  }
  public boolean haiVinto(Ambo ambo) {
    Vector <Integer> temp = leggiVector();
    return ((temp.indexOf(ambo.getA()) != -1) && (temp.indexOf(ambo.getB()) != -1));
    //Oppure (temp.contains(ambo.getA) && temp.contains(ambo.getB))
  }

  public String getNome() {
    return nome;
  }
}