import java.util.Random;

class Dado {
	private int nFacce;
	private Random rand = null;
	private int lastLancio = 0;

	public Dado() {
		this.nFacce = 6;
		rand = new Random();
	}

	public Dado(int _nFacce) {
		nFacce = _nFacce;
		rand = new Random();
	}

	public int lancio() {
		lastLancio = rand.nextInt(nFacce) + 1;
		return lastLancio;
	}

	public int getLast() {
		return lastLancio;
	}
}
