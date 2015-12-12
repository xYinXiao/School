import java.util.Vector;

class Secchiello {
	protected Vector<Dado> dadi = null;

	public void add(Dado dado) {
		dadi.add(dado);
	}
	
	public void add(int nFacce) {
		add(new Dado(nFacce));
	}

	public void add(int nDadi, int nFacce) {
		for(int k = 0;k < nDadi;k++)
			add(nFacce);
	}

	public Secchiello() {
		dadi = new Vector<Dado>();
	}

	public void svuota() {
		dadi.clear();
		System.out.println("\nSecchiello svuotato!");
	}

  public int leggi() {
    int somma = 0;

		for(int i = 0;i < dadi.size();i++) {
			somma += dadi.get(i).getLast(); //Boxing implicito. Primitivo -> Tipo di dato predefinito
			//ret.add(new Integer(dadi.get(i).lancia()));
		}
		return somma;
  }

	public int rovescia() {
		int i, somma = 0;

		for(i = 0;i < dadi.size();i++) {
			somma += dadi.elementAt(i).lancio();
		}
		return somma;
	}

  public Vector <Integer> leggiVector() {
    Vector<Integer> ret;

		ret = new Vector<Integer>();
		for(int i = 0;i < dadi.size();i++) {
			ret.add(dadi.get(i).getLast()); //Boxing implicito. Primitivo -> Tipo di dato predefinito
			//ret.add(new Integer(dadi.get(i).lancia()));
		}
		return ret;
  }

	//Java non controlla il tipo restituito nell'overloading
	public Vector <Integer> rovesciaVector() {
		Vector<Integer> ret;

		ret = new Vector<Integer>();
		for(int i = 0;i < dadi.size();i++) {
			ret.add(dadi.get(i).lancio()); //Boxing implicito. Primitivo -> Tipo di dato predefinito
			//ret.add(new Integer(dadi.get(i).lancia()));
		}
		return ret;
	}
}
