package oving3.debugging;

import java.util.Random;

public class CoffeeCupProgram {

	private CoffeeCup cup;
	private Random r;

	public void init() {
		cup = new CoffeeCup();
		r = new Random(123456789L);
	}

	public void run() {
		//part1();
		part2();
	}

	private void part1() {
		cup.increaseCupSize(40.0);
		cup.fillCoffee(20.5);
		cup.drinkCoffee(Math.floor(r.nextDouble() * 20.5));
		cup.fillCoffee(32.5);
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 38.9));
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 42));
		cup.increaseCupSize(17);
		cup.drinkCoffee(40);
		// Feilen skyldes at canDrink funksjonen returner false ettersom this.currentVolume < volume, da thrower drinkCoffe funksjonen IllegalArgumentException. 
		// Volum = 40.0 , currentVolume = 5.0 , capacity = 57.0
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 42));
		cup.drinkCoffee(Math.floor(r.nextDouble() * 20.5));
		cup.fillCoffee(32.5);
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 38.9));
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 42));
		cup.increaseCupSize(17);
	}

	private void part2() {
		cup = new CoffeeCup(40.0, 20.5);
		r = new Random(987654321L);
		cup.drinkCoffee(Math.floor(r.nextDouble() * 20.5));
		cup.fillCoffee(Math.floor(r.nextDouble() * 30));
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 38.9));
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 42));
		cup.increaseCupSize(Math.floor(r.nextDouble() * 26));
		cup.fillCoffee(Math.ceil(r.nextDouble() * 59));
		// Det er isValidVolume() som returner false, som gjør at fillCoffe() utløser unntak av typen InvalidArgumentException.
		cup.drinkCoffee(Math.ceil(r.nextDouble() * 42));
		cup.increaseCupSize(Math.floor(r.nextDouble() * 35));
		cup.fillCoffee(Math.floor(r.nextDouble() * 30));
		cup.increaseCupSize(Math.floor(r.nextDouble() * 26));
		//Verdier separert med komma: 40, 14.5, 38.5, 36.5, 6.5. 
	}

	public static void main(String[] args) {
		CoffeeCupProgram program = new CoffeeCupProgram();
		program.init();
		program.run();
	}

}
