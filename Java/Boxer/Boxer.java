import java.util.Random;
import java.io.*;

enum Condizione {
		SCOPERTO,
		GUARDIA,
		JABBING,
		STRAIGHT
}

class Boxer {
	private String name = null;
	private int maxDefence = 0;
	private int maxDamage = 0;
	private int hP = 0;
	private Random random = null;
	private Dado dado = null;
	private Condizione condizione;
	private int[][] passaggiDiStato;
	
	public Boxer(String _name) throws FileNotFoundException, IOException, TotalPointsException{
		String line;
		String[] values;
		
		name = _name;
		BufferedReader reader = new BufferedReader(new FileReader(name + ".txt")); //FileNotFoundException
		
		
		line = reader.readLine();	//IOException
		values = line.split(" ");
		hP = Integer.parseInt(values[0]);
		maxDamage = Integer.parseInt(values[1]);
		maxDefence = Integer.parseInt(values[2]);
		
		if (hP + maxDamage + maxDefence != 200) {
			throw new TotalPointsException();
		}
		
		passaggiDiStato = new int[4][4];
		line = reader.readLine();
		int row = 0, col;
		while(row < 4) {
			values = line.split(" ");
			for(col = 0;col < 4;col++) {
				passaggiDiStato[row][col] = Integer.parseInt(values[col]);
			}
			row++;
		}
		random = new Random();
		condizione = Condizione.SCOPERTO;
		dado = new Dado(100);
		
	}
	public Boxer(String name, int hP, int maxDamage, int maxDefence) {
		this.name = name;
		this.maxDamage = maxDamage;
		this.maxDefence = maxDefence;
		this.hP = hP;
		random = new Random();
		condizione = Condizione.SCOPERTO;
		dado = new Dado(100);
		passaggiDiStato = new int[][]{
			{10, 40, 20, 30},	/*Scoperto*/
			{0, 30, 50, 20},	/*Guardia*/
			{10, 40, 30, 20},	/*Jabbing*/
			{60, 10, 10, 20},	/*Straight*/
		};
	}
	public String cambiaStato() {
		int i = condizione.ordinal(), j, somma = 0;
		int casuale = dado.lancio();

		for(j = 0;j < 4 && casuale >= somma;j++) {
			somma += passaggiDiStato[i][j];
		}
		condizione = Condizione.values()[j - 1];
		return condizione.toString() + "  ";
	}
	public void fight(Boxer b2) {
		switch(this.getStato()) {
			case SCOPERTO:
				switch(b2.getStato()) {
					/*case Condizione.SCOPERTO:
					case Condizione.GUARDIA:
						break;*/
					case JABBING:
						sufferJabbing(0);
						break;
					case STRAIGHT:
						suffer(b2.straight(), 0);
						break;
				}
				break;
			case GUARDIA:
				switch(b2.getStato()) {
					case JABBING:
						sufferJabbing(maxDefence);
						break;
					case STRAIGHT:
						suffer(b2.straight(), maxDefence);
						break;
				}
				break;
			case JABBING:
				switch(b2.getStato()) {
					case SCOPERTO:
						b2.sufferJabbing(0);
						break;
					case GUARDIA:
						b2.sufferJabbing(b2.getDefence());
						break;
					case JABBING:
						b2.sufferJabbing(b2.getDefence() / 2);
						sufferJabbing(maxDefence / 2);
						break;
					case STRAIGHT:
						b2.sufferJabbing(0);
						suffer(b2.straight(), 0);
						break;
				}
				break;
			case STRAIGHT:
				switch(b2.getStato()) {
					case SCOPERTO:
						b2.suffer(straight(), 0);
						break;
					case GUARDIA:
						b2.suffer(straight(), b2.getDefence());
						break;
					case JABBING:
						b2.suffer(straight(), 0);
						sufferJabbing(0);
						break;
					case STRAIGHT:
						b2.suffer(straight(), 0);
						suffer(b2.straight(), 0);
						break;
				}
				break;
		}
	}
	public Condizione getStato() {
		return Condizione.values()[condizione.ordinal()];
	}
	public int hook() {
		return 1;
	}
	public int straight() {
		return random.nextInt(maxDamage + 1);
	}
	public int uppercut() {
		return 1;
	}
	public boolean sufferJabbing(int maxDefence) {
		int tempMaxDamage = ((maxDamage / 4 > 0) ? maxDamage / 4 : 1);
		int i, n = random.nextInt(tempMaxDamage + 1) + 1;

		for(i = 0;i < n;i++)
			suffer(random.nextInt(tempMaxDamage + 1), maxDefence);
		return isKO();
	}
	public boolean suffer(int damage, int maxDefence) {
		int realDamage = damage - ((maxDefence != 0) ? random.nextInt(maxDefence) : 0);
		hP -= (realDamage >= 0) ? realDamage : 0;
		return isKO();
	}
	public boolean isKO() {
		return (hP <= 0);
	}
	public String getName() {
		return name;
	}
	public int getDefence() {
		return maxDefence;
	}
	public int getDamage() {
		return maxDamage;
	}
	public int getHP() {
		return hP;
	}
}
