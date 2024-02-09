package testPokemon;

public class Main {

	public static void main(String[] args) {
		
		User user = new User();
		
		//所持しているポケモン
		user.pocket[0] = new Eevee();
		user.pocket[1] = new Eevee("Brown");
		user.pocket[2] = new Vaporeon("Blue");
		
		//イーブイが現れた
		Pokemon eevee = new Eevee();
		
		//イーブイを捕まえた
		user.getPokemon(eevee);

		//
		user.viewPartyStatus();
		user.visitPokemonCenter();
		
		user.pocket[0].evolve("ThunderStone");
	}

}