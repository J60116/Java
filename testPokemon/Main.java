package testPokemon;

public class Main {

	public static void main(String[] args) {
		//ポケモントレーナーの生成
		User user = new User();

		//いま所持しているポケモン
		user.pocket[0] = new Eevee();
		user.pocket[1] = new Eevee("Brown");
		user.pocket[2] = new Vaporeon("Blue");
		
		//ポケモンが現れた
		Pokemon eevee = new Eevee();
		
		//ポケモンを捕まえた
		user.getPokemon(eevee);

		//所持しているポケモンのステータスを見る
		user.viewPartyStatus();

		//ポケモンセンターに行く
		user.visitPokemonCenter();

		//2番目と3番目のポケモンに「かみなりのいし」を渡す
		user.givePokemonItem(user.pocket[1], "ThunderStone");
		user.givePokemonItem(user.pocket[2], "ThunderStone");
		
		//所持しているポケモンのステータスを見る
		user.viewPartyStatus();
		
	}

}