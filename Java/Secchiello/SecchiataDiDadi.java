import java.util.Random;
import java.math.BigInteger;

class SecchiataDiDadi {
	public static void main(String[] args) {
		Ruota ruota = new Ruota("Caraglio");
		Random random = new Random();
		Ambo ambo;
		BigInteger bet = BigInteger.valueOf(1);
		BigInteger spent = BigInteger.valueOf(1);
		int a, b;
		int count = 0;

		a = random.nextInt(90) + 1;
		b = random.nextInt(90) + 1;
		ambo = new Ambo(a, b);
		ruota.rovesciaVector();
		System.out.println("Ruota: " + ruota.leggiVector() + " Scommessa: " + bet);
		count++;
		while(!ruota.haiVinto(ambo)) {
			bet = bet.multiply(BigInteger.valueOf(2));
			spent = spent.add(bet);
			ruota.rovesciaVector();
			System.out.println("Ruota: " + ruota.leggiVector() + " Scommessa: " + bet);
			count++;
		}
		System.out.println("Ambo: " + ambo.getA() + " " + ambo.getB());
		System.out.println("Spesa: " + spent + "\nGuadagno: " + bet.multiply(BigInteger.valueOf(250)).subtract(spent));
	}
}
