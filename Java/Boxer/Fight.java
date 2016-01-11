import java.io.*;

class Fight{
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException{
		boolean correctInput = true;
		Boxer boxer1 = null, boxer2 = null;
		
		try {
			boxer1 = new Boxer(args[0]);
		}
		catch(TotalPointsException tpe) {
			correctInput = false;
			System.out.println("Somma totale dei valori del Boxer " + args[0] + " non uguale a 200");
		}
		try {
			boxer2 = new Boxer(args[1]);
		}
		catch(TotalPointsException tpe) {
			correctInput = false;
			System.out.println("Somma totale dei valori del Boxer " + args[1] + " non uguale a 200");
		}
		if (correctInput) {
			System.out.println(boxer1.getName() + "\t" + boxer2.getName());
			while(!boxer1.isKO() && !boxer2.isKO()){				//se almeno uno dei due e' KO allora esco dal while e termino l'esecuzione del combattimento
				System.out.println(boxer1.getHP() + "\t\t" + boxer2.getHP());
				Thread.sleep(1000);
				System.out.println(boxer1.cambiaStato() + " \t" +boxer2.cambiaStato());
				boxer1.fight(boxer2);
			}
			System.out.println(boxer1.getName() + ": " + boxer1.getHP() + "\n" + boxer2.getName() + ": " + boxer2.getHP());
			if (boxer1.isKO() && boxer2.isKO())
				System.out.println("Pareggio: nessun vincitore");
			else {
				System.out.println("Ha vinto " + (boxer1.isKO() ? boxer2.getName() : boxer1.getName()));
			}
		}
	}
}
