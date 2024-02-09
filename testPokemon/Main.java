package testPokemon;

public class Main {

	public static void main(String[] args) {
		
		User user = new User();
		
		user.pocket[0] = new Eevee();
		user.pocket[1] = new Eevee("Brown");
		user.pocket[2] = new Vaporeon("Blue");

		user.goToPokemonCenter();
		user.viewPartyStatus();
		
	}

}
